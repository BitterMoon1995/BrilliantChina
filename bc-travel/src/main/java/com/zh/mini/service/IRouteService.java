package com.zh.mini.service;

import com.zh.mini.entity.Route;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
public interface IRouteService extends IService<Route> {

    void saveRoute(Route route);
}
