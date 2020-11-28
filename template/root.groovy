/**
 * 种子脚本，创建最初始的job
 *
 * 如何创建root seed
 * 1. 检查repo地址
 * 2. 在jenkins中随意创建一个job
 * 2.1 指定 scm
 * 2.2 step dsl template/root.groovy
 * 3 执行，成功
 * 4 删除，此时可以使用seed/root
 *
 */
folder('seed') {
  displayName '种子脚本'
  description '用于创建各类模板目录结构'
}

def repo = 'http://192.168.56.1:3000/agilean/jenkins-course-template'

job('seed/root') {
  description('创建所有种子任务')
  scm {
    git {
      remote {
        url(repo)
      }
      branch("*/master")
    }
  }
  steps {
    dsl {
      external('template/root.groovy')
    }
  }
}
