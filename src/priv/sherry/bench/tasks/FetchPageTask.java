package priv.sherry.bench.tasks;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import priv.sherry.bench.beans.Page;
import priv.sherry.bench.beans.Transaction;
import priv.sherry.bench.constants.InputArguments;
import priv.sherry.bench.utils.HttpConnectionUtil;

/**
 * @ClassName: FetchPageTask
 * @Description: task for getting each page's transactions and calculate current
 *               page's balances
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class FetchPageTask implements Callable<HashMap<LocalDate, BigDecimal>> {

	// task name
	private String name;
	// url for getting current page
	private String url;
	// current page's balances
	private HashMap<LocalDate, BigDecimal> taskMap;

	public FetchPageTask(String s, String url, HashMap<LocalDate, BigDecimal> map) {
		name = s;
		this.url = url;
		taskMap = map;
	}

	@Override
	public HashMap<LocalDate, BigDecimal> call() throws Exception {

		// print the start time of each task
		Date d = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
		if (InputArguments.isDebug) {
			System.out.println("Log debug | start " + name + " to get transactions in one page at " + ft.format(d));
		}
		// start getting current page
		String jsonString = HttpConnectionUtil.connect(url);
		Page page = JSON.parseObject(jsonString, new TypeReference<Page>() {
		});

		// start calculating current page's balances hashmap
		for (Transaction t : page.getTransactions()) {
			taskMap.putIfAbsent(t.getDate(), new BigDecimal(0));
			taskMap.put(t.getDate(), taskMap.get(t.getDate()).add(t.getAmount()));
		}

		// log the end time of each task
		if (InputArguments.isDebug) {
			System.out.println("Log debug |  end " + name + " at " + ft.format(d) + "\r\r");
		}
		return taskMap;
	}

}
