package com.gxh.mapper;

import com.gxh.entity.ProductPic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author GXhuan
* @description 针对表【tb_productpic(商品图片表)】的数据库操作Mapper
* @createDate 2023-10-07 13:51:02
* @Entity com.gxh.entity.Productpic
*/
@Mapper
public interface ProductPicMapper extends BaseMapper<ProductPic> {

    int insertImgBatch(@Param("id") Integer id, @Param("pic") String pic);
}




