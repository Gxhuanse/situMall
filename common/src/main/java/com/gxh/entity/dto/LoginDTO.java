package com.gxh.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    String name;
    String pass;
    String captcha;
    String remember;
    String auto;
}
