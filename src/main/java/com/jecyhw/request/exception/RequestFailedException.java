package com.jecyhw.request.exception;

/**
 * Created by jecyhw on 16-8-18.
 * 抓取网页失败异常
 */
public class RequestFailedException extends Exception {
    /**
     * 请求的url
     */
    String url;
    /**
     * 请求的参数
     */
    String params;

    /**
     * 请求的错误消息
     */
    String message;

    public RequestFailedException() {
        super();
    }

    public RequestFailedException(String url, String message) {
        super(message);
        initProperties(url, "", message);
    }

    public RequestFailedException(String url, Throwable cause) {
        super(cause);
        initProperties(url, "", cause.getMessage());
    }

    public RequestFailedException(String url, String params, String message) {
        super(message);
        initProperties(url, params, message);
    }

    public RequestFailedException(String url, String params, String message, Throwable cause) {
        super(message, cause);
        initProperties(url, params, message);
    }

    public RequestFailedException(String url, String params, Throwable cause) {
        super(cause);
        initProperties(url, params, cause.getMessage());
    }

    private void initProperties(String url, String params, String message) {
        this.url = url;
        this.params = params;
        this.message = message;
    }

    @Override
    public String toString() {
        return "RequestFailedException{" +
                "url='" + url + '\'' +
                ", params='" + params + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
