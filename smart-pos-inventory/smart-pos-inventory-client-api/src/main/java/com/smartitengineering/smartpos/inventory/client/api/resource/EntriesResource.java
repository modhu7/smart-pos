/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.resource;

import com.smartitengineering.smartpos.inventory.client.api.domain.Entry;
import java.util.List;

/**
 *
 * @author saumitra
 */
public interface EntriesResource {

  public EntryResource create(Entry entry);

  public List<EntryResource> getEntryResources();

  public EntryResource getEntryResource();

}
