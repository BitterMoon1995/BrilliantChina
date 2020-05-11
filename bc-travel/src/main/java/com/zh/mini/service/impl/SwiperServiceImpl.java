package com.zh.mini.service.impl;

import com.zh.mini.entity.Swiper;
import com.zh.mini.mapper.SwiperMapper;
import com.zh.mini.service.ISwiperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
@Service
public class SwiperServiceImpl extends ServiceImpl<SwiperMapper, Swiper> implements ISwiperService {
    @Autowired
    SwiperMapper swiperMapper;


}
