package com.hong.test.rpc.ex2.support;

/**
 * RPC响应主体
 * Created by Hongwei on 2015/11/12.
 */
public class RpcResponse {
    private String requestId;   // 请求ID
    private Throwable error;    // 异常
    private Object result;      // 响应

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isError() {
        return error != null;
    }
}
