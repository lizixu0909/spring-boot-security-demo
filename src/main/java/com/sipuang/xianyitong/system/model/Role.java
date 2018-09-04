package com.sipuang.xianyitong.system.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@Data
@Table(name = "sys_role")
public class Role {
    @Id
    private Integer id;

    private String code;

    private String name;
}
