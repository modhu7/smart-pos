/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import com.smartitengineering.smartpos.inventory.api.domainid.SupplierId;
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
public class PersistantSupplier extends AbstractGenericPersistentDTO<PersistantSupplier, SupplierId, Long> {

  private String name;
  private String email;
  private String contactNumber;
  private Address address;

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  @JsonIgnore
  public boolean isValid() {
    if (StringUtils.isBlank(name)) {
      return false;
    }
    return true;
  }

  public static class SupplierIdImpl implements SupplierId {

    private String id;
    private String orgUniqueShortName;

    public SupplierIdImpl() {
    }

    public SupplierIdImpl(String orgUniqueShortName, String id){
      this.orgUniqueShortName = orgUniqueShortName;
      this.id = id;
    }

    public SupplierIdImpl(String compositId){
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
      this.orgUniqueShortName = params[0];
      this.id = params[1];
    }

    @Override
    public String getCompositId() {
      return orgUniqueShortName + ":" + id;
    }

    @Override
    public String toString() {
      return orgUniqueShortName + ":" + id;
    }

    @Override
    public int compareTo(SupplierId o) {
      if (o == null) {
        return 1;
      }
      if (equals(o)) {
        return 0;
      }
      return toString().compareTo(o.toString());
    }

    @Override
    public String getOrgUniqueShortName() {
      return orgUniqueShortName;
    }
  }
}
