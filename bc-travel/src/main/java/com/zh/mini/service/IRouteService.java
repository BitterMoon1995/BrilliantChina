package com.zh.mini.service;

import com.zh.mini.bo.StickyRoute;
import com.zh.mini.bo.StickyScene;
import com.zh.mini.entity.Route;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.mini.entity.Route;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
public interface IRouteService extends IService<Route> {

    void add(Route route);

    void resetOrder();

    void setImgs(Route route, String id);

    void edit(Route route);

    void delDetails(String id);

    List<Route> search(String username, String name);

    List<Route> allSearch(String name);

    List<StickyRoute> getSticky(Integer index, Integer offset);

    List<StickyRoute> search(Integer index, Integer offset, String name);
}
