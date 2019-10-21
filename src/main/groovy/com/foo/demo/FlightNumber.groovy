package com.foo.demo

/**
 * *<p>
 *
 * @author lxb
 */
class FlightNumber {
  String airline
  int number

  FlightNumber(String pattern) {
    airline = pattern.substring(0, 2)
    number = pattern.substring(2) as int;
  }

  FlightNumber() {
  }

  def plus(int delta) {
    def f = new FlightNumber();
    f.airline = airline
    f.number = this.number + delta
    return f;
  }

  @Override
  String toString() {
    return airline + number
  }
}