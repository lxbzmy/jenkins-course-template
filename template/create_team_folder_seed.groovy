/**
 * 创建一个项目组文件夹
 *
 * TODO 未完成【
 */
job('create_team') {
  description('为项目组创规范文件夹目录')
  parameters {
    stringParam("code", "", "项目代号 英文，用作文件夹名字")
    textParam("name", "", "项目名称 中文名称")
  }
  scm {
    git {
      remote {
        github("http://192.168.3.119:3000/agilean/jenkins-course-template.git", "")
      }
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