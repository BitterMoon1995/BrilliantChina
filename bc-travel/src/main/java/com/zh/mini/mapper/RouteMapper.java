package com.zh.mini.mapper;

import com.zh.mini.entity.Route;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.mini.entity.Scene;
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
public interface RouteMapper extends BaseMapper<Route> {
    @Select("SELECT * FROM mini_route WHERE username = #{username} AND LOCATE( #{name} ,name) > 0")
    List<Route> query(String username, String name);

    @Select("SELECT * FROM mini_route WHERE LOCATE( #{name} ,name) > 0")
    List<Route> allSearch(String name);
}
