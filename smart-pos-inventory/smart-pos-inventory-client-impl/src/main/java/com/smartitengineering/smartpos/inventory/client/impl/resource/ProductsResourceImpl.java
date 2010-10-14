/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.resource;

import com.smartitengineering.smartpos.inventory.client.api.domain.Product;
import com.smartitengineering.smartpos.inventory.client.api.resource.ProductResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.ProductsResource;
import com.smartitengineering.smartpos.inventory.client.impl.domain.ProductImpl;
import com.smartitengineering.util.rest.atom.AbstractFeedClientResource;
import com.smartitengineering.util.rest.atom.AtomClientUtil;
import com.smartitengineering.util.rest.client.ClientUtil;
import com.smartitengineering.util.rest.client.Resource;
import com.smartitengineering.util.rest.client.ResourceLink;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
/**
 *
 * @author saumitra
 */
public class ProductsResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements ProductsResource{

  private static final String REL_PRODUCT = "product";
  private static final String REL_ALT = "alternate";

  public ProductsResourceImpl(Resource referrer, ResourceLink pageLink) {
    super(referrer, pageLink);
  }

  @Override
  protected void processClientConfig(ClientConfig cc) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected Resource<? extends Feed> instantiatePageableResource(ResourceLink rl) {
    return new ProductsResourceImpl(this, rl);
  }
  
  @Override
  public ProductResource create(Product product) {
   ClientResponse response = post(MediaType.APPLICATION_JSON, product, ClientResponse.Status.CREATED);
   if(response.getLocation() == null)
    logger.info("response.getLocation() is null");
    final ResourceLink productLink = ClientUtil.createResourceLink(REL_PRODUCT, response.getLocation(),
                                                               MediaType.APPLICATION_ATOM_XML);
    logger.info(productLink.getUri().toString());
    return new ProductResourceImpl(this,productLink);
  }

  @Override
  public List<ProductResource> getOrganizationProductResource() {

    List<ProductResource>   productResources = new ArrayList<ProductResource>();
    for(Entry entry : getLastReadStateOfEntity().getEntries()){
      productResources.add(new ProductResourceImpl(this, AtomClientUtil.convertFromAtomLinkToResourceLink(entry.getLink(REL_ALT))));
    }
  return productResources;
  }

}
