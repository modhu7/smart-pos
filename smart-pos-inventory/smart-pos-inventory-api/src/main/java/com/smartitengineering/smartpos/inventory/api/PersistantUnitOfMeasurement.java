/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import com.smartitengineering.smartpos.inventory.api.domainid.UomId;
import com.smartitengineering.smartpos.inventory.impl.domainid.Utils;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 *
 * @author russel
 */
public class PersistantUnitOfMeasurement extends AbstractGenericPersistentDTO<PersistantUnitOfMeasurement, UomId, Long> {

  private String longName;
  private String symbol;
  private String uomType;         // length, weight.. etc etc
  private String uomSystem;       // metric system, SI system, etc 

  public String getLongName() {
    return longName;
  }

  public void setLongName(String longName) {
    this.longName = longName;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getUomSystem() {
    return uomSystem;
  }

  public void setUomSystem(String uomSystem) {
    this.uomSystem = uomSystem;
  }

  public String getUomType() {
    return uomType;
  }

  public void setUomType(String uomType) {
    this.uomType = uomType;
  }

  @JsonIgnore
  @Override
  public boolean isValid() {
    if (StringUtils.isBlank(getId().getId()) || StringUtils.isBlank(symbol)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    String str = "";
    str += getId() + "\n";
    str += getVersion() + "\n";
    str += symbol + "\n";
    str += uomType + "\n";
    str += uomSystem + "\n";
    return str;
  }

  //@JsonDeserialize(as = String.class)
  public static class UomIdImpl implements UomId {

    private String id;    

    public UomIdImpl() {
    }

    public UomIdImpl(String id){
      this.id = id;
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
    @JsonIgnore
    public String getCustomId(){
      return id;
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
      if (params == null || params.length != 1) {
        throw new IOException("Object should have been in the format globalNamespace:name!");
      }
      id = params[0];
    }

    @Override
    public String toString() {
      return id;
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
  }
}
