pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvnw.cmd clean package'
            }
        }
        stage('Test') {
                 steps {
                     bat 'mvnw.cmd test'
                 }
             }
        stage('Deploy'){
                 steps{
                 echo 'Deploying application...'
             }
        }
    }
}