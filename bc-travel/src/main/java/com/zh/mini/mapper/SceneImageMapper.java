package com.zh.mini.mapper;

import com.zh.mini.entity.SceneImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.mini.vo.StickyImgVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
@Repository
public interface SceneImageMapper extends BaseMapper<SceneImage> {

    //获取首页景区名片图
    @Select("SELECT s.name,s.slogan,i.src,i.url\n" +
            "FROM mini_scene s\n" +
            "LEFT JOIN mini_scene_image i\n" +
            "ON s.id = i.scene_id\n" +
            "WHERE i.type = 'postcard'\n" +
            "AND i.top = 1\n" +
            "ORDER BY i.order_num;")
    public List<StickyImgVo> getFloorList();
}
