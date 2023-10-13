package com.gxh.service.impl;

import com.gxh.entity.Cart;
import com.gxh.mapper.CartMapper;
import com.gxh.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author gxhuanse
 * @since 2023-10-13
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}
