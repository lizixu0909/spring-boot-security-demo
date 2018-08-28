package com.sipuang.xianyitong.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@Data
@Table(name = "t_role")
public class Role {
    @Id
    private Integer id;

    private String code;

    private String name;
}
