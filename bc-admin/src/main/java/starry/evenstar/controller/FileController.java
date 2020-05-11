package starry.evenstar.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import starry.evenstar.utils.FileUploadUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin

public class FileController {
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        return FileUploadUtil.uploadFile(file);
    }
    @RequestMapping("/multiFile")
    public List<String> multiFile(@RequestParam("file") MultipartFile[] files){
        ArrayList<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = FileUploadUtil.uploadFile(file);
            urls.add(url);
        }
        return urls;
    }
}
