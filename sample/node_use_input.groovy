node {
  stage("开始") {
    //当是一个多个参数的时候按照Map接收
    p = input id: 'btn1', message: '要测试吗', parameters: [
            choice(choices: ['不执行', '北京', '天津', '山西'], description: '选择案例', name: 'SUIT'),
            choice(choices: ['D', 'E'], description: '选择环境参数', name: 'DEV')
    ]
    env.SUIT = p.SUIT
    env.DEV = p.DEV
  }

  stage("冒烟") {
    if (env.SUIT == '不执行') {
      echo "冒烟跳过"
    } else {
      //当是一个参数的时候，按照字符串接收
      env.BOT = input id: 'btn2', message: '选择冒烟的烟囱', parameters: [
              choice(choices: ['1', '2', '3', '4'], description: '选择案例', name: 'BOT'),
      ]
    }
  }

  stage("回归") {
    echo "${env.SUIT} ${env.DEV} ${env.BOT} "
    sh 'env'
    echo "性能测试完成"
  }
}