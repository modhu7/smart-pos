/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.Address;
import com.smartitengineering.smartpos.inventory.client.api.domain.GeoLocation;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 *
 * @author russel
 */
public class AddressImpl implements Address{
  private String streetAddress;
  private String city;
  private String state;
  private String country;
  private String zip;
  private GeoLocation geoLocation;

  @Override
  public String getCity() {
    return city;
  }

  @Override
  public void setCity(String city) {
    this.city = city;
  }

  @Override
  public String getCountry() {
    return country;
  }

  @Override
  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String getState() {
    return state;
  }

  @Override
  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String getStreetAddress() {
    return streetAddress;
  }

  @Override
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  @Override
  public String getZip() {
    return zip;
  }

  @Override
  public void setZip(String zip) {
    this.zip = zip;
  }

  @Override
  public GeoLocation getGeoLocation() {
    return geoLocation;
  }

  @Override
  @JsonDeserialize(as = GeoLocationImpl.class)
  public void setGeoLocation(GeoLocation geoLocation) {
    this.geoLocation = geoLocation;
  }
}
