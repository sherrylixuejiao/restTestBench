package priv.sherry.bench.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @ClassName: RunningDailyBalancesServiceTest
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class RunningDailyBalancesServiceTest {
	RunningDailyBalancesService service = new RunningDailyBalancesService();

	/**
	 * Test method for
	 * {@link priv.sherry.bench.services.RunningDailyBalancesService#calculateRunningDailyBalances()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_CalculateRunningDailyBalances_Success() throws Exception {
		String expected = "2013-12-12 -227.35" + "\r"
				+ "2013-12-13 -1456.93" + "\r" + "2013-12-15 -1462.32" + "\r" + "2013-12-16 -6037.85" + "\r"
				+ "2013-12-17 4648.43" + "\r" + "2013-12-18 2807.14" + "\r" + "2013-12-19 22560.45" + "\r"
				+ "2013-12-20 18505.85" + "\r" + "2013-12-21 18487.87" + "\r" + "2013-12-22 18377.16" + "\r\r";
		String actual = service.calculateRunningDailyBalances();
		assertEquals(expected, actual);
	}

}
