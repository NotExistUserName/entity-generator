package com.xx.timer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;
import java.util.TimerTask;

/**
 * @author xiexing
 * @description 定时任务,删除指定文件
 * @date 2020/11/16
 */
@Slf4j
@Data
public class TimeTask extends TimerTask {

    private List<String> targetFilePaths;

    public TimeTask(List<String> targetFilePaths) {
        this.targetFilePaths = targetFilePaths;
    }

    @Override
    public void run() {
        if (this.getTargetFilePaths() == null || this.getTargetFilePaths().isEmpty()) {
            return;
        }
        for (int i = 0; i < this.getTargetFilePaths().size(); i++) {
            String filePath = this.getTargetFilePaths().get(i);
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
        log.info("文件全部删除完毕");
    }
}
