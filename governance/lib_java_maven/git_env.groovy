void call() {
  stage('env') {
    //sh encoding: 'utf-8', label: 'sdfasdfasdfasdf', returnStatus: true, returnStdout: true, script: ''
    node {
      //git log doc https://git-scm.com/docs/pretty-formats
      String[] three = 'git log -1 --pretty=%H空%an空%ae'.execute().text.trim().split("空");
      String[] url = 'git remote -v'.execute().text.trim().split('\\s');
      env.GIT_COMMIT = three[0]
      env.GIT_COMMIT7 = env.GIT_COMMIT.substring(0, 8);
      env.GIT_USER = three[1]
      env.GIT_EMAIL = three[2]
      env.GIT_BRANCH = env.BRANCH_NAME
      env.GIT_URL = url[1]
      sh 'env'
    }
  }
}
