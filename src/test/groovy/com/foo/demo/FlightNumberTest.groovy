package com.foo.demo;

import org.junit.Test;

/**
 * <p>
 *
 * @author lxb
 */
public class FlightNumberTest {

  @Test
  void 航班号加1() {
    def a = new FlightNumber('CA985');
    def b = a + 1;
    assert b.toString() == 'CA986';
  }

}