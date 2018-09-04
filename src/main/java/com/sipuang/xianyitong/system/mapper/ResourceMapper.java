package com.sipuang.xianyitong.system.mapper;

import com.sipuang.xianyitong.system.model.Resource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

/**
 * @author lijun
 * @date 2018-04-20.
 */
public interface ResourceMapper extends Mapper<Resource> {

    @Select("select * from sys_resource r join sys_role_ref_resource rr on r.id = rr.resource_id where rr.role_id=#{roleId}")
    Set<Resource> selectByRoleId(@Param("roleId") Integer roleId);
}
