package com.foo.demo

import org.junit.Test

/**
 * *<p>
 *
 * @author lxb
 */
class GdkDemo {

  @Test
  void 上下文with() {
    List list = [];
    list.with {
      add(1)
      add(2)
      add(3)
    }
    assert list.toString() == [1, 2, 3]
  }

  @Test
  void 省略括号() {
    println x: 1, y: 2, c: 3
  }

}
