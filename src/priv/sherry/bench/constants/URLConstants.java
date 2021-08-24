package priv.sherry.bench.constants;

/**
 * @ClassName: URLConstants
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class URLConstants {
	public final static String ENDPOINT = "https://resttest.bench.co/transactions/";
	
	public static String getEndpointURL(int pageNumber){
		return ENDPOINT + pageNumber + ".json";
	}
}
