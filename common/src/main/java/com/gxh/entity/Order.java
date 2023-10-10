package com.gxh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author gxhuanse
 * @since 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 地址id
     */
    private Integer addr_id;

    /**
     * 订单状态 0:未支付  1：已支付  2：已发货    3：交易结束   4：退款
     */
    private Integer status;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 逻辑删除，0：未删除，1：已删除
     */
    @TableLogic
    private Integer deleted;


}
