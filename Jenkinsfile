pipeline {
    agent any

    stages {
        stage('Build & Test') {
            steps {
                // For Linux/Mac Jenkins agent:
                sh './mvnw clean package'

                // If your Jenkins agent is running on Windows, use instead:
                // bat 'mvnw.cmd clean package'
            }
        }
    }
}