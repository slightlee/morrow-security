package com.morrow.modules.sys.service;

import com.morrow.common.pageUtil.PageData;
import com.morrow.common.util.Result;
import com.morrow.modules.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author morrow
 * @since 2020-09-05
 */
public interface SysUserService extends IService<SysUser> {

    Result pageList(Integer pageNo, Integer pageSize);
}
