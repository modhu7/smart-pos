/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.impl.AbstactObjectRowConverter;
import com.smartitengineering.smartpos.inventory.api.PersistantProduct;
import com.smartitengineering.smartpos.inventory.api.domainid.ProductId;
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
public class ProductRowConverter extends AbstactObjectRowConverter<PersistantProduct, ProductId> {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public static final String NAME = "name";
  public static final String DESCRIPTION = "description"; 
  public static final String SKU_ID = "skuId";
  public static final String SKU_NAME = "skuName";

  public static final byte[] FAMILY_SELF = Bytes.toBytes("self");
  public static final byte[] FAMILY_SKU = Bytes.toBytes("sku");

  public static final byte[] CELL_NAME = Bytes.toBytes(NAME);
  public static final byte[] CELL_DESCRIPTION = Bytes.toBytes(DESCRIPTION);
  public static final byte[] CELL_SKUID = Bytes.toBytes(SKU_ID);
  public static final byte[] CELL_SKUNAME = Bytes.toBytes(SKU_NAME);

  @Override
  protected String[] getTablesToAttainLock() {
    return new String[] {getInfoProvider().getMainTableName()};
  }

  @Override
  protected void getPutForTable(PersistantProduct t, ExecutorService es, Put put) {
    put.add(FAMILY_SELF, CELL_NAME, Bytes.toBytes(t.getName()));
    put.add(FAMILY_SELF, CELL_DESCRIPTION, Bytes.toBytes(t.getDescription()));
    put.add(FAMILY_SKU, CELL_SKUID, Bytes.toBytes(t.getSkuId()));
    put.add(FAMILY_SKU, CELL_SKUNAME, Bytes.toBytes(t.getSkuName()));
  }

  @Override
  protected void getDeleteForTable(PersistantProduct t, ExecutorService es, Delete delete) {
    logger.info("Deleting whole product with id:" + t.getId().toString());
  }

  @Override
  public PersistantProduct rowsToObject(Result result, ExecutorService es) {
    if(result.isEmpty())
      return null;

    final PersistantProduct product = new PersistantProduct();

    NavigableMap<byte[], NavigableMap<byte[], byte[]>> allFamilies = result.getNoVersionMap();

    NavigableMap<byte[], byte[]> self = allFamilies.get(FAMILY_SELF);

    if(self != null && !self.isEmpty()){
      try{
        product.setId(getInfoProvider().getIdFromRowId(result.getRow()));
      }catch(Exception ex){
        logger.error("could not parse id");
      }
      product.setName(Bytes.toString(self.get(CELL_NAME)));
      product.setDescription(Bytes.toString(self.get(CELL_DESCRIPTION)));
    }

    NavigableMap<byte[], byte[]> skuFamily = allFamilies.get(FAMILY_SKU);
    if(skuFamily != null && !skuFamily.isEmpty()){
      product.setSkuId(Bytes.toString(skuFamily.get(CELL_SKUID)));
      product.setDescription(Bytes.toString(skuFamily.get(CELL_SKUNAME)));
    }
    return product;
  }  
}
