package com.k.passbook.security;

/**
 * <h1>用 ThreadLocal 去单独存储每一个线程接待的 Token 信息</h1>
 */
public class AccessContext {
    private  static final ThreadLocal<String> token = new ThreadLocal<String>();

    public static String getToken() {
        return token.get();
    }

    public static void setToken(String tokenStr){
        token.set(tokenStr);
    }


    /**
     * 移除当前的token信息
     */
    public static void clearAccessKey() {
        token.remove();
    }
}
