package com.foo.demo

import org.junit.Test

/**
 * *<p>
 *
 * @author lxb
 */
class ChainCall {

  String 心声() {
    我 爱 你 中 国
  }

  @Test
  void 说出() {
    String a = 心声()
    println a
  }

  def 爱 = '爱'

  def 中 = '中'

  def 我(String input) {
    return this
  }

  def 你(String input) {
    return this
  }

  def 国 = '❤'

}
