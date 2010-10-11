/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.impl.AbstactObjectRowConverter;
import com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.domainid.UomId;
import java.util.LinkedHashMap;
import java.util.NavigableMap;
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
public class UOMRowConverter extends AbstactObjectRowConverter<UnitOfMeasurement, UomId> /*implements ObjectRowConverter<UnitOfMeasurement>*/ {

  protected final Logger logger = LoggerFactory.getLogger(getClass());
  public static final String SYMBOL = "symbol";
  public static final String TYPE = "type";
  public static final String SYSTEM = "system";
  public static final String ID = "id";
  public static final String NAME = "name";
  public static final byte[] FAMILY_SELF = Bytes.toBytes("self");
  public static final byte[] CELL_ID = Bytes.toBytes(ID);
  public static final byte[] CELL_SYMBOL = Bytes.toBytes(SYMBOL);
  public static final byte[] CELL_TYPE = Bytes.toBytes(TYPE);
  public static final byte[] CELL_SYSTEM = Bytes.toBytes(SYSTEM);
  public static final byte[] CELL_NAME = Bytes.toBytes(NAME);

  @Override
  public UnitOfMeasurement rowsToObject(Result startRow, ExecutorService executorService) {
    if (startRow.isEmpty()) {
      return null;
    }

    final UnitOfMeasurement measurement = new UnitOfMeasurement();

    NavigableMap<byte[], NavigableMap<byte[], byte[]>> allFamilies = startRow.getNoVersionMap();

    final NavigableMap<byte[], byte[]> self = allFamilies.get(FAMILY_SELF);

    if (self != null && !self.isEmpty()) {
      try {
        measurement.setId(getInfoProvider().getIdFromRowId(startRow.getRow()));
      }
      catch (Exception ex) {
        logger.error("Could parse ID", ex);
      }
      measurement.setSymbol(Bytes.toString(self.get(CELL_SYMBOL)));
      measurement.setUomSystem(Bytes.toString(self.get(CELL_SYSTEM)));
      measurement.setUomType(Bytes.toString(self.get(CELL_TYPE)));
      measurement.setLongName(Bytes.toString(self.get(CELL_NAME)));
    }

    return measurement;

  }

  @Override
  public LinkedHashMap<String, Delete> objectToDeleteableRows(UnitOfMeasurement instance, ExecutorService service) {
    LinkedHashMap<String, Delete> map = new LinkedHashMap<String, Delete>();
    Delete delete = new Delete(Bytes.toBytes(instance.getId().getId()));

    return map;
  }

  @Override
  protected String[] getTablesToAttainLock() {
    return new String[]{getInfoProvider().getMainTableName()};
  }

  @Override
  protected void getPutForTable(UnitOfMeasurement instance, ExecutorService service, Put put) {
    put.add(FAMILY_SELF, CELL_SYMBOL, Bytes.toBytes(instance.getSymbol()));
    put.add(FAMILY_SELF, CELL_SYSTEM, Bytes.toBytes(instance.getUomSystem()));
    put.add(FAMILY_SELF, CELL_TYPE, Bytes.toBytes(instance.getUomType()));
    put.add(FAMILY_SELF, CELL_NAME, Bytes.toBytes(instance.getLongName()));
  }

  @Override
  protected void getDeleteForTable(UnitOfMeasurement instance, ExecutorService service, Delete delete) {
    logger.info("Deleting self family");
    delete.deleteFamily(FAMILY_SELF);
  }
}
