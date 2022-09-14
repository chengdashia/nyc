package com.git.bds.nyc.valid;

import javax.validation.GroupSequence;

/**
 * @author 成大事
 * @since 2022/9/14 19:05
 */
public class ValidGroup {
    /**在售使用(配合spring的@Validated功能分组使用)*/
    public interface OnSell{}

    /**预售使用(配合spring的@Validated功能分组使用)*/
    public interface PreSale{}


    /**属性必须有这两个分组的才验证(配合spring的@Validated功能分组使用*/
    @GroupSequence({OnSell.class, PreSale.class})
    public interface All{}
}
