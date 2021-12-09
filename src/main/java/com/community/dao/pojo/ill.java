package com.community.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ill")
@ApiModel(value="ill对象", description="")
public class ill {

    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "虫害名称")
    private String ill_name;

    @ApiModelProperty(value = "仪器名称")
    private String robot_name;

    @ApiModelProperty(value = "安装地域")
    private String region;

    @ApiModelProperty(value = "仪器数量")
    private int robot_num;

    @ApiModelProperty(value = "操作")
    private String operation;

    @ApiModelProperty(value = "添加日期")
    private String add_date;
}
