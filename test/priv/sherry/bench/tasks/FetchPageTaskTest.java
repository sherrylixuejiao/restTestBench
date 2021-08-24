package priv.sherry.bench.tasks;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import priv.sherry.bench.tasks.FetchPageTask;

/**
 * @ClassName: TaskTest
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class FetchPageTaskTest {

	/**
	 * Test method for {@link priv.sherry.bench.tasks.FetchPageTask#call()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCall() throws Exception {
		String url = "https://resttest.bench.co/transactions/4.json";
		FetchPageTask t = new FetchPageTask("task4", url, new HashMap<LocalDate, BigDecimal>());
		HashMap<LocalDate, BigDecimal> actual = t.call();
		String actualString = "";
		for (Map.Entry<LocalDate, BigDecimal> entry : actual.entrySet()) {
			LocalDate date = entry.getKey();
			BigDecimal amount = entry.getValue();
			actualString += date + ":" + amount + "\r";
		}
		String expected = "2013-12-12:-227.35" + "\r" + "2013-12-13:-6156.83" + "\r" + "2013-12-20:-1874.75" + "\r";
		assertEquals(expected, actualString);
	}

	@Test(expected = Exception.class)
	public void testCall_nullString() throws Exception {
		String url = "";
		FetchPageTask t = new FetchPageTask("task4", url, new HashMap<LocalDate, BigDecimal>());
		t.call();
	}

	@Test(expected = Exception.class)
	public void testCall_exception() throws Exception {
		String url = "anan";
		FetchPageTask t = new FetchPageTask("task4", url, new HashMap<LocalDate, BigDecimal>());
		t.call();
	}

}
