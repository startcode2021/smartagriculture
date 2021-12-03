package com.community.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("robot_type")
@ApiModel(value="robot_type对象", description="")
public class RobotType implements Serializable {


    @ApiModelProperty(value = "设备序列号")
    private int index_num;

    @ApiModelProperty(value = "设备名称")
    private String robot_name;

    @ApiModelProperty(value = "设备类型")
    private String robot_type;

    @ApiModelProperty(value = "安装区域")
    private String robot_address;

    @ApiModelProperty(value = "设备数量")
    private int robot_num;

    @ApiModelProperty(value = "添加日期")
    private DateTime add_date;

    @ApiModelProperty(value = "备注")
    private String other;
}
