def call() {
  pipeline {
    agent any
    tools { maven 'default'}
    options {
        buildDiscarder(logRotator(numToKeepStr: '30'))
    }
    stages {
      stage('compile') {
        steps {
          sh "mvn clean compile -Dtest.failure.ignore=true"
        }
      }
      stage('findbugs'){
        steps {
              //https://spotbugs.readthedocs.io/en/latest/maven.html
              //https://plugins.jenkins.io/warnings-ng
              sh 'mvn com.github.spotbugs:spotbugs-maven-plugin:3.1.12:spotbugs'
              recordIssues(
                 enabledForFailure: true, aggregatingResults: true, 
                 tools: [spotBugs(pattern: 'target/spotbugsXml.xml', useRankAsPriority: true)],
                 qualityGates: [[threshold: 1, type: 'NEW_NORMAL', unstable: false]]
                 /*
                 sonar.analysis.mode=preview
                 https://docs.sonarqube.org/7.4/analysis/analysis-parameters/
                 */ 
              )
        }
      }
      stage('junit') {
        steps {
          sh 'mvn test'
          junit 'target/surefire-reports/*.xml'
        }
      }
      stage('package') {
        steps {
              sh 'mvn package -DskipTests=true'
          archiveArtifacts 'target/*.jar'
        }
      }
    }
  }
