package com.gxh.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author gxhuanse
 * @since 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_order_detail")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 商品id
     */
    private Integer goods_id;

    /**
     * 订单id
     */
    private Integer order_id;


}
