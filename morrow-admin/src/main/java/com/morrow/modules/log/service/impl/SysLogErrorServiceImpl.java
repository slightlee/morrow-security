package com.morrow.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.morrow.modules.log.entity.SysLogError;
import com.morrow.modules.log.mapper.SysLogErrorMapper;
import com.morrow.modules.log.service.SysLogErrorService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 异常日志 服务实现类
 * </p>
 *
 * @author morrow
 * @since 2020-09-07
 */
@Service
public class SysLogErrorServiceImpl extends ServiceImpl<SysLogErrorMapper, SysLogError> implements SysLogErrorService {

}
