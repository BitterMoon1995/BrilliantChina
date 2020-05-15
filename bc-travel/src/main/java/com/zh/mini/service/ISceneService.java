package com.zh.mini.service;

import com.zh.mini.entity.Scene;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
