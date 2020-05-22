package com.zh.mini.vo;

import com.zh.mini.bo.StickyScene;
import lombok.Data;

import java.util.List;

@Data
public class StickySceneVo {
    List<StickyScene> stickyList;
    Integer total;
}
