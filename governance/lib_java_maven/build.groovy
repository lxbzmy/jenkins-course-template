void call(){
    stage('package'){
      node{
        sh "env"
        sh "echo 'run build'"
//        junit 'target/surefire-reports/*.xml'
//        archiveArtifacts 'target/*.jar'
      }
    }
}