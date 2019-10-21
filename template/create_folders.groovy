folder('demo'){
    displayName '功能演示'
    description '给各个团队提供配置模板和功能演示'
}

folder('project-a'){
    displayName '项目一'
    description '项目一build文件夹\n\n联系人：张三'
}

folder('project-b'){
    displayName '项目二'
    description '项目一build文件夹\n\n联系人：李四'
}

folder('sandbox'){
    displayName '试验'
    description '试验jenkins各种功能'
}

freeStyleJob('sandbox/free-style-job'){
   description ''
   steps{
      shell ("env\necho Hello World!")
   }
}
