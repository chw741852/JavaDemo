package com.hong.design.mediator.mediator;

import com.hong.design.mediator.colleague.AbstractColleague;

/**
 * 调停者接口
 * Created by Hongwei on 2015/11/25.
 */
public interface IMediator {
    /**
     * 同事对象在自身改变的时候通知调停者方法
     * 让调停者去负责相应的与其他对象进行交互
     * @param colleague 同事对象
     */
    void changed(AbstractColleague colleague);
}
