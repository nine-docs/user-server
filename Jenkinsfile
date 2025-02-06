pipeline {
    agent any
    environment {
        DOCKER_HUB_IMAGE_REPO = "ninedocs-user-server"
        DOCKER_HUB_USERNAME   = "gunwoda"
    }
    options {
        disableConcurrentBuilds() // Prevent concurrent builds
    }
    stages {
        stage("Permission") {
            steps {
                sh "chmod +x ./gradlew"
            }
            post {
                success {
                    sh 'echo "# chmod success"'
                }
                failure {
                    sh 'echo "# chmod failure"'
                }
            }
        }
        stage("Gradle Test") {
            steps {
              withCredentials([file(credentialsId: 'user-server-env', variable: 'ENV_FILE')]){
                sh '''
                    set +x
                    . ${ENV_FILE}
                    ./gradlew test
                  '''
              }
            }
            post {
                success {
                    sh 'echo "# gradlew test success"'
                }
                failure {
                    sh 'echo "# gradlew test failure"'
                }
            }
        }
        stage('Set Tag') {
            steps {
                script {
                    // Set TAG to the short commit hash
                    env.TAG = "V-" + sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
                    echo "Git Commit Hash: ${env.TAG}"
                }
            }
        }
        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'gunwoo-dockerhub-cre', usernameVariable: 'username', passwordVariable: 'token')]) {
                    // Using Groovy string interpolation for the docker login command
                    sh "echo ${token} | docker login -u ${username} --password-stdin"
                }
            }
        }
        stage('Docker Build') {
            steps {
                // Build docker image with the tag from env.TAG
                sh "docker build -t ${DOCKER_HUB_USERNAME}/${DOCKER_HUB_IMAGE_REPO}:${env.TAG} ."
            }
        }
        stage('Push to Registry') {
            steps {
                // Push the built image to Docker Hub
                sh "docker push ${DOCKER_HUB_USERNAME}/${DOCKER_HUB_IMAGE_REPO}:${env.TAG}"
            }
        }
        stage('Update Helm values.yaml') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'git-credentials', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
                        sh '''
                        #!/bin/bash
                        echo "Cloning Helm repository..."
                        git clone https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/nine-docs/infra-manifest.git
                        cd infra-manifest/charts/user
                        echo "Updating values.yaml with tag: ${TAG}"
                        sed -i "s/tag:.*/tag: ${TAG}/" values.yaml
                        echo "Committing and pushing changes..."
                        git config user.name "jenkins-john"
                        git config user.email "john3210of@gmail.com"
                        git add values.yaml
                        git commit -m "Update tag to ${TAG}" || echo "No changes to commit"
                        git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/nine-docs/infra-manifest.git
                        '''
                    }
                }
            }
        }
        stage('Clean Up Workspace After Build') {
            steps {
                deleteDir() // 작업 공간 정리
            }
        }
    }
}