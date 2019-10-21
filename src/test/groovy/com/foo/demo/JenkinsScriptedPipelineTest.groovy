package com.foo.demo
import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

/**
 * *<p>
 *
 * @author lxb
 */
class JenkinsScriptedPipelineTest extends BasePipelineTest{
  @Before
  void setup(){
    super.setUp()
  }
  @Test
  void should_execute_without_errors() throws Exception {
    runScript("sample/node_foreach.groovy")
    printCallStack()
  }

}