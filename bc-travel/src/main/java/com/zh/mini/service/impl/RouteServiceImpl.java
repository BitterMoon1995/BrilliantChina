package com.zh.mini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.Route;
import com.zh.mini.entity.RouteImage;
import com.zh.mini.entity.Swiper;
import com.zh.mini.mapper.RouteMapper;
import com.zh.mini.service.IRouteImageService;
import com.zh.mini.service.IRouteService;
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
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements IRouteService {

    @Autowired
    IRouteImageService imageService;
    @Autowired
    ISwiperService swiperService;
    @Override
    public void saveRoute(Route route) {
        boolean isBlank = StringUtils.isBlank(route.getId());
        if (isBlank){
            save(route);
            String id = route.getId();

            saveDetails(route, id);
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
    void saveDetails(Route route,String id){
        List<RouteImage> introImgs = route.getIntroImgs();
        for (RouteImage img : introImgs) {
            img.setType("intro");
            img.setRouteId(id);
            imageService.save(img);
        }

        RouteImage postcard = route.getPostcard();
        postcard.setType("postcard");
        postcard.setRouteId(id);
        postcard.setTop(false);
        imageService.save(postcard);

        Swiper swiper = route.getSwiper();
        swiper.setTargetId(id);
        swiper.setTop(false);
        swiperService.save(swiper);
    }
}
