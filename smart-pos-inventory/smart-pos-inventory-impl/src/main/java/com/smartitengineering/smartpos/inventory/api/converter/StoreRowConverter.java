/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.ObjectRowConverter;
import com.smartitengineering.dao.impl.hbase.spi.impl.AbstactObjectRowConverter;
import com.smartitengineering.smartpos.admin.api.Address;
import com.smartitengineering.smartpos.admin.api.GeoLocation;
import com.smartitengineering.smartpos.inventory.api.Store;
import com.smartitengineering.smartpos.inventory.api.domainid.StoreId;
import com.smartitengineering.smartpos.inventory.impl.domainid.StoreIdImpl;
import java.util.LinkedHashMap;
import java.util.NavigableMap;
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
public class StoreRowConverter extends AbstactObjectRowConverter<Store, StoreId> {

  protected final Logger logger = LoggerFactory.getLogger(StoreRowConverter.class);

  public static final String NAME = "name";
  public static final String CODE = "code";
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
  public static final byte[] CELL_CODE = Bytes.toBytes(CODE);

  public static final byte[] CELL_STREEADDRESS = Bytes.toBytes(STREET_ADDRESS);
  public static final byte[] CELL_CITY = Bytes.toBytes(CITY);
  public static final byte[] CELL_STATE = Bytes.toBytes(STATE);
  public static final byte[] CELL_COUNTRY = Bytes.toBytes(COUNTRY);
  public static final byte[] CELL_ZIP = Bytes.toBytes(ZIP);
  public static final byte[] CELL_LONGITUDE = Bytes.toBytes(LONGITUDE);
  public static final byte[] CELL_LATITUDE = Bytes.toBytes(LATITUDE);


  public static final String STORE_TBL_NAME = "store";
    

  @Override
  public Store rowsToObject(Result startRow, ExecutorService executorService) {
    if(startRow.isEmpty()){
      return null;
    }

    final Store store = new Store();
    final Address address = new Address();
    final GeoLocation geoLocation = new GeoLocation();

    NavigableMap<byte[], NavigableMap<byte[], byte[]>> allFamilies = startRow.getNoVersionMap();

    final NavigableMap<byte[], byte[]> self = allFamilies.get(FAMILY_SELF);

    if(self != null && !self.isEmpty()){
      store.setName(Bytes.toString(self.get(CELL_NAME)));
      store.setCode(Bytes.toString(self.get(CELL_CODE)));

      address.setStreetAddress(Bytes.toString(self.get(CELL_STREEADDRESS)));
      address.setCity(Bytes.toString(CELL_CITY));
      address.setState(Bytes.toString(CELL_STATE));
      address.setCountry(Bytes.toString(CELL_COUNTRY));
      address.setZip(Bytes.toString(CELL_ZIP));

      geoLocation.setLatitude(Double.valueOf(Bytes.toString(CELL_LATITUDE)));
      geoLocation.setLongitude(Double.valueOf(Bytes.toString(CELL_LONGITUDE)));

      address.setGeoLocation(geoLocation);
      store.setAddress(address);
    }

    return store;
  }


  @Override
  protected String[] getTablesToAttainLock() {
    return new String[] {getInfoProvider().getMainTableName()};
  }

  @Override
  protected void getPutForTable(Store instance, ExecutorService service, Put put) {
    put.add(FAMILY_SELF, CELL_NAME, Bytes.toBytes(instance.getName()));
    put.add(FAMILY_SELF, CELL_CODE, Bytes.toBytes(instance.getCode()));
    put.add(FAMILY_ADDRESS, CELL_STREEADDRESS, Bytes.toBytes(instance.getAddress().getStreetAddress()));
    put.add(FAMILY_ADDRESS, CELL_CITY, Bytes.toBytes(instance.getAddress().getCity()));
    put.add(FAMILY_ADDRESS, CELL_STATE, Bytes.toBytes(instance.getAddress().getState()));
    put.add(FAMILY_ADDRESS, CELL_COUNTRY, Bytes.toBytes(instance.getAddress().getCountry()));
    put.add(FAMILY_ADDRESS, CELL_ZIP, Bytes.toBytes(instance.getAddress().getZip()));
    put.add(FAMILY_ADDRESS, CELL_LONGITUDE, Bytes.toBytes(instance.getAddress().getGeoLocation().getLongitude()));
    put.add(FAMILY_ADDRESS, CELL_LATITUDE, Bytes.toBytes(instance.getAddress().getGeoLocation().getLatitude()));
  }

  @Override
  protected void getDeleteForTable(Store instance, ExecutorService service, Delete delete) {
    delete.deleteFamily(FAMILY_SELF);
  }



}
