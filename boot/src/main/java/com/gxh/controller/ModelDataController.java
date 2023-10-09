package com.gxh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxh.common.ResponseBean;
import com.gxh.entity.Category;
import com.gxh.entity.ProductPic;
import com.gxh.service.ICategoryService;
import com.gxh.service.ProductPicService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ModelData")
public class ModelDataController {

    @Autowired
    ICategoryService categoryService;
    @Autowired
    ProductPicService productPicService;

    @RequestMapping("/categoryUpdata/{id}")
    public String categoryUpdataById(Model model, @PathVariable Integer id){
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Category::getId,id);
        List<Category> list = categoryService.list(wrapper);
        model.addAttribute("category",list);
        return "category/categoryUpdataModel";
    }

    @RequestMapping("/productEdit")
    public String productEditView(Integer Categoryid,Integer id,Model model){
        Category byId = categoryService.getById(Categoryid);//通过商品属性中的类别id查询父类id
        model.addAttribute("parentCatId",byId.getParentId());

        QueryWrapper<ProductPic> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(ProductPic::getProductId,id);
        List<ProductPic> list = productPicService.list(queryWrapper);
        model.addAttribute("pics",list);
        return "product/productUpdata";
    }

    @ResponseBody
    @RequestMapping("/SaveImg")
    public ResponseBean userAddImg(MultipartFile myFile) {
        ResponseBean responseBean;
        try {
            String fileName = myFile.getOriginalFilename();//获取文件名
            String fileSuffix=fileName.substring(fileName.lastIndexOf("."));//获取文件后缀名
            String fileSaveName = UUID.randomUUID()+fileSuffix;//生成文件存储的UUID名
            String savePath=System.getProperty("user.dir")+"/boot/src/main/resources/static/img/upload/"+fileSaveName;//文件存储路径

            try(InputStream inputStream = myFile.getInputStream();
                FileOutputStream outputStream=new FileOutputStream(savePath)
            )
            {
                IOUtils.copy(inputStream,outputStream);
                responseBean=ResponseBean.ok(0,fileSaveName);
            } catch (IOException e) {
                responseBean=ResponseBean.failed("上传失败");
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            responseBean=ResponseBean.failed("上传失败，服务器发生异常");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

}
