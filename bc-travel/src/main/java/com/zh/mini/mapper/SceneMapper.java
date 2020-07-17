package com.zh.mini.mapper;

import com.zh.mini.bo.SearchResult;
import com.zh.mini.entity.Scene;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.mini.bo.StickyObject;
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

    //景区名中文搜索  按用户名查询
    @Select("SELECT * FROM mini_scene " +
            "WHERE username = #{username} " +
            "AND LOCATE( #{name} ,name) > 0")
    List<Scene> query(String username,String name);

    //管理员搜索
    @Select("SELECT * FROM mini_scene WHERE LOCATE( #{name} ,name) > 0")
    List<Scene> allSearch(String name);

    //后台 景区置顶 联合分页查询
//    StickyObject这个业务类要管理该景区的所有置顶信息，所以联合查询的部分字段要取别名
    @Select("SELECT s.name name, s.slogan slogan,\n" +
    ////slogan后面没加逗号我头尼玛；SQL报错时，一般是"near"之前语句有问题
            "i.id imgId,i.top stickyTop,i.order_num stickyOrder,i.url,\n" +
            "sl.id sliderId,sl.top sliderTop,sl.order_num sliderOrder\n" +
            "FROM mini_scene AS s \n" +
            "LEFT JOIN mini_scene_image AS i\n" +
            "ON s.id=i.scene_id\n" +
            "LEFT JOIN mini_slider AS sl\n" +
            "ON s.id=sl.target_id\n" +
            "WHERE i.type='postcard'\n" +
            "ORDER BY s.create_time DESC\n" +
            "LIMIT #{index},#{offset}")
    List<StickyObject> getSticky(Integer index, Integer offset);

    //后台 景区置顶 搜索 联合分页条件查询
    @Select("SELECT s.name,\n" +
            "i.id imgId,i.top stickyTop,i.order_num stickyOrder,i.url,\n" +
            "sl.id sliderId,sl.top sliderTop,sl.order_num sliderOrder\n" +
            "FROM mini_scene AS s \n" +
            "LEFT JOIN mini_scene_image AS i\n" +
            "ON s.id=i.scene_id\n" +
            "LEFT JOIN mini_slider AS sl\n" +
            "ON s.id=sl.target_id\n" +
            "WHERE i.type='postcard'\n" +
            "AND LOCATE( #{name} ,s.`name`) > 0\n" +
            "ORDER BY s.create_time DESC\n" +
            "LIMIT #{index},#{offset}")
    List<StickyObject> search(Integer index, Integer offset, String name);

    @Select("ALTER TABLE mini_scene ORDER BY create_time DESC")
    void resetOrder();

    @Select("SELECT s.name name,s.slogan slogan,i.url url,i.src src " +
            "FROM mini_scene s\n" +
            "LEFT JOIN mini_scene_image i\n" +
            "ON s.id=i.scene_id\n" +
            "WHERE (s.name LIKE CONCAT('%',#{s},'%')\n" +
            "OR s.slogan LIKE CONCAT('%',#{s},'%'))\n" +
            "AND i.type='postcard'\n" +
            "ORDER BY i.order_num")
    List<SearchResult> search(String s);

    /*
    在mybatis里面写就是应该是 like  '%${name} %' 而不是 '%#{name} %'  。
    ${name} 是不带单引号的，而#{name} 是带单引号的。
     */

    //更多 页，列表展示
    @Select("SELECT s.name name,s.slogan slogan,s.price price,s.level level,i.url url,i.src src " +
            "FROM mini_scene s\n" +
            "LEFT JOIN mini_scene_image i\n" +
            "ON s.id=i.scene_id\n" +
            "AND i.type='postcard'\n" +
            "ORDER BY i.order_num")
    List<SearchResult> showList();
}
