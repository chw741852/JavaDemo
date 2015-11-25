package com.hong.design.mediator.colleague;

import com.hong.design.mediator.mediator.IMediator;

/**
 * CPU
 * Created by Hongwei on 2015/11/25.
 */
public class CPU extends AbstractColleague {
    public CPU(IMediator mediator) {
        super(mediator);
    }

    // 分析出来的视频数据
    private String videoData = "";
    // 分析出来的声音数据
    private String soundData = "";

    public String getVideoData() {
        return videoData;
    }

    public String getSoundData() {
        return soundData;
    }

    /**
     * 处理数据
     * @param data
     */
    public void executeData(String data) {
        //把数据分解开，前面是视频数据，后面是音频数据
        String[] array = data.split(",");
        videoData = array[0];
        soundData = array[1];

        // 通知主板，CPU完成工作
        getMediator().changed(this);
    }
}
