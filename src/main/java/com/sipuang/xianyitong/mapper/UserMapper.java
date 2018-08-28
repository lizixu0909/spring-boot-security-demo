package com.sipuang.xianyitong.mapper;

import com.sipuang.xianyitong.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lijun
 * @date 2018-04-20.
 */
public interface UserMapper extends Mapper<User> {
    @Select("select * from t_user where username=#{username}")
    User selectByUsername(@Param("username") String username);
}
