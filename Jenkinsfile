pipeline {
    agent any

    tools {
        // Match the tool names configured in your Jenkins Global Tool Configuration
        jdk 'JDK17'
        maven 'Maven3.9'
    }

    stages {
        stage('Checkout') {
            steps {
                // Clones your repository
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Runs Maven build and unit tests
                sh 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                // Add deployment commands here (e.g., executing SSH commands to a server
                // or building a Docker image)
                echo 'Deploying application...'
            }
        }
    }

    post {
        success {
            echo 'Build and deployment succeeded!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}