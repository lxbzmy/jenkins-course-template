void call(){
    stage('unit_test'){
      sh 'mvn test'
      junit '**/target/surefire-reports/*.xml'
    }
}