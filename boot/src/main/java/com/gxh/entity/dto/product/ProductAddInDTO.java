package com.gxh.entity.dto.product;

import com.gxh.entity.Product;
import com.gxh.entity.ProductPic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddInDTO extends Product {
    int category_id;
    List<String> pics;
}
