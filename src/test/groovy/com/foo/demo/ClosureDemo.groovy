package com.foo.demo

import org.junit.Test

/**
 * *<p>
 *
 * @author lxb
 */
class ClosureDemo {

  String username = "alex"

  @Test
  void 传参() {
    def fn  =  {a,b-> println "$a,$b" };
    fn(1,2)
  }

  @Test
  void 作用域在ClosureDemo() {
    def fn = { println username };
    fn();//alex
  }

  @Test
  void 作用域在Mail() {
    def fn  =  { println username };
    fn.resolveStrategy = Closure.DELEGATE_FIRST
    fn.delegate = new Mail();
    fn()//bob
  }

  class Mail {
    String username = "bob";
  }
}

