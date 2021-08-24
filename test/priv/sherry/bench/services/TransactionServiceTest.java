package priv.sherry.bench.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import org.junit.Test;

import priv.sherry.bench.beans.Page;
import priv.sherry.bench.beans.Transaction;

/**
 * @ClassName: TransactionServiceTest
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class TransactionServiceTest {

	TransactionService service = new TransactionService();

	/**
	 * Test method for
	 * {@link priv.sherry.bench.services.TransactionService#getPage(java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_GetPage_Success() throws Exception {
		String url = "https://resttest.bench.co/transactions/4.json";
		Page page = service.getPage(url);
		assertEquals(38, page.getTotalCount());
		assertEquals(4, page.getPage());
		assertEquals(8, page.getTransactions().size());
		String actualString = "";
		for (Transaction t : page.getTransactions()) {
			actualString += t.getDate() + ":" + t.getAmount() + ":" + t.getCompany() + ":" + t.getLedger() + "\r";
		}
		String expected = "2013-12-13:-117.81:LONDON DRUGS 78 POSTAL VANCOUVER BC:Insurance Expense" + "\r"
				+ "2013-12-13:-520.85:ECHOSIGN xxxxxxxx6744 CA xx8.80 USD @ xx0878:Equipment Expense" + "\r"
				+ "2013-12-13:-5518.17:APPLE STORE #R280 VANCOUVER BC:Equipment Expense" + "\r"
				+ "2013-12-20:-1874.75:NINJA STAR WORLD VANCOUVER BC:Equipment Expense" + "\r"
				+ "2013-12-12:-30.69:DHL YVR GW RICHMOND BC:Postage & Shipping Expense" + "\r"
				+ "2013-12-12:-42.53:FEDEX xxxxx5291 MISSISSAUGA ON:Office Expense" + "\r"
				+ "2013-12-12:-63.01:GROWINGCITY.COM xxxxxx4926 BC:Web Hosting & Services Expense" + "\r"
				+ "2013-12-12:-91.12:NESTERS MARKET #x0064 VANCOUVER BC:Business Meals & Entertainment Expense" + "\r";
		assertEquals(expected, actualString);
	}

	@Test(expected = Exception.class)
	public void test_GetPage_NullString() throws Exception {
		String url = "";
		service.getPage(url);
	}

	@Test(expected = Exception.class)
	public void test_Call_Exception() throws Exception {
		String url = "anan";
		service.getPage(url);
	}

	/**
	 * Test method for
	 * {@link priv.sherry.bench.services.TransactionService#getAllTransactions()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_GetAllTransactions_Success() throws Exception {
		Map<LocalDate, BigDecimal> actual = service.getAllTransactions();
		String actualString = "";
		for (Map.Entry<LocalDate, BigDecimal> entry : actual.entrySet()) {
			LocalDate date = entry.getKey();
			BigDecimal amount = entry.getValue();
			actualString += date + ":" + amount + "\r";
		}
		String expected = "2013-12-15:-5.39" + "\r" + "2013-12-12:-227.35" + "\r" + "2013-12-13:-1229.58" + "\r"
				+ "2013-12-22:-110.71" + "\r" + "2013-12-20:-4054.60" + "\r" + "2013-12-21:-17.98" + "\r"
				+ "2013-12-18:-1841.29" + "\r" + "2013-12-19:19753.31" + "\r" + "2013-12-16:-4575.53" + "\r"
				+ "2013-12-17:10686.28" + "\r";
		assertEquals(expected, actualString);
	}

}
