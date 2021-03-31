package com.zgd.hw.mvc.handler;

import com.zgd.hw.service.api.AdminService;
import entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author huangwei
 * @description
 * @create 2021-03-30-22:13
 */
@Controller
public class TestHandler {

    @Autowired
    AdminService adminService;

    @ResponseBody
    @RequestMapping(path = "send/array/one.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array){
        for (Integer integer : array) {
            System.out.println(integer);
        }

        return "success";
    }

    @RequestMapping(path = "/test/ssm.html")
    public String testSSM(ModelMap modelMap){
        List<Admin> admins = adminService.findAll();

        modelMap.addAttribute("adminList",admins);

        Logger logger = LoggerFactory.getLogger(TestHandler.class);
        logger.debug("执行测试成功----------------------------------++++++++++++++++++++++++++++++++-----------------------------");

        return "target";
    }
}
