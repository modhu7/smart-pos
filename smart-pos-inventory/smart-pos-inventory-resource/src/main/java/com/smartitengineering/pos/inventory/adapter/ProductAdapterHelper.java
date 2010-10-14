/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.pos.inventory.adapter;

import com.smartitengineering.smartpos.inventory.api.PersistantProduct;
import com.smartitengineering.smartpos.inventory.api.Product;
import com.smartitengineering.util.bean.adapter.AbstractAdapterHelper;

/**
 *
 * @author russel
 */
public class ProductAdapterHelper extends AbstractAdapterHelper<Product, PersistantProduct>{

  @Override
  protected PersistantProduct newTInstance() {
    return new PersistantProduct();
  }

  @Override
  protected void mergeFromF2T(Product fromBean, PersistantProduct toBean) {
    toBean.setId(new PersistantProduct.ProductIdImpl(fromBean.getOrgUniqueShortName(), fromBean.getId()));
    toBean.setName(fromBean.getName());
    toBean.setDescription(fromBean.getDescription());
    toBean.setSkuId(fromBean.getSkuId());
    toBean.setSkuName(fromBean.getSkuName());
  }

  @Override
  protected Product convertFromT2F(PersistantProduct toBean) {
    Product product = new Product();
    product.setId(toBean.getId().getId());
    product.setOrgUniqueShortName(toBean.getId().getOrgUniqeShortName());
    product.setName(toBean.getName());
    product.setDescription(toBean.getDescription());
    product.setSkuId(toBean.getSkuId());
    product.setSkuName(toBean.getSkuName());
    
    return product;
  }

}
