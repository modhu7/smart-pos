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

/**
 *
 * @author russel
 */
public class UnitOfMeasurement extends AbstractGenericPersistentDTO<UnitOfMeasurement, UomId, Long> {

  private String symbol;
  private String uomType;         // length, weight.. etc etc
  private String uomSystem;       // metric system, SI system, etc
  private Integer organizationId;

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

  public Integer getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Integer organizationID) {
    this.organizationId = organizationID;
  }

  public boolean isValid() {
    if (StringUtils.isBlank(getId().getId()) || StringUtils.isBlank(symbol)) {
      return false;
    }
    return true;
  }

  public String toString() {
    String str = "";
    str += getId() + "\n";
    str += getVersion() + "\n";
    str += symbol + "\n";
    str += uomType + "\n";
    str += uomSystem + "\n";
    return str;
  }

  public static class UomIdImpl implements UomId {

    private String id;
    private String orgUniqueShortName;

    public UomIdImpl() {
    }

    public UomIdImpl(String orgUniqueShortName, String id) {
      this.id = id;
      this.orgUniqueShortName = orgUniqueShortName;
    }

    public UomIdImpl(String compositId) {
      if (compositId != null || !compositId.equals("")) {
        String[] infos = compositId.split(":");
        if (infos.length > 1) {
          this.orgUniqueShortName = infos[0];
          this.id = infos[1];
        }else if(infos.length == 1){
          this.id = infos[0];
        }
      }
    }

    @Override
    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getOrgUniqueShortName() {
      return orgUniqueShortName;
    }

    public void setOrgUniqueShortName(String orgUniqueShortName) {
      this.orgUniqueShortName = orgUniqueShortName;
    }

    @Override
    public String getCompositeId() {
      return orgUniqueShortName + ":" + id;
    }

    @Override
    public String toString() {
      return orgUniqueShortName + ":" + id;
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
