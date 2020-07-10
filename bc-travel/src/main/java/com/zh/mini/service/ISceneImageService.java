package com.zh.mini.service;

import com.zh.mini.entity.SceneImage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.mini.vo.StickyImgVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
public interface ISceneImageService extends IService<SceneImage> {

    List<StickyImgVo> getFloorList();
}
