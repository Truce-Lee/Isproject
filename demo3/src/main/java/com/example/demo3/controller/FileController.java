package com.example.demo3.controller;



import cn.hutool.core.io.FileUtil;
import com.example.demo3.common.Result;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${ip:192.168.1.104}")
    String ip;
    @Value("${server.port}")
    String port;
    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";

    @PostMapping("/upload")

    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String mainName = FileUtil.mainName(originalFilename);
        String extName = FileUtil.extName(originalFilename);
        String projectPath = System.getProperty("user.dir");
//        String filePath = projectPath + "\\files";
        String filePath = projectPath + File.separator + "files";

        File parentFile = new File(ROOT_PATH);

        if (!FileUtil.exist(ROOT_PATH)){
            FileUtil.mkdir(ROOT_PATH);
        }

        if(FileUtil.exist(ROOT_PATH +File.separator+ originalFilename)){
            originalFilename = System.currentTimeMillis() + "_" + mainName + "." + extName;

        }
        File saveFile = new File(ROOT_PATH +File.separator+ originalFilename);
        file.transferTo(saveFile);
        String url = "http://" + ip + ":" + port + "/file/download/" + originalFilename;
        return Result.success(url);

    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String filePath = ROOT_PATH + File.separator + fileName;
        if(!FileUtil.exist(filePath)){
            return;
        }
        byte[] bytes = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }



}
