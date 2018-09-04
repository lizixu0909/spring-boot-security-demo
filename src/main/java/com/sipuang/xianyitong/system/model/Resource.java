package com.sipuang.xianyitong.system.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@Data
@Table(name = "sys_resource")
public class Resource {
    @Id
    private Integer id;
    /**
     * 访问地址
     */
    private String url;
    /**
     * 请求方式
     */
    private String method;
    /**
     * 名称
     */
    private String name;
}
