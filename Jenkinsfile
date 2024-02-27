pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub_id')
    }

    stages {
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew --version'
                sh './gradlew clean build'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build -t $DOCKERHUB_CREDENTIALS_USR/xyz-bank:latest .'
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh 'docker push $DOCKERHUB_CREDENTIALS_USR/xyz-bank:latest'
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}