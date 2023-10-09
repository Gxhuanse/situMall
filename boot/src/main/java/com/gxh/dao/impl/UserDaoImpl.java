package com.gxh.dao.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxh.dao.UserDao;
import com.gxh.entity.UserBean;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.user.UserSeletPageConditionInDTO;
import com.gxh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class UserDaoImpl implements UserDao {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserBean> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public PageDTO selectUserByPageUseCondition(UserSeletPageConditionInDTO dto) {

        PageDTO pagedto=new PageDTO();

        PageHelper.startPage(dto.getCurr(),dto.getNums());

        List<UserBean> userBeanList = userMapper.selectUserByPageCondition(dto);

        PageInfo<UserBean> pageInfo=new PageInfo<>(userBeanList);

        pagedto.setCount(pageInfo.getTotal());
        pagedto.setList(pageInfo.getList());
        return pagedto;
    }



    @Override
    public PageDTO selectByPageUseMybatisPlusReturnPage(int page, int limit) {
        IPage<UserBean> iPage=new Page<>(page,limit);
        userMapper.selectPage(iPage,null);

        PageDTO pageinfo=new PageDTO();

        Long count= iPage.getTotal();
        List<UserBean> userBeanList=iPage.getRecords();

        pageinfo.setCount(count);
        pageinfo.setList(userBeanList);
        return pageinfo;
    }

    @Override
    public PageDTO selectUserForPage(int page, int limit) {
        return selectByPageUseMybatisPlusReturnPage(page, limit);
    }

    @Override
    public UserBean selectByNameAndPass(String name,String pass) {
        return userMapper.selectByNameAndPass(name,pass);
    }

    @Override
    public int updataPasswordById(int id, String pass) {
        return userMapper.updataPasswordById(id, pass);
    }

    @Override
    public UserBean selectPassById(int id) {
        return userMapper.selectPassById(id);
    }

    @Override
    public int selectCount() {
        return userMapper.selectCount();
    }

    @Override
    public List<UserBean> selectAllUserByPage(int page, int limit) {
        int m=(page-1)*limit;
        return userMapper.selectAllUserByPage(m, limit);
    }

    @Override
    public int updateStatus(UserBean bean) {
        return userMapper.updateStatus(bean);
    }

    @Override
    public int insertUser(UserBean userBean) {
        return userMapper.insert(userBean);
    }

    @Override
    public int userDeleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int deleteUser(UserBean userBean) {
        return userMapper.deleteUser(userBean);
    }

    @Override
    public int updateUser(UserBean userBean) {
        return userMapper.updateById(userBean);
    }
}
