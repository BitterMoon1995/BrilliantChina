package com.zh.mini.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

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
@TableName("mini_scene")
public class Scene implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private String slogan;//宣传语

    private String location;//景区地址

    private String username;

    private Date createTime;

    //来吧，展示景区特有字段，心在跳爱在烧

    private String  level;//星级

    private Integer price;

    private Double longitude;//经度  经纬度将用于详情页路线规划导航

    private Double latitude;//纬度

    //图片区

    @TableField(exist = false)
    private List<SceneImage> introImgs;//详情页轮播图，6张

    @TableField(exist = false)
    private SceneImage postcard;//名片图

    @TableField(exist = false)
    private SceneImage richText;//富文本图片

    @TableField(exist = false)
    private Slider slider;//首页轮播图
}
