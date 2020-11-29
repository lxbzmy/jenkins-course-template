/**
 * 创建maven构建job
 * @param folder
 * @param name
 * @param scm_url
 */
mavenJob("${folder}/${name}") {
  logRotator(-1, 10)
  scm {
    git {
      remote {
        url(scm_url)
      }
      branch("*/master")
    }
  }
  triggers {
    pollSCM {
      scmpoll_spec('H/1 * * * *')
    }
  }
  goals('clean install')
}