package com.morrow.modules.security.oauth2;

import com.morrow.common.util.ConvertUtils;
import com.morrow.common.util.DateUtil;
import com.morrow.modules.security.entity.SysUserToken;
import com.morrow.modules.security.service.ShiroService;
import com.morrow.modules.security.user.UserDetail;
import com.morrow.modules.sys.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 认证
 *
 * @Author Tomorrow
 * @Date 2020/9/14 上午1:28
 */
@Component
public class Oauth2Realm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Oauth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserDetail user = (UserDetail)principalCollection.getPrimaryPrincipal();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(user);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        SysUserToken userToken = shiroService.getByToken(accessToken);
        //token失效
        if(userToken == null || DateUtil.asDate(userToken.getExpireDate()).getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        SysUser sysUser = shiroService.getUser(userToken.getUserId());

        //转换成UserDetail对象
        UserDetail userDetail = ConvertUtils.sourceToTarget(sysUser, UserDetail.class);

        //获取用户对应的部门数据权限
       // List<Long> deptIdList = shiroService.getDataScopeList(userDetail.getId());
      //  userDetail.setDeptIdList(deptIdList);

        //账号锁定
        if(userDetail.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDetail, accessToken, getName());
        return info;
    }
}
