allow_scm_jenkinsfile = false

skip_default_checkout = false

/*dev 和prod可以作为常量在任何地方使用*/
application_environments{
    dev{
      address = 'dev.foo.local'
    }
    sit{
      address = 'sit.foo.local'
    }
    uat{
      address = 'uat.foo.local'
    }
    pre{
      address = 'pre.foo.local'
    }
    prod{
      address = 'prod.foo.local'
    }
}
libraries{
    maven
    vm
}