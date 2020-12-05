/**
 * 演示在固定分支构建过程中，如果新构建在运行了，老构建就能够停止了
 */
node {
    stage('checkout') {
        echo "Check out code."
        sleep(30 * Math.random())
    }
    stage("compile") {
        milestone()
        echo "compile"
        sleep(30 * Math.random())
    }
    stage("unit test") {
        milestone()
        echo "unit test"
        sleep(30 * Math.random())
    }
    stage("pack it") {
        milestone()
        echo "create zip"
        sleep(30 * Math.random())
    }
    stage("pack it") {
        milestone()
        echo "upload to server"
        sleep(30 * Math.random())
    }
    stage("redeploy") {
        milestone()
        echo "deploy to server"
        sleep(30 * Math.random())
    }
    stage("api test") {
        milestone()
        echo "api test"
        sleep(30 * Math.random())
    }
}