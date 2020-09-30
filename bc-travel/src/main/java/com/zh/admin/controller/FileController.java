package com.zh.admin.controller;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.zh.core.aop.LimitFrequency;
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
        return FileUploadUtil.uploadFile(file);
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
            linuxUtil.linuxUtilsLogin("132.232.95.249",
                    "root", "Z:t6]RPg_f$k`5x92/a?1=p0Z+}6O");
            linuxUtil.deleteFile(path);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String s = "http://192.168.156.128//group1/M00/00/00/hOhf-V9ysNOASTKJAAY-dvHy3wY938.jpg";
        String pre = "/data/fastdfs/data/data";
        String path = pre + s.substring(32);

//        String[] strings = s.split("//");
//        System.out.println(strings[0]);
//        System.out.println(strings[1]);
//        System.out.println(strings[2].substring(10));
        System.out.println(s.split("//")[2].substring(10));
    }
}
