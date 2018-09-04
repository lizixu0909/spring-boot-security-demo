package com.sipuang.xianyitong.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@Data
@Table(name = "sys_user")
public class User {

    @Id
    private Integer id;

    private String username;

    @JsonIgnore
    private String password;

    private boolean enable;
}
