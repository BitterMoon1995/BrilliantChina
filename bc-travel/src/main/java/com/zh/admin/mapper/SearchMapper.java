package com.zh.admin.mapper;

import com.zh.admin.entity.Search;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author God周周神
 * @since 2020-07-16
 */
@Repository
public interface SearchMapper extends BaseMapper<Search> {

    @Select("DELETE FROM search \n" +
            "WHERE search_time = \n" +
            "(SELECT sub.search_time FROM\n" +
            "(SELECT * FROM search\n" +
            "WHERE search_time = \n" +
            "(SELECT MIN(search_time) FROM search)\n" +
            ")AS sub)\n" +
            "AND searcher = #{openid}")
    void delEarliest(String openid);
}
