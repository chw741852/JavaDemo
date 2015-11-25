package com.hong.design.mediator.mediator;

import com.hong.design.mediator.colleague.*;

/**
 * 主板
 * Created by Hongwei on 2015/11/25.
 */
public class MainBoard implements IMediator {
    private CDDriver cdDriver;      // 光驱
    private CPU cpu;                // cpu
    private VideoCard videoCard;    // 显卡
    private SoundCard soundCard;    // 声卡

    public void setCdDriver(CDDriver cdDriver) {
        this.cdDriver = cdDriver;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void setVideoCard(VideoCard videoCard) {
        this.videoCard = videoCard;
    }

    public void setSoundCard(SoundCard soundCard) {
        this.soundCard = soundCard;
    }

    @Override
    public void changed(AbstractColleague colleague) {
        if (colleague instanceof CDDriver) {
            operationCDDriverReadData((CDDriver) colleague);
        } else if (colleague instanceof CPU) {
            operationCpu((CPU) colleague);
        }
    }

    /**
     * 处理光驱读取数据后，交给cpu处理
     * @param cdDriver
     */
    private void operationCDDriverReadData(CDDriver cdDriver) {
        cpu.executeData(cdDriver.getData());
    }

    /**
     * 处理CPU处理完数据后，显卡与声卡处理cpu数据
     * @param cpu
     */
    private void operationCpu(CPU cpu) {
        videoCard.showData(cpu.getVideoData());
        soundCard.soundData(cpu.getSoundData());
    }
}
