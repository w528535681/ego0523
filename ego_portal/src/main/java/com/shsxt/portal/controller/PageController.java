package com.shsxt.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转页面
 */
@Controller
public class PageController {
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		return page;
	}

	/**
	 * 跳转首页
	 * @return
	 */
	@RequestMapping("/")
	public String page() {
		return "index";
	}
}