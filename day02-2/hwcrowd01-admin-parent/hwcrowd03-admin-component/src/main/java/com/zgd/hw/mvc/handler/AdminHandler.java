package com.zgd.hw.mvc.handler;

import com.zgd.hw.constant.HWCrowdConstant;
import com.zgd.hw.service.api.AdminService;
import entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author huangwei
 * @description
 * @create 2021-03-31-15:07
 */
@Controller
public class AdminHandler {

    @Autowired
    AdminService adminService;

    @RequestMapping(path = "/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPswd") String userPswd,
                          HttpSession session){
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);

        session.setAttribute(HWCrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);

        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping(path = "/admin/do/logout.html")
    public String doLoginOut(HttpSession session){
        session.invalidate();

        return "redirect:/admin/to/login/page.html";
    }
}
