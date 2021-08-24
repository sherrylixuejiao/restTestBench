package priv.sherry.bench.beans;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @ClassName: Transaction
 * @Description: represent transaction
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class Transaction {
	// "Date": "2013-12-22"
	private LocalDate date;
	// "Ledger": "Phone & Internet Expense"
	private String ledger;
	// "Amount": "-110.71"
	private BigDecimal amount;
	// "Company": "SHAW CABLESYSTEMS CALGARY AB"
	private String company;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getLedger() {
		return ledger;
	}
	public void setLedger(String ledger) {
		this.ledger = ledger;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}
