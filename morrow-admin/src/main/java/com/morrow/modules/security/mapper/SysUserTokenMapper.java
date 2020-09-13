package com.morrow.modules.security.mapper;

import com.morrow.modules.security.entity.SysUserToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统用户Token Mapper 接口
 * </p>
 *
 * @author morrow
 * @since 2020-09-13
 */
@Mapper
public interface SysUserTokenMapper extends BaseMapper<SysUserToken> {

    void updateToken(@Param("userId") Long userId, @Param("token") String token);
}
