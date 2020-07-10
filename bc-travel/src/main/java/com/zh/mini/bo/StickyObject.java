package com.zh.mini.bo;

import lombok.Data;

import java.util.Date;

@Data
public class StickyObject {
    String name;
    String url;
    String slogan;
    Date createTime;

    Boolean stickyTop;
    Integer stickyOrder;
    String imgId;

    Boolean sliderTop;
    Integer sliderOrder;
    String sliderId;
}
