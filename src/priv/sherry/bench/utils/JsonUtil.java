package priv.sherry.bench.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import priv.sherry.bench.beans.Page;

/**
 * @ClassName: JsonUtil
 * @Description: util class for conversion between json and java beans
 * @author Sherry
 * @date Aug 23, 2021
 *
 */
public class JsonUtil {
	/**
	 * 
	 * @Title: jsonToJavaBean @Description: convert from json string to
	 *         pages @param @param jsonString @return Page @throws
	 */
	public static Page jsonToJavaBean(String jsonString) throws Exception {

		try {
			Page page = JSON.parseObject(jsonString, new TypeReference<Page>() {
			});
			return page;
		} catch (Exception e) {
			// write log
			System.out.println("Log error | exception happened when converting json to beans " + e.getStackTrace());
			throw e;
		}

	}
}
