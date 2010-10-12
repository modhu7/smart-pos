/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.pos.admin.impl.converter;

import com.smartitengineering.dao.impl.hbase.spi.ExecutorService;
import com.smartitengineering.pos.admin.api.domain.id.OrganizationId;
import com.smartitengineering.smartpos.admin.api.Organization;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import com.smartitengineering.dao.impl.hbase.spi.impl.AbstactObjectRowConverter;

/**
 *
 * @author modhu7
 */
public class OrganizationRowConverter extends AbstactObjectRowConverter<Organization, OrganizationId> {
  
  public static final String ID = "id";
  public static final byte[] FAMILY_SELF = Bytes.toBytes("self");
  public static final byte[] CELL_ID = Bytes.toBytes(ID);

  @Override
  protected String[] getTablesToAttainLock() {
    return new String[]{getInfoProvider().getMainTableName()};
  }

  @Override
  protected void getPutForTable(Organization instance, ExecutorService service, Put put) {
    put.add(FAMILY_SELF, CELL_ID, Bytes.toBytes(instance.getRelativeUri()));
  }

  @Override
  protected void getDeleteForTable(Organization instance, ExecutorService service, Delete put) {    
  }

  @Override
  public Organization rowsToObject(Result startRow, ExecutorService executorService) {
    if (startRow.isEmpty()) {
      return null;
    }
    final Organization organization = new Organization();

    try {
      organization.setId(getInfoProvider().getIdFromRowId(startRow.getRow()));
    }
    catch (Exception ex) {
      logger.error("Could not parse ID", ex);
    }
    organization.setRelativeUri(Bytes.toString(startRow.getValue(FAMILY_SELF, CELL_ID)));
    return organization;
  }
}
