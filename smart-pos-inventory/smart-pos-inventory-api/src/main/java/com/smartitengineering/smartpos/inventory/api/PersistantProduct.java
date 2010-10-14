/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import com.smartitengineering.smartpos.inventory.api.domainid.ProductId;
import com.smartitengineering.smartpos.inventory.impl.domainid.Utils;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author russel
 */
public class PersistantProduct extends AbstractGenericPersistentDTO<PersistantProduct, ProductId, Long>{

  private String name;
  private String description;  
  private String skuId;
  private String skuName;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }  

  public String getSkuId() {
    return skuId;
  }

  public void setSkuId(String skuID) {
    this.skuId = skuID;
  }

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }
  

  @Override
  public boolean isValid(){
    if(StringUtils.isBlank(name))
      return false;
    return true;
  }

  public static class ProductIdImpl implements ProductId {

    private String id;
    private String orgUniqueShortName;

    public ProductIdImpl() {
    }

    public ProductIdImpl(String orgUniqueShortName, String id) {
      this.id = id;
      this.orgUniqueShortName = orgUniqueShortName;
    }

    public ProductIdImpl(String compositId) {
      System.out.println("Composit Id:" + compositId);
      String[] infos = compositId.split(":");
      if(infos.length == 1){
        throw new RuntimeException("Object should have been in the format");
      }
      System.out.println("size:" + infos.length);
      this.orgUniqueShortName = infos[0];
      this.id = infos[1];
      System.out.println("id: "+id);
      System.out.println("orgName:"+ orgUniqueShortName);
    }

    @Override
    public String getId() {
      return id;
    }

    @Override
    public void setId(String id) {
      this.id = id;
    }

    @JsonIgnore
    public String getOrgUniqeShortName(){
      if(orgUniqueShortName == null){
        throw new RuntimeException("No organization found");
      }
      return orgUniqueShortName;
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
      orgUniqueShortName = params[0];
      id = params[1];
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

    @Override
    public String getCompositeId() {
      return orgUniqueShortName + ":" + id;
    }

    @Override
    public String toString() {
      return orgUniqueShortName + ":" + id;
    }
  }

}
