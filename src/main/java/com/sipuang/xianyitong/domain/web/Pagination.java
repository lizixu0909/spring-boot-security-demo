package com.sipuang.xianyitong.domain.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Pattern;

/**
 * 分页类
 * 作为参数在controller
 *
 * @author lijun
 * @date 2018-04-20.
 */
@Data
public class Pagination {
    /**
     * 当前页
     */
    private Integer pageNum = 1;
    /**
     * 每页数量
     */
    private Integer pageSize = 20;
    /**
     * 分为DESC 和 ASC 不区分大小写
     */
    @Pattern(regexp = "(?i)^asc$|(?i)^desc$", message = "参数只能为asc或desc")
    private String orderBy = "desc";
    /**
     * 需要排序的字段
     */
    private String order;

    /**
     * 执行分页
     *
     * @param <T>
     * @return
     */
    public <T> Page<T> startPage() {
        if (StringUtils.isNotBlank(this.order)) {
            return PageHelper.startPage(this.pageNum, this.pageSize, order + " " + orderBy);
        }
        return PageHelper.startPage(this.pageNum, this.pageSize);
    }
}
