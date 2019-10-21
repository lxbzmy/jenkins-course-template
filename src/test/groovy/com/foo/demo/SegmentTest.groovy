package com.foo.demo

import org.junit.Test

import java.time.LocalDate
import java.time.LocalTime

class SegmentTest {

  @Test
  void 带参数构造() {
    def bean = new Segment(
            departureAirport: 'PEK',
            departureTime: LocalTime.parse("09:00"),
            arrivalAirport: "SHA",
            arrivalTime: LocalTime.parse("11:00")
    )

    assert bean.toString() == "PEK-SHA 09:00-11:00";
  }

  @Test
  void 参数默认值() {
    assert tomorrow(new LocalDate(2019, 10, 13)) == "2019-10-14"
    assert tomorrow() == "2019-10-14"
  }

  String tomorrow(LocalDate today = LocalDate.now()) {
    return today.plusDays(1).toString();
  }

  @Test
  void 多赋值() {
    def (number, seg) = foo();
    assert number == 'CA985'
    assert seg.departureAirport == "PEK"
  }

  List foo() {
    def bean = new Segment(
            departureAirport: 'PEK',
            departureTime: LocalTime.parse("09:00"),
            arrivalAirport: "SHA",
            arrivalTime: LocalTime.parse("11:00")
    )
    return ["CA985", bean];
  }


  @Test
  void map参数传递() {
    go(x: 1, y: 1, z: 1)
  }

  def go(Map location) {
    return location
  }

}
