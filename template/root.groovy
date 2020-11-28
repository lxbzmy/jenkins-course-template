/**
 * 种子脚本，创建最初始的job
 *
 * 如何创建root seed
 * 1. 检查repo地址
 * 2. 在jenkins中随意创建一个job
 * 2.1 指定 scm
 * 2.2 step dsl template/root.groovy
 * 3 执行，成功 (第一次执行时会失败，要去管理界面approval script)
 * 4 删除，此时可以使用seed/root
 *
 *
 */
def repo = 'http://192.168.56.1:3000/agilean/jenkins-course-template'

folder('seed') {
  displayName '种子脚本'
  description '用于创建各类模板目录结构'
}


job('seed/root') {
  description('创建所有种子任务')
  scm {
    git {
      remote {url(repo)}
      branch("*/master")
    }
  }
  steps {
    dsl {
      external('''template/root.groovy''')
    }
  }
}


/**
 * 创建一个项目组文件夹
 *
 */
job('seed/create_team_folder') {
  description('为项目组创规范文件夹目录')
  parameters {
    stringParam("code", "", "项目代号 英文，用作文件夹名字")
    textParam("name", "", "项目名称 中文名称")
  }
  scm {
    git {
      remote {url(repo)}
      branch("*/master")
    }
  }
  wrappers {
    buildNameSetter {
      template('${code}')
      runAtStart(true)
      runAtEnd(false)
      descriptionTemplate('创建JOB，代号： ${code}，名称：${name}')
    }
  }
  steps {
    dsl {
      external('template/create_team_folder.groovy')
    }
  }
}

/**
 * 创建一个项目组文件夹
 *
 */
job('seed/create_maven') {
  description('创建maven style job')
  parameters {
    stringParam("code", "", "文件夹")
    stringParam("name", "", "job 名字")
    stringParam("scm_url", "", "git库地址")
  }
  scm {
    git {
      remote {url(repo)}
      branch("*/master")
    }
  }
  wrappers {
    buildNameSetter {
      template('${folder}/${name}')
      runAtStart(true)
      runAtEnd(false)
      descriptionTemplate('创建JOB，代号： ${code}，名称：${name}')
    }
  }
  steps {
    dsl {
      external('template/create_maven.groovy')
    }
  }
}