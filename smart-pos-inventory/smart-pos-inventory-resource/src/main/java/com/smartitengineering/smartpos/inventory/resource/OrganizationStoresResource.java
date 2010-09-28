/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.resource;

import com.smartitengineering.smartpos.admin.api.Organization;
import com.smartitengineering.smartpos.admin.resource.RootResource;
import com.smartitengineering.smartpos.inventory.api.Store;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  public OrganizationStoresResource(@PathParam("uniqueShortName") String organizationUniqueShortName) {

    this.organizationUniqueShortName = organizationUniqueShortName;


  }

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

//    Collection<User> users = Services.getInstance().getUserService().getUserByOrganization(organizationUniqueShortName,
//                                                                                           null,
//                                                                                           false, count);

//    Organization org = Services.getInstance().getOrganizationService().getOrganizationByUniqueShortName(
//        organizationUniqueShortName);
//    if (org == null) {
//      responseBuilder = Response.status(Status.NOT_FOUND);
//      return responseBuilder.build();
//    }


    Collection<Store> stores = Services.getInstance().getStoreService().getByOrganization(
        organizationUniqueShortName, null, false, count);
    


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
//    Collection<User> users = Services.getInstance().getUserService().getUserByOrganization(organizationUniqueShortName,
//                                                                                           null, false, count);
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
//    Collection<User> users = Services.getInstance().getUserService().getUserByOrganization(
//        organizationUniqueShortName, beforeUserName, true, count);
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
//    Collection<User> users = Services.getInstance().getUserService().getUserByOrganization(
//        organizationUniqueShortName, beforeUserName, true, count);

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
      nextLink.setHref(nextUri.build(organizationUniqueShortName, lastStore.getCode()).toString());


      atomFeed.addLink(nextLink);

      /* link to the previous organizations based on count */
      Link prevLink = abderaFactory.newLink();
      prevLink.setRel(Link.REL_PREVIOUS);
      //User firstUser = userList.get(0);
      Store firstStore = StoreList.get(0);

      prevLink.setHref(
          previousUri.build(organizationUniqueShortName, firstStore.getCode()).toString());
      atomFeed.addLink(prevLink);

      //for (User user : users) {
      for (Store store : Stores) {

        Entry storeEntry = abderaFactory.newEntry();

        storeEntry.setId(store.getCode());
        storeEntry.setTitle(store.getName());
        storeEntry.setSummary(store.getName());
        //userEntry.setUpdated(Store.g);

        // setting link to the each individual user
        Link storeLink = abderaFactory.newLink();
        storeLink.setHref(OrganizationStoreResource.STORE_URI_BUILDER.clone().build(organizationUniqueShortName, store.
            getCode()).toString());
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
      if (store.getOrganizationID() == null) {
        throw new Exception("No organization found");
      }
      //Services.getInstance().getOrganizationService().populateOrganization(user);
      Services.getInstance().getStoreService().save(store);
      responseBuilder = Response.status(Status.CREATED);
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return responseBuilder.build();
  }

  private Store getObjectFromContent(String message) {
//    Map<String, String> keyValueMap = new HashMap<String, String>();
//
//    String[] keyValuePairs = message.split("&");
//
//    for (int i = 0; i < keyValuePairs.length; i++) {
//
//      String[] keyValuePair = keyValuePairs[i].split("=");
//      int l = keyValuePair.length;
//      if (l == 1) {
//        keyValueMap.put(keyValuePair[0], "");
//        //keyValuePair[1] = new String("");
//      }
//      else {
//        keyValueMap.put(keyValuePair[0], keyValuePair[1]);
//      }
//
//    }
//
//    User newUser = new User();
//
//    if (keyValueMap.get("id") != null) {
//      newUser.setId(Integer.valueOf(keyValueMap.get("id")));
//    }
//
//    if (keyValueMap.get("userName") != null) {
//      newUser.setUsername(keyValueMap.get("userName"));
//    }
//    if (keyValueMap.get("password") != null) {
//      newUser.setPassword(keyValueMap.get("password"));
//    }
//
//    Organization parentOrg = Services.getInstance().getOrganizationService().getOrganizationByUniqueShortName(
//        organizationUniqueShortName);
//
//    if (parentOrg != null) {
//      newUser.setOrganization(parentOrg);
//      newUser.setParentOrganizationID(parentOrg.getId());
//    }
//
//
////    if (keyValueMap.get("uniqueShortName") != null) {
////      Organization parentOrg = Services.getInstance().getOrganizationService().getOrganizationByUniqueShortName(keyValueMap.
////          get("uniqueShortName"));
////
////      if (parentOrg != null) {
////        newUser.setOrganization(parentOrg);
////
////      }
////    }
//
//    Person person = new Person();
//    BasicIdentity self = new BasicIdentity();
//    Name selfName = new Name();
//    boolean isValid = false;
//
//    if (keyValueMap.get("firstName") != null) {
//      isValid = true;
//      selfName.setFirstName(keyValueMap.get("firstName"));
//    }
//    if (keyValueMap.get("lastName") != null) {
//      isValid = true;
//      selfName.setLastName(keyValueMap.get("lastName"));
//    }
//    if (keyValueMap.get("middleInitial") != null) {
//      isValid = true;
//      selfName.setMiddleInitial(keyValueMap.get("middleInitial"));
//    }
//    self.setName(selfName);
//
//    if (keyValueMap.get("nationalID") != null) {
//      isValid = true;
//      self.setNationalID(keyValueMap.get("nationalID"));
//    }
//    if (isValid == true) {
//      person.setSelf(self);
//    }
//
//
//    BasicIdentity spouse = new BasicIdentity();
//    Name spouseName = new Name();
//    isValid = false;
//
//    if (keyValueMap.get("spouseFirstName") != null) {
//      isValid = true;
//      spouseName.setFirstName(keyValueMap.get("spouseFirstName"));
//    }
//    if (keyValueMap.get("spouseLastName") != null) {
//      isValid = true;
//      spouseName.setLastName(keyValueMap.get("spouseLastName"));
//    }
//    if (keyValueMap.get("spouseMiddleInitial") != null) {
//      isValid = true;
//      spouseName.setMiddleInitial(keyValueMap.get("spouseMiddleInitial"));
//    }
//    spouse.setName(spouseName);
//
//    if (keyValueMap.get("spouseNationalID") != null) {
//      isValid = true;
//      spouse.setNationalID(keyValueMap.get("spouseNationalID"));
//    }
//
//    if (isValid == true) {
//      person.setSpouse(spouse);
//    }
//
//
//    BasicIdentity mother = new BasicIdentity();
//    Name motherName = new Name();
//    isValid = false;
//
//    if (keyValueMap.get("motherFirstName") != null) {
//      isValid = true;
//      motherName.setFirstName(keyValueMap.get("motherFirstName"));
//    }
//    if (keyValueMap.get("motherLastName") != null) {
//      isValid = true;
//      motherName.setLastName(keyValueMap.get("motherLastName"));
//    }
//    if (keyValueMap.get("motherMiddleInitial") != null) {
//      isValid = true;
//      motherName.setMiddleInitial(keyValueMap.get("motherMiddleInitial"));
//    }
//    mother.setName(motherName);
//
//    if (keyValueMap.get("motherNationalID") != null) {
//      isValid = true;
//      mother.setNationalID(keyValueMap.get("motherNationalID"));
//    }
//    if (isValid == true) {
//      person.setMother(mother);
//    }
//
//    BasicIdentity father = new BasicIdentity();
//    Name fatherName = new Name();
//    isValid = false;
//
//    if (keyValueMap.get("fatherFirstName") != null) {
//      isValid = true;
//      fatherName.setFirstName(keyValueMap.get("fatherFirstName"));
//    }
//    if (keyValueMap.get("fatherLastName") != null) {
//      isValid = true;
//      fatherName.setLastName(keyValueMap.get("fatherLastName"));
//    }
//    if (keyValueMap.get("fatherMiddleInitial") != null) {
//      isValid = true;
//      fatherName.setMiddleInitial(keyValueMap.get("fatherMiddleInitial"));
//    }
//    father.setName(fatherName);
//
//    if (keyValueMap.get("fatherNationalID") != null) {
//      isValid = true;
//      father.setNationalID(keyValueMap.get("fatherNationalID"));
//    }
//    if (isValid == true) {
//      person.setFather(father);
//    }
//
//    Address address = new Address();
//    GeoLocation geoLocation = new GeoLocation();
//
//
//    if (keyValueMap.get("longitude") != null) {
//      Double longitude = Double.parseDouble(keyValueMap.get("longitude"));
//      geoLocation.setLongitude(longitude);
//    }
//
//    if (keyValueMap.get("latitude") != null) {
//      Double latitude = Double.parseDouble(keyValueMap.get("latitude"));
//      geoLocation.setLatitude(latitude);
//    }
//
//    address.setGeoLocation(geoLocation);
//
//    if (keyValueMap.get("city") != null) {
//      address.setCity(keyValueMap.get("city"));
//    }
//
//    if (keyValueMap.get("country") != null) {
//      address.setCountry(keyValueMap.get("country"));
//    }
//
//    if (keyValueMap.get("state") != null) {
//      address.setState(keyValueMap.get("state"));
//    }
//    if (keyValueMap.get("zip") != null) {
//      address.setZip(keyValueMap.get("zip"));
//    }
//    person.setAddress(address);
//
//    if (keyValueMap.get("birthDate") != null) {
//      String dateString = keyValueMap.get("birthDate");
//      SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
//      try {
//        Date birthDate = format.parse(dateString);
//      }
//      catch (Exception ex) {
//      }
//    }
//    if (keyValueMap.get("primaryEmail") != null) {
//      person.setPrimaryEmail(keyValueMap.get("primaryEmail"));
//    }
//
//    if (keyValueMap.get("phoneNumber") != null) {
//      person.setPhoneNumber(keyValueMap.get("phoneNumber"));
//    }
//    if (keyValueMap.get("secondaryEmail") != null) {
//      person.setSecondaryEmail(keyValueMap.get("secondaryEmail"));
//    }
//    if (keyValueMap.get("faxNumber") != null) {
//      person.setFaxNumber(keyValueMap.get("faxNumber"));
//    }
//    if (keyValueMap.get("cellPhoneNumber") != null) {
//      person.setCellPhoneNumber(keyValueMap.get("cellPhoneNumber"));
//    }
//
//    Store Store = new Store();
//    Store.setUser(newUser);
//    Store.setPerson(person);
//
//    return Store;
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

      
        Services.getInstance().getStoreService().save(store);
      
      

    }
    return responseBuilder.build();
  }
}
