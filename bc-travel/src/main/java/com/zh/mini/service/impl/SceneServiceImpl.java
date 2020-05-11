package com.zh.mini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.Scene;
import com.zh.mini.entity.SceneImage;
import com.zh.mini.entity.Scene;
import com.zh.mini.entity.Swiper;
import com.zh.mini.mapper.SceneMapper;
import com.zh.mini.service.ISceneImageService;
import com.zh.mini.service.ISceneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.mini.service.ISwiperService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
@Service
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements ISceneService {

    @Autowired
    ISceneImageService imageService;
    @Autowired
    ISwiperService swiperService;
    @Override
    public void saveScene(Scene scene) {
        boolean isBlank = StringUtils.isBlank(scene.getId());
        if (isBlank){
            save(scene);
            String id = scene.getId();

            saveDetails(scene, id);
        }
        else {
            updateById(scene);

            QueryWrapper<SceneImage> imageQueryWrapper = new QueryWrapper<>();
            imageQueryWrapper.eq("scene_id", scene.getId());
            imageService.remove(imageQueryWrapper);

            QueryWrapper<Swiper> swiperQueryWrapper = new QueryWrapper<>();
            swiperQueryWrapper.eq("target_id", scene.getId());
            swiperService.remove(swiperQueryWrapper);

            saveDetails(scene, scene.getId());
        }

    }
    void saveDetails(Scene scene,String id){
        List<SceneImage> introImgs = scene.getIntroImgs();
        for (SceneImage img : introImgs) {
            img.setSceneId(id);
            imageService.save(img);
        }

        SceneImage postcard = scene.getPostcard();
        postcard.setType("postcard");
        postcard.setSceneId(id);
        postcard.setTop(false);
        imageService.save(postcard);

        Swiper swiper = scene.getSwiper();
        swiper.setTargetId(id);
        swiper.setTop(false);
        swiperService.save(swiper);
    }
}
