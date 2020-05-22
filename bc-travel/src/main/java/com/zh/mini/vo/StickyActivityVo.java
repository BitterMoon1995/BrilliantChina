package com.zh.mini.vo;

import com.zh.mini.bo.StickyActivity;
import com.zh.mini.bo.StickyScene;
import lombok.Data;

import java.util.List;

@Data
public class StickyActivityVo {
    List<StickyActivity> stickyList;
    Integer total;
}
