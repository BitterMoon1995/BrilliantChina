package com.zh.mini.service;

import com.zh.mini.bo.SearchResult;
import com.zh.mini.bo.StickyObject;
import com.zh.mini.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.mini.entity.Route;
import com.zh.mini.entity.Activity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
public interface IActivityService extends IService<Activity> {

    Integer add(Activity activity);

    void resetOrder();

    void setImgs(Activity activity, String id);

    Integer edit(Activity activity);

    void delDetails(String id);

    List<Activity> search(String username, String name);

    List<Activity> allSearch(String name);

    List<StickyObject> getSticky(Integer index, Integer offset);

    List<StickyObject> search(Integer index, Integer offset, String name);

    List<SearchResult> search(String s);
}
