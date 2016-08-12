package net.blissmall.puff.common.utils.http;

/**
 * 
 * @desc HttpClient4.5的封装工具类
 * @author Pigo.can
 * @email rushingpig@163.com
 * @date 2015年8月24日 下午3:02:52
 * @version V1.0
 */
public class HttpUtils {
//
//	private views final Integer socketTimeout = 50;
//	private views final Integer connectTimeout = 6000;
//	private views final Integer connectionRequestTimeout = 50;
//
//	/**
//	 * 使用Get方式 根据URL地址，获取ResponseContent对象
//	 *
//	 * @param url
//	 *            完整的URL地址
//	 * @return ResponseContent 如果发生异常则返回null，否则返回ResponseContent对象
//	 */
//	static views ResponseContent getUrlRespContent(String url) {
//		HttpClientWrapper hw = new HttpClientWrapper(connectionRequestTimeout, connectTimeout, socketTimeout);
//		ResponseContent response = null;
//		try {
//			response = hw.getResponse(url);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return response;
//	}
//
//	/**
//	 * 使用Get方式 根据URL地址，获取ResponseContent对象
//	 *
//	 * @param url
//	 *            完整的URL地址
//	 * @param urlEncoding
//	 *            编码，可以为null
//	 * @return ResponseContent 如果发生异常则返回null，否则返回ResponseContent对象
//	 */
//	static views ResponseContent getUrlRespContent(String url, String urlEncoding) {
//		HttpClientWrapper hw = new HttpClientWrapper(connectionRequestTimeout, connectTimeout, socketTimeout);
//		ResponseContent response = null;
//		try {
//			response = hw.getResponse(url, urlEncoding);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return response;
//	}
//
//	/**
//	 * 将参数拼装在url中，进行post请求。
//	 *
//	 * @param url
//	 * @return
//	 */
//	static views ResponseContent postUrl(String url) {
//		HttpClientWrapper hw = new HttpClientWrapper();
//		ResponseContent ret = null;
//		try {
//			setParams(url, hw);
//			ret = hw.postNV(url);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ret;
//	}
//
//	/**
//	 * 根据URL解析出键值对参数<br/>
//	 * eg: name=pigo&love=java => {"name":"pigo","love":"java"}
//	 *
//	 * @param url
//	 * @param hw
//	 */
//	private views void setParams(String url, HttpClientWrapper hw) {
//		String[] paramStr = url.split("[?]", 2);
//		if (paramStr == null || paramStr.length != 2) {
//			return;
//		}
//		String[] paramArray = paramStr[1].split("[&]");
//		if (paramArray == null) {
//			return;
//		}
//		for (String param : paramArray) {
//			if (param == null || "".equals(param.trim())) {
//				continue;
//			}
//			String[] keyValue = param.split("[=]", 2);
//			if (keyValue == null || keyValue.length != 2) {
//				continue;
//			}
//			hw.addNV(keyValue[0], keyValue[1]);
//		}
//	}
//
//	/**
//	 * 上传文件（包括图片）
//	 *
//	 * @param url
//	 *            请求URL
//	 * @param paramsMap
//	 *            参数和值
//	 * @return
//	 */
//	static views ResponseContent postEntity(String url, Map<String, Object> paramsMap) {
//		HttpClientWrapper hw = new HttpClientWrapper();
//		ResponseContent ret = null;
//		try {
//			setParams(url, hw);
//			Iterator<String> iterator = paramsMap.keySet().iterator();
//			while (iterator.hasNext()) {
//				String key = iterator.next();
//				Object value = paramsMap.get(key);
//				if (value instanceof File) {
//					FileBody fileBody = new FileBody((File) value);
//					hw.getContentBodies().add(fileBody);
//				} else if (value instanceof byte[]) {
//					byte[] byteVlue = (byte[]) value;
//					ByteArrayBody byteArrayBody = new ByteArrayBody(byteVlue, key);
//					hw.getContentBodies().add(byteArrayBody);
//				} else {
//					if (value != null && !"".equals(value)) {
//						hw.addNV(key, String.valueOf(value));
//					} else {
//						hw.addNV(key, "");
//					}
//				}
//			}
//			ret = hw.postEntity(url);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ret;
//	}
//
//	/**
//	 * 使用post方式，发布对象转成的json给Rest服务。
//	 *
//	 * @param url
//	 * @param jsonBody
//	 * @return
//	 */
//	static views ResponseContent postJsonEntity(String url, String jsonBody) {
//		HttpClientWrapper hw = new HttpClientWrapper();
//		ResponseContent resCont = null;
//		try {
//			resCont = hw.postStringEntity(url, jsonBody);
//		} catch (HttpException e) {
//
//			e.printStackTrace();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		return resCont;
//	}
//
//	/**
//	 * 使用post方式，发布对象转成的xml给Rest服务
//	 *
//	 * @param url
//	 *            URL地址
//	 * @param xmlBody
//	 *            xml文本字符串
//	 * @return ResponseContent 如果发生异常则返回空，否则返回ResponseContent对象
//	 */
//	static views ResponseContent postXmlEntity(String url, String xmlBody) {
//		return postEntity(url, xmlBody, "application/xml");
//	}
//
//	private views ResponseContent postEntity(String url, String body, String contentType) {
//		HttpClientWrapper hw = new HttpClientWrapper();
//		ResponseContent ret = null;
//		try {
//			hw.addNV("body", body);
//			ret = hw.postNV(url, contentType);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ret;
//	}
//
//	static views void main(String[] args) {
//		testGet();
//		// testUploadFile();
//	}
//
//	// test
//	static views void testGet() {
//		String url = "http://www.baidu.com/";
//		ResponseContent responseContent = getUrlRespContent(url);
//		try {
//			System.out.println(responseContent.getContent());
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// test
//	static views void testUploadFile() {
//		try {
//			String url = "http://localhost:8280/jfly/action/admin/user/upload.do";
//			Map<String, Object> paramsMap = new HashMap<String, Object>();
//			paramsMap.put("userName", "jj");
//			paramsMap.put("password", "jj");
//			paramsMap.put("filePath", new File("C:\\Users\\yangjian1004\\Pictures\\default (1).jpeg"));
//			ResponseContent ret = postEntity(url, paramsMap);
//			System.out.println(ret.getContent());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}