package com.git.bds.nyc.admin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 成大事
 * @since 2023-01-04 15:47:52
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("advertisement")
@ApiModel(value = "Advertisement对象", description = "")
public class Advertisement extends Model<Advertisement> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("广告title")
    @TableField("title")
    private String title;

    @ApiModelProperty("图片地址")
    @TableField("picture_url")
    private String pictureUrl;


    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String PICTURE_URL = "picture_url";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
