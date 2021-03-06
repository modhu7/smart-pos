/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.pos.inventory.resource;

import com.smartitengineering.pos.inventory.adapter.ProductAdapterHelper;
import com.smartitengineering.smartpos.inventory.api.factory.Services;
import com.smartitengineering.smartpos.admin.resource.RootResource;
import com.smartitengineering.smartpos.inventory.api.PersistantProduct;
import com.smartitengineering.smartpos.inventory.api.PersistantSupplier;
import com.smartitengineering.smartpos.inventory.api.PersistantUnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.Product;
import com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.domainid.ProductId;
import com.smartitengineering.smartpos.inventory.api.domainid.SupplierId;
import com.smartitengineering.smartpos.inventory.api.domainid.UomId;
import com.smartitengineering.util.bean.adapter.GenericAdapterImpl;
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

/**
 *
 * @author russel
 */
@Path("/orgs/sn/{uniqueShortName}/inv/prods")
public class OrganizationProductsResource extends AbstractResource {

  protected final Logger logger = LoggerFactory.getLogger(OrganizationProductsResource.class);
  static final UriBuilder ORGANIZATION_PRODUCTS_URI_BUILDER;
  static final UriBuilder ORGANIZATION_PRODUCTS_BEFORE_PRODUCTCODE_URI_BUILDER;
  static final UriBuilder ORGANIZATION_PRODUCTS_AFTER_PRODUCTCODE_URI_BUILDER;  

