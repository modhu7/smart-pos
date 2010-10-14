/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.client.api.domain;

import org.codehaus.jackson.map.annotate.JsonDeserialize;


/**
 *
 * @author russel
 */
//@JsonDeserialize(as = AddressImpl.class)
public interface Address {

  public String getCity();

  public void setCity(String city);

  public String getCountry();

  public void setCountry(String country);

  public String getState();

  public void setState(String state);

  public String getStreetAddress();

  public void setStreetAddress(String streetAddress);

  public String getZip();

  public void setZip(String zip);

  public GeoLocation getGeoLocation();

  public void setGeoLocation(GeoLocation geoLocation);
}
