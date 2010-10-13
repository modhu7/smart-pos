/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.pos.inventory.resource;

import com.smartitengineering.smartpos.inventory.api.factory.Services;
import com.smartitengineering.smartpos.inventory.api.PersistantProduct;
import com.sun.jersey.api.view.Viewable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriBuilderException;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
@Path("/orgs/sn/{uniqueShortName}/inv/prods/code/{productCode}")
public class OrganizationProductResource extends AbstractResource{

  protected final Logger logger = LoggerFactory.getLogger(OrganizationProductResource.class);

  private PersistantProduct product;
  static final UriBuilder PRODUCT_URI_BUILDER = UriBuilder.fromResource(OrganizationProductResource.class);
  static final UriBuilder PRODUCT_CONTENT_URI_BUILDER;
  @Context
  private HttpServletRequest servletRequest;

  static {
    PRODUCT_CONTENT_URI_BUILDER = PRODUCT_URI_BUILDER.clone();
    try {
      PRODUCT_CONTENT_URI_BUILDER.path(OrganizationProductResource.class.getMethod("getProduct"));
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new InstantiationError();

    }
  }
  @PathParam("organizationShortName")
  private String organizationUniqueShortName;
  @PathParam("productCode")
  private String productCode;

  public OrganizationProductResource(@PathParam("organizationShortName") String organizationShortName, @PathParam(
      "productCode") String productCode) {
    product = Services.getInstance().getProductService().getByProductCodeAndOrganization(organizationShortName, productCode);

  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    Feed productFeed = getProductFeed();
    ResponseBuilder responseBuilder = Response.ok(productFeed);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/content")
  public Response getProduct() {
    ResponseBuilder responseBuilder = Response.ok(product);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getHtml() {
    ResponseBuilder responseBuilder = Response.ok();

    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationProductsResource/productDetailsHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationProductsResource/productDetails.jsp");
    Viewable view = new Viewable("/template/template.jsp", product);

    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @PUT
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(PersistantProduct product) {

    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);
    try {

      if (product.getOrganizationId() == null) {
        throw new Exception("No organization found");
      }
      
      Services.getInstance().getProductService().update(product);
      responseBuilder = Response.ok(getProductFeed());
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return responseBuilder.build();
  }

  private Feed getProductFeed() throws UriBuilderException, IllegalArgumentException {
    Feed productFeed = getFeed(product.getId().getId(), new Date());
    productFeed.setTitle(product.getName());

    // add a self link
    productFeed.addLink(getSelfLink());

    // add a edit link
    Link editLink = abderaFactory.newLink();
    editLink.setHref(uriInfo.getRequestUri().toString());
    editLink.setRel(Link.REL_EDIT);
    editLink.setMimeType(MediaType.APPLICATION_JSON);
    productFeed.addLink(editLink);

    // add a alternate link
    Link altLink = abderaFactory.newLink();
    altLink.setHref(PRODUCT_CONTENT_URI_BUILDER.clone().build(organizationUniqueShortName,
                                                           product.getId()).toString());
    altLink.setRel(Link.REL_ALTERNATE);
    altLink.setMimeType(MediaType.APPLICATION_JSON);
    productFeed.addLink(altLink);

    return productFeed;
  }

  @DELETE
  public Response delete() {
    Services.getInstance().getProductService().delete(product);
    ResponseBuilder responseBuilder = Response.ok();
    return responseBuilder.build();
  }

  @POST
  @Path("/delete")
  public Response deletePost() {
    Services.getInstance().getProductService().delete(product);
    ResponseBuilder responseBuilder = Response.ok();
    return responseBuilder.build();
  }

  @POST
  @Path("/update")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response updatePost(@HeaderParam("Content-type") String contentType, String message) {
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
      PersistantProduct newProduct = getProductFromContent(message);
      try {
        Services.getInstance().getProductService().update(newProduct);
        responseBuilder = Response.ok(getProductFeed());
      }
      catch (Exception ex) {
        responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      }
    }
    return responseBuilder.build();
  }

  private PersistantProduct getProductFromContent(String message) {

    Map<String, String> keyValueMap = new HashMap<String, String>();

    String[] keyValuePairs = message.split("&");

    for (int i = 0; i < keyValuePairs.length; i++) {

      String[] keyValuePair = keyValuePairs[i].split("=");
      if (keyValuePair.length > 1) {
        keyValueMap.put(keyValuePair[0], keyValuePair[1]);
      }
    }

    PersistantProduct newProduct = new PersistantProduct();

    // to do...

    return newProduct;
  }

}
