/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.admin.api;

import com.smartitengineering.domain.AbstractPersistentDTO;

/**
 *
 * @author modhu7
 */
public class GeoLocation extends AbstractPersistentDTO<GeoLocation>{

    private Double longitude;
    private Double latitude;

    public Double getLatitude() {
      if(latitude == null){
        latitude = new Double(0);
      }
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
      if(longitude == null){
        longitude = new Double(0);
      }
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    public boolean isValid() {
        if(longitude==null || latitude== null)
            return false;
        else
            return true;
    }



}
