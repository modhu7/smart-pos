package com.smartitengineering.smartpos.inventory.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.time.DateFormatUtils;
import org.jruby.RubyArray$i_method_0_0$RUBYINVOKER$compact_bang;

/**
 * Hello world!
 *
 */
public class App {

  public static void main(String[] args) {
    Date currentDate = new Date();



    String date = DateFormatUtils.format(currentDate, DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.toString());


  }
}
