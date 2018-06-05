package me.ztiany.mvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import me.ztiany.mvc.service.UserService;

@Controller
public class UserController {

    private final UserService mUserService;

    @Autowired
    public UserController(UserService userService) {
        this.mUserService = userService;
    }

    //入门程序 第一   包类 + 类包 + 方法名
    @RequestMapping(value = "/user/userlist.action")
    public ModelAndView itemList() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("userList", mUserService.selectUserList());
        mav.setViewName("userList");

        return mav;
    }

}
