package com.zh.mini.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mini_scene_image")
public class SceneImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private String src;

    private String url;//待定

    private String sceneId;//上传确定

    private Integer orderNum;//上传确定

    //intros 详情页轮播图 | postcard 明信片 | richText 详情图
    //明信片出现在首页楼层和景区列表页，两者的orderNum是一致的
    //即楼层图中置顶的景区也将处于列表页的前四行且顺序一致
    private String type;

    private Boolean top;//首页管理确定

    private Boolean isDelete;

}
