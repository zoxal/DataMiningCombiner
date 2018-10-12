package evm.dmc.core.chart;

import evm.dmc.core.api.back.Plottable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class HistogramTest {

	@Autowired
	Histogram histogram;

	// TODO: initialize
	Plottable data;

	@Test
	public final void testSaveToPng() throws IOException {
		histogram.setAttribIndexesToPlot(0);
		List<String> list = histogram.saveToPng(data, "Data/test");
		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
		assertNotNull(list.get(0));
		File file = new File(list.get(0));
		assertTrue(file.length() > 0);
		file.delete();

	}

	@Test
	public final void testSaveToPngSeveralArgs() throws IOException {
		histogram
				.setAttribIndexesToPlot(0, 1, 2, 3)
				.setWidth(2048);

		List<String> list = histogram.saveToPng(data, "Data/testSeveral");
		assertFalse(list.isEmpty());
		assertEquals(4, list.size());
		for (String fname : list) {
			assertNotNull(fname);
			File file = new File(fname);
			assertTrue(file.length() > 0);
			// file.delete();
		}

	}

	@Test
	public final void testGetBufferedImage() {
		histogram.setAttribIndexesToPlot(0);
		List<BufferedImage> list = histogram.getBufferedImage(data);

		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
		assertNotNull(list.get(0));
		assertNotNull(list.get(0).getData().getDataBuffer());

		// this gets the actual Raster data as a byte array
		// int[] byteArrayToCheck = ((DataBufferInt)
		// histogram.getBufferedImage(data).get(0).getData().getDataBuffer())
		// .getData();

		// assertArrayEquals(byteArrayTest, byteArrayToCheck);
	}

}
