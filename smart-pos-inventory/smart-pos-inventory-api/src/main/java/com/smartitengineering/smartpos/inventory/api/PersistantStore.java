/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import com.smartitengineering.smartpos.inventory.api.domainid.StoreId;
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
public class PersistantStore extends AbstractGenericPersistentDTO<PersistantStore, StoreId, Long> {

  private String name;  
  private Address address;   

  
  public Address getAddress() {
    if(address == null){
      address = new Address();
    }
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
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
    if (StringUtils.isNotBlank(getId().getId())) {
      return true;
    }

    return false;
  }

  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append("id:").append(getId());
    strBuilder.append("name:").append(name == null? "": name);
    strBuilder.append("Address: StreeAddress:").append(address.getStreetAddress() == null? "":address.getStreetAddress());
    strBuilder.append("Address: City:").append(address.getCity() == null? "":address.getCity());
    strBuilder.append("Address: State:").append(address.getState()== null? "":address.getState());
    strBuilder.append("Address: Country:").append(address.getCountry() == null ? "":address.getCountry());
    strBuilder.append("Address: Zip:").append(address.getZip() == null? "": address.getZip());
    strBuilder.append("Address: Longitude:").append(address.getGeoLocation() == null ? "":address.getGeoLocation().getLongitude());
    strBuilder.append("Address: Latitude:").append(address.getGeoLocation() == null ? "":address.getGeoLocation().getLatitude());
    return strBuilder.toString();
  }

  public static class StoreIdImpl implements StoreId {

    private String id;
    private String orgUniqueShortName;

    public StoreIdImpl() {
    }

    public StoreIdImpl(String orgUniqueShortName, String id) {
      this.id = id;
      this.orgUniqueShortName = orgUniqueShortName;
    }

    public StoreIdImpl(String compositId) {
      String[] infos = compositId.split(":");
      if(infos.length == 1){
        throw new RuntimeException("Object should have been in the format");
      }
      this.orgUniqueShortName = infos[0];
      this.id = infos[1];
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
    public int compareTo(StoreId o) {
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