  static {
    ORGANIZATION_PRODUCTS_URI_BUILDER = UriBuilder.fromResource(OrganizationProductsResource.class);

    ORGANIZATION_PRODUCTS_AFTER_PRODUCTCODE_URI_BUILDER = UriBuilder.fromResource(OrganizationProductsResource.class);
    try {
      ORGANIZATION_PRODUCTS_AFTER_PRODUCTCODE_URI_BUILDER.path(
          OrganizationProductsResource.class.getMethod("getAfter",
                                                       String.class));
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    ORGANIZATION_PRODUCTS_BEFORE_PRODUCTCODE_URI_BUILDER = UriBuilder.fromResource(OrganizationProductsResource.class);
    try {
      ORGANIZATION_PRODUCTS_BEFORE_PRODUCTCODE_URI_BUILDER.path(
          OrganizationProductsResource.class.getMethod("getBefore",
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
  private GenericAdapterImpl<Product, PersistantProduct> adapter;

  public OrganizationProductsResource() {    
    adapter = new GenericAdapterImpl();
    adapter.setHelper(new ProductAdapterHelper());
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getHtml() {
    ResponseBuilder responseBuilder = Response.ok();


    Collection<PersistantProduct> products = Services.getInstance().getProductService().getByOrganization(
        organizationUniqueShortName, null, false, count);



    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationProductsResource/productListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationProductsResource/productList.jsp");

    servletRequest.getAttribute("templateContent");

    Viewable view = new Viewable("/template/template.jsp", products);


    responseBuilder.entity(view);
    return responseBuilder.build();

  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/frags")
  public Response getHtmlFrags() {
    ResponseBuilder responseBuilder = Response.ok();

    Collection<PersistantProduct> products = Services.getInstance().getProductService().getByOrganization(
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
    Collection<PersistantProduct> products = Services.getInstance().getProductService().getByOrganization(
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

    Collection<PersistantProduct> products = Services.getInstance().getProductService().getByOrganization(
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

    Collection<PersistantProduct> products = Services.getInstance().getProductService().getByOrganization(
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

    Collection<PersistantProduct> products = Services.getInstance().getProductService().getByOrganization(
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

    Collection<PersistantProduct> products = Services.getInstance().getProductService().getByOrganization(
        organizationUniqueShortName, userName, isBefore, count);

    if (products != null && !products.isEmpty()) {

      MultivaluedMap<String, String> queryParam = uriInfo.getQueryParameters();
      List<PersistantProduct> productList = new ArrayList<PersistantProduct>(products);

      // uri builder for next and previous organizations according to count
      final UriBuilder nextUri = ORGANIZATION_PRODUCTS_AFTER_PRODUCTCODE_URI_BUILDER.clone();
      final UriBuilder previousUri = ORGANIZATION_PRODUCTS_BEFORE_PRODUCTCODE_URI_BUILDER.clone();

      // link to the next organizations based on count
      Link nextLink = abderaFactory.newLink();
      nextLink.setRel(Link.REL_NEXT);

      PersistantProduct lastProduct = productList.get(productList.size() - 1);


      for (String key : queryParam.keySet()) {
        final Object[] values = queryParam.get(key).toArray();
        nextUri.queryParam(key, values);
        previousUri.queryParam(key, values);
      }
      nextLink.setHref(nextUri.build(organizationUniqueShortName, lastProduct.getId().getId()).toString());


      atomFeed.addLink(nextLink);

      /* link to the previous organizations based on count */
      Link prevLink = abderaFactory.newLink();
      prevLink.setRel(Link.REL_PREVIOUS);

      PersistantProduct firstProduct = productList.get(0);

      prevLink.setHref(
          previousUri.build(organizationUniqueShortName, firstProduct.getId().getId()).toString());
      atomFeed.addLink(prevLink);

      //for (User user : users) {
      for (PersistantProduct product : products) {

        Entry productEntry = abderaFactory.newEntry();

        productEntry.setId(product.getId().getId());
        productEntry.setTitle(product.getName());
        productEntry.setSummary(product.getName());


        // setting link to the each individual user
        Link productLink = abderaFactory.newLink();
        productLink.setHref(OrganizationProductResource.PRODUCT_URI_BUILDER.clone().build(organizationUniqueShortName, product.
            getId().getId()).toString());
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
    ResponseBuilder responseBuilder = Response.status(Status.OK);

    product.setOrgUniqueShortName(organizationUniqueShortName);    
    PersistantProduct persistantProduct = adapter.convert(product);    

    try {
      basicPost(persistantProduct);
      responseBuilder = Response.status(Status.CREATED);
      responseBuilder.location(uriInfo.getBaseUriBuilder().path(OrganizationProductResource.PRODUCT_URI_BUILDER.clone().build(organizationUniqueShortName, persistantProduct.getId().getId()).toString()).build());
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return responseBuilder.build();
  }

  private PersistantProduct getObjectFromContent(String message) {

    Map<String, String> keyValueMap = new HashMap<String, String>();

    String[] keyValuePairs = message.split("&");

    for (int i = 0; i < keyValuePairs.length; i++) {

      String[] keyValuePair = keyValuePairs[i].split("=");
      keyValueMap.put(keyValuePair[0], keyValuePair[1]);
    }

    final PersistantProduct persistantProduct = new PersistantProduct();

    if(keyValueMap.get("id") != null){
      ProductId productId = new PersistantProduct.ProductIdImpl(organizationUniqueShortName, keyValueMap.get("id"));
      persistantProduct.setId(productId);
    }
    if(keyValueMap.get("name") != null){
      persistantProduct.setName(keyValueMap.get("name"));
    }
    if(keyValueMap.get("description")!= null){
      persistantProduct.setDescription(keyValueMap.get("description"));
    }
    if(keyValueMap.get("skuId")!= null){
      persistantProduct.setDescription(keyValueMap.get("skuId"));
    }   

    return persistantProduct;
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
      PersistantProduct product = getObjectFromContent(message);
      basicPost(product);

    }
    return responseBuilder.build();
  }

  public void basicPost(PersistantProduct product) {
    UomId uomId = new PersistantUnitOfMeasurement.UomIdImpl();
    uomId.setId(product.getSkuId());
    PersistantUnitOfMeasurement persistantUom = Services.getInstance().getUomService().getById(uomId);
    logger.info("uom:"+persistantUom);
    product.setSkuName(persistantUom.getLongName());
    Services.getInstance().getProductService().save(product);
  }
}
