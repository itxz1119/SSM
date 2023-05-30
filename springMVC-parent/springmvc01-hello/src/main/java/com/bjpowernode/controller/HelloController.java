package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HelloController {

   /* @RequestMapping("/")
    public String index(){
        return "index";
    }*/

    @RequestMapping("/hello")
    public ModelAndView hello(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println("hello");
        ModelAndView model = new ModelAndView();
        model.addObject("msg","helloWord");
        model.setViewName("success");
        return model;
    }

    @RequestMapping("/hello2")
    public String hello2(Model model){
        System.out.println("hello02");
        model.addAttribute("msg","helloWord02");
        return "success";
    }
}
