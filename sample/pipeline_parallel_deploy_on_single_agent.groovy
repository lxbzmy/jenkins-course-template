pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        echo "clone"
        sleep 10
        echo "compile"
        sleep 10
        echo "package"
        sleep 10
        sh '''mkdir -p target
echo "hello" > target/build.txt'''
        stash includes: 'target/build.txt', name: 'artifact'
      }
    }
    stage('并发部署') {
      parallel {
        stage('dev build') {
          steps {
            unstash 'artifact'
            echo "跑1"
            writeFile encoding: 'UTF-8', file: 'dev_build', text: 'done'
            sleep 30
          }
        }
        stage('sit build') {
          steps {
            unstash 'artifact'
            echo "跑2"
            sleep 20
            writeFile encoding: 'UTF-8', file: 'sit_build', text: 'done'
          }
        }
        stage('uat build') {
          steps {
            unstash 'artifact'
            echo "跑3"
            sleep 10
            writeFile encoding: 'UTF-8', file: 'uat_build', text: 'done'
          }
        }
      }
    }
  }
}