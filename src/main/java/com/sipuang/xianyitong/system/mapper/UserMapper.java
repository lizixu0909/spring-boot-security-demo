package com.sipuang.xianyitong.system.mapper;

import com.sipuang.xianyitong.system.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author lijun
 * @date 2018-04-20.
 */
public interface UserMapper extends Mapper<User> {
    @Select("select * from sys_user where username=#{username}")
    User selectByUsername(@Param("username") String username);

    @Delete("delete from sys_user_ref_role where user_id=#{id}")
    void deleteRolesByUserId(@Param("id") Integer id);

    void insertRoles(@Param("id") Integer id, @Param("roleIds") List<Integer> roleIds);
}
