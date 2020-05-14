package com.zh.mini.mapper;

import com.zh.mini.entity.Scene;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface SceneMapper extends BaseMapper<Scene> {

    //尝试从DB层排序，失败........
//    @Select("select * from mini_scene order by convert(name using gbk) collate gbk_chinese_ci")
//    List<Object> resetOrder();
}
