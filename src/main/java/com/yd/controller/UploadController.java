package com.yd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
//            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\李磊\\Desktop\\qrcode-download.png");
//            FileChannel channel = fileInputStream.getChannel();
//            System.out.println(channel.size());
            BufferedImage read = ImageIO.read(inputStream);
            System.out.println(read.getHeight());
            System.out.println(read.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
