/**
 * 方案3
 * 部署job和api测试job分开，限制api测试并行度=1
 * 优点：等待少，节省机器，api测试可以合并执行
 * 不足：取测试结果比较麻烦，要访问api 测试job
 * 改进点： milestone 可以去掉
 *
 */
folder('sea-ci-2') {
    description '演示三个微服务和一套测试案例，使用milestone和lock，效果是正在运行测试，则部署会等待，正在部署则api测试会等待'
}
String[] service = ['sea-auth', 'sea-core', 'sea-job','sea-limit','sea-dict','sea-oss']
for (item in service) {
    pipelineJob('sea-ci-2/' + item) {
        description()
        keepDependencies(false)
        definition {
            cps {
                script """/**
 * 多个服务共享测试环境时要做到互不干扰
 */
node {
    stage("build") {
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
        milestone()//可以去掉
        build 'sea-ci-2/api-test'
    }
}"""
                sandbox()
            }
        }
    }
}

String lock1 = service.first();
def lock2 = "[" + service[1..-1].collect({ it -> "[resource:'${it}']" }).join(",") + "]"

pipelineJob('sea-ci-2/api-test') {
    throttleConcurrentBuilds {
        maxTotal(1)
    }
    definition {
        cps {
            script """/**
 * 多个服务共享测试环境时要做到互不干扰
 */
node {
    stage("prepare") {
        echo "compile"
        sleep(3 * Math.random())
    }
    stage("run api test suit") {
        lock(resource: '${lock1}', extra: ${lock2}, inversePrecedence: true) {
            echo "run api test"
            sleep(30 + 10 * Math.random())
        }
    }
}"""
            sandbox()
        }
    }
}