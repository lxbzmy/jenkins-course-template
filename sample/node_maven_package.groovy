node {
  def mvnHome
  stage('checkout') {
    git 'https://github.com/devitcn/builder-demo.git'
    mvnHome = tool 'maven'
  }
  stage('build') {
    withEnv(["MVN_HOME=$mvnHome"]) {
      dir('maven-demo') {
        if (isUnix()) {
          sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
        } else {
          bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
        }
      }
    }
  }
  stage('results') {
    junit 'target/surefire-reports/*.xml'
    archiveArtifacts 'target/*.jar'
  }
}