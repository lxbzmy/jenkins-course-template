package com.foo.demo

import org.junit.Test

/**
 * *<p>
 *
 * @author lxb
 */
class MapDemo {

  @Test
  void map构造() {
    Map map = ['airline': 'CA',
               'number' : '985']
    //GString
    "${departureAirport}-${arrivalAirport} ${departureTime}-${arrivalTime}"
//多行字符串
    """离港：${departureAirport}-${arrivalAirport} 
到达：${departureTime}-${arrivalTime}"""

//字符串
    '单行字符串'

    '''多行字符串
'''

    assert map['airline'] == 'CA'
    assert map.airline == 'CA'
    assert map.number == '985';

    map.each {k,v->
      println k
      println v
    }
  }

  @Test
  void map方法() {
    List list = [['airline': 'CA','number' : '985'],
                 ['airline': 'CA','number' : '986']]

    List result = list.collect {it.number};
    assert result == ['985','986']

    assert list.number == ['985','986'];
  }
}
