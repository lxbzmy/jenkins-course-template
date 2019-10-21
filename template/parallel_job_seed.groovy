import groovy.transform.Field

pipelineJob("demo/script_pipeline_parallel") {
  displayName "数据库备份"
  description '定时备份数据库，也可以手工执行'

  triggers {
    cron('@midnight')
  }

  parameters {
    extendedChoice({
      name "database"
      description "要备份的数据源"
      value "db1,db2,db3"
      defaultValue "db1,db2,db3"
    } << default_parameter)

    extendedChoice({
      name "operations"
      description "要执行的操作，默认backup"
      value "backup,drop,update,restore"
      defaultValue "backup"
    } << default_parameter)
  }

  definition {
    cps {
      script '''/**
 * 并发执行备份脚本
 * <p>
 *
 * @author lxb
 */
currentBuild.displayName = "自动备份${currentBuild.displayName}"
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

parallel jobs'''
      sandbox()
    }
  }
}

@Field
Closure default_parameter = {
  type "PT_CHECKBOX"
  multiSelectDelimiter ","
  quoteValue false
  visibleItemCount 20
  saveJSONParameterToFile false
  projectName null
  propertyFile null
  groovyScript null
  groovyScriptFile null
  bindings null
  groovyClasspath null
  propertyKey null
  defaultPropertyFile null
  defaultGroovyScript null
  defaultGroovyScriptFile null
  defaultBindings null
  defaultGroovyClasspath null
  defaultPropertyKey null
  descriptionPropertyValue null
  descriptionPropertyFile null
  descriptionGroovyScript null
  descriptionGroovyScriptFile null
  descriptionBindings null
  descriptionGroovyClasspath null
  descriptionPropertyKey null
  javascriptFile null
  javascript null
}