package com.zh.mini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.bo.SearchResult;
import com.zh.mini.bo.StickyObject;
import com.zh.mini.entity.Scene;
import com.zh.mini.entity.SceneImage;
import com.zh.mini.entity.Slider;
import com.zh.mini.mapper.SceneMapper;
import com.zh.mini.service.ISceneImageService;
import com.zh.mini.service.ISceneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.mini.service.ISliderService;
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
    ISliderService sliderService;
    @Override
    public void add(Scene scene) {
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
            saveDetails(scene, scene.getId());
        }

    }

    @Override
    public void resetOrder() {
        mapper.resetOrder();
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

        QueryWrapper<Slider> qSlider = new QueryWrapper<>();
        qSlider.eq("target_id",id);
        Slider slider = sliderService.getOne(qSlider);
        scene.setSlider(slider);
    }

    @Override
    public void edit(Scene scene) {
        mapper.updateById(scene);

        saveDetails(scene,scene.getId());
    }

    void saveDetails(Scene scene, String id){
        List<SceneImage> introImgs = scene.getIntroImgs();
        SceneImage postcard = scene.getPostcard();
        Slider slider = scene.getSlider();
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

        if (slider!=null) {
            QueryWrapper<Slider> wrapper = new QueryWrapper<>();
            wrapper.eq("target_id",id);
            sliderService.remove(wrapper);
            slider.setTargetId(id);
            sliderService.save(slider);
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

        QueryWrapper<Slider> sliderWrapper = new QueryWrapper<>();
        sliderWrapper.eq("target_id",id);
        sliderService.remove(sliderWrapper);
    }

    @Override
    public List<Scene> search(String username, String name) {
        return mapper.query(username,name);
    }

    @Override
    public List<Scene> allSearch(String name) {
        return mapper.allSearch(name);
    }

    @Override
    public List<StickyObject> getSticky(Integer index, Integer offset) {
        return mapper.getSticky(index,offset);
    }

    @Override
    public List<StickyObject> search(Integer index, Integer offset, String name) {
        return mapper.search(index,offset,name);
    }

    @Override
    public List<SearchResult> search(String s) {
        return mapper.search(s);
    }

    @Override
    public List<SearchResult> showList() {
        return mapper.showList();
    }
}
