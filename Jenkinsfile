pipeline {
    agent any

    stages {
        stage('Maven build') {
            steps {
                checkout scmGit(branches: [[name: '*/develop']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/NeringaP/engineerapp.git']])

                // To run Maven on a Windows agent, use
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage('Build Docker image') {
            steps{
                script{
                    bat "docker build -t neridain/neringa_private:v0.0.1 ."
                }
            }
        }
        stage('Push image to DockerHub') {
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                        bat "docker login -u neridain -p ${dockerhubpwd}"
                    }
                    bat "docker push neridain/neringa_private:v0.0.1"
                }
            }
        }
    }
}
