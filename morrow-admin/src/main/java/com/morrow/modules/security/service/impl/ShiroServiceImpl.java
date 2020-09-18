package com.morrow.modules.security.service.impl;

import com.morrow.modules.security.entity.SysUserToken;
import com.morrow.modules.security.mapper.SysUserTokenMapper;
import com.morrow.modules.security.service.ShiroService;
import com.morrow.modules.security.user.UserDetail;
import com.morrow.modules.sys.entity.SysUser;
import com.morrow.modules.sys.enums.SuperAdminEnum;
import com.morrow.modules.sys.mapper.SysMenuMapper;
import com.morrow.modules.sys.mapper.SysUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO
 *
 * @Author Tomorrow
 * @Date 2020/9/14 上午1:33
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserTokenMapper sysUserTokenMapper;

    @Override
    public Set<String> getUserPermissions(UserDetail user) {
        //系统管理员，拥有最高权限
        List<String> permissionsList;
        if(user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            permissionsList = sysMenuMapper.getPermissionsList();
        }else{
            permissionsList = sysMenuMapper.getUserPermissionsList(user.getId());
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String permissions : permissionsList){
            if(StringUtils.isBlank(permissions)){
                continue;
            }
            permsSet.addAll(Arrays.asList(permissions.trim().split(",")));
        }

        return permsSet;
    }

    @Override
    public SysUserToken getByToken(String token) {
        return sysUserTokenMapper.getByToken(token);
    }

    @Override
    public SysUser getUser(Long userId) {
        return sysUserMapper.selectById(userId);
    }
}
