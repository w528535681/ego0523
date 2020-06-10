package com.shsxt.manager.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieService
 */
public interface CookieService {

    /**
     *  设置cookie
     * @param ticket
     * @param request
     * @param response
     * @return
     */
    boolean setCookie (String ticket, HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除cookie
     * @param request
     * @param response
     * @return
     */
    boolean deleteCookie(HttpServletRequest request,HttpServletResponse response);

    /**
     * 获取cookie
     * @param request
     * @return
     */
    String getCookie(HttpServletRequest request);
}
