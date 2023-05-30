package com.bjpowernode.controller;

import com.bjpowernode.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserController {

    /*@GetMapping("login")
    public String login(User user, Model model){
        System.out.println(user.getUsername() + "=====" + user.getPwd());
        model.addAttribute("msg","登陆成功!");
        return "success";
    }*/

    @RequestMapping("login")
    public String login(@RequestParam(required = false) String username,
                        String pwd, Model model,
                        @RequestHeader String referer,
                        @CookieValue(value = "JSESSIONID", required = false) String JSESSIONID
            , HttpServletRequest request) {
        System.out.println(username + "=====" + pwd);
        System.out.println("referer===" + referer);
        System.out.println("JSESSIONID===" + JSESSIONID);
        model.addAttribute("msg", "登陆成功!");
        request.getSession().setAttribute("loginUser", username);
        return "success";
    }

    @RequestMapping("loginTwo")
    @ResponseBody
    public String loginTwo(@RequestBody User user, HttpServletRequest request) {
        System.out.println(user.getUsername() + "=====" + user.getPwd());
        System.out.println(user.getBirth());
        request.getSession().setAttribute("loginUser", user);
        return "登陆成功!";
    }

    @RequestMapping("loginMap")
    public String loginMap(@RequestParam Map<String, Objects> map, Model model) {
        System.out.println(map);
        model.addAttribute("msg", "登陆成功!");
        return "success";
    }

    @RequestMapping("loginArray")
    public String loginArray(Integer[] ids) {
        System.out.println(Arrays.toString(ids));
        return "success";
    }

    @RequestMapping("loginList")
    public String loginList(@RequestParam List<String> username) {
        System.out.println(username);
        return "success";
    }

    @RequestMapping("upload")
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println("文件上传controller");
        //input中的name属性值
        //System.out.println(file.getName());
        //文件字节大小
        //System.out.println(file.getSize());
        //文件真实的名字
        String filename = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(new Date());
        /*
         * 第一种方式
         * */
        /*System.out.println(filename);

        File dist = new File("D:/AllSpace/upload/" + date);
        if (!dist.exists()){
            dist.mkdirs();
        }
        filename = UUID.randomUUID().toString().replaceAll("-","")
              + filename;
        System.out.println(filename);
        file.transferTo(new File(dist.getAbsoluteFile() + "/" + filename));*/

        /*
         * 第二种方式
         * */
        ServletContext context = request.getServletContext();
        String realPath = context.getRealPath("image");
        realPath = realPath + File.separator + date;
        File dist = new File(realPath);
        if (!dist.exists()) {
            dist.mkdirs();
        }
        filename = UUID.randomUUID().toString().replaceAll("-", "")
                + filename;
        file.transferTo(new File(realPath + File.separator + filename));
        return "success";
    }


}
