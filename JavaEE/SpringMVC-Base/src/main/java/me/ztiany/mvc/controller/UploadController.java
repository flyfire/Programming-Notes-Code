package me.ztiany.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.6.5 22:44
 */
@Controller
public class UploadController {

    //提交修改页面 入参  为 Items对象
    @RequestMapping(value = "/upload/uploadFile.action")
    public String uploadFile(MultipartFile pictureFile) {

        System.out.println(pictureFile.getOriginalFilename());
        System.out.println(new File(".").getAbsolutePath());
        //pictureFile.transferTo();

        return "redirect:success";
    }
}
