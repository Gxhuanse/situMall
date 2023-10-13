package com.gxh.service;

import com.gxh.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @author gxhuanse
 * @since 2023-10-13
 */
public interface ICartService extends IService<Cart> {

    void add(Cart cart);
}
