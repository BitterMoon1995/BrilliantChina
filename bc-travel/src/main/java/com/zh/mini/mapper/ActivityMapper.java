package com.zh.mini.mapper;

import com.zh.mini.bo.SearchResult;
import com.zh.mini.bo.StickyObject;
import com.zh.mini.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.mini.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
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
public interface ActivityMapper extends BaseMapper<Activity> {
    @Select("SELECT * FROM mini_activity WHERE username = #{username} AND LOCATE( #{name} ,name) > 0")
    List<Activity> query(String username, String name);

    @Select("SELECT * FROM mini_activity WHERE LOCATE( #{name} ,name) > 0")
    List<Activity> allSearch(String name);

    @Select("SELECT s.name,\n" +
            "i.id imgId,i.top stickyTop,i.order_num stickyOrder,i.url,\n" +
            "sl.id sliderId,sl.top sliderTop,sl.order_num sliderOrder\n" +
            "FROM mini_activity AS s \n" +
            "LEFT JOIN mini_activity_image AS i\n" +
            "ON s.id=i.activity_id\n" +
            "LEFT JOIN mini_slider AS sl\n" +
            "ON s.id=sl.target_id\n" +
            "WHERE i.type='postcard'\n" +
            "ORDER BY s.create_time DESC\n" +
            "LIMIT #{index},#{offset}")
    List<StickyObject> getSticky(Integer index, Integer offset);

    @Select("SELECT s.name,\n" +
            "i.id imgId,i.top stickyTop,i.order_num stickyOrder,i.url,\n" +
            "sl.id sliderId,sl.top sliderTop,sl.order_num sliderOrder\n" +
            "FROM mini_activity AS s \n" +
            "LEFT JOIN mini_activity_image AS i\n" +
            "ON s.id=i.activity_id\n" +
            "LEFT JOIN mini_slider AS sl\n" +
            "ON s.id=sl.target_id\n" +
            "WHERE i.type='postcard'\n" +
            "AND LOCATE( #{name} ,s.`name`) > 0\n" +
            "ORDER BY s.create_time DESC\n" +
            "LIMIT #{index},#{offset}")
    List<StickyObject> search(Integer index, Integer offset, String name);

    @Select("ALTER TABLE mini_activity ORDER BY create_time DESC")
    void resetOrder();

    @Select("SELECT a.name name,a.slogan slogan,i.url url " +
            "FROM mini_activity a\n" +
            "LEFT JOIN mini_activity_image i\n" +
            "ON a.id=i.activity_id\n" +
            "WHERE (a.name LIKE CONCAT('%',#{s},'%')\n" +
            "OR a.slogan LIKE CONCAT('%',#{s},'%'))\n" +
            "AND i.type='postcard'")
    List<SearchResult> search(String s);
}
