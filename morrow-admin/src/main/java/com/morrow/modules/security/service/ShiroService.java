package com.morrow.modules.security.service;

import com.morrow.modules.security.entity.SysUserToken;
import com.morrow.modules.security.user.UserDetail;
import com.morrow.modules.sys.entity.SysUser;

import java.util.Set;

public interface ShiroService {

    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(UserDetail user);

    SysUserToken getByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUser getUser(Long userId);

}
