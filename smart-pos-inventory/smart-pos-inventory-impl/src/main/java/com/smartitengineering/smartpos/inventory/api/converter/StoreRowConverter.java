/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.impl.AbstactObjectRowConverter;
import com.smartitengineering.smartpos.inventory.api.Address;
import com.smartitengineering.smartpos.inventory.api.GeoLocation;
import com.smartitengineering.smartpos.inventory.api.PersistantStore;
import com.smartitengineering.smartpos.inventory.api.domainid.StoreId;
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
public class StoreRowConverter extends AbstactObjectRowConverter<PersistantStore, StoreId> {

  protected final Logger logger = LoggerFactory.getLogger(StoreRowConverter.class);
  public static final String NAME = "name";
  public static final String STREET_ADDRESS = "street address";
  public static final String CITY = "city";
  public static final String STATE = "state";
  public static final String COUNTRY = "country";
  public static final String ZIP = "zip";
  public static final String LONGITUDE = "longitude";
  public static final String LATITUDE = "latitude";
  public static final byte[] FAMILY_SELF = Bytes.toBytes("self");
  public static final byte[] FAMILY_ADDRESS = Bytes.toBytes("address");
  public static final byte[] CELL_NAME = Bytes.toBytes(NAME);
  public static final byte[] CELL_STREEADDRESS = Bytes.toBytes(STREET_ADDRESS);
  public static final byte[] CELL_CITY = Bytes.toBytes(CITY);
  public static final byte[] CELL_STATE = Bytes.toBytes(STATE);
  public static final byte[] CELL_COUNTRY = Bytes.toBytes(COUNTRY);
  public static final byte[] CELL_ZIP = Bytes.toBytes(ZIP);
  public static final byte[] CELL_LONGITUDE = Bytes.toBytes(LONGITUDE);
  public static final byte[] CELL_LATITUDE = Bytes.toBytes(LATITUDE);

  @Override
  public PersistantStore rowsToObject(Result startRow, ExecutorService executorService) {
    if (startRow.isEmpty()) {
      return null;
    }

    final PersistantStore store = new PersistantStore();
    final Address address = new Address();
    final GeoLocation geoLocation = new GeoLocation();

    NavigableMap<byte[], NavigableMap<byte[], byte[]>> allFamilies = startRow.getNoVersionMap();

    final NavigableMap<byte[], byte[]> self = allFamilies.get(FAMILY_SELF);

    if (self != null && !self.isEmpty()) {
      try {
        store.setId(getInfoProvider().getIdFromRowId(startRow.getRow()));
      }
      catch (Exception ex) {
        logger.error("could not parse id");
      }
      store.setName(Bytes.toString(self.get(CELL_NAME)));
    }

    final NavigableMap<byte[], byte[]> familyAddress = allFamilies.get(FAMILY_ADDRESS);

    if (familyAddress != null && !familyAddress.isEmpty()) {

      address.setStreetAddress(Bytes.toString(self.get(CELL_STREEADDRESS)));
      address.setCity(Bytes.toString(CELL_CITY));
      address.setState(Bytes.toString(CELL_STATE));
      address.setCountry(Bytes.toString(CELL_COUNTRY));
      address.setZip(Bytes.toString(CELL_ZIP));

      try {
        geoLocation.setLatitude(Double.valueOf(Bytes.toString(CELL_LATITUDE)));
      }
      catch (Exception ex) {
        geoLocation.setLatitude(new Double(0));
        logger.info("exception in setting latitude, setting 0");
      }

      try {
        geoLocation.setLongitude(Double.valueOf(Bytes.toString(CELL_LONGITUDE)));
      }
      catch (Exception ex) {
        geoLocation.setLongitude(new Double(0));
        logger.info("exception in setting longitude, setting 0");
      }

      address.setGeoLocation(geoLocation);
      store.setAddress(address);
    }

    return store;
  }

  @Override
  protected String[] getTablesToAttainLock() {
    return new String[]{getInfoProvider().getMainTableName()};
  }

  @Override
  protected void getPutForTable(PersistantStore instance, ExecutorService service, Put put) {

    put.add(FAMILY_SELF, CELL_NAME, Bytes.toBytes(instance.getName()));
    if (instance.getAddress() != null) {
      if (instance.getAddress().getStreetAddress() != null) {
        put.add(FAMILY_ADDRESS, CELL_STREEADDRESS, Bytes.toBytes(instance.getAddress().getStreetAddress()));
      }
      else {
        put.add(FAMILY_ADDRESS, CELL_STREEADDRESS, Bytes.toBytes(""));
      }
      if (instance.getAddress().getCity() != null) {
        put.add(FAMILY_ADDRESS, CELL_CITY, Bytes.toBytes(instance.getAddress().getCity()));
      }
      else {
        put.add(FAMILY_ADDRESS, CELL_CITY, Bytes.toBytes(""));
      }
      if (instance.getAddress().getState() != null) {
        put.add(FAMILY_ADDRESS, CELL_STATE, Bytes.toBytes(instance.getAddress().getState()));
      }
      else {
        put.add(FAMILY_ADDRESS, CELL_STATE, Bytes.toBytes(""));
      }
      if (instance.getAddress().getCountry() != null) {
        put.add(FAMILY_ADDRESS, CELL_COUNTRY, Bytes.toBytes(instance.getAddress().getCountry()));
      }
      else {
        put.add(FAMILY_ADDRESS, CELL_COUNTRY, Bytes.toBytes(""));
      }
      if (instance.getAddress().getZip() != null) {
        put.add(FAMILY_ADDRESS, CELL_ZIP, Bytes.toBytes(instance.getAddress().getZip()));
      }
      else {
        put.add(FAMILY_ADDRESS, CELL_ZIP, Bytes.toBytes(""));
      }
    }
    if (instance.getAddress().getGeoLocation() != null) {
      logger.info("inside geolocation");
      put.add(FAMILY_ADDRESS, CELL_LONGITUDE, Bytes.toBytes(instance.getAddress().getGeoLocation().getLongitude()));
      put.add(FAMILY_ADDRESS, CELL_LATITUDE, Bytes.toBytes(instance.getAddress().getGeoLocation().getLatitude()));
    }
  }

  @Override
  protected void getDeleteForTable(PersistantStore instance, ExecutorService service, Delete delete) {
    logger.info("Deleting whole store with id" + instance.getId().toString());
  }
}
