package com.sipuang.xianyitong.bo;

import com.sipuang.xianyitong.model.Resource;
import com.sipuang.xianyitong.model.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleBO extends Role {
    private Set<Resource> resources = new HashSet<>();
}
