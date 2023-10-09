package com.gxh.entity.dto.category;

import com.gxh.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CategorySeletPageConditionInDTO extends Category {
    Integer curr;
    Integer nums;
}
