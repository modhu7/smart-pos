/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.pos.admin.impl.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.ObjectRowConverter;
import com.smartitengineering.smartpos.admin.api.Organization;
import java.util.LinkedHashMap;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author modhu7
 */
public class OrganizationRowConverter implements ObjectRowConverter{

  protected final Logger logger = LoggerFactory.getLogger(OrganizationRowConverter.class);

  public static final byte[][] SHORT_NAME = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("shortName")};


  public LinkedHashMap<String, Put> objectToRows(Organization instance) {
    LinkedHashMap<String, Put> map = new LinkedHashMap<String, Put>();
    Put put = new Put(Bytes.toBytes(instance.getId().getId()));
    put.add(SHORT_NAME[0], SHORT_NAME[1], Bytes.toBytes(instance.getShortName()));
    return map;
  }

  @Override
  public LinkedHashMap objectToDeleteableRows(Object instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Object rowsToObject(Result startRow, ExecutorService executorService) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public LinkedHashMap objectToRows(Object instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
