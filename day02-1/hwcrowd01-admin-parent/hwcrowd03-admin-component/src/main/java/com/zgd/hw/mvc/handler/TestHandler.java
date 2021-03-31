package com.zgd.hw.mvc.handler;

import com.zgd.hw.service.api.AdminService;
import com.zgd.hw.util.ResultEntity;
import entity.Admin;
import entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
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
    @RequestMapping(path = "send/array/two.html")
    public String testReceiveArrayTwo(@RequestBody List<Integer> array){
        for (Integer integer : array) {
            System.out.println(integer);
        }
        return ResultEntity.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(path = "send/compose/object.json")
    public ResultEntity<Student> testReceiveArrayThree(@RequestBody Student student){
        Logger logger = LoggerFactory.getLogger(TestHandler.class);
        logger.info(student.toString());

        // 加个异常，测试一下ajax异常处理
        String a = null;
        System.out.println(a.length());

        return ResultEntity.successWithData(student);
    }

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

        String a = null;
        System.out.println(a.length());

        return "target";
    }
}
