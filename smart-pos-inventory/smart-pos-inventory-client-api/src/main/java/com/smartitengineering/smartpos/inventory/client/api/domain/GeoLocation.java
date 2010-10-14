/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.domain;

/**
 *
 * @author russel
 */
public interface GeoLocation {

  public void setLatitude(Double latitude);

  public Double getLatitude();

  public void setLongitude(Double longitude);

  public Double getLongitude();
}
