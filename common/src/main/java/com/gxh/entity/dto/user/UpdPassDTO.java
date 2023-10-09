package com.gxh.entity.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdPassDTO {
    Integer id;
    String oldpass;
    String newpass;
    String repass;
}
