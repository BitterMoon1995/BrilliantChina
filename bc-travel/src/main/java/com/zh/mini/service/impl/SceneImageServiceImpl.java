package com.zh.mini.service.impl;

import com.zh.mini.entity.SceneImage;
import com.zh.mini.mapper.SceneImageMapper;
import com.zh.mini.service.ISceneImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.mini.vo.StickyImgVo;
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
public class SceneImageServiceImpl extends ServiceImpl<SceneImageMapper, SceneImage> implements ISceneImageService {

    @Autowired
    SceneImageMapper mapper;

    @Override
    public List<StickyImgVo> getFloorList() {
        return mapper.getFloorList();
    }
}
