package com.hong.test.rpc.ex2.support;

import java.util.Arrays;

/**
 * RPC请求主体
 * Created by Hongwei on 2015/11/12.
 */
public class RpcRequest {
    private String requestId;           // 请求的ID
    private String className;           // 请求类名
    private String methodName;          // 请求方法名
    private Class<?>[] parameterTypes;  // 参数类型
    private Object[] params;            // 参数值

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "requestId='" + requestId + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
