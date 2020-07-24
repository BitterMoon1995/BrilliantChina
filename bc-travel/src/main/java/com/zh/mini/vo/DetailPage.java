package com.zh.mini.vo;

import com.zh.mini.entity.SceneImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailPage {

    List<SceneImage> introImgs;

    String name;
    String location;
    String level;
    Integer price;
    Double longitude;//经度
    Double latitude;//纬度

    SceneImage richText;
}
