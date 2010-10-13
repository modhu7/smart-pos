/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.pos.inventory.resource;

import com.smartitengineering.smartpos.inventory.api.factory.Services;
import com.smartitengineering.smartpos.admin.resource.RootResource;
import com.smartitengineering.smartpos.inventory.api.PersistantEntry;
import com.sun.jersey.api.view.Viewable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
@Path("/orgs/sn/{uniqueShortName}/inv/entries")
public class OrganizationEntriesResource extends AbstractResource {

  protected final Logger logger = LoggerFactory.getLogger(getClass());
  static final UriBuilder ORGANIZATION_ENTRIES_URI_BUILDER;
  static final UriBuilder ORGANIZATION_ENTRIES_BEFORE_ENTRYDATE_URI_BUILDER;
  static final UriBuilder ORGANIZATION_ENTRIES_AFTER_ENTRYDATE_URI_BUILDER;

  public OrganizationEntriesResource(@PathParam("uniqueShortName") String organizationUniqueShortName) {

    this.organizationUniqueShortName = organizationUniqueShortName;


  }

  static {
    ORGANIZATION_ENTRIES_URI_BUILDER = UriBuilder.fromResource(OrganizationEntriesResource.class);

    ORGANIZATION_ENTRIES_AFTER_ENTRYDATE_URI_BUILDER = UriBuilder.fromResource(OrganizationEntriesResource.class);
    try {
      ORGANIZATION_ENTRIES_AFTER_ENTRYDATE_URI_BUILDER.path(OrganizationEntriesResource.class.getMethod("getAfter",
                                                                                                        String.class));
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    ORGANIZATION_ENTRIES_BEFORE_ENTRYDATE_URI_BUILDER = UriBuilder.fromResource(OrganizationEntriesResource.class);
    try {
      ORGANIZATION_ENTRIES_BEFORE_ENTRYDATE_URI_BUILDER.path(OrganizationEntriesResource.class.getMethod("getBefore",
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

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganization(
        organizationUniqueShortName, null, false, count);



    
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryList.jsp");
    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);

    Viewable view = new Viewable("/template/template.jsp", entries);        

    responseBuilder.entity(view);
    return responseBuilder.build();

  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/frags")
  public Response getHtmlFrags() {
    ResponseBuilder responseBuilder = Response.ok();

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganization(
        organizationUniqueShortName, null, false, count);


    Viewable view = new Viewable("entryFrags.jsp", entries, OrganizationEntriesResource.class);
    responseBuilder.entity(view);
    return responseBuilder.build();

  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/before/{beforeEntryDate}")
  public Response getBefore(@PathParam("beforeEntryDate") String beforeEntryDate) {
    return get(organizationUniqueShortName, null, beforeEntryDate, true);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/before/{beforeEntryDate}")
  public Response getBeforeHtml(@PathParam("beforeEntryDate") String beforeEntryDate) {
    ResponseBuilder responseBuilder = Response.ok();

    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    Date entryDate = new Date();
    try {
      entryDate = df.parse(beforeEntryDate);
    }
    catch (Exception ex) {      
      logger.error(ex.getMessage());
    }

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganization(
        organizationUniqueShortName, entryDate, true, count);

    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryList.jsp");
    Viewable view = new Viewable("/template/template.jsp", entries);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/before/{beforeEntryDate}/frags")
  public Response getBeforeHtmlFrags(@PathParam("beforeEntryDate") String beforeEntryDate) {
    ResponseBuilder responseBuilder = Response.ok();

    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    Date entryDate = new Date();
    try {
      entryDate = df.parse(beforeEntryDate);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganization(
        organizationUniqueShortName, entryDate, true, count);

    Viewable view = new Viewable("entriesFrags.jsp", entries);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/after/{afterEntryDate}")
  public Response getAfter(@PathParam("afterEntryDate") String afterEntryDate) {
    return get(organizationUniqueShortName, null, afterEntryDate, false);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/after/{afterEntryDate}")
  public Response getAfterHtml(@PathParam("afterEntryDate") String afterEntryDate) {

    ResponseBuilder responseBuilder = Response.ok();

    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    Date entryDate = new Date();
    try {
      entryDate = df.parse(afterEntryDate);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganization(
        organizationUniqueShortName, entryDate, false, count);
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryList.jsp");
    Viewable view = new Viewable("/template/template.jsp", entries);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/after/{afterEntryDate}/frags")
  public Response getAfterHtmlFrags(@PathParam("afterEntryDate") String afterEntryDate) {

    ResponseBuilder responseBuilder = Response.ok();

    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    Date entryDate = new Date();
    try {
      entryDate = df.parse(afterEntryDate);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganization(
        organizationUniqueShortName, entryDate, false, count);

    Viewable view = new Viewable("entryFrags.jsp", entries);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    return get(organizationUniqueShortName, null, null, true);
  }

  private Response get(String uniqueOrganizationName, PersistantEntry.TransactionType type, String entryDate, boolean isBefore) {
    ResponseBuilder responseBuilder = Response.ok();
    Feed atomFeed = getFeed(entryDate, new Date());

    Link parentLink = abderaFactory.newLink();
    parentLink.setHref(UriBuilder.fromResource(RootResource.class).build().toString());
    parentLink.setRel("parent");
    atomFeed.addLink(parentLink);

    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date();
    try {
      date = df.parse(entryDate);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganizationAndType(
        uniqueOrganizationName, type, date, isBefore, count);

    if (entries != null && !entries.isEmpty()) {

      MultivaluedMap<String, String> queryParam = uriInfo.getQueryParameters();
      List<PersistantEntry> entryList = new ArrayList<PersistantEntry>(entries);

      // uri builder for next and previous organizations according to count
      final UriBuilder nextUri = ORGANIZATION_ENTRIES_AFTER_ENTRYDATE_URI_BUILDER.clone();
      final UriBuilder previousUri = ORGANIZATION_ENTRIES_BEFORE_ENTRYDATE_URI_BUILDER.clone();

      // link to the next organizations based on count
      Link nextLink = abderaFactory.newLink();
      nextLink.setRel(Link.REL_NEXT);
      //User lastUser = userList.get(userList.size() - 1);
      PersistantEntry lastEntry = entryList.get(entryList.size() - 1);


      for (String key : queryParam.keySet()) {
        final Object[] values = queryParam.get(key).toArray();
        nextUri.queryParam(key, values);
        previousUri.queryParam(key, values);
      }
      nextLink.setHref(nextUri.build(organizationUniqueShortName, lastEntry.getEntryDate().toString()).toString());


      atomFeed.addLink(nextLink);

      /* link to the previous organizations based on count */
      Link prevLink = abderaFactory.newLink();
      prevLink.setRel(Link.REL_PREVIOUS);
      //User firstUser = userList.get(0);
      PersistantEntry firstEntry = entryList.get(0);

      prevLink.setHref(
          previousUri.build(organizationUniqueShortName, firstEntry.getEntryDate()).toString());
      atomFeed.addLink(prevLink);

      //for (User user : users) {
      for (PersistantEntry entry : entries) {

        org.apache.abdera.model.Entry entryEntry = abderaFactory.newEntry();

        entryEntry.setId(entry.getEntryDate().toString());
//        entryEntry.setTitle(entry.getProduct().getName());
//        entryEntry.setSummary(entry.getProduct().getName());


        // setting link to the each individual user
        Link entryLink = abderaFactory.newLink();
        entryLink.setHref(OrganizationEntryResource.ENTRY_URI_BUILDER.clone().build(organizationUniqueShortName, entry.
            getEntryDate().toString()).toString());
        entryLink.setRel(Link.REL_ALTERNATE);
        entryLink.setMimeType(MediaType.APPLICATION_ATOM_XML);

        entryEntry.addLink(entryLink);

        atomFeed.addEntry(entryEntry);
      }
    }
    responseBuilder.entity(atomFeed);
    return responseBuilder.build();
  }

  /*
   * General json post in entries. It accepts all types of transaction
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(PersistantEntry entry) {
    return generalPost(entry);
  }

  /*
   * General html post in entries. It accepts all types of transaction.
   */
  @POST
  public Response postHtml(
      @HeaderParam("Content-type") String contentType, String message) {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);

    if (StringUtils.isBlank(message)) {
      responseBuilder = Response.status(Status.BAD_REQUEST);
      return responseBuilder.build();
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
      PersistantEntry entry = getObjectFromContent(message);


      Services.getInstance().getEntryService().save(entry);

    }
    return responseBuilder.build();
  }

  /*
   * Get method for atom xml for purchase type entry
   */
  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/purchase")
  public Response getPurchase(){
    return get(organizationUniqueShortName, PersistantEntry.TransactionType.INBOUND_PURCHASE, null, false);
  }

  /*
   *  Get method for html views for purchase type entry.
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/purchase")
  public Response getPurchaseHtml(){
    ResponseBuilder responseBuilder = Response.ok();

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganizationAndType(
        organizationUniqueShortName, PersistantEntry.TransactionType.INBOUND_PURCHASE, null, false, count);



    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryPurchaseListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryPurchaseList.jsp");

    Viewable view = new Viewable("/template/template.jsp", entries);


    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  /*
   * General json post for purchase type entry.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/purchase")
  public Response postForPurchase(PersistantEntry entry) {
    entry.setType(PersistantEntry.TransactionType.INBOUND_PURCHASE);

    return generalPost(entry);
  }

  /*
   * General html post for purchase type entry.
   */
  @POST
  @Path("/purchase")
  public Response postHtmlForPurchase(@HeaderParam("Content-type") String contentType, String message) {
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
      PersistantEntry entry = getObjectFromContent(message);


      Services.getInstance().getEntryService().save(entry);

    }
    return responseBuilder.build();
  }

  /*
   * Get method for atom xml for returnin type entry
   */
  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/returnin")
  public Response getReturnIn(){
    return get(organizationUniqueShortName, PersistantEntry.TransactionType.INBOUND_RETURN, null, false);
  }

  /*
   *  Get method for html views for returnin type entry.
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/returnin")
  public Response getReturnInHtml(){
    ResponseBuilder responseBuilder = Response.ok();

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganizationAndType(
        organizationUniqueShortName, PersistantEntry.TransactionType.INBOUND_RETURN, null, false, count);



    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryReturnInListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryReturnInList.jsp");

    Viewable view = new Viewable("/template/template.jsp", entries);


    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  /*
   * General json post for inbound return type entry (i.e. goods return after sale..).
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/returnin")
  public Response postForReturnIn(PersistantEntry entry) {
    entry.setType(PersistantEntry.TransactionType.INBOUND_RETURN);

    return generalPost(entry);
  }

  /*
   * General json post for inbound return type entry (i.e. goods return after sale..).
   */
  @POST
  @Path("/returnin")
  public Response postHtmlForReturnIn(@HeaderParam("Content-type") String contentType, String message) {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);

    if (StringUtils.isBlank(message)) {
      responseBuilder = Response.status(Status.BAD_REQUEST);
      return responseBuilder.build();
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
      PersistantEntry entry = getObjectFromContent(message);


      Services.getInstance().getEntryService().save(entry);

    }
    return responseBuilder.build();
  }

  /*
   * Get method for atom xml for transferin type entry
   */
  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/transferin")
  public Response getTransferIn(){
    return get(organizationUniqueShortName, PersistantEntry.TransactionType.INBOUND_WAREHOUSE_RECIEVE, null, false);
  }

  /*
   *  Get method for html views for transferin type entry.
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/transferin")
  public Response getTransferInHtml(){
    ResponseBuilder responseBuilder = Response.ok();

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganizationAndType(
        organizationUniqueShortName, PersistantEntry.TransactionType.INBOUND_WAREHOUSE_RECIEVE, null, false, count);



    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryTransferInListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryTransferInList.jsp");

    Viewable view = new Viewable("/template/template.jsp", entries);


    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  /*
   * General json post for inbound transfer type entry. i.e. accepts goods from other store
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/transferin")
  public Response postForTransfer(PersistantEntry entry) {
    entry.setType(PersistantEntry.TransactionType.INBOUND_WAREHOUSE_RECIEVE);

    return generalPost(entry);
  }

  /*
   * General html post for inbound transfer type entry. i.e. accepts goods from other store.
   */
  @POST
  @Path("/transferin")
  public Response postHtmlForTransfer(@HeaderParam("Content-type") String contentType, String message) {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);

    if (StringUtils.isBlank(message)) {
      responseBuilder = Response.status(Status.BAD_REQUEST);
      return responseBuilder.build();
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
      PersistantEntry entry = getObjectFromContent(message);


      Services.getInstance().getEntryService().save(entry);

    }
    return responseBuilder.build();
  }

  /*
   * Get method for atom xml for sale type entry
   */
  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/sale")
  public Response getSale(){
    return get(organizationUniqueShortName, PersistantEntry.TransactionType.OUTBOUND_SALE, null, false);
  }

  /*
   *  Get method for html views for sale type entry.
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/sale")
  public Response getSaleHtml(){
    ResponseBuilder responseBuilder = Response.ok();

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganizationAndType(
        organizationUniqueShortName, PersistantEntry.TransactionType.OUTBOUND_SALE, null, false, count);



    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entrySaleListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entrySaleList.jsp");

    Viewable view = new Viewable("/template/template.jsp", entries);


    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  /*
   * General json post for sale type entry i.e. outbound entry.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/sale")
  public Response postForSale(PersistantEntry entry) {
    entry.setType(PersistantEntry.TransactionType.OUTBOUND_SALE);

    return generalPost(entry);
  }

  /*
   * General html post for sale type entry i.e. outbound entry.
   */
  @POST
  @Path("/sale")
  public Response postHtmlForSale(@HeaderParam("Content-type") String contentType, String message) {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);

    if (StringUtils.isBlank(message)) {
      responseBuilder = Response.status(Status.BAD_REQUEST);
      return responseBuilder.build();
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
      PersistantEntry entry = getObjectFromContent(message);


      Services.getInstance().getEntryService().save(entry);

    }
    return responseBuilder.build();
  }

  /*
   * Get method for atom xml for returnout type entry
   */
  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/returnout")
  public Response getReturnOut(){
    return get(organizationUniqueShortName, PersistantEntry.TransactionType.OUTBOUND_RETURN, null, false);
  }

  /*
   *  Get method for html views for returnout type entry.
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/returnout")
  public Response getReturnOutHtml(){
    ResponseBuilder responseBuilder = Response.ok();

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganizationAndType(
        organizationUniqueShortName, PersistantEntry.TransactionType.OUTBOUND_RETURN, null, false, count);



    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryReturnOutListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryReturnOutList.jsp");

    Viewable view = new Viewable("/template/template.jsp", entries);


    responseBuilder.entity(view);
    return responseBuilder.build();
  }


  /*
   * General json post outbound return type entry, i.e. return goods from store due to damage or other causes.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/returnout")
  public Response postForReturnOut(PersistantEntry entry) {

    entry.setType(PersistantEntry.TransactionType.OUTBOUND_RETURN);
    return generalPost(entry);
  }

  /*
   * General json post for outbound return type entry, i.e. return goods from store due to damage or other causes.
   */
  @POST
  @Path("/returnout")
  public Response postHtmlForReturnOut(@HeaderParam("Content-type") String contentType, String message) {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);

    if (StringUtils.isBlank(message)) {
      responseBuilder = Response.status(Status.BAD_REQUEST);
      return responseBuilder.build();
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
        logger.error(ex.getMessage());
      }
    }
    else {
      contentType = contentType;
      isHtmlPost = false;
    }

    if (isHtmlPost) {
      PersistantEntry entry = getObjectFromContent(message);


      Services.getInstance().getEntryService().save(entry);

    }
    return responseBuilder.build();
  }

  /*
   * Get method for atom xml for trasnferout type entry
   */
  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/transferout")
  public Response getTransferOut(){
    return get(organizationUniqueShortName, PersistantEntry.TransactionType.OUTBOUND_WAREHOUSE_TRANSFER, null, false);
  }

  /*
   *  Get method for html views for transferout type entry.
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/transferout")
  public Response getTransferOutHtml(){
    ResponseBuilder responseBuilder = Response.ok();

    Collection<PersistantEntry> entries = Services.getInstance().getEntryService().getByOrganizationAndType(
        organizationUniqueShortName, PersistantEntry.TransactionType.OUTBOUND_WAREHOUSE_TRANSFER, null, false, count);



    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryTransferOutListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationEntriesResource/entryTransferOutList.jsp");

    Viewable view = new Viewable("/template/template.jsp", entries);


    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  /*
   * General json post for outbound transfer type entry. i.e. send goods to other store
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/transferout")
  public Response postForTransferOut(PersistantEntry entry) {
    entry.setType(PersistantEntry.TransactionType.OUTBOUND_WAREHOUSE_TRANSFER);

    return generalPost(entry);
  }

  /*
   * General html post for outbound transfer type entry. i.e. send goods to other store
   */
  @POST
  @Path("/transferout")
  public Response postHtmlForTransferOut(@HeaderParam("Content-type") String contentType, String message) {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);

    if (StringUtils.isBlank(message)) {
      responseBuilder = Response.status(Status.BAD_REQUEST);
      return responseBuilder.build();
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
      PersistantEntry entry = getObjectFromContent(message);


      Services.getInstance().getEntryService().save(entry);

    }
    return responseBuilder.build();
  }

  private Response generalPost(PersistantEntry entry) {
    ResponseBuilder responseBuilder;

    try {
      Services.getInstance().getEntryService().save(entry);
      responseBuilder = Response.status(Status.CREATED);
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      logger.error(ex.getMessage());
    }
    return responseBuilder.build();
  }

//  private Response generalHtmlPost(String contentType, String message){
//
//  }
  private PersistantEntry getObjectFromContent(String message) {

    Map<String, String> keyValueMap = new HashMap<String, String>();

    String[] keyValuePairs = message.split("&");

    for (int i = 0; i < keyValuePairs.length; i++) {

      String[] keyValuePair = keyValuePairs[i].split("=");
      keyValueMap.put(keyValuePair[0], keyValuePair[1]);
    }

    PersistantEntry entry = new PersistantEntry();

    if(keyValueMap.get("entryDate") != null){
      try{
        entry.setEntryDate(new SimpleDateFormat("dd:MM:yyyy").parse(keyValueMap.get("entryDate")));
      }catch(Exception ex){
        logger.error(ex.getMessage());
      }
    }else{
      entry.setEntryDate(new Date());
    }

    if(keyValueMap.get("expiryDate") != null){
      try{
        entry.setExpiryDate(new SimpleDateFormat("dd:MM:yyyy").parse(keyValueMap.get("expiryDate")));
      }catch(Exception ex){
        logger.error(ex.getMessage());
      }
    }else{
      entry.setExpiryDate(new Date());
    }

    if(keyValueMap.get("productId") != null){
      entry.setProductId(keyValueMap.get("productId"));
    }

    if(keyValueMap.get("storeId") != null){
      entry.setStoreId(keyValueMap.get("storeId"));
    }
    if(keyValueMap.get("type") != null){
      entry.setType(getTransactionType(keyValueMap.get("type")));
    }
    
    return new PersistantEntry();
  }

  private PersistantEntry.TransactionType getTransactionType(String string){
    if(string.toUpperCase().equals("INBOUND_PURCHASE")){
      return PersistantEntry.TransactionType.INBOUND_PURCHASE;
    }
    if(string.toUpperCase().equals("INBOUND_RETURN")){
      return PersistantEntry.TransactionType.INBOUND_RETURN;
    }
    if(string.toUpperCase().equals("INBOUND_WAREHOUSE_RECIEVE")){
      return PersistantEntry.TransactionType.INBOUND_WAREHOUSE_RECIEVE;
    }
    if(string.toUpperCase().equals("OUTBOUND_SALE")){
      return PersistantEntry.TransactionType.OUTBOUND_SALE;
    }
    if(string.toUpperCase().equals("OUTBOUND_RETURN")){
      return PersistantEntry.TransactionType.OUTBOUND_RETURN;
    }
    if(string.toUpperCase().equals("OUTBOUND_WAREHOUSE_TRANSFER")){
      return PersistantEntry.TransactionType.OUTBOUND_WAREHOUSE_TRANSFER;
    }
    return PersistantEntry.TransactionType.INBOUND_PURCHASE;
  }
}
