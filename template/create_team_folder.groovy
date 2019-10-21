/**
 * 为项目组创建固定结构文件夹
 *
 * 使用方法
 * 1 创建freestyle job
 * 2 定义参数
 * 3 粘贴本脚本
 * 4 保存
 *
 * @param {string} code 项目代号 英文，用作文件夹名字
 * @param {string} name 项目名称 中文名称
 *
 * @author lxb
 */
println "创建文件夹： ${code}，显示为：${name}"
folder(code) {
  displayName name ?: code
  description '集成管理员：张三'

}
folder("$code/cycle") {
  displayName '回收站'
  description '存放废弃的任务'
}


