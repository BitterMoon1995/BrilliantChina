package com.zh.core.utils;

import com.zh.common.ConstantTable;
import com.zh.core.constant.Host01;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUploadUtil {
    public static String uploadFile(MultipartFile file) {
        try {
            ClientGlobal.init("trackerConf");
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }

        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
            /*
            连不上tracker，立即返回
            */
            return "tracker_down";
        }

        StorageServer storageServer = null;
        try {
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (IOException e) {
            e.printStackTrace();
            /*
            tracker连不上storage，立即返回
             */
            return "storage_down";
        }

        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        String[] uploadInfo = new String[0];
        try {
            byte[] fileBytes = file.getBytes();
            String filename = file.getOriginalFilename();
            assert filename != null;
            String extName = filename.substring(filename.lastIndexOf(".") + 1);
            uploadInfo = storageClient.upload_file(fileBytes, extName, null);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }

        StringBuilder url= new StringBuilder(Host01.httpUrl);
        for (String s : uploadInfo) {
            url.append("/").append(s);
        }
        return url.toString();
    }
}
