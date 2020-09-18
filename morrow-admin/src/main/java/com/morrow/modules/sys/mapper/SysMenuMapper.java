package com.morrow.modules.sys.mapper;

import com.morrow.modules.sys.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author morrow
 * @since 2020-09-13
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 查询所有权限列表
     */
    List<String> getPermissionsList();

    /**
     * 查询用户权限列表
     * @param userId  用户ID
     */
    List<String> getUserPermissionsList(Long userId);

}
