package net.blissmall.puff.common.utils.http;

import java.io.UnsupportedEncodingException;

/**
 * @desc HTTP的返回参数
 * @author Pigo.can
 * @email rushingpig@163.com
 * @date 2015年8月24日 下午2:45:46
 * @version V1.0
 */
public class ResponseContent {

	private String encoding;

	private byte[] contentBytes;

	private int statusCode;

	private String contentType;

	private String contentTypeString;

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentTypeString() {
		return this.contentTypeString;
	}

	public void setContentTypeString(String contenttypeString) {
		this.contentTypeString = contenttypeString;
	}

	public String getContent() throws UnsupportedEncodingException {
		return this.getContent(this.encoding);
	}

	public String getContent(String encoding) throws UnsupportedEncodingException {
		if (encoding == null) {
			return new String(contentBytes);
		}
		return new String(contentBytes, encoding);
	}

	public String getUTFContent() throws UnsupportedEncodingException {
		return this.getContent("UTF-8");
	}

	public byte[] getContentBytes() {
		return contentBytes;
	}

	public void setContentBytes(byte[] contentBytes) {
		this.contentBytes = contentBytes;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}