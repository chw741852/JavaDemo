package com.hong.test.proxy.statics;

/**
 * 委托类（包含业务逻辑）
 * Created by Hongwei on 2015/10/27.
 */
public class AccountImpl implements Account {
    @Override
    public void queryAccount() {
        System.out.println("查询账户...");
    }

    @Override
    public void updateAccount() {
        System.out.println("更新账户...");
    }
}
