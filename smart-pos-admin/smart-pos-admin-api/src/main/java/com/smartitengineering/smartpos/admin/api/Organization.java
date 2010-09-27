/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.admin.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import com.smartitengineering.domain.AbstractPersistentDTO;
import com.smartitengineering.smartpos.inventory.api.Product;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 *
 * @author russel
 */

public class Organization extends AbstractGenericPersistentDTO<Organization, Long, Long> {


    private String name;

    private String uniqueShortName;
    
    private Address address;
    //private String contactPerson;

    private Date lastModifiedDate;

    private Integer[] productIds;

    private Integer[] storeIds;

    private Integer[] uomIds;

    private Integer[] supplierIds;

    public Organization() {
    }
    
    public Organization(String name, String uniqueShortName) {
        this.name = name;
        this.uniqueShortName = uniqueShortName;
    }

    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public String getUniqueShortName() {
        return uniqueShortName;
    }

    
    public void setUniqueShortName(String uniqueShortName) {
        this.uniqueShortName = uniqueShortName;
    }

    @JsonIgnore
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    @JsonIgnore
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Address getAddress() {
        if(address==null)
            address = new Address();
        return address;
    }

    public void setAddress(Address address) {
        if(address==null)
            return;
        this.address = address;
    }

  public Integer[] getProductIds() {
    return productIds;
  }

  public void setProductIds(Integer[] productIds) {
    this.productIds = productIds;
  }

  public Integer[] getStoreIds() {
    return storeIds;
  }

  public void setStoreIds(Integer[] storeIds) {
    this.storeIds = storeIds;
  }

  public Integer[] getSupplierIds() {
    return supplierIds;
  }

  public void setSupplierIds(Integer[] supplierIds) {
    this.supplierIds = supplierIds;
  }

  public Integer[] getUomIds() {
    return uomIds;
  }

  public void setUomIds(Integer[] uomIds) {
    this.uomIds = uomIds;
  }

//    public String getContactPerson() {
//        if(contactPerson == null)
//            contactPerson = "";
//        return contactPerson;
//    }
//
//    public void setContactPerson(String contactPerson) {
//        this.contactPerson = contactPerson;
//    }



    @JsonIgnore
    public boolean isValid(){
        return StringUtils.isNotBlank(name) && StringUtils.isNotBlank(uniqueShortName);
    }


}
