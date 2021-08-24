package priv.sherry.bench.tasks;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import priv.sherry.bench.tasks.FetchPageExecuter;

/**
 * @ClassName: ThreadPoolTest
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class FetchPageExecuterTest {

	FetchPageExecuter pool = new FetchPageExecuter();

	/**
	 * Test method for
	 * {@link priv.sherry.bench.tasks.FetchPageExecuter#execute(int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExecute() throws Exception {
		String expected = "2013-12-15:-5.39\r2013-12-12:-227.35\r2013-12-13:-1229.58\r2013-12-22:-110.71\r2013-12-20:-4054.60\r2013-12-21:-17.98\r2013-12-18:-1841.29\r2013-12-19:19753.31\r2013-12-16:-4575.53\r2013-12-17:10686.28\r";
		HashMap<LocalDate, BigDecimal> actual = pool.execute(4);
		String actualString = "";
		for (Map.Entry<LocalDate, BigDecimal> entry : actual.entrySet()) {
			LocalDate date = entry.getKey();
			BigDecimal amount = entry.getValue();
			actualString += date + ":" + amount + "\r";
		}
		assertEquals(expected, actualString);
	}

	@Test(expected = Exception.class)
	public void testExecute_wrongTask() throws Exception {
		pool.execute(50);
	}

	@Test
	public void testExecute_invalidTask() throws Exception {
		pool.execute(-1);
	}

}
