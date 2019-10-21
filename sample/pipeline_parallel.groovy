pipeline {
    agent any
    stages {
        stage('并发') {
            parallel {
                stage('第一道') {
                    steps {
                        echo "跑1"
                    }
                }
                stage('第二道') {
                    steps {
                        echo "跑2"
                    }
                }
            }
        }
    }
}