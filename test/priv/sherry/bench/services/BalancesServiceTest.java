package priv.sherry.bench.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import priv.sherry.bench.beans.Balances;

/**
 * @ClassName: BalancesServiceTest
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class BalancesServiceTest {
	/**
	 * @Title: setUp @Description: TODO(这里用一句话描述这个方法的作用) @param @throws
	 *         java.lang.Exception 参数 @return void 返回类型 @throws
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link priv.sherry.bench.services.BalancesService#aggregateDailyBalances()}.
	 */
	@Test
	public void test_AggregateDailyBalances_Success() {

		Map<LocalDate, BigDecimal> map = new HashMap<>();
		map.put(LocalDate.parse("2021-08-22"), new BigDecimal("100"));
		map.put(LocalDate.parse("2021-08-23"), new BigDecimal("-1"));
		Balances expected = new Balances(map);
		expected.sortDates();
		expected.setBalances(map);

		Map<LocalDate, BigDecimal> totalAmountMap = new HashMap<>();
		totalAmountMap.put(LocalDate.parse("2021-08-23"), new BigDecimal("-101"));
		totalAmountMap.put(LocalDate.parse("2021-08-22"), new BigDecimal("100"));
		Balances actual = BalancesService.aggregateDailyBalances(new Balances(totalAmountMap));
		assertEquals(expected.balanceString(), actual.balanceString());
	}

}
