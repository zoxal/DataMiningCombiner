package evm.dmc.weka.function;

//@RunWith(SpringRunner.class)
//@TestPropertySource("classpath:wekatest.properties")
//@ContextConfiguration(classes = {DataMiningCombinerApplicationTests.class})
//@ComponentScan( basePackages="evm.dmc.core, evm.dmc.weka")
//@DataJpaTest  // TODO: remove where unneeded
public class WekaCSVSaveTest {
//	@Autowired
//	@WekaFW
//	WekaFramework frmwk;
//
//	WekaCSVSave csv;
//
//	@Value("${wekatest.datasource}")
//	String sourceFile;
//	Data data;
//
//	String destFile = "temp.csv";
//	File tmpFile;
//
//	@Rule
//	public TemporaryFolder folder = new TemporaryFolder();
//
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@Before
//	public void init() throws IOException {
//		csv = (WekaCSVSave) frmwk.getDMCFunction(WekaFunctions.CSVSAVER);
//		assertNotNull(csv);
//		tmpFile = folder.newFile(destFile);
//
//		// TODO
//		// potentially contains fails
//		CSVLoader load = (CSVLoader) frmwk.getDMCFunction("Weka_CSVLoader");
//
//		load.setSource(sourceFile);
//		data = load.get();
//		assertNotNull(data);
//		assertNotNull(data.getData());
//
//	}
//
//	@Test
//	public final void testSave() throws ClassCastException, StoreDataException {
//		csv.setDestination(tmpFile);
//		csv.save(data);
//		assertTrue(tmpFile.length() != 0);
//
//	}
//
//	@Test(expected = StoreDataException.class)
//	public final void testSavigWithoutDestination() throws StoreDataException {
//		csv.save(data);
//
//	}
//
//	// @Test(expected = ClassCastException.class)
//	// public final void testSavingUnsupportedDataType() throws
//	// ClassCastException, StoringException {
//	// csv.setDestination(tmpFile);
//	// Data<String> badData = frmwk.getData(StringData.class);
//	// badData.setData("Hello, World");
//	// csv.save(badData);
//	// }

}
