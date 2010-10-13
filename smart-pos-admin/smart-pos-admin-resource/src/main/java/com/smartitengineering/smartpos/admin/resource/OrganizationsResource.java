/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.admin.resource;

import com.smartitengineering.pos.admin.api.domain.id.OrganizationId;
import com.smartitengineering.pos.admin.api.factory.Services;
import com.smartitengineering.pos.admin.impl.domain.id.OrganizationIdImpl;
import com.smartitengineering.smartpos.admin.api.Address;
import com.smartitengineering.smartpos.admin.api.Organization;
import com.smartitengineering.user.client.api.LoginResource;
import com.smartitengineering.user.client.impl.RootResourceImpl;
import com.sun.jersey.api.view.Viewable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
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
import org.apache.abdera.Abdera;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author russel
 */
@Path("/orgs")
public class OrganizationsResource extends AbstractResource {

  static final UriBuilder ORGANIZATION_URI_BUILDER;
  static com.smartitengineering.user.client.api.RootResource rootResource = RootResourceImpl.getInstance(
      "smartadmin@smart-user", "02040250204039");
  @Context
  private HttpServletRequest servletRequest;

  static {
    ORGANIZATION_URI_BUILDER = UriBuilder.fromResource(OrganizationsResource.class);
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    return get(null, true);
  }


  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getHtml() {
    ResponseBuilder responseBuilder = Response.ok();

    Collection<Organization> organizations = Services.getInstance().getOrganizationService().getAll();

    com.smartitengineering.user.client.api.LoginResource loginResource = rootResource.getLoginResource();

    com.smartitengineering.user.client.api.OrganizationsResource organizationsResource = loginResource.getOrganizationsResource();

    List<com.smartitengineering.user.client.api.OrganizationResource> organizationResourceList = organizationsResource.
        getOrganizationResources();


    for (com.smartitengineering.user.client.api.OrganizationResource organizationResource : organizationResourceList) {
      organizationResource.getOrganization();
    }

    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/user/ws/resources/OrganizationsResource/OrganizationHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/user/ws/resources/OrganizationsResource/organizationList.jsp");
    Viewable view = new Viewable("/template/template.jsp");

    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get(String organizationName, boolean isBefore) {
   
    ResponseBuilder responseBuilder = Response.ok();

    // create a new atom feed
    Feed atomFeed = Abdera.getNewFactory().newFeed();

    // create a link to parent resource, in this case now it is linked to root resource
    Link parentResourceLink = Abdera.getNewFactory().newLink();
    parentResourceLink.setHref(UriBuilder.fromResource(RootResource.class).build().toString());
    parentResourceLink.setRel("root");
    atomFeed.addLink(parentResourceLink);

    Collection<Organization> organizations = Services.getInstance().getOrganizationService().getAll();

    if (organizations != null && !organizations.isEmpty()) {

      MultivaluedMap<String, String> queryParam = uriInfo.getQueryParameters();
      List<Organization> organizationList = new ArrayList<Organization>(organizations);

      // add entry of individual organization
      for (Organization organization : organizations) {
        Entry organizationEntry = abderaFactory.newEntry();

        organizationEntry.setId(organization.getId().getId());
        organizationEntry.setTitle(organization.getId().getId());
//        organizationEntry.setUpdated(organization.getLastModifiedDate());

        /* setting link to the individual organization resource*/

        Link organizationLink = abderaFactory.newLink();
        organizationLink.setHref(OrganizationResource.ORGANIZATION_URI_BUILDER.clone().build(organization.getId().getId()).toString());
        organizationLink.setRel(Link.REL_ALTERNATE);
        organizationLink.setMimeType(MediaType.APPLICATION_ATOM_XML);
        organizationEntry.addLink(organizationLink);

        atomFeed.addEntry(organizationEntry);
      }
    }
    responseBuilder.entity(atomFeed);
    return responseBuilder.build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(Organization organization) {
    ResponseBuilder responseBuilder;
    try {      
      Services.getInstance().getOrganizationService().save(organization);
      responseBuilder = Response.status(Response.Status.CREATED);
      responseBuilder.location(OrganizationResource.ORGANIZATION_URI_BUILDER.clone().build(organization.getId().getId()));

    }
    catch (Exception ex) {
      responseBuilder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return responseBuilder.build();
  }

  private Organization getObjectFromContent(String message) {

    Map<String, String> keyValueMap = new HashMap<String, String>();

    String[] keyValuePairs = message.split("&");

    for (int i = 0; i < keyValuePairs.length; i++) {

      String[] keyValuePair = keyValuePairs[i].split("=");
      keyValueMap.put(keyValuePair[0], keyValuePair[1]);
    }    
    

    Organization newOrganization = new Organization();
    OrganizationId organizationId = new OrganizationIdImpl();

    if (keyValueMap.get("uniqueShortName") != null) {
      organizationId.setId(keyValueMap.get("uniqueShortName"));
      newOrganization.setId(organizationId);
    }
    if (keyValueMap.get("uri") != null) {
      newOrganization.setRelativeUri(keyValueMap.get("uri") );
    }

    return newOrganization;
  }

  @POST
  public Response post(@HeaderParam("Content-type") String contentType, String message) {
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
      Organization newOrganization = getObjectFromContent(message);
      Services.getInstance().getOrganizationService().save(newOrganization);
    }
    return responseBuilder.build();
  }
}
