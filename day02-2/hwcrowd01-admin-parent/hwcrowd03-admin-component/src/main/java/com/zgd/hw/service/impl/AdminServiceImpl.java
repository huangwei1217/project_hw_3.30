package com.zgd.hw.service.impl;

import com.zgd.hw.constant.HWCrowdConstant;
import com.zgd.hw.exception.LoginFailedException;
import com.zgd.hw.mapper.AdminMapper;
import com.zgd.hw.service.api.AdminService;
import com.zgd.hw.util.HWCrowdUtil;
import entity.Admin;
import entity.AdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author huangwei
 * @description
 * @create 2021-03-30-21:24
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> findAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public void save(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1.根据登录账号查询Admin对象
        // ①创建AdminExample对象
        AdminExample adminExample = new AdminExample();

        // ②创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();

        // ③在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);

        // ④调用AdminMapper的方法执行查询
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        // 2.判断Admin对象是否为null
        if (admins == null || admins.size() == 0){
            throw new LoginFailedException(HWCrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        if (admins.size() > 1){
            throw new RuntimeException(HWCrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = admins.get(0);

        // 3.如果Admin对象为null则抛出异常
        if (admin == null){
            throw new LoginFailedException(HWCrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 4.如果Admin对象不为null则将数据库密码从Admin对象中取出
        String dbPswd = admin.getUserPswd();

        // 5.将表单提交的明文密码进行加密
        String FormPswd = HWCrowdUtil.md5(userPswd);

        // 6.对密码进行比较
        if (!Objects.equals(dbPswd,FormPswd)){
            // 7.如果比较结果是不一致则抛出异常
            throw new LoginFailedException(HWCrowdConstant.MESSAGE_LOGIN_FAILED);

        }

        // 8.如果一致则返回Admin对象
        return admin;
    }
}
