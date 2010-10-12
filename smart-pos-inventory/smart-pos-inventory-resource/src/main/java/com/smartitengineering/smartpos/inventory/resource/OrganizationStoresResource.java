/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.resource;

import com.smartitengineering.smartpos.admin.api.Address;
import com.smartitengineering.smartpos.admin.api.GeoLocation;
import com.smartitengineering.smartpos.inventory.api.factory.Services;
import com.smartitengineering.smartpos.admin.resource.RootResource;
import com.smartitengineering.smartpos.inventory.api.Store;
import com.smartitengineering.smartpos.inventory.api.Store.StoreIdImpl;
import com.smartitengineering.smartpos.inventory.api.domainid.StoreId;
import com.smartitengineering.smartpos.inventory.api.service.StoreService;
import com.sun.jersey.api.view.Viewable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.action.GetLongAction;

/**
 *
 * @author russel
 */
@Path("/orgs/sn/{uniqueShortName}/inv/stores")
public class OrganizationStoresResource extends AbstractResource {

  protected final Logger logger = LoggerFactory.getLogger(OrganizationStoresResource.class);
  static final UriBuilder ORGANIZATION_STORES_URI_BUILDER;
  static final UriBuilder ORGANIZATION_STORES_BEFORE_USERNAME_URI_BUILDER;
  static final UriBuilder ORGANIZATION_STORES_AFTER_USERNAME_URI_BUILDER;

