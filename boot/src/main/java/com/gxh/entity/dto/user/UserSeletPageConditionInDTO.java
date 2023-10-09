package com.gxh.entity.dto.user;

import com.gxh.entity.UserBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserSeletPageConditionInDTO extends UserBean {
    Integer curr;
    Integer nums;
}
