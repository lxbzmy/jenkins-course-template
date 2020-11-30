libraries{
    lib_java_maven
}
//allow_scm_jenkinsfile = false
application_environments{
    dev1{
        message = "dev1"
        ip = "127.0.0.1"
    }
    dev2{
        message = "dev2"
    }
}
pipeline_template = "maven.groovy"
stages{
   maven_default{
     unit_test
     build
     deploy
   }

  feature_branch{
    //git_env
    unit_test
    build
    deploy
  }

  pr_branch{

  }

}
