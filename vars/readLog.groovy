def call(Closure lamda) {
  if (lamda) {
    currentBuild.rawBuild.getLogReader().readLines().each lamda;
  } else {
    return currentBuild.rawBuild.getLogReader();
  }
}
