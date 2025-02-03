pipeline {
    agent any
    environment {
        DOCKER_HUB_IMAGE_REPO = "ninedocs-user-server"
        DOCKER_HUB_USERNAME = "gunwoda"
    }
    options {
        disableConcurrentBuilds() // 동시에 빌드 실행 방지
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
                    // Git 커밋 해시값 가져오기
                    TAG = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
                    echo "Git Commit Hash: ${TAG}"
                }
            }
        }
        stage('Docker Login') {
            steps {
                // Jenkins Credentials를 사용하여 Docker Hub에 로그인
                withCredentials([usernamePassword(credentialsId: 'gunwoo-dockerhub-cre', usernameVariable: 'username', passwordVariable: 'token')]) {
                    sh "echo ${token} | docker login -u ${username} --password-stdin"
                }
            }
        }
        stage('Docker Build') {
            steps {
                // Docker 이미지 빌드
                sh "docker build -t ${DOCKER_HUB_USERNAME}/${DOCKER_HUB_IMAGE_REPO}:${TAG} ."
            }
        }
        stage('Push to Registry') {
            steps {
                // Docker Registry로 푸시
                sh "docker push ${DOCKER_HUB_USERNAME}/${DOCKER_HUB_IMAGE_REPO}:${TAG}"
            }
        }
        stage('Update Helm values.yaml') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'git-credentials', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
                        sh """
                        #!/bin/bash
                        echo "Cloning Helm repository..."
                        git clone https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/nine-docs/infra-manifest.git || echo "Repo already exists"
                        cd infra-manifest/charts/user

                        echo "Current Directory: $(pwd)"
                        echo "Contents of values.yaml before update:"
                        cat values.yaml

                        echo "Updating values.yaml with tag: ${TAG}"
                        sed -i "s/tag:.*/tag: ${TAG}/" values.yaml

                        echo "Contents of values.yaml after update:"
                        cat values.yaml

                        echo "Committing and pushing changes..."
                        git config user.name "jenkins-john"
                        git config user.email "john3210of@gmail.com"
                        git add values.yaml
                        git commit -m "Update tag to ${TAG}" || echo "No changes to commit"
                        git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/nine-docs/infra-manifest.git
                        """
                    }
                }
            }
        }
    }
    post {
        cleanup {
            cleanWs() // 워크스페이스 정리
        }
    }
}
