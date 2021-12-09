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
@TableName("place")
@ApiModel(value="Place对象", description="")
public class Place {

    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "地点名称")
    private String place_name;

    @ApiModelProperty(value = "GPS名称")
    private String gps_name;

    @ApiModelProperty(value = "经度")
    private String lon;

    @ApiModelProperty(value = "纬度")
    private String lat;

    @ApiModelProperty(value = "空气温度")
    private Double air_temp;

    @ApiModelProperty(value = "空气湿度")
    private Double air_hump;

    @ApiModelProperty(value = "土壤温度")
    private Double ground_temp;

    @ApiModelProperty(value = "土壤湿度")
    private Double ground_hump;

    @ApiModelProperty(value = "光照度")
    private Double lux;

    @ApiModelProperty(value = "降雨量值")
    private Double rain;


}
