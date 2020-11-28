folder('delivery-pipeine-demo') {
  description '给各个团队提供配置模板和功能演示'
  primaryView '流水线视图1'
  views {
    deliveryPipelineView('流水线视图1') {
      pipelines {
        component('研发打包', 'package')
      }
      allowPipelineStart(true)
      pipelineInstances(10)
      columns(1)
      sorting(Sorting.TITLE)
      updateInterval(5)
      enableManualTriggers()
      showAvatars()
      showChangeLog()
      showDescription()
      showPromotions()
      showStaticAnalysisResults()
      showTestResults()
      showTotalBuildTime()
      allowRebuild()
      linkToConsoleLog()
      useTheme("overview")
    }

    buildPipelineView('流水线视图2') {
      title('研发打包')
      displayedBuilds(10)
      selectedJob('package')
      showPipelineParameters()
      refreshFrequency(60)
    }
  }
}

freeStyleJob('delivery-pipeine-demo/package') {
  description '取代码和打包，并且设置PIPELINE_VERSION为BUILD_NUMBER'
  wrappers {
    deliveryPipelineVersion('${BUILD_NUMBER}', true)
  }
  deliveryPipelineConfiguration('package', 'package')
  steps {
    if (System.getProperty("os.name").contains("Windows")) {
      batchFile('set\necho %PIPELINE_VERSION% > package.txt')
    } else {
      shell('env\necho $PIPELINE_VERSION>package.txt')
    }
  }
  publishers {
    archiveArtifacts('package.txt')
    downstream {
      childProjects('unit_test,deploy_to_D')
      threshold('success')
    }
    buildPipelineTrigger('delivery-pipeine-demo/deploy_to_E,delivery-pipeine-demo/deploy_to_F') {
    }
  }
}

freeStyleJob('delivery-pipeine-demo/unit_test') {
  description 'sonar'
  deliveryPipelineConfiguration('package', 'sonar')
  steps {
    copyArtifacts('package') {
      buildSelector {
        specific {
          buildNumber('$PIPELINE_VERSION')
        }
      }
    }
    if (System.getProperty("os.name").contains("Windows")) {
      batchFile('set\ntype package.txt')
    } else {
      shell("env\ncat package.txt")
    }

  }
}

for (item in ['D', 'E', 'F']) {
  freeStyleJob('delivery-pipeine-demo/deploy_to_' + item) {
    description "部署到环境：${item}"
    deliveryPipelineConfiguration('deploy', "env_$item")
    steps {
      buildName {
        nameTemplate('deploy $PIPELINE_VERSION')
      }
      copyArtifacts('package') {
        buildSelector {
          specific {
            buildNumber('$PIPELINE_VERSION')
          }
        }
      }
      if (System.getProperty("os.name").contains("Windows")) {
        batchFile('set\ntype package.txt')
      } else {
        shell('echo deploy to ' + item + '\ncat package.txt')
      }
    }
  }
}


