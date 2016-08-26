package net.blissmall.puff.vo.http;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Maintains request data for a request that was redirected, so that after authentication
 * the user can be redirected to the originally requested page.
 */
public class SavedRequest implements Serializable {

    //TODO - complete JavaDoc

    private String method;
    private String queryString;
    private String requestURI;

    /**
     * Constructs a new instance from the given HTTP request.
     *
     * @param request the current request to save.
     */
    public SavedRequest(HttpServletRequest request) {
        this.method = request.getMethod();
        this.queryString = request.getQueryString();
        this.requestURI = request.getRequestURI();
    }

    public String getMethod() {
        return method;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getRequestUrl() {
        StringBuilder requestUrl = new StringBuilder(getRequestURI());
        if (getQueryString() != null) {
            requestUrl.append("?").append(getQueryString());
        }
        return requestUrl.toString();
    }
}