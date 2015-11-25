package com.hong.design.mediator.colleague;

import com.hong.design.mediator.mediator.IMediator;

/**
 * 显卡
 * Created by Hongwei on 2015/11/25.
 */
public class VideoCard extends AbstractColleague {
    public VideoCard(IMediator mediator) {
        super(mediator);
    }

    /**
     * 显示视频数据
     * @param data
     */
    public void showData(String data) {
        System.out.println("您正在观看的是：" + data);
    }
}
