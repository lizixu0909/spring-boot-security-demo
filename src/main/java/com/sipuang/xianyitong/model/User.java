package com.sipuang.xianyitong.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@Data
@Table(name = "t_user")
public class User {

    @Id
    private Integer id;

    private String username;

    @JsonIgnore
    private String password;

    private boolean enable;
}
