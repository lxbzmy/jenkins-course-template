/**
 * 种子脚本，创建最初始的job
 *
 */
folder('seed') {
  displayName '种子脚本'
  description '用于创建各类模板目录结构'
}

job('seed/root') {
  description('创建所有种子任务')
  scm {
    git {
      remote {
        github("http://192.168.3.119:3000/agilean/jenkins-course-template.git", "")
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
