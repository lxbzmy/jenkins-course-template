folder('sea-ci') {
    description '演示三个微服务共享一套测试案例，使用milestone和lock，效果是正在运行测试，则部署会等待，正在部署则api测试会等待'
}

for (item in ['sea-auth', 'sea-core', 'sea-job']) {
    pipelineJob('sea-ci/' + item) {
        description()
        keepDependencies(false)
        definition {
            cpsScm {
                """/**
 * 多个服务共享测试环境时要做到互不干扰
 */
node {
    stage("build") {
        milestone()
        echo "compile"
        sleep(30 * Math.random())
    }
    stage("redeploy") {
        milestone()
        lock('sit-${item}') {
            echo "deploy to sit server"
            sleep(30 * Math.random())
        }
    }
    stage("api test") {
        milestone()
        lock(resource: 'sit-sea-core', extra: [[resource:'sit-sea-auth'], [resource:'sit-sea-job']], inversePrecedence: true, skipIfLocked: true) {
            echo "run api test"
            sleep(30 + 10 * Math.random())
        }
    }
}"""
            }
        }
    }
}


