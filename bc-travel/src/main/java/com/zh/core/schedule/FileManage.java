package com.zh.core.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.zh.core.constant.Host01;
import com.zh.core.utils.LinuxUtils;
import com.zh.mini.entity.SceneImage;
import com.zh.mini.entity.Slider;
import com.zh.mini.mapper.SceneImageMapper;
import com.zh.mini.mapper.SliderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

//（大数据人工智能区块链）运维
@Configuration
@EnableScheduling
public class FileManage {

    @Autowired
    SceneImageMapper sceneImgMapper;
    @Autowired
    SliderMapper sliderMapper;

    @Scheduled(cron = "0 0 4 * * ?")//每天凌晨4点
    public void imageClean(){
        

        /*
        *   加个线程池
        * */

        //查所有要删除的图片
        QueryWrapper<SceneImage> sceneImgQ = new QueryWrapper<>();
        sceneImgQ.eq("is_delete",true);
        QueryWrapper<Slider> sliderQ = new QueryWrapper<>();
        sliderQ.eq("is_delete",true);

        List<SceneImage> sceneImages = sceneImgMapper.selectList(sceneImgQ);
        List<Slider> sliders = sliderMapper.selectList(sliderQ);
        
        //服务器硬盘中删除
        delImgs(sceneImages,sliders);
        //删除表记录
        sceneImgMapper.delete(sceneImgQ);
        sliderMapper.delete(sliderQ);
    }

    public void delImgs(List<SceneImage> sceneImages,List<Slider> sliderList){
        LinuxUtils jschUtils = LinuxUtils.getInstance2();
        //获取会话、建立连接
        try {
            jschUtils.linuxUtilsLogin(Host01.ipAddress,
                    "root", Host01.password);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        for (SceneImage sceneImage : sceneImages) {
            String url = sceneImage.getSrc();
            System.out.println(url);
            try {
                jschUtils.deleteFile(url.split("//")[2].substring(10));
            } catch (SftpException | JSchException e) {
                e.printStackTrace();
                return;
            }
        }
        for (Slider slider : sliderList) {
            String url = slider.getSrc();
            try {
                jschUtils.deleteFile(url.split("//")[2].substring(10));
            } catch (SftpException | JSchException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
