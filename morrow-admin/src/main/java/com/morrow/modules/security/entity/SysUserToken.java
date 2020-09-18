package com.morrow.modules.security.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户Token
 * </p>
 *
 * @author morrow
 * @since 2020-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUserToken对象", description="系统用户Token")
public class SysUserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户token")
    private String token;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expireDate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;


}
