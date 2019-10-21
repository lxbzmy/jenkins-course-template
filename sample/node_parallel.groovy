/**
 * stage并发
 *
 * @author lxb
 */
jobs = [1, 2, 3, 4].collectEntries {
  String name = "第${it}道"
  Closure job = {
    node {
      stage(name) {
        sh "env && echo 运动员$it"
      }
    }
  }
  return [name, job]
}
//语法格式: parallel HashMap<String,Closure>
parallel jobs