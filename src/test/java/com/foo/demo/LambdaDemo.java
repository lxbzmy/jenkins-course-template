package com.foo.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * @author lxb
 */
public class LambdaDemo {

  @Test
  public void lambda() {
    List<String> list = Arrays.asList("a", "b", "c");
    list.stream().filter((item) -> {
      return item.compareTo("b") >= 0;
    });
    list.stream().filter((item) -> item.compareTo("b") >= 0);
  }

}
