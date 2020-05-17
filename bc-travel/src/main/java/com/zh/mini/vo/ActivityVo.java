package com.zh.mini.vo;

import com.zh.mini.entity.Activity;
import lombok.Data;

import java.util.List;

@Data
public class ActivityVo {
    List<Activity> activityList;
    Integer total;
}
