void call(){
  stage('deploy'){
    node{
      echo config.long_name
    }
  }
}