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
    private int place_name;
}
