package com.morrow.modules.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.morrow.common.util.Result;
import com.morrow.modules.security.entity.SysUserToken;

/**
 * <p>
 * 系统用户Token 服务类
 * </p>
 *
 * @author morrow
 * @since 2020-09-13
 */
public interface SysUserTokenService extends IService<SysUserToken> {

    /**
     * 生成token
     * @param userId  用户ID
     */
    Result createToken(Long userId);

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(Long userId);

}
