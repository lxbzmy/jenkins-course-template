node {
   stage('git') { 
      git 'https://github.com/devitcn/builder-demo.git'
   }
   stage('Build') {
    dir('maven-demo'){
        docker.image('maven:3-alpine').inside{ c ->
          sh 'mvn package'
        }
        docker.withRegistry('https://localhost:500') {
          def customImage = docker.build("my-image:${env.BUILD_ID}")
          customImage.push()
        }
      }
   }
}