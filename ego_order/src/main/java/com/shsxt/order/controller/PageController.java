package com.shsxt.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 跳转页面
 */

@Controller
public class PageController {
    /**
     * 页面跳转
     *
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        return page;
    }

    /**
     * 跳转到登录页面
     *
     * @param redirectUrl
     * @param model
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public String login(String redirectUrl, Model model) {

        model.addAttribute("redirectUrl", redirectUrl);
        return "login";
    }
}