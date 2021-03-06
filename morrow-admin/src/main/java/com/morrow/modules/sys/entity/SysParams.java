package com.morrow.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 参数管理
 * </p>
 *
 * @author morrow
 * @since 2020-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysParams对象", description="参数管理")
public class SysParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "参数编码")
    private String paramCode;

    @ApiModelProperty(value = "参数值")
    private String paramValue;

    @ApiModelProperty(value = "类型   0：系统参数   1：非系统参数")
    private Integer paramType;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建者")
    private Long creator;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新者")
    private Long updater;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;


}
