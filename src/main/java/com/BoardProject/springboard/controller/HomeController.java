package com.BoardProject.springboard.controller;

import com.BoardProject.springboard.domain.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/accessDenied")
    public ModelAndView  accessDenied(ModelAndView mav) {
        mav.addObject("data",new Message("접근이 거부되었습니다.","/"));
        mav.setViewName("message");
        return mav;
    }
}
