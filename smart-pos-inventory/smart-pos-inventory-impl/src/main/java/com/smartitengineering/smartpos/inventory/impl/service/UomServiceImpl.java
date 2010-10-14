/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.dao.common.queryparam.MatchMode;
import com.smartitengineering.dao.common.queryparam.Order;
import com.smartitengineering.dao.common.queryparam.QueryParameter;
import com.smartitengineering.dao.common.queryparam.QueryParameterFactory;
import com.smartitengineering.dao.impl.hbase.CommonDao;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderImpl;
import com.smartitengineering.smartpos.inventory.api.PersistantUnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.converter.UOMRowConverter;
import com.smartitengineering.smartpos.inventory.api.domainid.UomId;
import com.smartitengineering.smartpos.inventory.api.service.UomService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import javax.ws.rs.QueryParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class UomServiceImpl extends AbstractUomService implements UomService {

  protected final Logger logger = LoggerFactory.getLogger(UomServiceImpl.class);  

  public UomServiceImpl(){       
  }
  

  @Override
  public void save(PersistantUnitOfMeasurement uom) {
    try{
      commonWriteDao.save(uom);
    }catch(Exception ex){
      logger.error(ex.getMessage());
      ex.printStackTrace();
    }
  }

  @Override
  public void update(PersistantUnitOfMeasurement uom) {
    commonWriteDao.update(uom);
  }

  @Override
  public void delete(PersistantUnitOfMeasurement uom) {
    commonWriteDao.delete(uom);
  }

  @Override
  public Collection<PersistantUnitOfMeasurement> getAllUoms() {
    final List<PersistantUnitOfMeasurement> list = commonReadDao.getList();
    if(list == null  || list.isEmpty())
      return Collections.emptyList();
    return list;
  }

  @Override
  public Collection<PersistantUnitOfMeasurement> getByOrganization(String organizatinUniqueShortName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<PersistantUnitOfMeasurement> getByOrganization(String organizationUniqueShortName, String name,
                                                         boolean isSmallerThan,
                                                         int count) {

    logger.info("uomName:"+name);
    logger.info("orgName:"+organizationUniqueShortName);
    logger.info("Small?"+isSmallerThan);
    logger.info("count: "+count);

    List<QueryParameter> params = new ArrayList<QueryParameter>();

    if(StringUtils.isNotBlank(organizationUniqueShortName)){
      QueryParameter qp = QueryParameterFactory.getStringLikePropertyParam("id", organizationUniqueShortName, MatchMode.START);
      params.add(qp);
    }
    if(StringUtils.isNotBlank(name)){
      if(isSmallerThan){
        params.add(QueryParameterFactory.getLesserThanPropertyParam("name", name));
      }else{
        params.add(QueryParameterFactory.getGreaterThanPropertyParam("name", name));
      }
    }
    params.add(QueryParameterFactory.getMaxResultsParam(count));
    params.add(QueryParameterFactory.getOrderByParam("name", isSmallerThan?Order.DESC: Order.ASC));

    List<PersistantUnitOfMeasurement> uoms = commonReadDao.getList(params);// getOtherList(params);

    if(uoms != null && !uoms.isEmpty()){
      Collections.sort(uoms, new Comparator<PersistantUnitOfMeasurement>() {

        @Override
        public int compare(PersistantUnitOfMeasurement o1, PersistantUnitOfMeasurement o2) {
          return o1.getId().getId().toUpperCase().compareTo(o2.getId().getId().toUpperCase());
        }
      });
      return uoms;
    }else{
      return Collections.emptyList();
    }        
  }

  public List<PersistantUnitOfMeasurement> getByUomNames(List<String> uomNames) {

    QueryParameter<String> param = QueryParameterFactory.<String>getIsInPropertyParam("name", uomNames.toArray(new String[uomNames.size()]));

    Collection<PersistantUnitOfMeasurement> result;
    try {
      result = commonReadDao.getList(param);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      result = Collections.<PersistantUnitOfMeasurement>emptyList();
    }
    return new ArrayList<PersistantUnitOfMeasurement>(result);

  }

  @Override
  public PersistantUnitOfMeasurement getByUomId(UomId uomId){
    List<QueryParameter> params = new ArrayList<QueryParameter>();
    QueryParameter qp = QueryParameterFactory.getStringLikePropertyParam("id", uomId.getId(), MatchMode.EXACT);
    params.add(qp);
    List<PersistantUnitOfMeasurement> uoms = commonReadDao.getList(params);
    logger.info("Size:" +uoms.size());
    return uoms.get(0);
  }

  @Override
  public PersistantUnitOfMeasurement getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public PersistantUnitOfMeasurement getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName, String name,
                                                   boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }  
}
