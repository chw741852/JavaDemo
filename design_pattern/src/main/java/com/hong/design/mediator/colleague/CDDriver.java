package com.hong.design.mediator.colleague;

import com.hong.design.mediator.mediator.IMediator;

/**
 * 光驱
 * Created by Hongwei on 2015/11/25.
 */
public class CDDriver extends AbstractColleague {
    public CDDriver(IMediator mediator) {
        super(mediator);
    }

    // 光驱读取出来的数据
    private String data = "";

    public String getData() {
        return data;
    }

    /**
     * 读取光盘
     */
    public void readCD() {
        // 逗号前是视频数据，逗号后是声音
        data = "One Piece,海贼王我当定了";
        // 通知主板自己状态发送改变
        getMediator().changed(this);
    }
}
