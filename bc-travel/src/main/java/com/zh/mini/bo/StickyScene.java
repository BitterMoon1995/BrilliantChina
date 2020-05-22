package com.zh.mini.bo;

import lombok.Data;

@Data
public class StickyScene {
    String name;
    String url;

    Boolean stickyTop;
    Integer stickyOrder;
    String imgId;

    Boolean sliderTop;
    Integer sliderOrder;
    String sliderId;
}
