/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.resource;

import com.smartitengineering.smartpos.admin.resource.RootResource;
import com.smartitengineering.smartpos.inventory.api.Product;
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

/**
 *
 * @author russel
 */
@Path("/orgs/sn/{uniqueShortName}/inv/prds")
public class OrganizationProductsResource extends AbstractResource {

  static final UriBuilder ORGANIZATION_PRODUCTS_URI_BUILDER;
  static final UriBuilder ORGANIZATION_PRODUCTS_BEFORE_PRODUCTCODE_URI_BUILDER;
  static final UriBuilder ORGANIZATION_PRODUCTS_AFTER_PRODUCTCODE_URI_BUILDER;

  public OrganizationProductsResource(@PathParam("uniqueShortName") String organizationUniqueShortName) {

    this.organizationUniqueShortName = organizationUniqueShortName;


  }

  static {
    ORGANIZATION_PRODUCTS_URI_BUILDER = UriBuilder.fromResource(OrganizationProductsResource.class);

    ORGANIZATION_PRODUCTS_AFTER_PRODUCTCODE_URI_BUILDER = UriBuilder.fromResource(OrganizationProductsResource.class);
    try {
      ORGANIZATION_PRODUCTS_AFTER_PRODUCTCODE_URI_BUILDER.path(OrganizationProductsResource.class.getMethod("getAfter",
                                                                                                     String.class));
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    ORGANIZATION_PRODUCTS_BEFORE_PRODUCTCODE_URI_BUILDER = UriBuilder.fromResource(OrganizationProductsResource.class);
    try {
      ORGANIZATION_PRODUCTS_BEFORE_PRODUCTCODE_URI_BUILDER.path(OrganizationProductsResource.class.getMethod("getBefore",
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


    Collection<Product> products = Services.getInstance().getProductService().getByOrganization(
        organizationUniqueShortName, null, false, count);



    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationProductsResource/productListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationProductsResource/productList.jsp");

    Viewable view = new Viewable("/template/template.jsp", products);


    responseBuilder.entity(view);
    return responseBuilder.build();

  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/frags")
  public Response getHtmlFrags() {
    ResponseBuilder responseBuilder = Response.ok();

    Collection<Product> products = Services.getInstance().getProductService().getByOrganization(
        organizationUniqueShortName, null, false, count);


    Viewable view = new Viewable("productFrags.jsp", products, OrganizationProductsResource.class);
    responseBuilder.entity(view);
    return responseBuilder.build();

  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/before/{beforeProductCode}")
  public Response getBefore(@PathParam("beforeProductCode") String beforeProductCode) {
    return get(organizationUniqueShortName, beforeProductCode, true);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/before/{beforeProductCode}")
  public Response getBeforeHtml(@PathParam("beforeProductCode") String beforeProductCode) {
    ResponseBuilder responseBuilder = Response.ok();
//    Collection<User> users = Services.getInstance().getUserService().getUserByOrganization(
//        organizationUniqueShortName, beforeUserName, true, count);
    Collection<Product> products = Services.getInstance().getProductService().getByOrganization(
        organizationUniqueShortName, beforeProductCode, true, count);

    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationProductsResource/productList.jsp");
    Viewable view = new Viewable("/template/template.jsp", products);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/before/{beforeProductCode}/frags")
  public Response getBeforeHtmlFrags(@PathParam("beforeProductCode") String beforeProductCode) {
    ResponseBuilder responseBuilder = Response.ok();
//    Collection<User> users = Services.getInstance().getUserService().getUserByOrganization(
//        organizationUniqueShortName, beforeUserName, true, count);

    Collection<Product> products = Services.getInstance().getProductService().getByOrganization(
        organizationUniqueShortName, beforeProductCode, true, count);

    Viewable view = new Viewable("productFrags.jsp", products);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/after/{afterProductCode}")
  public Response getAfter(@PathParam("afterProductCode") String afterProductCode) {
    return get(organizationUniqueShortName, afterProductCode, false);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/after/{afterProductCode}")
  public Response getAfterHtml(@PathParam("afterProductCode") String afterProductCode) {

    ResponseBuilder responseBuilder = Response.ok();

    Collection<Product> products = Services.getInstance().getProductService().getByOrganization(
        organizationUniqueShortName, afterProductCode, false, count);
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationProductsResource/productList.jsp");
    Viewable view = new Viewable("/template/template.jsp", products);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/after/{afterProductCode}/frags")
  public Response getAfterHtmlFrags(@PathParam("afterProductCode") String afterProductCode) {

    ResponseBuilder responseBuilder = Response.ok();

    Collection<Product> products = Services.getInstance().getProductService().getByOrganization(
        organizationUniqueShortName, afterProductCode, false, count);

    Viewable view = new Viewable("productFrags.jsp", products);
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

    Collection<Product> products = Services.getInstance().getProductService().getByOrganization(
        organizationUniqueShortName, userName, isBefore, count);

    if (products != null && !products.isEmpty()) {

      MultivaluedMap<String, String> queryParam = uriInfo.getQueryParameters();
      List<Product> productList = new ArrayList<Product>(products);

      // uri builder for next and previous organizations according to count
      final UriBuilder nextUri = ORGANIZATION_PRODUCTS_AFTER_PRODUCTCODE_URI_BUILDER.clone();
      final UriBuilder previousUri = ORGANIZATION_PRODUCTS_BEFORE_PRODUCTCODE_URI_BUILDER.clone();

      // link to the next organizations based on count
      Link nextLink = abderaFactory.newLink();
      nextLink.setRel(Link.REL_NEXT);
      //User lastUser = userList.get(userList.size() - 1);
      Product lastProduct = productList.get(productList.size() - 1);


      for (String key : queryParam.keySet()) {
        final Object[] values = queryParam.get(key).toArray();
        nextUri.queryParam(key, values);
        previousUri.queryParam(key, values);
      }
      nextLink.setHref(nextUri.build(organizationUniqueShortName, lastProduct.getProductCode()).toString());


      atomFeed.addLink(nextLink);

      /* link to the previous organizations based on count */
      Link prevLink = abderaFactory.newLink();
      prevLink.setRel(Link.REL_PREVIOUS);
      //User firstUser = userList.get(0);
      Product firstProduct = productList.get(0);

      prevLink.setHref(
          previousUri.build(organizationUniqueShortName, firstProduct.getProductCode()).toString());
      atomFeed.addLink(prevLink);

      //for (User user : users) {
      for (Product product : products) {

        Entry productEntry = abderaFactory.newEntry();

        productEntry.setId(product.getProductCode());
        productEntry.setTitle(product.getName());
        productEntry.setSummary(product.getName());
        

        // setting link to the each individual user
        Link productLink = abderaFactory.newLink();
        productLink.setHref(OrganizationProductResource.PRODUCT_URI_BUILDER.clone().build(organizationUniqueShortName, product.
            getProductCode()).toString());
        productLink.setRel(Link.REL_ALTERNATE);
        productLink.setMimeType(MediaType.APPLICATION_ATOM_XML);

        productEntry.addLink(productLink);

        atomFeed.addEntry(productEntry);
      }
    }
    responseBuilder.entity(atomFeed);
    return responseBuilder.build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(Product product) {


    ResponseBuilder responseBuilder;

    try {
      if (product.getParentOrganizationID() == null) {
        throw new Exception("No organization found");
      }
      //Services.getInstance().getOrganizationService().populateOrganization(user);
      Services.getInstance().getProductService().save(product);
      responseBuilder = Response.status(Status.CREATED);
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return responseBuilder.build();
  }

  private Product getObjectFromContent(String message) {

    return new Product();
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
      Product product = getObjectFromContent(message);


      Services.getInstance().getProductService().save(product);

    }
    return responseBuilder.build();
  }
}
