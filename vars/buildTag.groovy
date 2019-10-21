def call() {
  //#JENKINS中已经存在BUILD_TAG变量，如果你不想覆盖
  env.JENKINS_TAG = env.BUILD_TAG
  env.GIT_COMMIT7 = env.GIT_COMMIT.substring(0,7)
  env.BUILD_TAG2 = "${env.GIT_BRANCH}/${env.GIT_COMMIT7}"
  def p = java.util.regex.Pattern.compile('[/:](\\w+)/(\\w+)\\.git$');
  def a = p.matcher(env.GIT_URL);
  if(a.find()){
    env.GIT_GROUP1 = a.group(1)
    env.GIT_GROUP2 = a.group(2)
  }
}