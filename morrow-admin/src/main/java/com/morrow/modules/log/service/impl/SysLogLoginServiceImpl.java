package com.morrow.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.morrow.modules.log.entity.SysLogLogin;
import com.morrow.modules.log.mapper.SysLogLoginMapper;
import com.morrow.modules.log.service.SysLogLoginService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author morrow
 * @since 2020-09-07
 */
@Service
public class SysLogLoginServiceImpl extends ServiceImpl<SysLogLoginMapper, SysLogLogin> implements SysLogLoginService {

}
