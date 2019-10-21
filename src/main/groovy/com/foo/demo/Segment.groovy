package com.foo.demo

import java.time.LocalTime

/** 航班航段和时间
 * @author lxb
 */
class Segment {
  /**离港机场三字码，例如：PEK*/
  String departureAirport
  /**到达机场三字码，例如SHA*/
  String arrivalAirport
  /**离港时间，例如：09:00*/
  LocalTime departureTime
  /**到达时间，例如 11:00*/
  LocalTime arrivalTime

  @Override
  String toString() {
    "${departureAirport}-${arrivalAirport}" +
            " ${departureTime}-${arrivalTime}"
  }
}
