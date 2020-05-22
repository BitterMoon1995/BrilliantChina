package com.zh.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.admin.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 暮星公主周薇儿
 * @since 2020-05-02
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("ALTER TABLE `user` ORDER BY role,username")
    void resetOrder();
}
