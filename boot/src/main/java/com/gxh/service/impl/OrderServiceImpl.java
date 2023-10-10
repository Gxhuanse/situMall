package com.gxh.service.impl;

import com.gxh.entity.Order;
import com.gxh.mapper.OrderMapper;
import com.gxh.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author gxhuanse
 * @since 2023-10-09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
