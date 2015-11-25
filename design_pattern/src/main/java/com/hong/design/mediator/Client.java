package com.hong.design.mediator;

import com.hong.design.mediator.colleague.CDDriver;
import com.hong.design.mediator.colleague.CPU;
import com.hong.design.mediator.colleague.SoundCard;
import com.hong.design.mediator.colleague.VideoCard;
import com.hong.design.mediator.mediator.MainBoard;

/**
 * 在日常生活中，我们经常使用电脑来看电影，把这个过程描述出来，简化后假定会有如下的交互过程：
 * （1）首先是光驱要读取光盘上的数据，然后告诉主板，它的状态改变了。
 * （2）主板去得到光驱的数据，把这些数据交给CPU进行分析处理。
 * （3）CPU处理完后，把数据分成了视频数据和音频数据，通知主板，它处理完了。
 * （4）主板去得到CPU处理过后的数据，分别把数据交给显卡和声卡，去显示出视频和发出声音。
 *
 * Created by Hongwei on 2015/11/25.
 */
public class Client {
    public static void main(String[] args) {
        // 创建调停者 -- 主板
        MainBoard mainBoard = new MainBoard();
        // 创建同事类
        CDDriver cdDriver = new CDDriver(mainBoard);
        CPU cpu = new CPU(mainBoard);
        VideoCard videoCard = new VideoCard(mainBoard);
        SoundCard soundCard = new SoundCard(mainBoard);
        // 调停者通知所有同事
        mainBoard.setCdDriver(cdDriver);
        mainBoard.setCpu(cpu);
        mainBoard.setVideoCard(videoCard);
        mainBoard.setSoundCard(soundCard);
        // 开始看电影，光驱读取光盘
        cdDriver.readCD();
    }
}
