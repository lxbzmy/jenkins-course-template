def call() {
  pipeline {
    agent any
    stages {
      stage('clean') {
        steps {
          sh 'mvn clean'
        }
      }
      stage('compile') {
        steps {
          sh 'mvn compile'
        }
      }
      stage('junit') {
        steps {
          sh 'mvn test'
          junit 'target/surefire-reports/*.xml'
        }
      }
      stage('package') {
        steps {
          sh 'mvn -DskipTests=true package'
          archiveArtifacts 'target/*.jar'
        }
      }
    }
  }
}
