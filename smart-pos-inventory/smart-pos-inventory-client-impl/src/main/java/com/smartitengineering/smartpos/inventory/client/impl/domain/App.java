/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.client.impl.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.Product;
import com.smartitengineering.smartpos.inventory.client.api.resource.ProductResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.ProductsResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.RootResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.StoreResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.StoresResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.UomResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.UomsResource;
import com.smartitengineering.smartpos.inventory.client.impl.resource.RootResourceImpl;
import com.smartitengineering.user.client.impl.domain.Address;
import com.sun.jersey.api.client.UniformInterfaceException;
import java.net.URI;

/**
 *
 * @author russel
 */
public class App {

  public static void main(String[] args) {

    final String ROOT_URI_STRING = "http://localhost:10090/orgs/sn/SITEL/dashboard";

    try {
      RootResource rootResource = RootResourceImpl.getRoot(new URI(ROOT_URI_STRING));

      rootResource = RootResourceImpl.getRoot(new URI(ROOT_URI_STRING));


//    UomsResource uomsResource = rootResource.getOrganizationUomsResource();
//
////
//    UnitOfMeasurementImpl uom = new UnitOfMeasurementImpl();
//    uom.setId("KG");
//    uom.setLongName("Kilogram");
//    uom.setSymbol("Kg");
//    uom.setUomSystem("SI");
//    uom.setUomType("Weight");
//
//    UomResource uomResource = uomsResource.create(uom);
//
//
//    UnitOfMeasurement fetchedUom = uomResource.getUnitOfMeasurement();
//
//    fetchedUom.setSymbol("Kg");
//    fetchedUom.setUomSystem("Metric");
//    fetchedUom.setUomType("Weight");
//
//    uomResource.update();
////
//    UnitOfMeasurement changedUom = uomResource.getUnitOfMeasurement();
//
////
//    uomResource.delete();
//    try {
//      uomResource.get();
//
//    }
//    catch(UniformInterfaceException ex){
//
//    }
//    catch (Exception e) {
//
//    }
//
//    uomResource = uomsResource.create(uom);


    StoresResource storesResource = rootResource.getStoresResource();
    

    StoreImpl store = new StoreImpl();
    Address address = new Address();
    store.setId("S1");
    store.setName("Store 1");
    address.setStreetAddress("Haji Chinu Mia road");
    address.setCity("Dhaka");
    address.setState("Dhaka");
    address.setCountry("Bangladesh");
    address.setZip("1207");
    //store.setAddress(address);

//    StoreResource storeResource = storesResource.create(store);
//    Assert.assertNotNull(storeResource);

    ProductsResource productsResource = rootResource.getProductsResource();

    ProductImpl product = new ProductImpl();
    product.setId("P1");
    product.setName("Product1");
    product.setDescription("Product 1, Bekar company");
    product.setSkuId("KG");

    ProductResource productResource = productsResource.create(product);
    

    Product fetchedProduct = productResource.getProduct();
    
    fetchedProduct.setName("Modified Product Name");


    productResource.update();
//
    Product changedProduct = productResource.getProduct();
    
//
    productResource.delete();
    



    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
