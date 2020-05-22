package com.zh.mini.service.impl;

import com.zh.mini.entity.Slider;
import com.zh.mini.mapper.SliderMapper;
import com.zh.mini.service.ISliderService;
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
public class SliderServiceImpl extends ServiceImpl<SliderMapper, Slider> implements ISliderService {
    @Autowired
    SliderMapper sliderMapper;


}
