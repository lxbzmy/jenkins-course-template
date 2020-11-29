pipeline {
  agent any
  stages {
    stage("checkout") {
      steps {
        cleanWs()
        writeFile encoding: 'UTF-8', file: 'bom.txt', text: 'mock source code clone'
        sleep 10
      }
    }
    stage('build') {
      matrix {
        axes {
          axis {
            name 'ALT'
            values 'dev', 'sit', 'uat'
          }
        }
        stages {
          stage("build") {
            steps {
              dir(ALT) {
                echo "build for env: $ALT"
                sh '''cp ../bom.txt .'''
                sleep 10
                echo "compile"
                sleep 10
                echo "package"
                sleep 10
                writeFile encoding: 'utf-8', file: 'target/build.txt', text: "$ALT build"
              }
              archiveArtifacts artifacts: "$ALT/target/*", followSymlinks: false
            }
          }
        }
      }
    }
  }
}