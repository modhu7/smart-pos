
import com.google.inject.AbstractModule;
import com.smartitengineering.dao.hbase.ddl.HBaseTableGenerator;
import com.smartitengineering.dao.hbase.ddl.config.json.ConfigurationJsonParser;
import com.smartitengineering.smartpos.inventory.client.api.domain.Product;
import com.smartitengineering.smartpos.inventory.client.api.domain.Store;
import com.smartitengineering.smartpos.inventory.client.api.domain.Supplier;
import com.smartitengineering.smartpos.inventory.client.api.domain.UnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.client.api.resource.ProductResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.ProductsResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.RootResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.StoreResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.StoresResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.SupplierResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.SuppliersResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.UomResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.UomsResource;
import com.smartitengineering.smartpos.inventory.client.impl.domain.AddressImpl;
import com.smartitengineering.smartpos.inventory.client.impl.domain.ProductImpl;
import com.smartitengineering.smartpos.inventory.client.impl.domain.StoreImpl;
import com.smartitengineering.smartpos.inventory.client.impl.domain.SupplierImpl;
import com.smartitengineering.smartpos.inventory.client.impl.domain.UnitOfMeasurementImpl;
import com.smartitengineering.smartpos.inventory.client.impl.resource.RootResourceImpl;
import com.smartitengineering.smartpos.inventory.guicebinder.Initializer;
import com.smartitengineering.util.rest.client.ConnectionConfig;
import com.smartitengineering.util.bean.guice.GuiceUtil;
import com.smartitengineering.util.rest.client.ApplicationWideClientFactoryImpl;
import com.sun.jersey.api.client.UniformInterfaceException;
import java.io.File;
import java.net.URI;
import java.util.Properties;
import javax.ws.rs.core.Response.Status;
import junit.framework.Assert;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author russel
 */
public class AllResourceTest {

  private static Server jettyServer;
  private static final int PORT = 10090;
  private RootResource rootResource;
  private static final HBaseTestingUtility TEST_UTIL = new HBaseTestingUtility();
  public static final String ROOT_URI_STRING = "http://localhost:" + PORT + "/orgs/sn/SITEL/dashboard";

  @BeforeClass
  public static void setUp() throws Exception {

    /*
     * Start HBase and initialize tables
     */

    System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
                       "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");

    TEST_UTIL.startMiniCluster();

//    HBaseAdmin admin = new HBaseAdmin(TEST_UTIL.getConfiguration());
//    HTableDescriptor uomTable = new HTableDescriptor("uom");
//    uomTable.addFamily(new HColumnDescriptor("self"));
//    admin.createTable(uomTable);

    new HBaseTableGenerator(ConfigurationJsonParser.getConfigurations(AllResourceTest.class.getClassLoader().
        getResourceAsStream(
        "com/smartitengineering/pos/inventory/impl/schema.json")), TEST_UTIL.getConfiguration(), true).generateTables();

    // DI
    Properties properties = new Properties();
    properties.setProperty(GuiceUtil.CONTEXT_NAME_PROP,
                           "com.smartitengineering.dao.impl.hbase,com.smartitengineering.user.client");
    properties.setProperty(GuiceUtil.IGNORE_MISSING_DEP_PROP, Boolean.TRUE.toString());
    properties.setProperty(GuiceUtil.MODULES_LIST_PROP, ConfigurationModule.class.getName());
    GuiceUtil.getInstance(properties).register();
    Initializer.init();

    // start web application container

    System.out.println("::: Starting server :::");
    jettyServer = new Server(PORT);
    //final String webapp = "./target/smartpos/";
    final String webapp = "./src/test/webapp/";
    if (!new File(webapp).exists()) {
      throw new IllegalStateException("WebApp file/dir does not exist!");
    }
    Handler webAppHandler = new WebAppContext(webapp, "/");
    jettyServer.setHandler(webAppHandler);
    jettyServer.start();

    // set up client properties

    System.setProperty(ApplicationWideClientFactoryImpl.TRACE, Boolean.TRUE.toString());
  }

  @AfterClass
  public static void tearDown() throws Exception {
    TEST_UTIL.shutdownMiniCluster();
    jettyServer.stop();
  }

  @Test
  public void testCreate() throws Exception {

    rootResource = RootResourceImpl.getRoot(new URI(ROOT_URI_STRING));
    Assert.assertNotNull(rootResource);

    UomsResource uomsResource = rootResource.getOrganizationUomsResource();
    Assert.assertNotNull(uomsResource);
//
    UnitOfMeasurementImpl uom = new UnitOfMeasurementImpl();
    uom.setId("KG");
    uom.setLongName("Kilogram");
    uom.setSymbol("Kg");
    uom.setUomSystem("SI");
    uom.setUomType("Weight");

    UomResource uomResource = uomsResource.create(uom);
    Assert.assertNotNull(uomResource);

    UnitOfMeasurement fetchedUom = uomResource.getUnitOfMeasurement();
    Assert.assertNotNull(fetchedUom);
    fetchedUom.setSymbol("Kg");
    fetchedUom.setUomSystem("Metric");
    fetchedUom.setUomType("Weight");

    uomResource.update();
//
    UnitOfMeasurement changedUom = uomResource.getUnitOfMeasurement();
    Assert.assertNotSame(fetchedUom.getUomSystem(), uom.getUomSystem());
//
    uomResource.delete();
    try {
      uomResource.get();
      Assert.fail("Should have thorown exception");
    }
    catch (UniformInterfaceException ex) {
      Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), ex.getResponse().getStatus());
    }
    catch (Exception e) {
      Assert.fail("Should not throw exception other than UniformInterfaceException");
    }

    uomResource = uomsResource.create(uom);


    //     Product
