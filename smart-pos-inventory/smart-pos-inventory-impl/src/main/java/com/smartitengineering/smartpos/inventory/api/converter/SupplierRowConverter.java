/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.impl.AbstactObjectRowConverter;
import com.smartitengineering.smartpos.inventory.api.Address;
import com.smartitengineering.smartpos.inventory.api.GeoLocation;
import com.smartitengineering.smartpos.inventory.api.PersistantSupplier;
import com.smartitengineering.smartpos.inventory.api.domainid.SupplierId;
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
public class SupplierRowConverter extends AbstactObjectRowConverter<PersistantSupplier, SupplierId> {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public static final String NAME = "name";
  public static final String EMAIL = "email";
  public static final String CONTACT_NO = "contact_no";
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
  public static final byte[] CELL_EMAIL = Bytes.toBytes(EMAIL);
  public static final byte[] CELL_CONTACTNO = Bytes.toBytes(CONTACT_NO);
  public static final byte[] CELL_STREEADDRESS = Bytes.toBytes(STREET_ADDRESS);
  public static final byte[] CELL_CITY = Bytes.toBytes(CITY);
  public static final byte[] CELL_STATE = Bytes.toBytes(STATE);
  public static final byte[] CELL_COUNTRY = Bytes.toBytes(COUNTRY);
  public static final byte[] CELL_ZIP = Bytes.toBytes(ZIP);
  public static final byte[] CELL_LONGITUDE = Bytes.toBytes(LONGITUDE);
  public static final byte[] CELL_LATITUDE = Bytes.toBytes(LATITUDE);
  
  

  @Override
  protected String[] getTablesToAttainLock() {
    return new String[] {getInfoProvider().getMainTableName()};
  }

  @Override
  protected void getPutForTable(PersistantSupplier t, ExecutorService es, Put put) {

    put.add(FAMILY_SELF, CELL_NAME, Bytes.toBytes(t.getName()));
    put.add(FAMILY_SELF, CELL_EMAIL, Bytes.toBytes(t.getEmail()));
    put.add(FAMILY_SELF, CELL_CONTACTNO, Bytes.toBytes(t.getContactNumber()));

    if (t.getAddress() != null) {
      if (t.getAddress().getStreetAddress() != null) {
        put.add(FAMILY_ADDRESS, CELL_STREEADDRESS, Bytes.toBytes(t.getAddress().getStreetAddress()));
      }
      else {
        put.add(FAMILY_ADDRESS, CELL_STREEADDRESS, Bytes.toBytes(""));
      }
      if (t.getAddress().getCity() != null) {
        put.add(FAMILY_ADDRESS, CELL_CITY, Bytes.toBytes(t.getAddress().getCity()));
      }
      else {
        put.add(FAMILY_ADDRESS, CELL_CITY, Bytes.toBytes(""));
      }
      if (t.getAddress().getState() != null) {
        put.add(FAMILY_ADDRESS, CELL_STATE, Bytes.toBytes(t.getAddress().getState()));
      }
      else {
        put.add(FAMILY_ADDRESS, CELL_STATE, Bytes.toBytes(""));
      }
      if (t.getAddress().getCountry() != null) {
        put.add(FAMILY_ADDRESS, CELL_COUNTRY, Bytes.toBytes(t.getAddress().getCountry()));
      }
      else {
        put.add(FAMILY_ADDRESS, CELL_COUNTRY, Bytes.toBytes(""));
      }
      if (t.getAddress().getZip() != null) {
        put.add(FAMILY_ADDRESS, CELL_ZIP, Bytes.toBytes(t.getAddress().getZip()));
      }
      else {
        put.add(FAMILY_ADDRESS, CELL_ZIP, Bytes.toBytes(""));
      }
    }
    if (t.getAddress().getGeoLocation() != null) {
      logger.info("inside geolocation");
      put.add(FAMILY_ADDRESS, CELL_LONGITUDE, Bytes.toBytes(t.getAddress().getGeoLocation().getLongitude()));
      put.add(FAMILY_ADDRESS, CELL_LATITUDE, Bytes.toBytes(t.getAddress().getGeoLocation().getLatitude()));
    }
  }

  @Override
  protected void getDeleteForTable(PersistantSupplier t, ExecutorService es, Delete delete) {
    logger.info("Deleting whole family with id : " + t.getId().toString());
  }

  @Override
  public PersistantSupplier rowsToObject(Result result, ExecutorService es) {
    if (result.isEmpty()) {
      return null;
    }

    final PersistantSupplier supplier = new PersistantSupplier();
    final Address address = new Address();
    final GeoLocation geoLocation = new GeoLocation();

    NavigableMap<byte[], NavigableMap<byte[], byte[]>> allFamilies = result.getNoVersionMap();

    final NavigableMap<byte[], byte[]> self = allFamilies.get(FAMILY_SELF);

    if (self != null && !self.isEmpty()) {
      try {
        supplier.setId(getInfoProvider().getIdFromRowId(result.getRow()));
      }
      catch (Exception ex) {
        logger.error("could not parse id");
      }
      supplier.setName(Bytes.toString(self.get(CELL_NAME)));
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
      supplier.setAddress(address);
    }

    return supplier;
  }

  

}
