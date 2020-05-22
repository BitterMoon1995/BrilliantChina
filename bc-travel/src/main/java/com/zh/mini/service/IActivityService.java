package com.zh.mini.service;

import com.zh.mini.bo.StickyActivity;
import com.zh.mini.bo.StickyScene;
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

    void saveActivity(Activity activity);

    void resetOrder();

    void setImgs(Activity activity, String id);

    void editActivity(Activity activity);

    void delDetails(String id);

    List<Activity> search(String username, String name);

    List<Activity> allSearch(String name);

    List<StickyActivity> getSticky(Integer index, Integer offset);

    List<StickyActivity> search(Integer index, Integer offset, String name);
}
