pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17'
        }
    }

    environment {
        EC2_HOST = 'ec2-user@3.83.98.46'
        REMOTE_DIR = '/home/ec2-user/springboot-app'
        JAR_NAME = 'FlightBooking-0.0.1-SNAPSHOT.jar'
        SSH_CREDENTIALS_ID = 'ec2-ssh-key'
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'git-creds', url: 'https://github.com/ArpitAgrawal001/FlightBookingSystem.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests=false'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t flightbooking-app:latest .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker tag flightbooking-app:latest your-dockerhub-username/flightbooking-app:latest
                        docker push your-dockerhub-username/flightbooking-app:latest
                    """
                }
            }
        }


        stage('Deploy to EC2') {
            steps {
                sshagent (credentials: [env.SSH_CREDENTIALS_ID]) {
                    sh """
                        scp target/*.jar ${EC2_HOST}:${REMOTE_DIR}/${JAR_NAME}
                    """
                }
            }
        }

        stage('Run on EC2') {
            steps {
                sshagent (credentials: [env.SSH_CREDENTIALS_ID]) {
                    sh """
                        ssh ${EC2_HOST} '
                            pkill -f ${JAR_NAME} || true
                            nohup java -jar ${REMOTE_DIR}/${JAR_NAME} > ${REMOTE_DIR}/app.log 2>&1 &
                        '
                    """
                }
            }
        }
    }

    post {
        success {
            echo '✅ Deployment Successful!'
        }
        failure {
            echo '❌ Pipeline Failed. Check logs.'
        }
    }
}
