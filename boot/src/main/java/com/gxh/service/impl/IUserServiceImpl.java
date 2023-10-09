package com.gxh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxh.dao.UserDao;
import com.gxh.entity.UserBean;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.user.UserSeletPageConditionInDTO;
import com.gxh.mapper.UserMapper;
import com.gxh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper,UserBean> implements IUserService {

    @Autowired
    UserDao userDao;

    @Override
    public int loginToCode(String name, String pass) {
        UserBean bean;
        try {
            bean= userDao.selectByNameAndPass(name, pass);
        } catch (Exception e) {
            return -1;//出现异常
        }
        if (bean==null){//用户不存在
            return 3;
        } else if (bean.getUserStatus() == 0) {//用户被禁用
            return 2;
        } else if (bean.getUserPass().equals(pass)&&
                bean.getUserNickname().equals(name)) {//用户名和密码正确
            return 1;
        }else return 4;//密码不正确
    }

    @Override
    public PageDTO selectByPageUseMybatisPlusReturnPage(int page, int limit) {
        return userDao.selectByPageUseMybatisPlusReturnPage(page, limit);
    }

    @Override
    public PageDTO selectIserByPageUseCondition(UserSeletPageConditionInDTO dto) {
        return userDao.selectUserByPageUseCondition(dto);
    }

    @Override
    public UserBean selectById(Integer id) {
        return userDao.selectPassById(id);
    }

    @Override
    public UserBean loginToBean(String name, String pass) {
        return userDao.selectByNameAndPass(name, pass);
    }

    @Override
    public List<UserBean> queryAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public int updatePass(int id, String oldpass, String newpass, String repass) {//密码修改
        if (!Pattern.matches("\\S{3,16}",newpass)) {
            return 4;//新密码格式不规范
        }
        UserBean bean = userDao.selectPassById(id);//查询该用户信息
        if (Objects.equals(newpass,repass)){//验证两次新密码是否相同
            if (Objects.equals(bean.getUserPass(),oldpass)){//验证旧密码是否正确
                if (!Objects.equals(oldpass,newpass)){//判断新旧密码是否相同
                    if(userDao.updataPasswordById(id, newpass)!=0){//进行密码修改并判断修改情况
                        return 0;//修改成功
                    }else {
                        return -1;//修改失败
                    }
                }else {
                    return 1;//新旧密码相同
                }
            }else{
                return 2;//旧密码错误
            }
        }else {
            return 3;//两次新密码不同
        }
    }

    @Override
    public int resetPass(int id, String pass) {
        return userDao.updataPasswordById(id, pass);
    }

    @Override
    public int updateStatus(UserBean bean) {
        return userDao.updateStatus(bean);
    }

    @Override
    public PageDTO selectUserForPage(int page, int limit) {
        return userDao.selectUserForPage(page, limit);
    }

    @Override
    public int UserAdd(UserBean userBean) {
        return userDao.insertUser(userBean);
    }

    @Override
    public int userDeleteById(Integer id) {
        return userDao.userDeleteById(id);
    }

    @Override
    public int userDelete(UserBean userBean) {
        return userDao.deleteUser(userBean);
    }

    @Override
    public int userUpdate(UserBean userBean) {
        return userDao.updateUser(userBean);
    }
}
