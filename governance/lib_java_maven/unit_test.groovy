void call(){
    stage('unit_test'){
      node{
        sh "echo 'compile' "
//        sh "mvn compile"
      }
    }
}