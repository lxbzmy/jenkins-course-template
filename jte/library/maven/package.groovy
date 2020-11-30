void call(){
    stage('package'){
        sh 'mvn package -DskipTests=true'
        archiveArtifacts 'target/*.jar'
    }
}