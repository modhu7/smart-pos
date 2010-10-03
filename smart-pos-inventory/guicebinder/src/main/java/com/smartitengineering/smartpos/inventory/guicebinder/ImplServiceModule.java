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
import com.smartitengineering.smartpos.inventory.api.Product;
import com.smartitengineering.smartpos.inventory.api.Store;
import com.smartitengineering.smartpos.inventory.api.Supplier;
import com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement;
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
import com.smartitengineering.smartpos.inventory.impl.guice.StoreSchemaBaseConfigProvider;
import com.smartitengineering.smartpos.inventory.impl.guice.SupplierSchemaBaseConfigProvider;
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
    bind( new TypeLiteral<ObjectRowConverter<UnitOfMeasurement>>(){}).to(UOMRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<UnitOfMeasurement, UomId>>(){}).to(new TypeLiteral<CommonDao<UnitOfMeasurement, UomId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<UnitOfMeasurement>>(){}).to(new TypeLiteral<CommonDao<UnitOfMeasurement, UomId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<UnitOfMeasurement, UomId>>(){}).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<UnitOfMeasurement, UomId>>(){}).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<UnitOfMeasurement, UomId>> wTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<UnitOfMeasurement, UomId>>() {};
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<UnitOfMeasurement>>() {
    }).toProvider(UomSchemaBaseConfigProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<UomId>>() {}).toInstance(UomId.class);
    bind(new TypeLiteral<SchemaInfoProvider<UnitOfMeasurement, UomId>>() {}).to(wTypeLiteral).in(Singleton.class);

    /*
     * Start injection specific to common dao of Entry
     */

    bind( new TypeLiteral<ObjectRowConverter<Entry>>(){}).to(EntryRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<Entry, EntryId>>(){}).to(new TypeLiteral<CommonDao<Entry, EntryId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<Entry>>(){}).to(new TypeLiteral<CommonDao<Entry, EntryId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<Entry, EntryId>>(){}).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<Entry, EntryId>>(){}).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<Entry, EntryId>> entryTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<Entry, EntryId>>() {};
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<Entry>>() {
    }).toProvider(EntrySchemaBaseConfigProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<EntryId>>() {}).toInstance(EntryId.class);
    bind(new TypeLiteral<SchemaInfoProvider<Entry, EntryId>>() {}).to(entryTypeLiteral).in(Singleton.class);

    /*
     * Start injection specific to common dao of Product
     */

    bind( new TypeLiteral<ObjectRowConverter<Product>>(){}).to(ProductRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<Product, ProductId>>(){}).to(new TypeLiteral<CommonDao<Product, ProductId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<Product>>(){}).to(new TypeLiteral<CommonDao<Product, ProductId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<Product, ProductId>>(){}).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<Product, ProductId>>(){}).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<Product, ProductId>> productTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<Product, ProductId>>() {};
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<Product>>() {
    }).toProvider(ProductSchemaBaseConfigProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<ProductId>>() {}).toInstance(ProductId.class);
    bind(new TypeLiteral<SchemaInfoProvider<Product, ProductId>>() {}).to(productTypeLiteral).in(Singleton.class);

    /*
     * Start injection specific to common dao of Store
     */

    bind( new TypeLiteral<ObjectRowConverter<Store>>(){}).to(StoreRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<Store, StoreId>>(){}).to(new TypeLiteral<CommonDao<Store, StoreId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<Store>>(){}).to(new TypeLiteral<CommonDao<Store, StoreId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<Store, StoreId>>(){}).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<Store, StoreId>>(){}).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<Store, StoreId>> storeTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<Store, StoreId>>() {};
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<Store>>() {
    }).toProvider(StoreSchemaBaseConfigProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<StoreId>>() {}).toInstance(StoreId.class);
    bind(new TypeLiteral<SchemaInfoProvider<Store, StoreId>>() {}).to(storeTypeLiteral).in(Singleton.class);

    /*
     * Start injection specific to common dao of Supplier
     */

    bind( new TypeLiteral<ObjectRowConverter<Supplier>>(){}).to(SupplierRowConverter.class).in(Singleton.class);
    bind(new TypeLiteral<CommonReadDao<Supplier, SupplierId>>(){}).to(new TypeLiteral<CommonDao<Supplier, SupplierId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonWriteDao<Supplier>>(){}).to(new TypeLiteral<CommonDao<Supplier, SupplierId>>(){}).in(Singleton.class);
    bind(new TypeLiteral<CommonDao<Supplier, SupplierId>>(){}).to(new TypeLiteral<com.smartitengineering.dao.impl.hbase.CommonDao<Supplier, SupplierId>>(){}).in(Singleton.class);
    final TypeLiteral<SchemaInfoProviderImpl<Supplier, SupplierId>> supplierTypeLiteral = new TypeLiteral<SchemaInfoProviderImpl<Supplier, SupplierId>>() {};
    bind(new TypeLiteral<SchemaInfoProviderBaseConfig<Supplier>>() {
    }).toProvider(SupplierSchemaBaseConfigProvider.class).in(Scopes.SINGLETON);

    bind(new TypeLiteral<Class<SupplierId>>() {}).toInstance(SupplierId.class);
    bind(new TypeLiteral<SchemaInfoProvider<Supplier, SupplierId>>() {}).to(supplierTypeLiteral).in(Singleton.class);

    bind(DomainIdInstanceProvider.class).to(DomainIdInstanceProviderImpl.class).in(Scopes.SINGLETON);   

  }

}
