package priv.sherry.bench.tasks;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import priv.sherry.bench.constants.InputArguments;
import priv.sherry.bench.constants.URLConstants;

/**
 * @ClassName: FetchPageExecuter
 * @Description: Initiate fixed size thread pool to execute tasks of fetching all the pages
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class FetchPageExecuter {
	// thread pool
	private ExecutorService pool;
	// total hashmap
	private HashMap<LocalDate, BigDecimal> totalTransactionMap = new HashMap<>();

	/**
	 * 
	 * @Description: execute the thread pool 
	 * @param totalTaskCount size of the thread pool
	 * @return totalTransactionMap 
	 * @throws Exception
	 */
	public HashMap<LocalDate, BigDecimal> execute(int totalTaskCount) throws Exception {

		if (totalTaskCount < 1)
			return null;

		// creates a thread pool with no. of maxThreadCount
		int maxThreadCount = Math.min(totalTaskCount, InputArguments.THREAD_COUNT);
		pool = Executors.newFixedThreadPool(maxThreadCount);

		// creates tasks
		List<FetchPageTask> fetchPageTaskList = new ArrayList<>();
		for (int i = 1; i <= totalTaskCount; i++) {
			String url = URLConstants.getEndpointURL(i);
			FetchPageTask fetchPageTask = new FetchPageTask("Task" + i, url, new HashMap<LocalDate, BigDecimal>());
			fetchPageTaskList.add(fetchPageTask);
		}

		// pass the task list to thread pool
		List<Future<HashMap<LocalDate, BigDecimal>>> futures;
		if (InputArguments.isDebug) {
			System.out.println("Log debug | STEP 2: start the thread pool with " + maxThreadCount
					+ " threads for getting all transactions from rest api and merge the result \r\r");
		}
		try {
			futures = pool.invokeAll(fetchPageTaskList);
			// merge each hashmap to total hashmap
			for (Future<HashMap<LocalDate, BigDecimal>> future : futures) {
				HashMap<LocalDate, BigDecimal> taskMap = future.get();
				mergeToTotalMap(taskMap);
			}

		} catch (Exception e) {
			// write log
			System.out.println("Log error | exception happened when executing tasks in thread pool:" + e.getStackTrace());
			throw e;
		} finally {
			pool.shutdown();
		}

		return totalTransactionMap;

	}

	/**
	 * 
	 * @Title: mergeToTotalMap 
	 * @Description: merge each page to totalTransactionMap
	 * @param taskMap 
	 * @return void 
	 * @throws
	 */
	public void mergeToTotalMap(HashMap<LocalDate, BigDecimal> taskMap) {
		if (taskMap == null)
			return;
		for (Map.Entry<LocalDate, BigDecimal> entry : taskMap.entrySet()) {
			LocalDate date = entry.getKey();
			BigDecimal amount = entry.getValue();
			totalTransactionMap.putIfAbsent(date, new BigDecimal(0));
			totalTransactionMap.put(date, totalTransactionMap.get(date).add(amount));
		}
	}

}
