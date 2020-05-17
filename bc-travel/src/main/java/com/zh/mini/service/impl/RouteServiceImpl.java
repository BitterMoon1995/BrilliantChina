package com.zh.mini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.*;
import com.zh.mini.mapper.RouteMapper;
import com.zh.mini.mapper.RouteMapper;
import com.zh.mini.service.IRouteImageService;
import com.zh.mini.service.IRouteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.mini.service.IRouteImageService;
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
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements IRouteService {

    @Autowired
    RouteMapper mapper;
    @Autowired
    IRouteImageService imageService;
    @Autowired
    ISwiperService swiperService;
    @Override
    public void add(Route route) {
        boolean isBlank = StringUtils.isBlank(route.getId());
        if (isBlank){
            boolean saved = save(route);
            String id = "";
            if (saved) {
                id = route.getId();
                saveDetails(route, id);
            }
        }
        else {
            updateById(route);

            QueryWrapper<RouteImage> imageQueryWrapper = new QueryWrapper<>();
            imageQueryWrapper.eq("route_id", route.getId());
            imageService.remove(imageQueryWrapper);

            QueryWrapper<Swiper> swiperQueryWrapper = new QueryWrapper<>();
            swiperQueryWrapper.eq("target_id", route.getId());
            swiperService.remove(swiperQueryWrapper);

            saveDetails(route, route.getId());
        }

    }

    @Override
    public void resetOrder() {
        List<Route> list = this.list();
        Comparator<Object> comparator = Collator.getInstance(Locale.CHINA);
        Collections.sort(list,comparator);

        System.out.printf(String.valueOf(list));

    }

    @Override
    public void setImgs(Route route, String id) {
        QueryWrapper<RouteImage> qIntros = new QueryWrapper<>();
        qIntros.eq("route_id",id).eq("type","intros");
        List<RouteImage> intros = imageService.list(qIntros);
        route.setIntroImgs(intros);

        QueryWrapper<RouteImage> qPostcard = new QueryWrapper<>();
        qPostcard.eq("route_id",id).eq("type","postcard");
        RouteImage postcard = imageService.getOne(qPostcard);
        route.setPostcard(postcard);

        QueryWrapper<Swiper> qSwiper = new QueryWrapper<>();
        qSwiper.eq("target_id",id);
        Swiper swiper = swiperService.getOne(qSwiper);
        route.setSwiper(swiper);
    }

    @Override
    public void edit(Route route) {
        mapper.updateById(route);

        saveDetails(route,route.getId());
    }

    void saveDetails(Route route, String id){
        List<RouteImage> introImgs = route.getIntroImgs();
        RouteImage postcard = route.getPostcard();
        Swiper swiper = route.getSwiper();
        RouteImage richText = route.getRichText();

        if (introImgs!=null) {
            QueryWrapper<RouteImage> wrapper = new QueryWrapper<>();
            wrapper.eq("type","intros").eq("route_id",id);
            imageService.remove(wrapper);

            int size = introImgs.size();
            for (int i = 0; i < size; i++) {
                RouteImage image = introImgs.get(i);
                image.setRouteId(id);
                image.setOrderNum(i);
                imageService.save(image);
            }
        }

        if (postcard!=null) {
            QueryWrapper<RouteImage> wrapper = new QueryWrapper<>();
            wrapper.eq("type","postcard").eq("route_id",id);
            imageService.remove(wrapper);
            postcard.setRouteId(id);
            imageService.save(postcard);
        }

        if (swiper!=null) {
            QueryWrapper<Swiper> wrapper = new QueryWrapper<>();
            wrapper.eq("target_id",id);
            swiper.setTargetId(id);
            swiperService.save(swiper);
        }

        if (richText!=null) {
            QueryWrapper<RouteImage> wrapper = new QueryWrapper<>();
            wrapper.eq("type","richText").eq("route_id",id);
            imageService.remove(wrapper);
            richText.setRouteId(id);
            imageService.save(richText);
        }

    }

    public void delDetails(String id){
        QueryWrapper<RouteImage> routeImageWrapper = new QueryWrapper<>();
        routeImageWrapper.eq("route_id",id);
        imageService.remove(routeImageWrapper);

        QueryWrapper<Swiper> swiperWrapper = new QueryWrapper<>();
        swiperWrapper.eq("target_id",id);
        swiperService.remove(swiperWrapper);
    }

    @Override
    public List<Route> search(String username, String name) {
        return mapper.query(username,name);
    }

    @Override
    public List<Route> allSearch(String name) {
        return mapper.allSearch(name);
    }
}