  static {
    ORGANIZATION_STORES_URI_BUILDER = UriBuilder.fromResource(OrganizationStoresResource.class);

    ORGANIZATION_STORES_AFTER_USERNAME_URI_BUILDER = UriBuilder.fromResource(OrganizationStoresResource.class);
    try {
      ORGANIZATION_STORES_AFTER_USERNAME_URI_BUILDER.path(OrganizationStoresResource.class.getMethod("getAfter",
                                                                                                     String.class));
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    ORGANIZATION_STORES_BEFORE_USERNAME_URI_BUILDER = UriBuilder.fromResource(OrganizationStoresResource.class);
    try {
      ORGANIZATION_STORES_BEFORE_USERNAME_URI_BUILDER.path(OrganizationStoresResource.class.getMethod("getBefore",
                                                                                                      String.class));
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
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
  public Response getHtml() {
    ResponseBuilder responseBuilder = Response.ok();

    Collection<Store> stores = Services.getInstance().getStoreService().getByOrganization(organizationUniqueShortName,
                                                                                          null,
                                                                                          true, count);

    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationStoresResource/storeListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationStoresResource/storeList.jsp");

    Viewable view = new Viewable("/template/template.jsp", stores);


    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/frags")
  public Response getHtmlFrags() {
    ResponseBuilder responseBuilder = Response.ok();
    Collection<Store> Stores = Services.getInstance().getStoreService().getByOrganization(
        organizationUniqueShortName, null, false, count);

    Viewable view = new Viewable("storeFrags.jsp", Stores, OrganizationStoresResource.class);
    responseBuilder.entity(view);
    return responseBuilder.build();

  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/before/{beforeStoreName}")
  public Response getBefore(@PathParam("beforeStoreName") String beforeStoreName) {
    return get(organizationUniqueShortName, beforeStoreName, true);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/before/{beforeStoreName}")
  public Response getBeforeHtml(@PathParam("beforeStoreName") String beforeStoreName) {
    ResponseBuilder responseBuilder = Response.ok();
    Collection<Store> Stores = Services.getInstance().getStoreService().getByOrganization(
        organizationUniqueShortName, beforeStoreName, true, count);

    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationStoresResource/storeList.jsp");
    Viewable view = new Viewable("/template/template.jsp", Stores);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/before/{beforeStoreName}/frags")
  public Response getBeforeHtmlFrags(@PathParam("beforeStoreName") String beforeStoreName) {
    ResponseBuilder responseBuilder = Response.ok();

    Collection<Store> Stores = Services.getInstance().getStoreService().getByOrganization(
        organizationUniqueShortName, beforeStoreName, true, count);

    Viewable view = new Viewable("storeFrags.jsp", Stores);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/after/{afterStoreName}")
  public Response getAfter(@PathParam("afterStoreName") String afterStoreName) {
    return get(organizationUniqueShortName, afterStoreName, false);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/after/{afterStoreName}")
  public Response getAfterHtml(@PathParam("afterStoreName") String afterStoreName) {

    ResponseBuilder responseBuilder = Response.ok();

    Collection<Store> Stores = Services.getInstance().getStoreService().getByOrganization(
        organizationUniqueShortName, afterStoreName, false, count);
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationStoresResource/storeList.jsp");
    Viewable view = new Viewable("/template/template.jsp", Stores);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/after/{afterStoreName}/frags")
  public Response getAfterHtmlFrags(@PathParam("afterStoreName") String afterStoreName) {

    ResponseBuilder responseBuilder = Response.ok();

    Collection<Store> Stores = Services.getInstance().getStoreService().getByOrganization(
        organizationUniqueShortName, afterStoreName, false, count);

    Viewable view = new Viewable("storeFrags.jsp", Stores);
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

    Collection<Store> Stores = Services.getInstance().getStoreService().getByOrganization(
        organizationUniqueShortName, userName, isBefore, count);

    if (Stores != null && !Stores.isEmpty()) {

      MultivaluedMap<String, String> queryParam = uriInfo.getQueryParameters();
      List<Store> StoreList = new ArrayList<Store>(Stores);

      // uri builder for next and previous organizations according to count
      final UriBuilder nextUri = ORGANIZATION_STORES_AFTER_USERNAME_URI_BUILDER.clone();
      final UriBuilder previousUri = ORGANIZATION_STORES_BEFORE_USERNAME_URI_BUILDER.clone();

      // link to the next organizations based on count
      Link nextLink = abderaFactory.newLink();
      nextLink.setRel(Link.REL_NEXT);
      //User lastUser = userList.get(userList.size() - 1);
      Store lastStore = StoreList.get(StoreList.size() - 1);


      for (String key : queryParam.keySet()) {
        final Object[] values = queryParam.get(key).toArray();
        nextUri.queryParam(key, values);
        previousUri.queryParam(key, values);
      }
      nextLink.setHref(nextUri.build(organizationUniqueShortName, lastStore.getId().getId()).toString());


      atomFeed.addLink(nextLink);

      /* link to the previous organizations based on count */
      Link prevLink = abderaFactory.newLink();
      prevLink.setRel(Link.REL_PREVIOUS);
      Store firstStore = StoreList.get(0);

      prevLink.setHref(
          previousUri.build(organizationUniqueShortName, firstStore.getId().getId()).toString());
      atomFeed.addLink(prevLink);

      for (Store store : Stores) {

        Entry storeEntry = abderaFactory.newEntry();

        storeEntry.setId(store.getId().getId());
        storeEntry.setTitle(store.getName());
        storeEntry.setSummary(store.getName());


        // setting link to the each individual user
        Link storeLink = abderaFactory.newLink();
        storeLink.setHref(OrganizationStoreResource.STORE_URI_BUILDER.clone().build(organizationUniqueShortName, store.
            getId().getId()).toString());
        storeLink.setRel(Link.REL_ALTERNATE);
        storeLink.setMimeType(MediaType.APPLICATION_ATOM_XML);

        storeEntry.addLink(storeLink);

        atomFeed.addEntry(storeEntry);
      }
    }
    responseBuilder.entity(atomFeed);
    return responseBuilder.build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(Store store) {
    ResponseBuilder responseBuilder;

    try {
      basicPost(store);
      responseBuilder = Response.status(Status.CREATED);
      responseBuilder.location(uriInfo.getBaseUriBuilder().path(OrganizationStoreResource.STORE_URI_BUILDER.clone().
          build(organizationUniqueShortName,
                store.getId().getId()).toString()).build());
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      logger.error(ex.getMessage());
    }
    return responseBuilder.build();
  }

  private Store getObjectFromContent(String message) {

    Map<String, String> keyValueMap = new HashMap<String, String>();

    String[] keyValuePairs = message.split("&");

    for (int i = 0; i < keyValuePairs.length; i++) {

      String[] keyValuePair = keyValuePairs[i].split("=");
      keyValueMap.put(keyValuePair[0], keyValuePair[1]);
    }

    final Store store = new Store();
    final Address address = new Address();
    final GeoLocation geoLocation = new GeoLocation();

    if(keyValueMap.get("id") != null){
      StoreId storeId = new StoreIdImpl();
      storeId.setId(keyValueMap.get("id"));
      store.setId(storeId);
    }

    if(keyValueMap.get("name")!= null){
      store.setName(keyValueMap.get("name"));
    }

    if(keyValueMap.get("streetAddress")!= null){
      address.setStreetAddress(keyValueMap.get("streetAddress"));
    }
    if(keyValueMap.get("city")!= null){
      address.setCity(keyValueMap.get("city"));
    }
    if(keyValueMap.get("state")!= null){
      address.setState(keyValueMap.get("state"));
    }
    if(keyValueMap.get("country")!= null){
      address.setCountry(keyValueMap.get("country"));
    }
    if(keyValueMap.get("zip")!= null){
      address.setStreetAddress(keyValueMap.get("zip"));
    }
    if(keyValueMap.get("longitude")!= null){
      geoLocation.setLongitude( Double.parseDouble(keyValueMap.get("longitude")));
    }
    if(keyValueMap.get("latitude")!= null){
      geoLocation.setLatitude(Double.parseDouble(keyValueMap.get("latitude")));
    }

    address.setGeoLocation(geoLocation);
    store.setAddress(address);

    return new Store();
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
      Store store = getObjectFromContent(message);
      basicPost(store);
    }
    return responseBuilder.build();
  }

  private void basicPost(Store store) {
    StoreId storeId = new StoreIdImpl(organizationUniqueShortName, store.getId().getId());
    store.setId(storeId);
    logger.info(store.getId().getCompositeId());
    logger.info(store.toString());
    Services.getInstance().getStoreService().save(store);
  }
}
