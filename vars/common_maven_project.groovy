def call() {
  pipeline {
    agent any
    environment {
      //#JENKINS中已经存在BUILD_TAG变量，如果你不想覆盖
      GIT_COMMIT7 = env.GIT_COMMIT.substring(0,8)
      BUILD_TAG2 = "${env.GIT_BRANCH}/${env.GIT_COMMIT7}"
    }
    stages {
      stage('编译') {
        steps {
          hello()
          sh 'echo $BUILD_NUMBER > artifact.txt'
          sh 'echo $GIT_COMMIT7 > git_commit.txt'
          sh 'echo $BRANCH_NAME > git_branch.txt'
          sh 'echo $BUILD_TAG2 > build_tag.txt'
          script{
            currentBuild.displayName = "编译"+currentBuild.number
            currentBuild.description = '部署到D环境'
          }

        }
      }
      stage('打包') {
        steps {
          archiveArtifacts '*.txt'
        }
      }
    }
  }
}
