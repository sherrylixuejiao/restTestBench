package priv.sherry.bench.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import priv.sherry.bench.beans.Balances;

/**
 * @ClassName: RunningDailyBalancesService
 * @Description: get all transactions and aggregate running daily balances
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class RunningDailyBalancesService {

	/**
	 * 
	 * @Title: calculateRunningDailyBalances 
	 * @Description: the main steps to get
	 *         all transactions and aggregate running daily
	 *         balances 
	 * @param
	 * @return String for running
	 *         daily balance 
	 * @throws Exception
	 */
	public String calculateRunningDailyBalances() throws Exception {

		// step1: get all transactions from restful API and put them in a
		// hashmap<Data,Amount>
		TransactionService transactionService = new TransactionService();
		Map<LocalDate, BigDecimal> totalAmountMap = transactionService.getAllTransactions();

		// step2: aggregate daily balances from the hashmap we've got
		Balances dailyBalance = BalancesService.aggregateDailyBalances(new Balances(totalAmountMap));

		// step3: print out running daily balance
		return dailyBalance.balanceString();
	}
}
