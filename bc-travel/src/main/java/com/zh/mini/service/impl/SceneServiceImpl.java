package com.zh.mini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.Scene;
import com.zh.mini.entity.SceneImage;
import com.zh.mini.entity.Swiper;
import com.zh.mini.mapper.SceneMapper;
import com.zh.mini.service.ISceneImageService;
import com.zh.mini.service.ISceneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.mini.service.ISwiperService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

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
    SceneMapper mapper;
    @Autowired
    ISceneImageService imageService;
    @Autowired
    ISwiperService swiperService;
    @Override
    public void saveScene(Scene scene) {
        boolean isBlank = StringUtils.isBlank(scene.getId());
        if (isBlank){
            boolean saved = save(scene);
            String id = "";
            if (saved) {
                id = scene.getId();
                saveDetails(scene, id);
            }
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

    @Override
    public void resetOrder() {
        List<Scene> list = this.list();
        Comparator<Object> comparator = Collator.getInstance(Locale.CHINA);
        Collections.sort(list,comparator);

        System.out.printf(String.valueOf(list));

    }

    @Override
    public void setImgs(Scene scene, String id) {
        QueryWrapper<SceneImage> qIntros = new QueryWrapper<>();
        qIntros.eq("scene_id",id).eq("type","intros");
        List<SceneImage> intros = imageService.list(qIntros);
        scene.setIntroImgs(intros);

        QueryWrapper<SceneImage> qPostcard = new QueryWrapper<>();
        qPostcard.eq("scene_id",id).eq("type","postcard");
        SceneImage postcard = imageService.getOne(qPostcard);
        scene.setPostcard(postcard);

        QueryWrapper<Swiper> qSwiper = new QueryWrapper<>();
        qSwiper.eq("target_id",id);
        Swiper swiper = swiperService.getOne(qSwiper);
        scene.setSwiper(swiper);
    }

    @Override
    public void editScene(Scene scene) {
        mapper.updateById(scene);

        saveDetails(scene,scene.getId());
    }

    void saveDetails(Scene scene, String id){
        List<SceneImage> introImgs = scene.getIntroImgs();
        SceneImage postcard = scene.getPostcard();
        Swiper swiper = scene.getSwiper();
        SceneImage richText = scene.getRichText();

        if (introImgs!=null) {
            QueryWrapper<SceneImage> wrapper = new QueryWrapper<>();
            wrapper.eq("type","intros").eq("scene_id",id);
            imageService.remove(wrapper);

            int size = introImgs.size();
            for (int i = 0; i < size; i++) {
                SceneImage image = introImgs.get(i);
                image.setSceneId(id);
                image.setOrderNum(i);
                imageService.save(image);
            }
        }

        if (postcard!=null) {
            QueryWrapper<SceneImage> wrapper = new QueryWrapper<>();
            wrapper.eq("type","postcard").eq("scene_id",id);
            imageService.remove(wrapper);
            postcard.setSceneId(id);
            imageService.save(postcard);
        }

        if (swiper!=null) {
            QueryWrapper<Swiper> wrapper = new QueryWrapper<>();
            wrapper.eq("target_id",id);
            swiper.setTargetId(id);
            swiperService.save(swiper);
        }

        if (richText!=null) {
            QueryWrapper<SceneImage> wrapper = new QueryWrapper<>();
            wrapper.eq("type","richText").eq("scene_id",id);
            imageService.remove(wrapper);
            richText.setSceneId(id);
            imageService.save(richText);
        }

    }

    public void delDetails(String id){
        QueryWrapper<SceneImage> sceneImageWrapper = new QueryWrapper<>();
        sceneImageWrapper.eq("scene_id",id);
        imageService.remove(sceneImageWrapper);

        QueryWrapper<Swiper> swiperWrapper = new QueryWrapper<>();
        swiperWrapper.eq("target_id",id);
        swiperService.remove(swiperWrapper);
    }

    @Override
    public List<Scene> search(String username, String name) {
        return mapper.query(username,name);
    }

    @Override
    public List<Scene> allSearch(String name) {
        return mapper.allSearch(name);
    }
}
