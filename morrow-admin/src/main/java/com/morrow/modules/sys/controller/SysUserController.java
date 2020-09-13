package com.morrow.modules.sys.controller;


import com.morrow.modules.sys.entity.SysUser;
import com.morrow.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author morrow
 * @since 2020-09-13
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "用户管理")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;


    @GetMapping("/list")
    @ApiOperation("列表")
    public Object list(){
        List<SysUser> list = sysUserService.list();
        return list;
    }

//    @PostMapping("/pagelist")
//    @ApiOperation("列表分页")
//    public Result pagellist(@RequestParam Integer pageNo, @RequestParam Integer pageSize){
//        Result result = sysUserService.pageList(pageNo, pageSize);
//        return result;
//    }
}
