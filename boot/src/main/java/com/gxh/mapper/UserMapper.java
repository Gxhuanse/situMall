package com.gxh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxh.entity.UserBean;
import com.gxh.entity.dto.user.UserSeletPageConditionInDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserBean> {

    List<UserBean> selectAllUser();

    List<UserBean> selectUserByPageCondition(UserSeletPageConditionInDTO dto);

    @Select("select *from situ_prj_02_boot.tb_user where user_nickname=#{name} and user_pass=#{pass}")
    UserBean selectByNameAndPass(@Param("name") String name, @Param("pass") String pass);

    @Update("update situ_prj_02_boot.tb_user set user_pass=#{pass} where id=#{id}")
    int updataPasswordById(@Param("id") int id, @Param("pass") String pass);

    @Select(" select *from situ_prj_02_boot.tb_user where id=#{id};")
    UserBean selectPassById(int id);

    @Update("update situ_prj_02_boot.tb_user set user_status=#{userStatus} where id=#{id}")
    int updateStatus(UserBean bean);

    @Select("select  count(*)as count from situ_prj.tb_user")
    int selectCount();

    @Select("select *from situ_prj_02_boot.tb_user limit #{page},#{limit}")
    List<UserBean> selectAllUserByPage(@Param("page") int page, @Param("limit") int limit);

    int insertUser(UserBean userBean);

    @Delete("delete from situ_prj_02_boot.tb_user where id=#{id}")
    int deleteUser(UserBean userBean);

    int updateUser(UserBean userBean);
}
