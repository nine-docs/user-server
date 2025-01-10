pipeline {
    agent any
    environment {
        DOCKER_HUB_IMAGE_REPO =  "ninedocs-user-server"
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
                sh "./gradlew test"
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
        stage("Gradle Build") {
            steps {
                sh "./gradlew clean build"
            }
            post {
                success {
                    sh 'echo "# gradlew build success"'
                }
                failure {
                    sh 'echo "# gradlew build failure"'
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
                withCredentials([usernamePassword(credentialsId: 'gunwoo-dockerhub-cre', usernameVariable: 'username', passwordVariable: 'token')]){
                  // Docker Registry로 푸시
                  sh "docker push ${DOCKER_HUB_USERNAME}/${DOCKER_HUB_IMAGE_REPO}:${TAG}"
                }
            }
        }

    }
}
