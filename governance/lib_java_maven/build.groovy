void call(){
    stage('package'){
      node{
        sh "env"
        sh "mvn package -Dtest.failure.ignore=true"
        junit 'target/surefire-reports/*.xml'
        archiveArtifacts 'target/*.jar'
      }
    }
}