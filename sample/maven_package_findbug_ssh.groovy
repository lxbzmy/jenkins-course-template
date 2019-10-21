pipeline {
  agent any
  tools { maven 'default'}
  stages {
    stage('package') {
      steps {
        buildEnv()
        sh "mvn clean package -Dtest.failure.ignore=true -DbuildTag=${BUILD_TAG2}"
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
    stage('spotbugs'){
      when {
        branch 'feature/*'
      }
      steps {
        //https://spotbugs.readthedocs.io/en/latest/maven.html
        //https://plugins.jenkins.io/warnings-ng
        sh 'mvn com.github.spotbugs:spotbugs-maven-plugin:3.1.12:spotbugs'
        recordIssues(
                enabledForFailure: true, aggregatingResults: true,
                tools: [spotBugs(pattern: 'target/spotbugsXml.xml', useRankAsPriority: true)]
        )
      }
    }
    stage('report violations to github'){
      when {
        changeRequest()
      }
      steps {
        sh 'mvn com.github.spotbugs:spotbugs-maven-plugin:3.1.12:spotbugs'
        //如果只想在github review 界面看issue，那么就不用在jenkins里面记录了。反正 pr很快就没了
        recordIssues(
                enabledForFailure: true, aggregatingResults: true,
                tools: [spotBugs(pattern: 'target/spotbugsXml.xml', useRankAsPriority: true)]
        )
        reportChangeRequestViolations()
      }
    }
    stage('publish'){
      when {
        branch "(developer|release)"
      }
      steps {
        sshagent(['user1_at_dev1']) {
          sh '''HOST="user1@dev1.loccal"
                scp -o StrictHostKeyChecking=no target/maven-demo.war $HOST:~/app
                ssh $HOST ~/app/restart.sh'''
        }
      }
    }
    stage("debug"){
      when { triggeredBy 'RestartDeclarativePipelineCause' }
      steps {
        script {
          def run = rootRun();
          echo "原始流水线编号${run.number}"
          env.ROOT_NUMBER=run.number;
          sh "echo '${run.number}'"
        }
        sh 'env'
      }
    }
  }
}