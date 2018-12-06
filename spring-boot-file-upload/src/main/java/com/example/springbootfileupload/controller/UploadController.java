package com.example.springbootfileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/12/6
 * @ Description：文件上传
 * @ throws
 */
@Controller
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "E://temp//";

    //跳转到上传文件页面
    @RequestMapping("/")
    public String index(){
        return "upload";
    }

    //上传文件
    @RequestMapping("/upload")
    public String singleFileUpload(@RequestParam("file")MultipartFile filename, RedirectAttributes redirectAttributes){

        //判断是否为空
        if (filename.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        String originalFilename = filename.getOriginalFilename();
        try {
            String filePath = UPLOADED_FOLDER +originalFilename;

            File file = new File(filePath);
            //文件目录不存在,创建目录
            if (!file.getParentFile().exists()) {
                //创建目录
                file.getParentFile().mkdir();
            }
            Path path = Paths.get(filePath);
            byte[] bytes = filename.getBytes();
            //写数据到指定位置
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + filename.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
