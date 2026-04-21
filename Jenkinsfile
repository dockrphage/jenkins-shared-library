@Library('my-shared-library') _

pipeline {
    agent any
    
    parameters {
        choice(name: 'ENVIRONMENT', choices: ['dev', 'prod'], description: 'Deployment environment')
    }
    
    stages {
        stage('Load Configuration') {
            steps {
                script {
                    // Load the config based on the chosen environment
                    env.CURRENT_CONFIG = loadConfig(params.ENVIRONMENT)
                    echo "Running in ${params.ENVIRONMENT} mode"
                    echo "Log level set to: ${env.CURRENT_CONFIG.log_level}"
                    echo "Lambda memory: ${env.CURRENT_CONFIG.lambda_memory}"
                }
            }
        }
        
        stage('Notify Build Start') {
            steps {
                script {
                    notifySlack("Build STARTED for ${params.ENVIRONMENT}", 'warning')
                }
            }
        }
        
        stage('Simulate Lambda Build') {
            steps {
                echo "Building Lambda for ${env.CURRENT_CONFIG.region}..."
                // Here you would run your actual build, e.g., 'sam build'
            }
        }
        
        stage('Notify Build Result') {
            steps {
                script {
                    // This runs only if the pipeline succeeds to this point
                    notifySlack("Build SUCCEEDED for ${params.ENVIRONMENT}", 'good')
                }
            }
        }
    }
    
    post {
        failure {
            script {
                notifySlack("Build FAILED for ${params.ENVIRONMENT}", 'danger')
            }
        }
    }
}
