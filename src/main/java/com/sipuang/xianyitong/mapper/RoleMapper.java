package com.sipuang.xianyitong.mapper;

import com.sipuang.xianyitong.model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author lijun
 * @date 2018-04-20.
 */
public interface RoleMapper extends Mapper<Role> {

    @Select("select r.* from t_role r join t_user_ref_role ur on r.id = ur.role_id where ur.user_id = #{userId}")
    Set<Role> selectByUserId(@Param("userId") Integer userId);

    @Delete("delete from t_role_ref_resource where role_id = #{roleId}")
    void deleteResourceByRoleId(@Param("roleId") Integer roleId);

    void insertResources(@Param("roleId") Integer roleId, @Param("resourceIds") List<Integer> resourceIds);
}
