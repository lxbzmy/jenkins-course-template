void call(config){
  stage("deploy"){
    echo "Deploy to $config"
    sleep 5
    echo "Done."
  }
}