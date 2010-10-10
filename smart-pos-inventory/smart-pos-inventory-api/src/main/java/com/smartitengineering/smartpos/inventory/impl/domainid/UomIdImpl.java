/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.domainid;

import com.smartitengineering.smartpos.inventory.api.domainid.UomId;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author russel
 */
public class UomIdImpl implements UomId{

  private String id;
  private String orgUniqueShortName;

  public UomIdImpl(){
    
  }

  public UomIdImpl(String orgUniqueShortName, String id){
    this.id = id;
    this.orgUniqueShortName = orgUniqueShortName;
  }

  public UomIdImpl(String compositId){
    String[] infos = compositId.split(":");
    this.orgUniqueShortName = infos[0];
    this.id = infos[1];
  }

  @Override
  public String getId() {
    return id;
  }

  public void setId(String id){
    this.id = id;
  }

  public String getOrgUniqueShortName() {
    return orgUniqueShortName;
  }

  public void setOrgUniqueShortName(String orgUniqueShortName) {
    this.orgUniqueShortName = orgUniqueShortName;
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
//    setGlobalNamespace(params[0]);
//    setName(params[1]);
  }

  @Override
  public int compareTo(UomId o) {
    if (o == null) {
      return 1;
    }
    if (equals(o)) {
      return 0;
    }
    return toString().compareTo(o.toString());
  }

  @Override
  public String getCompositeId(){
    return orgUniqueShortName + ":" + id;
  }

  @Override
  public String toString(){
    return orgUniqueShortName + ":" + id;
  }
}