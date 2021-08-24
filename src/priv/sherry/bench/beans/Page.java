package priv.sherry.bench.beans;

import java.util.List;

/**
 * @ClassName: Page
 * @Description: represent page
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class Page {

	// total count of all transactions
	private int totalCount;
	// current page number
	private int page;
	// list of transactions in current page
	private List<Transaction> transactions;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
