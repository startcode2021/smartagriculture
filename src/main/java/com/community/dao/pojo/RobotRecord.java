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
@TableName("robot_record")
@ApiModel(value="robot_record对象", description="")
public class RobotRecord {

    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "设备id")
    private int robot_id;

    @ApiModelProperty(value = "设备名称")
    private String robot_name;

    @ApiModelProperty(value = "安装区域")
    private String region;

    @ApiModelProperty(value = "空气温度")
    private double air_temp;

    @ApiModelProperty(value = "空气湿度")
    private double air_hump;

    @ApiModelProperty(value = "土壤温度")
    private double ground_temp;

    @ApiModelProperty(value = "土壤湿度")
    private double ground_hump;

    @ApiModelProperty(value = "光照强度")
    private double lux;

    @ApiModelProperty(value = "CO₂浓度")
    private double co2;

    @ApiModelProperty(value = "NH₃浓度")
    private double nh3;

    @ApiModelProperty(value = "H₂S浓度")
    private double h2s;

    @ApiModelProperty(value = "添加时间")
    private String add_time;
}
