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
import com.smartitengineering.dao.impl.hbase.spi.FilterConfigs;
import com.smartitengineering.dao.impl.hbase.spi.MergeService;
import com.smartitengineering.dao.impl.hbase.spi.ObjectRowConverter;
import com.smartitengineering.dao.impl.hbase.spi.SchemaInfoProvider;
import com.smartitengineering.dao.impl.hbase.spi.impl.DiffBasedMergeService;
import com.smartitengineering.dao.impl.hbase.spi.impl.MixedExecutorServiceImpl;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderBaseConfig;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderImpl;
import com.smartitengineering.smartpos.inventory.api.PersistantEntry;
import com.smartitengineering.smartpos.inventory.api.PersistantProduct;
import com.smartitengineering.smartpos.inventory.api.PersistantStore;
import com.smartitengineering.smartpos.inventory.api.PersistantSupplier;
import com.smartitengineering.smartpos.inventory.api.PersistantUnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.converter.EntryRowConverter;
import com.smartitengineering.smartpos.inventory.api.converter.ProductRowConverter;
import com.smartitengineering.smartpos.inventory.api.converter.StoreRowConverter;
import com.smartitengineering.smartpos.inventory.api.converter.SupplierRowConverter;
import com.smartitengineering.smartpos.inventory.api.converter.UOMRowConverter;
import com.smartitengineering.smartpos.inventory.api.domainid.EntryId;
import com.smartitengineering.smartpos.inventory.api.domainid.ProductId;
import com.smartitengineering.smartpos.inventory.api.domainid.StoreId;
import com.smartitengineering.smartpos.inventory.api.domainid.SupplierId;
import com.smartitengineering.smartpos.inventory.api.domainid.UomId;
import com.smartitengineering.smartpos.inventory.impl.domainidinstanceprovider.DomainIdInstanceProviderImpl;
import com.smartitengineering.smartpos.inventory.impl.guice.EntrySchemaBaseConfigProvider;
import com.smartitengineering.smartpos.inventory.impl.guice.ProductSchemaBaseConfigProvider;
import com.smartitengineering.smartpos.inventory.impl.guice.StoreFilterConfigsProvider;
import com.smartitengineering.smartpos.inventory.impl.guice.StoreSchemaBaseConfigProvider;
import com.smartitengineering.smartpos.inventory.impl.guice.SupplierSchemaBaseConfigProvider;
import com.smartitengineering.smartpos.inventory.impl.guice.UomFilterConfigsProvider;
import com.smartitengineering.smartpos.inventory.impl.guice.UomSchemaBaseConfigProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author russel
 */
