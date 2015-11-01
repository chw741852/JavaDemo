package com.hong.test.proxy.statics;

/**
 * 账户代理类（增强AccountImpl实现类）
 *
 * 静态代理：由程序员创建或特定工具自动生成源代码，再对其编译。在程序运行前，代理类的.class文件就已经存在了。
 *
 * Created by Hongwei on 2015/10/27.
 */
public class AccountProxy implements Account {
    private AccountImpl account;

    /**
     * 覆盖默认构造器
     * @param account 账户委托类
     */
    public AccountProxy(AccountImpl account) {
        this.account = account;
    }

    @Override
    public void queryAccount() {
        System.out.println("事务处理之前");
        account.queryAccount();
        System.out.println("事务处理之后");
    }

    @Override
    public void updateAccount() {
        System.out.println("事务处理之前");
        account.updateAccount();
        System.out.println("事务处理之后");
    }
}
