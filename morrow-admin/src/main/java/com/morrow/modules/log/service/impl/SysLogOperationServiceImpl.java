package com.morrow.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.morrow.modules.log.entity.SysLogOperation;
import com.morrow.modules.log.mapper.SysLogOperationMapper;
import com.morrow.modules.log.service.SysLogOperationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author morrow
 * @since 2020-09-07
 */
@Service
public class SysLogOperationServiceImpl extends ServiceImpl<SysLogOperationMapper, SysLogOperation> implements SysLogOperationService {

}
