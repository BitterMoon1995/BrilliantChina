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
    public Integer add(Scene scene) {
        boolean saved = save(scene);
        String id = "";
        if (saved) {
            id = scene.getId();
            saveDetails(scene, id);
        }
        return 666;
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
    public Integer edit(Scene scene) {
        Scene she = mapper.selectById(scene.getId());
        //修改时景区名防重
        String name = scene.getName();
        QueryWrapper<Scene> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        Integer count = mapper.selectCount(wrapper);
        if (!she.getName().equals(name) && count>=1) return 400;

        mapper.updateById(scene);
        saveDetails(scene,scene.getId());
        return 666;
    }

    void saveDetails(Scene scene, String id){
        List<SceneImage> introImgs = scene.getIntroImgs();
        SceneImage postcard = scene.getPostcard();
        Slider slider = scene.getSlider();
        SceneImage richText = scene.getRichText();

        //增量修改，有则改之，无则麻了
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

        //修改了项目的轮播图或名片，要把原图片的置顶、顺序、URL这些信息同步到新图片上
        //如果原图片没有这些信息或者根本就没有原图片，跳过
        if (postcard!=null) {
            QueryWrapper<SceneImage> wrapper = new QueryWrapper<>();
            wrapper.eq("type","postcard").eq("scene_id",id);
            SceneImage preOne = imageService.getOne(wrapper);
            postcard.setSceneId(id);

            //没有原图片
            if (preOne!=null) {
                //原图片无该信息   这就是爪哇的判空吗？i了
                if (preOne.getTop() != null)
                    postcard.setTop(preOne.getTop());
                if (preOne.getUrl() != null)
                    postcard.setUrl(preOne.getUrl());
                if (preOne.getOrderNum() != null)
                    postcard.setOrderNum(preOne.getOrderNum());
            }

            imageService.remove(wrapper);
            imageService.save(postcard);
        }

        if (slider!=null) {
            QueryWrapper<Slider> wrapper = new QueryWrapper<>();
            wrapper.eq("target_id",id);
            Slider preOne = sliderService.getOne(wrapper);
            slider.setTargetId(id);

            if (preOne!=null) {
                if (preOne.getTop() != null)
                    slider.setTop(preOne.getTop());
                if (preOne.getUrl() != null)
                    slider.setUrl(preOne.getUrl());
                if (preOne.getOrderNum() != null)
                    slider.setOrderNum(preOne.getOrderNum());
            }

            sliderService.remove(wrapper);
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
