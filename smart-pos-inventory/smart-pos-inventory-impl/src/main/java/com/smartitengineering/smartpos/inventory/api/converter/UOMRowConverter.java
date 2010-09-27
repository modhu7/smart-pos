/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.ObjectRowConverter;
import com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement;
import java.util.LinkedHashMap;
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
public class UOMRowConverter implements ObjectRowConverter<UnitOfMeasurement> {

  protected final Logger logger = LoggerFactory.getLogger(UOMRowConverter.class);

  public static final byte[][] SYMBOL = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("symbol")};
  public static final byte[][] TYPE = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("type")};
  public static final byte[][] SYSTEM = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("system")};
  public static final byte[][] ORG_ID = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("org_id")};
  public static final String UOM_TBL_NAME = "uom";

  @Override
  public LinkedHashMap<String, Put> objectToRows(UnitOfMeasurement instance) {
    LinkedHashMap<String, Put> map = new LinkedHashMap<String, Put>();
    Put put = new Put(Bytes.toBytes(instance.getId()));
    put.add(SYMBOL[0], SYMBOL[1], Bytes.toBytes(instance.getSymbol()));
    put.add(TYPE[0], TYPE[1], Bytes.toBytes(instance.getUomType()));
    put.add(SYSTEM[0], SYSTEM[1], Bytes.toBytes(instance.getUomSystem()));
    put.add(ORG_ID[0], ORG_ID[1], Bytes.toBytes(instance.getOrganizationId()));
    map.put(UOM_TBL_NAME, put);
    return map;
  }

  @Override
  public LinkedHashMap<String, Delete> objectToDeleteableRows(UnitOfMeasurement instance) {
    LinkedHashMap<String, Delete> map = new LinkedHashMap<String, Delete>();
    Delete delete = new Delete(Bytes.toBytes(instance.getId()));
    map.put(UOM_TBL_NAME, delete);
    return map;
  }

  @Override
  public UnitOfMeasurement rowsToObject(Result startRow, ExecutorService executorService) {
    if(startRow.isEmpty()) {
      return null;
    }
    final UnitOfMeasurement measurement = new UnitOfMeasurement();
    measurement.setId(Bytes.toString(startRow.getRow()));
    measurement.setOrganizationId(Bytes.toInt(startRow.getValue(ORG_ID[0], ORG_ID[1])));
    measurement.setSymbol(Bytes.toString(startRow.getValue(SYMBOL[0], SYMBOL[1])));
    measurement.setUomType(Bytes.toString(startRow.getValue(TYPE[0], TYPE[1])));
    measurement.setUomSystem(Bytes.toString(startRow.getValue(SYSTEM[0], SYSTEM[1])));
    return measurement;
  }

  @Override
  public LinkedHashMap<String, Put> objectToRows(UnitOfMeasurement instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public LinkedHashMap<String, Delete> objectToDeleteableRows(UnitOfMeasurement instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
