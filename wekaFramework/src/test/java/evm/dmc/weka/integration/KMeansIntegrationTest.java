package evm.dmc.weka.integration;

//@RunWith(SpringRunner.class)
//@TestPropertySource("classpath:wekatest.properties")
//@ContextConfiguration(classes = {DataMiningCombinerApplicationTests.class})
//@ComponentScan( basePackages="evm.dmc.core, evm.dmc.weka")
//@DataJpaTest  // TODO: remove where unneeded
public class KMeansIntegrationTest {
//	@Autowired
//	@WekaFW
//	Framework frmwk;
//
//	@Autowired
//	@WekaFW
//	DataFactory datFactory;
//
//	@Value("${wekatest.datasource}")
//	String sourceFileName;
//
//	@Test
//	public final void test() {
//		CSVLoader loader = (CSVLoader) frmwk.getDMCFunction(WekaFunctions.CSVLOADER);
//		Data data = ((WekaData) loader.setSource(sourceFileName).get()).getAttributes(6, 9, 12);
//		assertNotNull(data);
//		WekaData wData = (WekaData) datFactory.castToNativeData(data);
//		assertEquals(3, wData.getData().numAttributes());
//
//		DMCFunction pca = frmwk.getDMCFunction(WekaFunctions.PCA);
//		pca.setArgs(wData);
//		pca.execute();
//
//		WekaData reduced = (WekaData) pca.getResults();
//
//		assertEquals(2, reduced.getData().numAttributes());
//
//		// execute Normalization
//		DMCFunction norm = frmwk.getDMCFunction(WekaFunctions.NORMALIZATION);
//		norm.setArgs(data);
//		norm.execute();
//		WekaData normal = (WekaData) norm.getResults();
//
//		double[] res0 = normal.getAllValuesAsDoubleAt(0);
//		double[] res1 = normal.getAllValuesAsDoubleAt(1);
//
//		int max0 = (int) Arrays.stream(res0).max().getAsDouble();
//		int max1 = (int) Arrays.stream(res1).max().getAsDouble();
//
//		int min0 = (int) Arrays.stream(res0).min().getAsDouble();
//		int min1 = (int) Arrays.stream(res1).min().getAsDouble();
//
//		assertEquals(max0, 1);
//		assertEquals(max1, 1);
//
//		assertEquals(min0, 0);
//		assertEquals(min1, 0);
//
//		// DMCFunction kmenas = frmwk.getDMCFunction(WekaFunctions.KMEANS);
//		// kmenas.setArgs(data);
//
//	}

}
