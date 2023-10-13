package com.gxh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author gxhuanse
 * @since 2023-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品数量
     */
    private Integer count;

    /**
     * 商品id
     */
    private Integer product_id;

    /**
     * 用户id
     */
    private Integer user_id;

    @TableLogic
    private Integer deleted;


}
