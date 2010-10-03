/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.guicebinder;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.smartitengineering.dao.common.CommonDao;
import com.smartitengineering.dao.common.CommonReadDao;
import com.smartitengineering.dao.common.CommonWriteDao;
import com.smartitengineering.dao.impl.hbase.spi.AsyncExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.DomainIdInstanceProvider;
import com.smartitengineering.dao.impl.hbase.spi.MergeService;
import com.smartitengineering.dao.impl.hbase.spi.ObjectRowConverter;
import com.smartitengineering.dao.impl.hbase.spi.SchemaInfoProvider;
import com.smartitengineering.dao.impl.hbase.spi.impl.DiffBasedMergeService;
import com.smartitengineering.dao.impl.hbase.spi.impl.MixedExecutorServiceImpl;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderBaseConfig;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderImpl;
import com.smartitengineering.smartpos.inventory.api.Entry;
import com.smartitengineering.smartpos.inventory.api.converter.EntryRowConverter;
import com.smartitengineering.smartpos.inventory.api.domainid.EntryId;
import com.smartitengineering.smartpos.inventory.impl.domainidinstanceprovider.DomainIdInstanceProviderImpl;
import com.smartitengineering.smartpos.inventory.impl.guice.EntrySchemaBaseConfigProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author russel
 */
public class EntryServiceModule extends AbstractModule{

  @Override
  protected void configure() {
    bind(AsyncExecutorService.class).to(MixedExecutorServiceImpl.class).in(Singleton.class);
    bind(ExecutorService.class).toInstance(Executors.newCachedThreadPool());
    bind(Integer.class).annotatedWith(Names.named("maxRows")).toInstance(new Integer(50));
    bind(Long.class).annotatedWith(Names.named("waitTime")).toInstance(new Long(10));
    bind(TimeUnit.class).annotatedWith(Names.named("unit")).toInstance(TimeUnit.SECONDS);
    bind(Boolean.class).annotatedWith(Names.named("mergeEnabled")).toInstance(Boolean.TRUE);
    bind(MergeService.class).to(DiffBasedMergeService.class).in(Singleton.class);


    /*
     * Start injection specific to common dao of workspace
     */    
    bind( new TypeLiteral<ObjectRowConverter<Entry>>(){}).to(EntryRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<Entry, EntryId>>(){}).to(new TypeLiteral<CommonDao<Entry, EntryId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<Entry>>(){}).to(new TypeLiteral<CommonDao<Entry, EntryId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<Entry, EntryId>>(){}).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<Entry, EntryId>>(){}).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<Entry, EntryId>> wTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<Entry, EntryId>>() {};
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<Entry>>() {
    }).toProvider(EntrySchemaBaseConfigProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<EntryId>>() {}).toInstance(EntryId.class);
    bind(new TypeLiteral<SchemaInfoProvider<Entry, EntryId>>() {}).to(wTypeLiteral).in(Singleton.class);
    bind(DomainIdInstanceProvider.class).to(DomainIdInstanceProviderImpl.class).in(Scopes.SINGLETON);
  }

}
