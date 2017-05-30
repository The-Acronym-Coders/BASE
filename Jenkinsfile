pipeline {
    agent any
    stages {
        stage('Set Dev Variable') {
            when {
                expression {
                    return env.BRANCH_NAME.contains("dev")
                }
            }
            env.BRANCH="Snapshoot"
        }
        stage('Clean') {
            steps {
                echo 'Cleaning Project'
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        stage('Setup') {
            steps {
                echo 'Setting up Workspace'
                sh './gradlew setupdecompworkspace'
            }
        }
        stage('Build and Deploy') {
            steps {
                echo 'Building and Deploying to Maven'
                sh './gradlew build --refresh-dependencies'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying to Maven'
                sh './gradlew uploadArchives'
            }
        }
    }
    post {
        always {
            archive 'build/libs/**.jar'
        }
    }
}
