package com.gxh.mapper;

import com.gxh.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 购物车 Mapper 接口
 * </p>
 *
 * @author gxhuanse
 * @since 2023-10-13
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

}
