package priv.sherry.bench;

import priv.sherry.bench.constants.InputArguments;
import priv.sherry.bench.services.RunningDailyBalancesService;

/**
 * @ClassName: Main
 * @Description: main entrance for rest test from bench accounting
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class Main {

	/**
	 * @Title: main 
	 * @Description: main entrance for rest test from bench
	 *         accounting 
	 * @param args 
	 * @return void
	 */
	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			// display debug log
			if (args[0].equals("-debug")) {
				InputArguments.isDebug = true;
			}
			// assign the thread number to call API according to the service's throughput
			if (args.length > 1 && args[1].equals("-thread") && Integer.valueOf(args[2]) > 0) {
				InputArguments.THREAD_COUNT = Integer.valueOf(args[2]);
			}
		}

		RunningDailyBalancesService service = new RunningDailyBalancesService();
		try {
			// calculate running daily balances
			String result = service.calculateRunningDailyBalances();
			// print running daily balances
			System.out.println(result);
		} catch (Exception e) {
			// write log
			System.out.println("Log error | program stopped due to exception");
		}

	}

}
