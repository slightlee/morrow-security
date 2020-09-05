package com.morrow.modules.sys.controller;


import com.morrow.modules.sys.entity.SysUser;
import com.morrow.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author morrow
 * @since 2020-09-05
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {


    @Autowired
    SysUserService sysUserService;


    @GetMapping("/list")
    public Object list(){
        List<SysUser> list = sysUserService.list();
        return list;
    }
}
