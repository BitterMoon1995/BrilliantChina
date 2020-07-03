package com.zh.mini.service;

import com.zh.mini.bo.SearchResult;
import com.zh.mini.bo.StickyObject;
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

    Integer add(Route route);

    void resetOrder();

    void setImgs(Route route, String id);

    Integer edit(Route route);

    void delDetails(String id);

    List<Route> search(String username, String name);

    List<Route> allSearch(String name);

    List<StickyObject> getSticky(Integer index, Integer offset);

    List<StickyObject> search(Integer index, Integer offset, String name);

    List<SearchResult> search(String s);
}
