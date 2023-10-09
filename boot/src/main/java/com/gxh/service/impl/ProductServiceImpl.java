package com.gxh.service.impl;

import com.gxh.dao.ProductDao;
import com.gxh.dao.ProductPicDao;
import com.gxh.entity.Product;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.product.ProductAddInDTO;
import com.gxh.entity.dto.product.ProductSelectPageConditionInDTO;
import com.gxh.mapper.ProductMapper;
import com.gxh.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gxhuanse
 * @since 2023-09-27
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    ProductDao productDao;
    @Autowired
    ProductPicDao productPicDao;

    @Override
    public PageDTO selectProductByPageCondition(ProductSelectPageConditionInDTO dto) {
        return productDao.selectProductByPageCondition(dto);
    }

    @Override
    public int updataStatus(Product product) {
        return productDao.updataStatus(product);
    }

    @Override
    @Transactional
    public int productAdd(ProductAddInDTO dto) {
        int productAdd = productDao.productAdd(dto);
        List<String> pics=null;
         if(dto.getPics()!=null && !dto.getPics().isEmpty()){
             pics=new ArrayList<>(dto.getPics());
             int  insertImgBatch= productPicDao.insertImgBatch(dto.getId(),pics);
         }
        return productAdd;
    }

    @Override
    @Transactional
    public int productEdit(ProductAddInDTO inDTO) {
        List<String> pics = inDTO.getPics();
        int updataProduct = productDao.updataProduct(inDTO);
        int DeleteImgBatch = productPicDao.deleteImgBatch(inDTO.getId());
        int  insertImgBatch= productPicDao.insertImgBatch(inDTO.getId(),pics);
        return updataProduct;
    }

    @Override
    public int productDelete(Integer id) {
        int delete= productDao.deleteProduct(id);
        int DeleteImgBatch = productPicDao.deleteImgBatch(id);
        return delete;
    }
}
