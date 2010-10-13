/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.pos.admin.binder.guice;

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
import com.smartitengineering.dao.impl.hbase.spi.FilterConfigs;
import com.smartitengineering.dao.impl.hbase.spi.MergeService;
import com.smartitengineering.dao.impl.hbase.spi.ObjectRowConverter;
import com.smartitengineering.dao.impl.hbase.spi.SchemaInfoProvider;
import com.smartitengineering.dao.impl.hbase.spi.impl.DiffBasedMergeService;
import com.smartitengineering.dao.impl.hbase.spi.impl.MixedExecutorServiceImpl;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderBaseConfig;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderImpl;
import com.smartitengineering.pos.admin.api.domain.id.OrganizationId;
import com.smartitengineering.pos.admin.impl.converter.OrganizationRowConverter;
import com.smartitengineering.pos.admin.impl.guice.OrganizationFilterConfigsProvider;
import com.smartitengineering.pos.admin.impl.guice.OrganizationSchemaBaseConfigProvider;
import com.smartitengineering.pos.admin.impl.provider.domain.idinstance.DomainIdInstanceProviderImpl;
import com.smartitengineering.smartpos.admin.api.Organization;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author russel
 */
public class ImplServiceModule extends AbstractModule {

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
     * Start injection specific to common dao of organization
     */
    bind(new TypeLiteral<ObjectRowConverter<Organization>>() {
    }).to(OrganizationRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<Organization, OrganizationId>>() {
    }).to(new TypeLiteral<CommonDao<Organization, OrganizationId>>() {
    }).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<Organization>>() {
    }).to(new TypeLiteral<CommonDao<Organization, OrganizationId>>() {
    }).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<Organization, OrganizationId>>() {
    }).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<Organization, OrganizationId>>() {
    }).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<Organization, OrganizationId>> wTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<Organization, OrganizationId>>() {
    };
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<Organization>>() {
    }).toProvider(OrganizationSchemaBaseConfigProvider.class).in(Scopes.SINGLETON);
    bind(new TypeLiteral<FilterConfigs<Organization>>() {
    }).toProvider(OrganizationFilterConfigsProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<OrganizationId>>() {
    }).toInstance(OrganizationId.class);
    bind(new TypeLiteral<SchemaInfoProvider<Organization, OrganizationId>>() {
    }).to(wTypeLiteral).in(Singleton.class);

    bind(DomainIdInstanceProvider.class).to(DomainIdInstanceProviderImpl.class).in(Scopes.SINGLETON);   
  }
}
