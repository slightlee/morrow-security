package com.morrow.modules.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录表单
 *
 * @Author Tomorrow
 * @Date 2020/9/13 下午11:30
 */
@Data
@ApiModel(value = "登录表单")
public class LoginDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String captcha;

    @ApiModelProperty(value = "唯一标识")
    private String uuid;


}
