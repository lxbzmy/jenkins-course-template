/**
 * 顺序生成一批stage
 *
 * @author lxb
 */
currentBuild.displayName = "接力${currentBuild.number}"
currentBuild.description = """"""
node {
  def steps = ["第一棒", "第二棒", "第三棒", "第四棒"]
  for (def item in steps) {
    stage(item) {
      sh """env && echo 备份 $item """
    }
  }
}
