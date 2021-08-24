package priv.sherry.bench.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import priv.sherry.bench.beans.Page;
import priv.sherry.bench.constants.InputArguments;
import priv.sherry.bench.constants.URLConstants;
import priv.sherry.bench.tasks.FetchPageExecuter;
import priv.sherry.bench.utils.HttpConnectionUtil;
import priv.sherry.bench.utils.JsonUtil;

/**
 * @ClassName: TransactionService
 * @Description: used to get all transactions from rest api and return an
 *               hashmap as (date, amount)
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class TransactionService {
	/**
	 * 
	 * @Title: getAllTransactions @Description: get all transactions from rest
	 *         api
	 * @param
	 * @throws Exception
	 * @return Map<LocalDate,BigDecimal> return an hashmap as (date, amount)
	 */
	public Map<LocalDate, BigDecimal> getAllTransactions() throws Exception {

		// get first page
		Page firstPage = getPage(URLConstants.getEndpointURL(1));

		// calculate total pages
		int totalTask = 0;
		if (firstPage != null) {
			int tatalCount = firstPage.getTotalCount();
			int pageSize = firstPage.getTransactions().size();
			totalTask = (int)(Math.floor((tatalCount - 1) / pageSize) + 1);
			if (InputArguments.isDebug) {
				System.out.println("Log Debug | STEP 1: date source information: " + tatalCount + " transactions, "
						+ totalTask + " pages, pageSize " + pageSize + "\r\r");
			}
		}

		// start thread pool to fetch all pages
		FetchPageExecuter fetchPageExecuter = new FetchPageExecuter();
		HashMap<LocalDate, BigDecimal> totalAmountMap = fetchPageExecuter.execute(totalTask);

		// return total amount
		return totalAmountMap;
	}

    // get one page and parse json response
	public Page getPage(String url) throws Exception {
		return JsonUtil.jsonToJavaBean(HttpConnectionUtil.connect(url));
	}

}
