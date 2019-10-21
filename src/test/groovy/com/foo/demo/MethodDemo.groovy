package com.foo.demo

import groovy.json.JsonOutput
import org.junit.Test

/**
 * *<p>
 *
 * @author lxb
 */
class MethodDemo {

  public String hello(String name) {
    return "Hello $name";
  }

  @Test
  void 正常的方法调用() {
    assert hello("CA985") == "Hello CA985"
  }

  @Test
  void 方法调用的括号可以省略() {
    String r = hello "CA985"
    assert r == "Hello CA985"
  }

  public String 买(name1, name2) {
    return "你买了 $name1 $name2";
  }

  @Test
  void 省略括号时不能省略逗号() {
    String r = 买 '茄子', '生菜'
    assert r == "你买了 茄子 生菜"
  }

  public String json(Map) {
    JsonOutput.toJson map;
  }

  @Test
  void 传map时括号省略() {

    Map<String, String> param = new HashMap<>();
    param.put("from", "PEK")
    param.put("to", "SHA")
    json(param)

    json(['from': 'PEK', 'to': 'SHA'])
    json([form: 'PEK', to: 'SHA'])
    json(from: 'PEK', to: 'SHA')
    json from: 'PEK', to: 'SHA'
  }

  public String json(List result, String message) {

  }

  @Test
  void 第一个参数为list时慎重() {
    //语法错误：json [1, 2, 3, 4], '成功'
    json([1, 2, 3, 4], '成功')
    def list = [1, 2, 3, 4]
    json list, '成功'

  }

  String json(String message, List result) {

  }

  @Test
  void 含有List时不能省略括号因为中括号会当成成员访问() {
    json([1, 2, 3, 4], '成功')
    json '成功', [1, 2, 3, 4]
  }

  void a(String name, String name2) {

  }

  @Test
  void a() {
  }


}
