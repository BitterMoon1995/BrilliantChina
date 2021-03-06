package com.zh.mini.service;

import com.zh.mini.bo.SearchResult;
import com.zh.mini.entity.Scene;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.mini.bo.StickyObject;

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

    Integer add(Scene scene);

    void resetOrder();

    void setImgs(Scene scene, String id);

    Integer edit(Scene scene);

    void delDetails(String id);

    List<Scene> search(String username, String name);

    List<Scene> allSearch(String name);

    List<StickyObject> getSticky(Integer index, Integer offset);

    List<StickyObject> search(Integer index, Integer offset, String name);

    List<SearchResult> search(String s);

    List<SearchResult> showList();

}
