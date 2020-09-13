package com.morrow.modules.log.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录日志
 * </p>
 *
 * @author morrow
 * @since 2020-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysLogLogin对象", description="登录日志")
public class SysLogLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "用户操作   0：用户登录   1：用户退出")
    private Integer operation;

    @ApiModelProperty(value = "状态  0：失败    1：成功    2：账号已锁定")
    private Integer status;

    @ApiModelProperty(value = "用户代理")
    private String userAgent;

    @ApiModelProperty(value = "操作IP")
    private String ip;

    @ApiModelProperty(value = "用户名")
    private String creatorName;

    @ApiModelProperty(value = "创建者")
    private Long creator;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;


}
