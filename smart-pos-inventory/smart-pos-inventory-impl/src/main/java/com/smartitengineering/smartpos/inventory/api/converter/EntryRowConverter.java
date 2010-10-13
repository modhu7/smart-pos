/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.ObjectRowConverter;
import com.smartitengineering.smartpos.inventory.api.PersistantEntry;
import com.smartitengineering.smartpos.inventory.impl.domainid.EntryIdImpl;
import java.util.LinkedHashMap;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
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
public class EntryRowConverter implements ObjectRowConverter<PersistantEntry> {

  protected final Logger logger = LoggerFactory.getLogger(EntryRowConverter.class);

  public static final byte[][] QUANTITY = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("quantity")};
  public static final byte[][] ENTRY_DATE = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("entryDate")};
  public static final byte[][] EXPIRY_DATE = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("expiryDate")};
  public static final byte[][] PRODUCT_ID = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("productId")};
  public static final byte[][] STORE_ID = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("storeId")};
  public static final byte[][] ORG_ID = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("org_id")};
  public static final String ENTRY_TBL_NAME = "entry";

  //@Override
  public LinkedHashMap<String, Put> objectToRows(PersistantEntry instance) {
    LinkedHashMap<String, Put> map = new LinkedHashMap<String, Put>();
    Put put = new Put(Bytes.toBytes(instance.getId().getId()));
    put.add(QUANTITY[0], QUANTITY[1], Bytes.toBytes(instance.getQuantity()));
    put.add(ENTRY_DATE[0], ENTRY_DATE[1], Bytes.toBytes( DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(instance.getEntryDate())));
    put.add(EXPIRY_DATE[0], EXPIRY_DATE[1], Bytes.toBytes( DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(instance.getExpiryDate())));
    put.add(PRODUCT_ID[0], PRODUCT_ID[1], Bytes.toBytes(instance.getProductId()));
    put.add(STORE_ID[0], STORE_ID[1], Bytes.toBytes(instance.getStoreId()));
    put.add(ORG_ID[0], ORG_ID[1], Bytes.toBytes(instance.getOrganizationId()));

    return map;
  }

  //@Override
  public LinkedHashMap<String, Delete> objectToDeleteableRows(PersistantEntry instance) {
    LinkedHashMap<String, Delete> map = new LinkedHashMap<String, Delete>();
    Delete delete = new Delete(Bytes.toBytes(instance.getId().getId()));
    map.put(ENTRY_TBL_NAME, delete);
    return map;
  }

  @Override
  public PersistantEntry rowsToObject(Result startRow, ExecutorService executorService) {
    if (startRow.isEmpty()) {
      return null;
    }
    PersistantEntry entry = new PersistantEntry();
    entry.setId(new EntryIdImpl(Bytes.toString(startRow.getRow())));
    entry.setQuantity(Bytes.toDouble(startRow.getValue(QUANTITY[0], QUANTITY[1])));
    try{
    entry.setEntryDate(DateUtils.parseDate( Bytes.toString(startRow.getValue(ENTRY_DATE[0], ENTRY_DATE[1])) , new String[]{DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern()}));
    entry.setExpiryDate(DateUtils.parseDate( Bytes.toString(startRow.getValue(EXPIRY_DATE[0], EXPIRY_DATE[1])) , new String[]{DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern()}));
    }catch(Exception ex){
      ex.printStackTrace();
    }

    entry.setOrganizationId(Bytes.toInt(startRow.getValue(ORG_ID[0], ORG_ID[1])));
    entry.setProductId(Bytes.toString(startRow.getValue(PRODUCT_ID[0], PRODUCT_ID[1])));
    entry.setStoreId(Bytes.toString(startRow.getValue(STORE_ID[0], STORE_ID[1])));

    return entry;
  }

  @Override
  public LinkedHashMap<String, Put> objectToRows(PersistantEntry instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public LinkedHashMap<String, Delete> objectToDeleteableRows(PersistantEntry instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
