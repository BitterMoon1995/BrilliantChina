package com.zh.mini.bo;

import lombok.Data;

import java.util.Date;

@Data
public class StickyScene {
    String name;
    String url;
    Date createTime;

    Boolean stickyTop;
    Integer stickyOrder;
    String imgId;

    Boolean sliderTop;
    Integer sliderOrder;
    String sliderId;
}
