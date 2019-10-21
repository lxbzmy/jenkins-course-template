import org.jenkinsci.plugins.pipeline.modeldefinition.causes.RestartDeclarativePipelineCause;
import org.jenkinsci.plugins.workflow.cps.replay.ReplayCause;
import org.jenkinsci.plugins.workflow.support.steps.build.RunWrapper;

import hudson.model.Run;


def call() {
  Run<?, ?> rootBuild = findRootRun(currentBuild.getRawBuild());
  RunWrapper rootCause = new RunWrapper(rootBuild, false);
  return rootCause;
}
public Run<?, ?> findRootRun(Run<?, ?> run) {
    ReplayCause cause = run.getCause(ReplayCause.class);
    if (cause != null) {
        Run<?, ?> parent = cause.getOriginal();
        return findRootRun(parent);
    }
    RestartDeclarativePipelineCause cause2 = run
            .getCause(RestartDeclarativePipelineCause.class);
    if (cause2 != null) {
        return findRootRun(cause2.getOriginal());
    }
    return run;
}