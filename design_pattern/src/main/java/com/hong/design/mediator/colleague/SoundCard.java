package com.hong.design.mediator.colleague;

import com.hong.design.mediator.mediator.IMediator;

/**
 * 声卡
 * Created by Hongwei on 2015/11/25.
 */
public class SoundCard extends AbstractColleague {
    public SoundCard(IMediator mediator) {
        super(mediator);
    }

    /**
     * 按照音频数据发出声音
     * @param data
     */
    public void soundData(String data) {
        System.out.println("画外音：" + data);
    }
}
