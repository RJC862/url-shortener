pipeline {
    agent any

    tools {
        jdk 'jdk21'
    }

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