//    ProductsResource productsResource = rootResource.getProductsResource();
//
//    ProductImpl product = new ProductImpl();
//    product.setId("P1");
//    product.setName("Product1");
//    product.setDescription("Product 1, Bekar company");
//    product.setSkuId("KG");
//
//    ProductResource productResource = productsResource.create(product);
//    Assert.assertNotNull(productResource);
//
//    Product fetchedProduct = productResource.getProduct();
//    Assert.assertNotNull(fetchedProduct);
//    fetchedProduct.setName("Modified Product Name");
//
//
//    productResource.update();
//
////
//    Product changedProduct = productResource.getProduct();
//    Assert.assertNotSame(changedProduct.getName(), product.getName());
////
//    productResource.delete();
//    try {
//      productResource.get();
//      Assert.fail("Should have thorown exception");
//    }
//    catch (UniformInterfaceException ex) {
//      Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), ex.getResponse().getStatus());
//    }
//    catch (Exception e) {
//      Assert.fail("Should not throw exception other than UniformInterfaceException");
//    }
//
//    // store test
//
//
//    StoresResource storesResource = rootResource.getStoresResource();
//    Assert.assertNotNull(storesResource);
//
//    StoreImpl store = new StoreImpl();
//    AddressImpl address = new AddressImpl();
//    store.setId("S1");
//    store.setName("Store 1");
//    address.setStreetAddress("Haji Chinu Mia road");
//    address.setCity("Dhaka");
//    address.setState("Dhaka");
//    address.setCountry("Bangladesh");
//    address.setZip("1207");
//    store.setAddress(address);
//
//    StoreResource storeResource = storesResource.create(store);
//    Assert.assertNotNull(storeResource);
//
//    Store fetchedStore = storeResource.getStore();
//    Assert.assertNotNull(fetchedStore);
//    System.out.println(fetchedStore.getAddress());
//    System.out.println(fetchedStore.getAddress().getGeoLocation());
//
//    fetchedStore.setName("Modified Store");
//
//
//    storeResource.update();
//
//    Store updatedStore = storeResource.getStore();
//    Assert.assertNotSame(updatedStore.getName(), store.getName());
//
//    storeResource.delete();
//
//    try {
//      storeResource.get();
//      Assert.fail("Should have thorown exception");
//    }
//    catch (UniformInterfaceException ex) {
//      Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), ex.getResponse().getStatus());
//    }
//    catch (Exception e) {
//      Assert.fail("Should not throw exception other than UniformInterfaceException");
//    }

    // Supplier test

//    SuppliersResource suppliersResource =  rootResource.getSuppliersResource();
//    Assert.assertNotNull(suppliersResource);
//
//    Supplier supplier = new SupplierImpl();
//    AddressImpl saddress = new AddressImpl();
//    supplier.setId("S1");
//    supplier.setName("Supplier 1");
//    supplier.setContactNumber("01716075296");
//    supplier.setEmail("russel@gmail.com");
//    address.setStreetAddress("Haji Chinu Mia road");
//    address.setCity("Dhaka");
//    address.setState("Dhaka");
//    address.setCountry("Bangladesh");
//    address.setZip("1207");
//    store.setAddress(address);
//
//    SupplierResource supplierResource = suppliersResource.create(supplier);
//    Assert.assertNotNull(supplierResource);
//
//    Supplier fetchedSupplier = supplierResource.getSupplier();
//    Assert.assertNotNull(fetchedSupplier);
//    System.out.println(fetchedStore.getAddress());
//    System.out.println(fetchedStore.getAddress().getGeoLocation());
//
//    fetchedStore.setName("Modified Store");
//
//
//    supplierResource.update();
//
//    Supplier updatedSupplier = supplierResource.getSupplier();
//    Assert.assertNotSame(updatedSupplier.getName(), supplier.getName());
//
//    supplierResource.delete();
//
//    try {
//      supplierResource.get();
//      Assert.fail("Should have thorown exception");
//    }
//    catch (UniformInterfaceException ex) {
//      Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), ex.getResponse().getStatus());
//    }
//    catch (Exception e) {
//      Assert.fail("Should not throw exception other than UniformInterfaceException");
//    }

  }

  public static class ConfigurationModule extends AbstractModule {

    @Override
    protected void configure() {
      bind(Configuration.class).toInstance(TEST_UTIL.getConfiguration());
      ConnectionConfig config = new ConnectionConfig();
      config.setBasicUri("orgs/sn/SITEL/dashboard");
      config.setContextPath("/");
      config.setHost("localhost");
      config.setPort(PORT);
      bind(ConnectionConfig.class).toInstance(config);
    }
  }
}
