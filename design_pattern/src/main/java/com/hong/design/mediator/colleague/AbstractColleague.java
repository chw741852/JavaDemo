package com.hong.design.mediator.colleague;

import com.hong.design.mediator.mediator.IMediator;

/**
 * 同事抽象类
 * Created by Hongwei on 2015/11/25.
 */
public abstract class AbstractColleague {
    // 持有一个调停者对象
    private IMediator mediator;

    public AbstractColleague(IMediator mediator) {
        this.mediator = mediator;
    }

    public IMediator getMediator() {
        return mediator;
    }
}
