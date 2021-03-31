package com.zgd.hw.service.impl;

import com.zgd.hw.mapper.AdminMapper;
import com.zgd.hw.service.api.AdminService;
import entity.Admin;
import entity.AdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
