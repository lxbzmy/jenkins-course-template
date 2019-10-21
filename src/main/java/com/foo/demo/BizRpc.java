package com.foo.demo;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *
 * @author lxb
 */
public interface BizRpc {
  List<Segment> queryFlightDetail(String flightNumber, LocalDate date);
}

