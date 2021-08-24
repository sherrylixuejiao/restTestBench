package priv.sherry.bench.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @ClassName: HttpConnectionUtilTest
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class HttpConnectionUtilTest {

	/**
	 * Test method for
	 * {@link priv.sherry.bench.utils.HttpConnectionUtil#connect(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testConnect() throws Exception {

		String jsonString = HttpConnectionUtil.connect("https://resttest.bench.co/transactions/4.json");
		assertEquals(1385, jsonString.length());
	}

	@Test(expected = Exception.class)
	public void testConnect_nullUrl() throws Exception {
		HttpConnectionUtil.connect("");

	}

	@Test(expected = Exception.class)
	public void testConnectn_wrongJsonString() throws Exception {
		HttpConnectionUtil.connect("abcds");

	}

}
