package com.gxh.entity.dto.product;

import com.gxh.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProductSelectPageConditionInDTO extends Product {
    Integer curr;
    Integer nums;
}
