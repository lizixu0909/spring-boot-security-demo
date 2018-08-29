package com.sipuang.xianyitong.mapper;

import com.sipuang.xianyitong.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author lijun
 * @date 2018-04-20.
 */
public interface UserMapper extends Mapper<User> {
    @Select("select * from t_user where username=#{username}")
    User selectByUsername(@Param("username") String username);

    @Delete("delete from t_user_ref_role where user_id=#{userId}")
    void deleteRolesByUserId(@Param("userId") Integer userId);

    void insertRoles(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);
}
