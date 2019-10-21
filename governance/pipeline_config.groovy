libraries{
    lib_java_maven
}
allow_scm_jenkinsfile = false 
application_environments{
    dev1{
        long_name = "dev1"
    }
    dev2{
        long_name = "dev2"
    }
}
pipeline_template = "maven.groovy"
stages{
   mvn_default{
     git_env
     unit_test
     build
   }
}
