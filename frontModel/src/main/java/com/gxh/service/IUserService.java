package com.gxh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxh.entity.UserBean;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.user.UserSeletPageConditionInDTO;

import java.util.List;

public interface IUserService extends IService<UserBean> {

    /**
     * 登录验证功能，
     * @param name 用户名
     * @param pass 密码
     * @return 登录状态码 返回值1为成功，2为禁用，3为不存在，4为密码错误
     */
    int loginToCode(String name, String pass);

    UserBean loginToBean(String name, String pass);

    PageDTO selectByPageUseMybatisPlusReturnPage(int page, int limit);

    PageDTO selectIserByPageUseCondition(UserSeletPageConditionInDTO dto);

    List<UserBean> queryAllUser();

    UserBean selectById(Integer id);

    /**
     * 修改密码功能
     * @param id 要修改的id
     * @param oldpass 原来的密码
     * @param newpass 新密码
     * @param repass 验证新密码
     * @return 修改状态码 返回值 0为成功，1为新旧密码相同，2为旧密码错误，3为两次新密码不同，4为新密码不规范，-1为失败
     */
    int updatePass(int id, String oldpass, String newpass, String repass);

    int resetPass(int id, String pass);

    int updateStatus(UserBean bean);

    PageDTO selectUserForPage(int page, int limit);

    int UserAdd(UserBean userBean);

    int userDelete(UserBean userBean);

    int userDeleteById(Integer id);

    int userUpdate(UserBean userBean);
}
