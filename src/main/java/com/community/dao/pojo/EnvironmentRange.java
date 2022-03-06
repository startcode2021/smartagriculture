package com.community.dao.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("environment_range")
@ApiModel(value="environment_range对象", description="")
public class EnvironmentRange {
    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "大棚区域id")
    private int place_id;

    @ApiModelProperty(value = "环境因素")
    private String environment;

    @ApiModelProperty(value = "环境上限")
    private double upper_limit;

    @ApiModelProperty(value = "环境下限")
    private double lower_limit;
}
