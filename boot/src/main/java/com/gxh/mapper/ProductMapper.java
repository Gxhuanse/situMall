package com.gxh.mapper;

import com.gxh.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxh.entity.dto.product.ProductSelectPageConditionOutDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxhuanse
 * @since 2023-09-27
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<ProductSelectPageConditionOutDTO> selectProductByPageCondition(Product product);

}
