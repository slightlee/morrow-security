package com.morrow.modules.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.morrow.modules.log.entity.SysLogError;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 异常日志 Mapper 接口
 * </p>
 *
 * @author morrow
 * @since 2020-09-07
 */
@Mapper
public interface SysLogErrorMapper extends BaseMapper<SysLogError> {

}
