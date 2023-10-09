package com.gxh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class UserBean{
    @TableId(type = IdType.AUTO)
    Integer id;
    String userName;
    String userNickname;
    String userPass;
    String userPassSalt;
    Integer userStatus;
    Integer userType;
    Integer userSex;
    String userEmail;
    String userPhone;
    String userImgid;
    Integer deleted;
}
