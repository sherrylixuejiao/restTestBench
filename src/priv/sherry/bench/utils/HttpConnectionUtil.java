package priv.sherry.bench.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName: HttpConnectionUtil
 * @Description: util class for connecting rest api with http connection
 * @author Sherry
 * @date Aug 22, 2021
 *
 */
public class HttpConnectionUtil {
	/**
	 * 
	 * @Title: connect
	 * @Description: connect rest api by http connection
	 * @param urlStr
	 *            rest api url
	 * @param
	 * @param @throws
	 *            Exception
	 * @return String for json string
	 * @throws Exception
	 */
	public static String connect(String urlStr) throws Exception {
		URL url = null;
		HttpURLConnection conn = null;
		BufferedReader br = null;
		StringBuffer stringBuffer = null;
		try {
			// open connection
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();

			// set request method and properties
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setConnectTimeout(15000);
			conn.setReadTimeout(15000);

			// get json data
			int responseCode = conn.getResponseCode();
			switch(responseCode) {
				case 200:
					br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					stringBuffer = new StringBuffer();
					String line;
					while ((line = br.readLine()) != null) {
						stringBuffer.append(line);
						stringBuffer.append("\r");
					}
					break;
				case 404:
					System.out.println("Log error | 404 returned");
				case 500:
					// server side error, could do retry
					System.out.println("Log error | internal server error");
				default:
					System.out.println("Log error | failed to get Http response");
			}

		} catch (Exception e) {
			// write log 
			System.out.println(
					"Log error | Http connection exception happened when connecting url:" + urlStr + "." + "response code: "
							+ conn.getResponseCode() + " , message: " + conn.getResponseMessage() + e.getStackTrace());
			throw e;
		} finally {
			// release resource
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Log error | exception happened when closing inpout string reader:" + e.getStackTrace());
				}
			conn.disconnect();
		}
		return stringBuffer.toString();
	}
}
