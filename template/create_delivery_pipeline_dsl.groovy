freeStyleJob('package') {
  description '取代码和打包，并且设置PIPELINE_VERSION为BUILD_NUMBER'
  wrappers {
    deliveryPipelineVersion('${BUILD_NUMBER}', true)
  }
  deliveryPipelineConfiguration('package', 'package')
  steps {
    shell('env\necho $PIPELINE_VERSION>package.txt')
  }
  publishers {
    archiveArtifacts('package.txt')
    downstream {
      childProjects('unit_test')
      threshold('success')
    }
    downstream {
      childProjects('deploy_to_D')
      threshold('success')
    }
    buildPipelineTrigger('sandbox/deploy_to_E,sandbox/deploy_to_F') {
    }
  }
}

freeStyleJob('unit_test') {
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
    shell("env\ncat package.txt")
  }
}

for (item in ['D', 'E', 'F']) {
  freeStyleJob('deploy_to_' + item) {
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
      shell('echo deploy to ' + item + '\ncat package.txt')
    }
  }
}


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