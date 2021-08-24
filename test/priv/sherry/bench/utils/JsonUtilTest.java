package priv.sherry.bench.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import priv.sherry.bench.beans.Page;

/**
 * @ClassName: JsonUtilTest
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class JsonUtilTest {

	/**
	 * Test method for
	 * {@link priv.sherry.bench.utils.JsonUtil#jsonToJavaBean(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void test_JsonToJavaBean_Success() throws Exception {
		String jsonString = HttpConnectionUtil.connect("https://resttest.bench.co/transactions/4.json");
		Page page = JsonUtil.jsonToJavaBean(jsonString);
		assertEquals(38, page.getTotalCount());
		assertEquals(4, page.getPage());
		assertEquals(8, page.getTransactions().size());

	}
	
	@Test
	public void test_JsonToJavaBean_NullUrl() throws Exception {
		JsonUtil.jsonToJavaBean("");
	}

	@Test(expected = Exception.class)
	public void test_JsonToJavaBean_WrongJsonString() throws Exception {
		JsonUtil.jsonToJavaBean("abscd");
	}


}
