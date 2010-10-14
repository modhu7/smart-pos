/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.GeoLocation;

/**
 *
 * @author russel
 */
public class GeoLocationImpl implements GeoLocation{

  private Double longitude;

  private Double latitude;

  @Override
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  @Override
  public Double getLatitude() {
    return latitude;
  }

  @Override
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  @Override
  public Double getLongitude() {
    return longitude;
  }

}
