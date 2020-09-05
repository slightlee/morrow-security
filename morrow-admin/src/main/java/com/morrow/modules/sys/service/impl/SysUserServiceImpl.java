package com.morrow.modules.sys.service.impl;

import com.morrow.modules.sys.entity.SysUser;
import com.morrow.modules.sys.mapper.SysUserMapper;
import com.morrow.modules.sys.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
