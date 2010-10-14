/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.resource;

import com.smartitengineering.smartpos.inventory.api.Supplier;
import com.smartitengineering.smartpos.inventory.api.domainid.SupplierId;
import com.smartitengineering.smartpos.inventory.api.factory.Services;
import com.smartitengineering.smartpos.inventory.impl.domainid.SupplierIdImpl;
import com.sun.jersey.api.view.Viewable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author saumitra
 */
@Path("/orgs/sn/{uniqueShortName}/inv/supplier")
public class SuppliersResource extends AbstractResource{

  protected final Logger logger = LoggerFactory.getLogger(OrganizationStoresResource.class);

  static final UriBuilder SUPPLIERS_URI_BUILDER;
  //  public SuppliersResource(@PathParam("uniqueShortName") String organizationUniqueShortName) {
//
//    this.organizationUniqueShortName = organizationUniqueShortName;
//
//  }
  static {
    SUPPLIERS_URI_BUILDER = UriBuilder.fromResource(OrganizationStoresResource.class);
  }
  @DefaultValue("10")
  @QueryParam("count")
  private Integer count;
  @PathParam("uniqueShortName")
  private String organizationUniqueShortName;
  @Context
  private HttpServletRequest servletRequest;

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getHtml(){
    ResponseBuilder responseBuilder = Response.ok();

    Collection<Supplier> suppliers = Services.getInstance().getSupplierService().getByOrganization(
        organizationUniqueShortName);

    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationStoresResource/supplierListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationStoresResource/supplierList.jsp");

    Viewable view = new Viewable("/template/template.jsp", suppliers);

    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    return get(organizationUniqueShortName, null, true);
  }


  private Response get(String uniqueOrganizationName, String userName, boolean isBefore) {
    ResponseBuilder responseBuilder = Response.ok();
    Feed atomFeed = getFeed(userName, new Date());

    Link parentLink = abderaFactory.newLink();
    parentLink.setHref(UriBuilder.fromResource(RootResource.class).build().toString());
    parentLink.setRel("parent");
    atomFeed.addLink(parentLink);

    Collection<Supplier> suppliers = Services.getInstance().getSupplierService().getByOrganization(
        organizationUniqueShortName);

    if (suppliers != null && !suppliers.isEmpty()) {

      MultivaluedMap<String, String> queryParam = uriInfo.getQueryParameters();
      List<Supplier> SupplierList = new ArrayList<Supplier>(suppliers);

        //for (User user : users) {
      for (Supplier supplier : suppliers) {

        Entry supplyEntry = abderaFactory.newEntry();

        supplyEntry.setId(supplier.getName());
        supplyEntry.setTitle(supplier.getName());
        supplyEntry.setSummary(supplier.getName());
        //userEntry.setUpdated(Store.g);

        // setting link to the each individual user
        Link storeLink = abderaFactory.newLink();
        storeLink.setHref(OrganizationStoreResource.STORE_URI_BUILDER.clone().build(organizationUniqueShortName, supplier.
            getId()).toString());
        storeLink.setRel(Link.REL_ALTERNATE);
        storeLink.setMimeType(MediaType.APPLICATION_ATOM_XML);

        supplyEntry.addLink(storeLink);

        atomFeed.addEntry(supplyEntry);
      }
    }
    responseBuilder.entity(atomFeed);
    return responseBuilder.build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(Supplier supplier) {
    ResponseBuilder responseBuilder;

    try {
      basicPost(supplier);
      responseBuilder = Response.status(Status.CREATED);
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      logger.error(ex.getMessage());
    }
    return responseBuilder.build();
  }

  private Supplier getObjectFromContent(String message) {

    return new Supplier();
  }


  @POST
  public Response post(
      @HeaderParam("Content-type") String contentType, String message) {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);

    if (StringUtils.isBlank(message)) {
      responseBuilder = Response.status(Status.BAD_REQUEST);
      responseBuilder.build();
    }

    final boolean isHtmlPost;

    if (StringUtils.isBlank(contentType)) {
      contentType = MediaType.APPLICATION_OCTET_STREAM;
      isHtmlPost = false;
    }
    else if (contentType.equals(MediaType.APPLICATION_FORM_URLENCODED)) {
      contentType = MediaType.APPLICATION_OCTET_STREAM;
      isHtmlPost = true;

      try {
        //Will search for the first '=' if not found will take the whole string
        final int startIndex = 0;//message.indexOf("=") + 1;
        //Consider the first '=' as the start of a value point and take rest as value
        final String realMsg = message.substring(startIndex);
        //Decode the message to ignore the form encodings and make them human readable
        message = URLDecoder.decode(realMsg, "UTF-8");

      }
      catch (UnsupportedEncodingException ex) {
        ex.printStackTrace();
      }
    }
    else {
      contentType = contentType;
      isHtmlPost = false;
    }

    if (isHtmlPost) {
      Supplier supplier = getObjectFromContent(message);
      basicPost(supplier);
    }
    return responseBuilder.build();
  }

  private void basicPost(Supplier supplier){
    SupplierId supplierId = new SupplierIdImpl(organizationUniqueShortName, supplier.getId().getId());
    supplier.setId(supplierId);
    logger.info(supplier.getId().toString());
    logger.info(supplier.toString());
    Services.getInstance().getSupplierService().save(supplier);
  }



}
