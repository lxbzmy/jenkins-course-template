package com.foo.demo

import org.junit.Test

/**
 * *<p>
 *
 * @author lxb
 */
class ListDemo {

  @Test
  void 集合操作() {
    List<String> list = ["a", "b"]

    assert list[0] == 'a';
    assert list[-1] == 'b';

    list << 'c'
    assert list == ['a','b','c']

    assert list[1..2] == ["b", "c"]
    assert list[0..-1] == ["a", "b", "c"]
  }

  @Test
  void 集合便捷方法() {
    List list = ['a', 'b', 'c']

    assert list.first() == 'a'
    assert list.last() == 'c'
    assert list.reverse() == ['c', 'b', 'a'];
    assert list + ['d', 'e'] == ['a', 'b', 'c', 'd', 'e'];
  }

  @Test
  void 集合检索() {
    List list = ['a', 'b', 'c']
    assert list.find({ it == 'a' }) == 'a'
    assert list.find { it == 'a' } == 'a'
    assert list.find() { it == 'a' } == 'a'
  }


}
