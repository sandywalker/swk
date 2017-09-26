package com.wenku.queue;

/**
 * 文档处理状态
 * Created by sandy on 09/09/2017.
 */
public enum ProcState {

    wait("待处理"),processing("处理中"),success("成功"),failed("失败");

    private String title;

    public String getTitle() {
        return title;
    }

    ProcState(String title) {
        this.title = title;
    }
}
