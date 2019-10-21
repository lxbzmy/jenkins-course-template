void call(){
    stage('unit_test'){
      node{
        sh "mvn compile"
      }
    }
}