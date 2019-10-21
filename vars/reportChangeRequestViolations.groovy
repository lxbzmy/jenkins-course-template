def call() {
  ViolationsToGitHub(
    [
      credentialsId: 'github_login_with_lxbzmy', 
      gitHubUrl: 'https://api.github.com/', 
      repositoryOwner: env.GIT_GROUP1, 
      repositoryName: env.GIT_GROUP2,

      commentOnlyChangedContent: true, 
      createCommentWithAllSingleFileComments: true, 
      createSingleFileComments: true, 
      minSeverity: 'ERROR',
      maxNumberOfViolations: 100,
      keepOldComments: false,
      commentTemplate: """{{violation.reporter}}: {{violation.rule}}
{{violation.file}} #{{violation.startLine}}
{{violation.message}}""",  
      pullRequestId: env.CHANGE_ID, 
      violationConfigs: [[parser: 'FINDBUGS', pattern: '.*/target/spotbugsXml.xml', reporter: 'spotbugs']]
    ]
  )
}
