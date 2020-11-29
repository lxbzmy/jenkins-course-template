/**
 * @param string folder
 * @param string name
 * @param string scm_url 注意，我用git_url，结果报异常，难道关键词冲突？2020-11-29
 */

pipelineJob("$folder/$name") {
  description ''

  triggers {
    cron('@midnight')
  }

  definition {
    cps {
      script """pipeline {
  agent any
  tools { maven 'default'}
  stages {
    stage('checkout'){
      steps {
        git branch: 'master', url: '${scm_url}\'
      }
    }
    stage('compile') {
      steps {
        sh "mvn clean package -Dtest.failure.ignore=true"
      }
    }
    stage('junit') {
      steps {
        sh 'mvn test\'
        junit 'target/surefire-reports/*.xml\'
      }
    }
    stage('package') {
      steps {
        sh 'mvn package -DskipTests=true\'
        archiveArtifacts 'target/*.jar\'
      }
    }
    stage('spotbugs'){
      steps {
        sh 'mvn com.github.spotbugs:spotbugs-maven-plugin:3.1.12:spotbugs\'
        recordIssues(
            enabledForFailure: true, aggregatingResults: true,
            tools: [spotBugs(pattern: 'target/spotbugsXml.xml', useRankAsPriority: true)]
        )
      }
    }
    stage('publish'){
      when {
        branch "(developer|release)"
      }
      steps {
        echo "TODO execute deploy script"
      }
    }
  }
}"""
      sandbox()
    }
  }
}