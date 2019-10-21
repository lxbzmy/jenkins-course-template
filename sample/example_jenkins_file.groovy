pipeline {
  agent any
  environment {
      ts = java.util.LocalDateTime.now().toString()
  }
  parameters {
       string name: 'DEPLOY', defaultValue: 'SIT', description: ''
  }
  stages {
    stage('package') {
       sh 'mvn package'
       junit 'target/surefire-reports/*.xml'
       archiveArtifacts 'target/*.jar'
    }
  }
}