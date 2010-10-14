/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.impl.service;

import com.google.inject.Inject;
import com.smartitengineering.dao.common.CommonReadDao;
import com.smartitengineering.dao.common.CommonWriteDao;
import com.smartitengineering.smartpos.inventory.api.PersistantEntry;
import com.smartitengineering.smartpos.inventory.api.domainid.EntryId;
import com.smartitengineering.smartpos.inventory.api.service.EntryService;

/**
 *
 * @author russel
 */
public abstract class AbstractEntryService implements EntryService {

  @Inject
  protected CommonReadDao<PersistantEntry, EntryId> commonReadDao;
  @Inject
  protected CommonWriteDao<PersistantEntry> commonWriteDao;

  public PersistantEntry getById(EntryId id) {
    if (id == null) {
      return null;
    }
    return commonReadDao.getById(id);
  }
}
