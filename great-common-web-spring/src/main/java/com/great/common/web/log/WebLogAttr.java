package com.great.common.web.log;

import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created on 2021/9/8 16:59
 *
 * @author Y.X
 */
public class WebLogAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * HttpServletRequest
     */
    private HttpServletRequest request;

    /**
     * JoinPoint
     */
    private JoinPoint point;

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 开始时间
     */
    private long startTime;

    /**
     * 结束时间
     */
    private long endTime;

    /**
     * 请求URL
     */
    private String url;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * 请求方法
     */
    private String httpMethod;

    /**
     * 执行方法
     */
    private String method;

    /**
     * 执行类名
     */
    private String className;

    /**
     * 参数列表（不包括 servlet）
     */
    private Map<String, Object> params;

    /**
     * 执行结果
     */
    private Object result;

    /**
     * 异常
     */
    private Throwable exception;

    /**
     * 接口耗时
     */
    private long cost;

    public WebLogAttr() {
        this.startTime = System.currentTimeMillis();
        this.endTime = this.startTime;
        this.requestId = UUID.randomUUID().toString().replace("-", "");
    }

    public JoinPoint getPoint() {
        return point;
    }

    public void setPoint(JoinPoint point) {
        this.point = point;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getShortRequestId() {
        return this.requestId.substring(0, 8).toUpperCase();
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String shortClassName() {
        return this.className == null || this.className.length() <= 1
                ? "" : this.className.substring(this.className.lastIndexOf('.') + 1);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public long getCost() {
        return this.cost == 0 ? endTime - startTime : this.cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    void setResultAndEndTime(Object result) {
        this.result = result;
        this.endTime = System.currentTimeMillis();
    }

    void addParam(String name, Object value) {
        if (this.params == null) {
            this.params = new TreeMap<>();
        }
        this.params.put(name, value);
    }
}
