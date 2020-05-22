package com.zh.mini.service;

import com.zh.mini.entity.Scene;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.mini.bo.StickyScene;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
public interface ISceneService extends IService<Scene> {

    void saveScene(Scene scene);

    void resetOrder();

    void setImgs(Scene scene, String id);

    void editScene(Scene scene);

    void delDetails(String id);

    List<Scene> search(String username, String name);

    List<Scene> allSearch(String name);

    List<StickyScene> getSticky(Integer index, Integer offset);

    List<StickyScene> search(Integer index, Integer offset, String name);
}
