/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.ObjectRowConverter;
import com.smartitengineering.smartpos.inventory.api.Product;
import com.smartitengineering.smartpos.inventory.impl.domainid.ProductIdImpl;
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
public class ProductRowConverter implements ObjectRowConverter<Product> {

  protected final Logger logger = LoggerFactory.getLogger(ProductRowConverter.class);

  public static final byte[][] NAME = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("name")};
  public static final byte[][] DESCRIPTION = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("description")};
  public static final byte[][] SKU_ID = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("sku_id")};
  public static final byte[][] ORG_ID = new byte[][]{Bytes.toBytes("self"), Bytes.toBytes("org_id")};
  public static final String PRODUCT_TBL_NAME = "product";

//  @Override
//  public LinkedHashMap<String, Put> objectToRows(Product instance) {
//    LinkedHashMap<String, Put> map = new LinkedHashMap<String, Put>();
//    Put put = new Put(Bytes.toBytes(instance.getId()));
//    put.add(NAME[0], NAME[1], Bytes.toBytes(instance.getName()));
//    put.add(DESCRIPTION[0], DESCRIPTION[1], Bytes.toBytes(instance.getDescription()));
//    put.add(SKU_ID[0], SKU_ID[1], Bytes.toBytes(instance.getSkuId()));
//    put.add(ORG_ID[0], ORG_ID[1], Bytes.toBytes(instance.getOrganizationId()));
//    map.put(PRODUCT_TBL_NAME, put);
//    return map;
//  }
//
//  @Override
//  public LinkedHashMap<String, Delete> objectToDeleteableRows(Product instance) {
//    LinkedHashMap<String, Delete> map = new LinkedHashMap<String, Delete>();
//    Delete delete = new Delete(Bytes.toBytes(instance.getId()));
//    map.put(PRODUCT_TBL_NAME, delete);
//    return map;
//  }

  @Override
  public Product rowsToObject(Result startRow, ExecutorService executorService) {
    if (startRow.isEmpty()) {
      return null;
    }
    final Product product = new Product();
    product.setId(new ProductIdImpl(Bytes.toString(startRow.getRow())));
    product.setOrganizationId(Bytes.toInt(startRow.getValue(ORG_ID[0], ORG_ID[1])));
    product.setName(Bytes.toString(startRow.getValue(NAME[0], NAME[1])));
    product.setDescription(Bytes.toString(startRow.getValue(DESCRIPTION[0], DESCRIPTION[1])));
    product.setSkuId(Bytes.toString(startRow.getValue(SKU_ID[0], SKU_ID[1])));
    return product;
  }

  @Override
  public LinkedHashMap<String, Put> objectToRows(Product instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public LinkedHashMap<String, Delete> objectToDeleteableRows(Product instance, ExecutorService service) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
