
import com.google.inject.AbstractModule;
import com.smartitengineering.dao.hbase.ddl.HBaseTableGenerator;
import com.smartitengineering.dao.hbase.ddl.config.json.ConfigurationJsonParser;
import com.smartitengineering.smartpos.inventory.client.api.domain.UnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.client.api.resource.RootResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.UomResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.UomsResource;
import com.smartitengineering.smartpos.inventory.client.impl.domain.UnitOfMeasurementImpl;
import com.smartitengineering.smartpos.inventory.client.impl.resource.RootResourceImpl;
import com.smartitengineering.smartpos.inventory.guicebinder.Initializer;
import com.smartitengineering.util.rest.client.ConnectionConfig;
import com.smartitengineering.util.bean.guice.GuiceUtil;
import com.smartitengineering.util.rest.client.ApplicationWideClientFactoryImpl;
import java.io.File;
import java.net.URI;
import java.util.Properties;
import junit.framework.Assert;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
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
public class UomResourceTest {

  private static Server jettyServer;
  private static final int PORT = 10090;
  private RootResource rootResource;
  private static final HBaseTestingUtility TEST_UTIL = new HBaseTestingUtility();
  public static final String ROOT_URI_STRING = "http://localhost:" + PORT + "/orgs/sn/SITEL/dashboard";
  

  @BeforeClass
  public static void setUp() throws Exception{

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

    new HBaseTableGenerator(ConfigurationJsonParser.getConfigurations(UomResourceTest.class.getClassLoader().getResourceAsStream(
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
  public static void tearDown() throws Exception{
    TEST_UTIL.shutdownMiniCluster();
    jettyServer.stop();
  }

  @Test
  public void testCreate() throws Exception{

    rootResource = RootResourceImpl.getRoot(new URI(ROOT_URI_STRING));
    Assert.assertNotNull(rootResource);

    UomsResource uomsResource = rootResource.getOrganizationUomsResource();
    Assert.assertNotNull(uomsResource);
//
    UnitOfMeasurementImpl uom = new UnitOfMeasurementImpl();
    uom.setId("KG");
    //uom.setName("Kilogram");
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

    UnitOfMeasurement changedUom = uomResource.getUnitOfMeasurement();
    Assert.assertNotSame("Kg", uom.getSymbol());

    uomResource.delete();       
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
