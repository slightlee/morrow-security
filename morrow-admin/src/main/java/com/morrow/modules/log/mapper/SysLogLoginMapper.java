package com.morrow.modules.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.morrow.modules.log.entity.SysLogLogin;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 登录日志 Mapper 接口
 * </p>
 *
 * @author morrow
 * @since 2020-09-07
 */
@Mapper
public interface SysLogLoginMapper extends BaseMapper<SysLogLogin> {

}
