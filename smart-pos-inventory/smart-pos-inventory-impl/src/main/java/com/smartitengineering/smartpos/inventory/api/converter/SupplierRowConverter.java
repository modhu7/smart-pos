/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.ObjectRowConverter;
import com.smartitengineering.smartpos.inventory.api.PersistantSupplier;
import java.util.LinkedHashMap;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class SupplierRowConverter implements ObjectRowConverter<PersistantSupplier>{

  protected final Logger logger = LoggerFactory.getLogger(SupplierRowConverter.class);

  @Override
  public LinkedHashMap<String, Put> objectToRows(PersistantSupplier instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public LinkedHashMap<String, Delete> objectToDeleteableRows(PersistantSupplier instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

//  @Override
//  public LinkedHashMap<String, Put> objectToRows(PersistantSupplier instance) {
//    throw new UnsupportedOperationException("Not supported yet.");
//  }
//
//  @Override
//  public LinkedHashMap<String, Delete> objectToDeleteableRows(PersistantSupplier instance) {
//    throw new UnsupportedOperationException("Not supported yet.");
//  }

  @Override
  public PersistantSupplier rowsToObject(Result startRow, ExecutorService executorService) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
