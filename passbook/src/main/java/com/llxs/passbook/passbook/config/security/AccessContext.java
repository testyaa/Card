package com.llxs.passbook.passbook.config.security;

public class AccessContext {

    private static final ThreadLocal<Long> token = new ThreadLocal<>();

    public static long getUserId() {
        return token.get();
    }

    public static void setTokenInfo(long userId) {
        token.set(userId);
    }

    public static void clearAccessKey() {
        token.remove();
    }
}
