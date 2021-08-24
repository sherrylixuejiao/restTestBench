package priv.sherry.bench.services;

import priv.sherry.bench.beans.Balances;

/**
 * @ClassName: BalancesService
 * @Description: aggregating running daily balances
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class BalancesService {


	/**
	 * 
	 * @Title: aggregateDailyBalances 
	 * @Description: function for aggregating
	 *         running daily balances 
	 * @param  dailyBalances
	 * @return Balances 
	 */
	public static Balances aggregateDailyBalances(Balances dailyBalances) {
		if (dailyBalances == null)
			return null;
		// sort date
		dailyBalances.sortDates();
		// aggregate
		dailyBalances.aggregateBalance();
		// return dailyBalance
		return dailyBalances;
	}
}
