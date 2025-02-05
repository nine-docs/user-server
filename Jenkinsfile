pipeline {
    agent any
    environment {
        DOCKER_HUB_IMAGE_REPO = "ninedocs-user-server"
        DOCKER_HUB_USERNAME   = "gunwoda"
        ENV_FILE = credentials('user-env-file')
    }
    options {
        disableConcurrentBuilds() // Prevent concurrent builds
    }
    stages {
        stage('Load Environment Variables') {
            steps {
                script {
                    // .env 파일의 내용을 읽어 환경변수로 설정
                    def envVars = readFile("${ENV_FILE}").split("\n")
                    envVars.each { line ->
                        if (line && !line.startsWith("#")) { // 주석 제외
                            def (key, value) = line.tokenize("=")
                            env."${key.trim()}" = value.trim()
                        }
                    }
                }
            }
        }
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
                sh "./gradlew clean test"
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
                    env.TAG = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
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
                sh """
                docker build -t ${DOCKER_HUB_USERNAME}/${DOCKER_HUB_IMAGE_REPO}:${env.TAG} . \
                --build-arg RDS_URL=${RDS_URL} \
                --build-arg RDS_USERNAME=${RDS_USERNAME} \
                --build-arg RDS_PASSWORD=${RDS_PASSWORD} \
                --build-arg REDIS_HOST=${REDIS_HOST} \
                --build-arg REDIS_PASSWORD=${REDIS_PASSWORD} \
                --build-arg JWT_SECRET=${JWT_SECRET} \
                --build-arg EMAIL_URL=${EMAIL_URL} \
                --build-arg SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
                """
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