/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.ObjectRowConverter;
import com.smartitengineering.smartpos.inventory.api.Store;
import java.util.LinkedHashMap;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class StoreRowConverter implements ObjectRowConverter<Store>{

  protected final Logger logger = LoggerFactory.getLogger(StoreRowConverter.class);

  public static final byte[][] NAME = new byte[][] {Bytes.toBytes("self"), Bytes.toBytes("name")};
  public static final byte[][] CODE = new byte[][] {Bytes.toBytes("self"), Bytes.toBytes("code")};
  public static final byte[][] ORGANIZATION = new byte[][] {Bytes.toBytes("organization"), Bytes.toBytes("id")};
  public static final byte[][] ADDRESS = new byte[][] {Bytes.toBytes("self"), Bytes.toBytes("address")};
  public static final byte[][] PRODUCTSID = new byte[][] {Bytes.toBytes("products"), Bytes.toBytes("id")};
  public static final String STORE_TBL_NAME = "store";
  
  @Override
  public LinkedHashMap<String, Put> objectToRows(Store instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public LinkedHashMap<String, Delete> objectToDeleteableRows(Store instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

//  @Override
//  public LinkedHashMap<String, Put> objectToRows(Store instance) {
//    LinkedHashMap<String, Put> map = new LinkedHashMap<String, Put>();
//
//    Put put = new Put(Bytes.toBytes(instance.getId()));
//
//    put.add(NAME[0], NAME[1], Bytes.toBytes(instance.getName()));
//    put.add(CODE[0], CODE[1], Bytes.toBytes(instance.getCode()));
//    put.add(ORGANIZATION[0], ORGANIZATION[1], Bytes.toBytes(instance.getOrganization().getId()));
//    put.add(ADDRESS[0], ADDRESS[1], Bytes.toBytes(instance.getAddress().getStreetAddress()));
//
//    for(String productId:instance.getProductIds()){
//      put.add(PRODUCTSID[0], Bytes.toBytes(Bytes.toString(PRODUCTSID[1]) + ":"+ productId), Bytes.toBytes(productId));
//    }
//
//
//    map.put(STORE_TBL_NAME, put);
//    return map;
//  }
//
//  @Override
//  public LinkedHashMap<String, Delete> objectToDeleteableRows(Store instance) {
//    throw new UnsupportedOperationException("Not supported yet.");
//  }

  @Override
  public Store rowsToObject(Result startRow, ExecutorService executorService) {
    if(startRow.isEmpty()){
      return null;
    }
    final Store store = new Store();

    store.setId(Bytes.toString(startRow.getRow()));

    //store.setProductIds(startRow.getValue(family, qualifier);

    return store;
  }

}
