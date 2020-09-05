package com.morrow.modules.sys.mapper;

import com.morrow.modules.sys.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author morrow
 * @since 2020-09-05
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}
