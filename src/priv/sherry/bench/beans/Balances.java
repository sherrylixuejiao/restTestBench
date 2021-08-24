package priv.sherry.bench.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Balances
 * @Description: represent running daily balances
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class Balances {

	// a list of all the dates
	private List<LocalDate> dates; 
	//hashmap for running daily balance
	private Map<LocalDate, BigDecimal> balances; 

	public Balances(Map<LocalDate, BigDecimal> map) {
		dates = new ArrayList<LocalDate>(map.keySet());
		balances = map;
	}

	/**
	 * 
	 * @Title: sortDates
	 * @Description: sort the dates ASC
	 * @param  
	 * @return void 
	 * @throws
	 */
	public void sortDates() {
		Collections.sort(dates, new Comparator<LocalDate>() {

			@Override
			public int compare(LocalDate o1, LocalDate o2) {
				return o1.compareTo(o2);
			}

		});
	}

	/**
	 * 
	 * @Title: aggregateBalance
	 * @Description: aggregate the running daily balances
	 * @param 
	 * @return void 
	 * @throws
	 */
	public void aggregateBalance() {
		BigDecimal sum = new BigDecimal("0");
		for (LocalDate d : dates) {
			balances.put(d, balances.get(d).add(sum));
			sum = balances.get(d);
		}
	}

	/**
	 * 
	 * @Title: balanceString
	 * @Description: return print string
	 * @param 
	 * @return String
	 * @throws
	 */
	public String balanceString() {
		StringBuffer stringBuffer = new StringBuffer();
		for (LocalDate d : dates) {
			stringBuffer.append(d + " " + balances.get(d));
			stringBuffer.append("\n");
		}
		stringBuffer.append("\n");
		return stringBuffer.toString();
	}

	public List<LocalDate> getDates() {
		return dates;
	}

	public void setDates(List<LocalDate> dates) {
		this.dates = dates;
	}

	public Map<LocalDate, BigDecimal> getBalances() {
		return balances;
	}

	public void setBalances(Map<LocalDate, BigDecimal> balances) {
		this.balances = balances;
	}
	
	

}
