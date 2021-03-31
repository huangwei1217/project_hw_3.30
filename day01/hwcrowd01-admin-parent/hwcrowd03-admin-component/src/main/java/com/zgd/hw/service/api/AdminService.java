package com.zgd.hw.service.api;

import entity.Admin;

import java.util.List;

/**
 * @author huangwei
 * @description
 * @create 2021-03-30-21:23
 */
public interface AdminService {

    List<Admin> findAll();

    void save(Admin admin);

}
