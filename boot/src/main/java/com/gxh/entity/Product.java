package com.gxh.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author gxhuanse
 * @since 2023-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品描述
     */
    private String productDescribe;

    /**
     * 分类
     */
    private Integer categoryId;

    /**
     * 版本
     */
    private String version;

    /**
     * 类型
     */
    private String type;

    /**
     * 售价
     */
    private BigDecimal sellPrice;

    /**
     * 标价
     */
    private BigDecimal markPrice;

    /**
     * 是否限量 0不限量，1限量
     */
    private Integer limitFlg;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 是否推荐 0：不推荐 1：推荐
     */
    private Integer recom;

    /**
     * 是否上架 0 ：下架 1：上架
     */
    private Integer status;

    /**
     * 是否删除 0：未删除  1：已删除
     */
    @TableLogic
    private Integer deleted;


}
