/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.domainid;

import com.smartitengineering.smartpos.inventory.api.domainid.ProductId;
import com.smartitengineering.smartpos.inventory.impl.Utils;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author russel
 */
public class ProductIdImpl implements ProductId{

  private String id;
  
  public ProductIdImpl(){}
  
  public ProductIdImpl(String id){
    this.id = id;
  }

  @Override
  public String getId() {
    return this.id;
  }

  public void setId(String id){
    this.id = id;
  }

  @Override
  public void writeExternal(DataOutput output) throws IOException {
    output.write(toString().getBytes("UTF-8"));
  }

  @Override
  public void readExternal(DataInput input) throws IOException, ClassNotFoundException {
    String idString = Utils.readStringInUTF8(input);
    if (StringUtils.isBlank(idString)) {
      throw new IOException("No content!");
    }
    String[] params = idString.split(":");
    if (params == null || params.length != 2) {
      throw new IOException("Object should have been in the format globalNamespace:name!");
    }
  }

  @Override
  public int compareTo(ProductId o) {
    if (o == null) {
      return 1;
    }
    if (equals(o)) {
      return 0;
    }
    return toString().compareTo(o.toString());
  }

}
