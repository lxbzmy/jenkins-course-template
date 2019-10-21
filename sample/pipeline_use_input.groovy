/**
 * 结论：在pipeline 函数中
 *
 * 各个stage之间是隔离的，env必须显示传递
 *
 * 重启stage时只有一个env，和第一次执行还是有细微的区别
 *
 */
pipeline {
  agent any
  stages {
    stage("准备") {
      input {
        message '选择测试案例'; ok '开始'
        parameters {
          choice choices: ['不做', '火车', '汽车'], description: '', name: 'SUITE'
          choice choices: ['D', 'E', 'F'], description: '', name: 'DEV'
        }
      }
      steps {
        sh 'echo $SUITE'//会注入到env里面，但是，下一个stage会清理
        script { env.SUITE = env.SUITE; env.DEV = env.DEV }
      }
    }
    stage("试验观察环境变量") {
      steps {
        //当选择从指定阶段重新运行时，两个值都会有
        echo "${env.SUITE} ${env.SUITE2}"
      }
    }
    stage("冒烟") {
      input {
        message '选择工人数量？'; ok '开始'
        parameters {
          choice choices: ['4', '5', '6'], description: '', name: 'SUITE2'
        }
      }
      steps {
        echo env.SUITE2; echo env.SUITE
        script { env.SUITE2 = env.SUITE2 }
      }
    }
    stage("性能") {
      steps { echo env.SUITE; echo env.SUITE2 }
    }
  }
}