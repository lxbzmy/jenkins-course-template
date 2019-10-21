package com.foo.demo

import groovy.json.JsonBuilder
import groovy.xml.MarkupBuilder
import org.junit.Test

/**
 * *<p>
 *
 * @author lxb
 */
class HtmlDslTest {

  @Test
  public void html() {
    MarkupBuilder builder = new MarkupBuilder();
    builder.html(lang: 'en') {
      head {
        title("例一")
      }
      body {
        h1('Jenkins')
        p('按照：')
        p {
          mkp.yield "下载："
          a(href: '/jenkins.war', "jenkins.war")
        }
      }
    }
  }

  @Test
  void json_builder() {
    def builder = new JsonBuilder();
    builder {
      flight {
        airline 'CA'
        number '985'
      }
      result 200
    }
    println builder.toString()

    assert builder.toString() == '{"flight":{"airline":"CA","number":"985"},"result":200}'
  }
}
