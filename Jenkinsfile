pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                // Nehme Code aus GitHub Repo
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            // Starte nachdem step mvn test abgelaufen ist
            post {
                // selbst wenn Test fehlschlägt, soll der XML Report gesammelt werden
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                // Plugin überspringe Tests, da sie vorhin liefen
                sh 'mvn package -DskipTests'
            }
        }

    }
}