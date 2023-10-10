package com.gxh.mapper;

import com.gxh.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author gxhuanse
 * @since 2023-10-09
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
