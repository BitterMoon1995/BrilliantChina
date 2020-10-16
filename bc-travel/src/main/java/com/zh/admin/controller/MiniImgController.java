package com.zh.admin.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.zh.admin.entity.MiniImg;
import com.zh.admin.service.IMiniImgService;
import com.zh.common.*;
import com.zh.core.constant.Host01;
import com.zh.core.utils.LinuxUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 周总书记
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/admin/mini-img")
public class MiniImgController {
    @Autowired
    IMiniImgService service;
    @RequestMapping("/upload")
    public iResult upload(@RequestBody MiniImg miniImg){//2.2.6仍要加@RequestBody

        System.out.println(miniImg);
        //空校验
        if (StringUtils.isAnyBlank(miniImg.getDescription(),miniImg.getSrc())){
            return new iResult(Status.illegalParam());
        }
        String id = miniImg.getId();
        //新增
        if (StringUtils.isBlank(id)){
            service.save(miniImg);
        }
        //修改
        else {
            service.updateById(miniImg);
        }

        return new iResult(Status.success());
    }

    @GetMapping("/queryAll")
    public iResult queryAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        Page<MiniImg> page = new Page<>(pageNum,pageSize);
        List<MiniImg> imgList = service.page(page).getRecords();

        int total = service.count();
        iResult iResult = new iResult(Status.success(), imgList, total);
        System.out.println(iResult);
        return iResult;
    }

    @DeleteMapping("/del")
    public iResult del(@RequestParam String id){

        MiniImg img = service.getById(id);
        try {
            String src0 = img.getSrc();
            String src = Host01.storagePath + src0.split("//")[2].substring(10);
            LinuxUtils.getInstance().deleteFile(src);
        } catch (SftpException | JSchException e) {
            return iResult.serverDown;
        }
        if (service.removeById(id))
            return iResult.success;
        else return iResult.serverDown;
    }
}
