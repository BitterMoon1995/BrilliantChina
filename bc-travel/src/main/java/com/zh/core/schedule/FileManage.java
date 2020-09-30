package com.zh.core.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
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

    @Scheduled(fixedRate = 1000 * 60 * 60)//1小时
    public void imageClean(){
        

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
        String pre = "/data/fastdfs/data/data";
        //获取会话、建立连接
        try {
            jschUtils.linuxUtilsLogin("132.232.95.249",
                    "root", "Z:t6]RPg_f$k`5x92/a?1=p0Z+}6O");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        for (SceneImage sceneImage : sceneImages) {
            String url = sceneImage.getSrc();
            System.out.println(url);
            System.out.println(pre+url.split("//")[2].substring(10));
            try {
                jschUtils.deleteFile(pre+url.split("//")[2].substring(10));
            } catch (SftpException | JSchException e) {
                e.printStackTrace();
                return;
            }
        }
        for (Slider slider : sliderList) {
            String url = slider.getSrc();
            try {
                jschUtils.deleteFile(pre+url.split("//")[2].substring(10));
            } catch (SftpException | JSchException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
