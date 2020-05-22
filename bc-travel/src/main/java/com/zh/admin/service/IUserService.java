package com.zh.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.admin.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 暮星公主周薇儿
 * @since 2020-05-02
 */
public interface IUserService extends IService<User> {
    void resetOrder();

}
