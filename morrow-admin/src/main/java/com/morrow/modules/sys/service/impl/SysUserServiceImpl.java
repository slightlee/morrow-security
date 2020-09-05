package com.morrow.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.morrow.common.util.Result;
import com.morrow.modules.sys.entity.SysUser;
import com.morrow.modules.sys.mapper.SysUserMapper;
import com.morrow.modules.sys.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author morrow
 * @since 2020-09-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;


    @Override
    public Result pageList(Integer pageNo, Integer pageSize) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        IPage<SysUser> page = new Page<SysUser>(pageNo,pageSize);
        IPage<SysUser> iPage = sysUserMapper.selectPage(page, queryWrapper);
        return Result.ok().put("count",iPage.getTotal()).put("data",iPage.getRecords());
    }


}
