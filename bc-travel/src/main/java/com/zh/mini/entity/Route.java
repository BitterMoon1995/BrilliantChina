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
@TableName("mini_route")
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private String planning;//旅游线路游玩规划

    private String slogan;

    private String username;

    private Date createTime;

    @TableField(exist = false)
    private List<RouteImage> introImgs;//详情页轮播图，6张

    @TableField(exist = false)
    private RouteImage postcard;//名片图

    @TableField(exist = false)
    private RouteImage richText;//富文本图片

    @TableField(exist = false)
    private Slider slider;//首页轮播图
}
