package com.zh.mini.vo;

import com.zh.mini.entity.Scene;
import lombok.Data;

import java.util.List;

@Data
public class SceneVo {
    List<Scene> sceneList;
    Integer total;
}
