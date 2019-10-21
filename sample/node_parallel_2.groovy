/**
 * 并发执行备份脚本
 * <p>
 *
 * @author lxb
 */
currentBuild.displayName = "自动备份${currentBuild.number}"
currentBuild.description = """数据库：$env.database
操作：$env.operations
"""
String db = env.database
jobs = db.split(',').collectEntries { name ->
  def stageName = "备份$name";
  def run = {
    node {
      stage(stageName) {
//        dir("/opt/backup/"){}
        sh """echo 备份$name $env.operations"""
//        bat "backup.bat"
      }
    }
  }
  return [stageName, run]
}

parallel jobs