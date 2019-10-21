pipeline {
    agent any
    tools { maven 'default'}
    environment {
        //#JENKINS中已经存在BUILD_TAG变量，如果你不想覆盖
        BUILD_TAG2 = "${env.GIT_BRANCH}/${env.GIT_COMMIT.substring(0,7)}"
        GIT_COMMIT7=env.GIT_COMMIT.substring(0,7)
    }
    stages {
      stage('compile') {
        steps { sh 'mvn clean compile'}
      }
      stage('package') {
          steps { 
              sh 'mvn package -DskipTests=true'
              archiveArtifacts 'target/*.jar'
          }
      }
   }
}