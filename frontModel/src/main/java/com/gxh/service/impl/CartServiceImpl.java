package com.gxh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxh.entity.Cart;
import com.gxh.mapper.CartMapper;
import com.gxh.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    CartMapper cartMapper;

    @Override
    public void add(Cart cart) {
        //参数判断
        //判断购物车中有没有商品
        //查询数据库
        LambdaQueryWrapper<Cart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getUser_id,cart.getUser_id());
        queryWrapper.eq(Cart::getProduct_id,cart.getProduct_id());
        List<Cart> cartList = cartMapper.selectList(queryWrapper);
        if (cartList!=null&& !cartList.isEmpty()){
            Cart ucart=cartList.get(0);
            //设置数量=原有+前端传入
            ucart.setCount(ucart.getCount()+cart.getCount());
            cartMapper.updateById(ucart);
        }else {
            cartMapper.insert(cart);
        }
    }
}