public class ImplServiceModule extends AbstractModule{

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
     * Start injection specific to common dao of uom
     */    
    bind( new TypeLiteral<ObjectRowConverter<PersistantUnitOfMeasurement>>(){}).to(UOMRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<PersistantUnitOfMeasurement, UomId>>(){}).to(new TypeLiteral<CommonDao<PersistantUnitOfMeasurement, UomId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<PersistantUnitOfMeasurement>>(){}).to(new TypeLiteral<CommonDao<PersistantUnitOfMeasurement, UomId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<PersistantUnitOfMeasurement, UomId>>(){}).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<PersistantUnitOfMeasurement, UomId>>(){}).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<PersistantUnitOfMeasurement, UomId>> wTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<PersistantUnitOfMeasurement, UomId>>() {};
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<PersistantUnitOfMeasurement>>() {
    }).toProvider(UomSchemaBaseConfigProvider.class).in(Scopes.SINGLETON);
    bind(new TypeLiteral<FilterConfigs<PersistantUnitOfMeasurement>>() {
    }).toProvider(UomFilterConfigsProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<UomId>>() {}).toInstance(UomId.class);
    bind(new TypeLiteral<SchemaInfoProvider<PersistantUnitOfMeasurement, UomId>>() {}).to(wTypeLiteral).in(Singleton.class);

    /*
     * Start injection specific to common dao of PersistantEntry
     */

    bind( new TypeLiteral<ObjectRowConverter<PersistantEntry>>(){}).to(EntryRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<PersistantEntry, EntryId>>(){}).to(new TypeLiteral<CommonDao<PersistantEntry, EntryId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<PersistantEntry>>(){}).to(new TypeLiteral<CommonDao<PersistantEntry, EntryId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<PersistantEntry, EntryId>>(){}).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<PersistantEntry, EntryId>>(){}).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<PersistantEntry, EntryId>> entryTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<PersistantEntry, EntryId>>() {};
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<PersistantEntry>>() {
    }).toProvider(EntrySchemaBaseConfigProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<EntryId>>() {}).toInstance(EntryId.class);
    bind(new TypeLiteral<SchemaInfoProvider<PersistantEntry, EntryId>>() {}).to(entryTypeLiteral).in(Singleton.class);

    /*
     * Start injection specific to common dao of PersistantProduct
     */

    bind( new TypeLiteral<ObjectRowConverter<PersistantProduct>>(){}).to(ProductRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<PersistantProduct, ProductId>>(){}).to(new TypeLiteral<CommonDao<PersistantProduct, ProductId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<PersistantProduct>>(){}).to(new TypeLiteral<CommonDao<PersistantProduct, ProductId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<PersistantProduct, ProductId>>(){}).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<PersistantProduct, ProductId>>(){}).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<PersistantProduct, ProductId>> productTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<PersistantProduct, ProductId>>() {};
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<PersistantProduct>>() {
    }).toProvider(ProductSchemaBaseConfigProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<ProductId>>() {}).toInstance(ProductId.class);
    bind(new TypeLiteral<SchemaInfoProvider<PersistantProduct, ProductId>>() {}).to(productTypeLiteral).in(Singleton.class);

    /*
     * Start injection specific to common dao of PersistantStore
     */

    bind( new TypeLiteral<ObjectRowConverter<PersistantStore>>(){}).to(StoreRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<PersistantStore, StoreId>>(){}).to(new TypeLiteral<CommonDao<PersistantStore, StoreId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<PersistantStore>>(){}).to(new TypeLiteral<CommonDao<PersistantStore, StoreId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<PersistantStore, StoreId>>(){}).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<PersistantStore, StoreId>>(){}).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<PersistantStore, StoreId>> storeTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<PersistantStore, StoreId>>() {};
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<PersistantStore>>() {
    }).toProvider(StoreSchemaBaseConfigProvider.class).in(Scopes.SINGLETON);
    bind(new TypeLiteral<FilterConfigs<PersistantStore>>() {
    }).toProvider(StoreFilterConfigsProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<StoreId>>() {}).toInstance(StoreId.class);
    bind(new TypeLiteral<SchemaInfoProvider<PersistantStore, StoreId>>() {}).to(storeTypeLiteral).in(Singleton.class);

    /*
     * Start injection specific to common dao of PersistantSupplier
     */

    bind( new TypeLiteral<ObjectRowConverter<PersistantSupplier>>(){}).to(SupplierRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<PersistantSupplier, SupplierId>>(){}).to(new TypeLiteral<CommonDao<PersistantSupplier, SupplierId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<PersistantSupplier>>(){}).to(new TypeLiteral<CommonDao<PersistantSupplier, SupplierId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<PersistantSupplier, SupplierId>>(){}).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<PersistantSupplier, SupplierId>>(){}).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<PersistantSupplier, SupplierId>> supplierTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<PersistantSupplier, SupplierId>>() {};
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<PersistantSupplier>>() {
    }).toProvider(SupplierSchemaBaseConfigProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<SupplierId>>() {}).toInstance(SupplierId.class);
    bind(new TypeLiteral<SchemaInfoProvider<PersistantSupplier, SupplierId>>() {}).to(supplierTypeLiteral).in(Singleton.class);

    bind(DomainIdInstanceProvider.class).to(DomainIdInstanceProviderImpl.class).in(Scopes.SINGLETON);   

  }

}
