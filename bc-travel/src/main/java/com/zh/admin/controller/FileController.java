package com.zh.admin.controller;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.zh.common.iResult;
import com.zh.core.aop.LimitFrequency;
import com.zh.core.constant.Host01;
import com.zh.core.utils.FileUploadUtil;
import com.zh.core.utils.LinuxUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin

public class FileController {
    @LimitFrequency
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String res = FileUploadUtil.uploadFile(file);
        if (res.equals("tracker_down")||res.equals("storage_down")){
            return "FileServiceDown";
        }
        return res;
    }

    @RequestMapping("/multiFile")
    public List<String> multiFile(@RequestParam("file") MultipartFile[] files) {
        ArrayList<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = FileUploadUtil.uploadFile(file);
            urls.add(url);
        }
        return urls;
    }

    @DeleteMapping("/delFile")
    public void delFile(String url) {//接收仓库坐标+文件名
        String pre = "/data/fastdfs/data/data";
        String path = pre + url;//拼接前缀
        System.out.println(path);
        LinuxUtils linuxUtil = LinuxUtils.getInstance2();

        try {
            linuxUtil.linuxUtilsLogin(Host01.ipAddress,
                    "root", Host01.password);
            linuxUtil.deleteFile(path);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
