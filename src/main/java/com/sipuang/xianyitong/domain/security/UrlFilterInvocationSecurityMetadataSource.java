package com.sipuang.xianyitong.domain.security;

import com.sipuang.xianyitong.bo.RoleBO;
import com.sipuang.xianyitong.model.Resource;
import com.sipuang.xianyitong.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private RoleService roleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        /**
         * 从容器中获取用户信息
         */
        FilterInvocation fi = (FilterInvocation) object;
        HttpServletRequest request = fi.getHttpRequest();
        AntPathRequestMatcher matcher;
        List<RoleBO> roles = roleService.getAll();
        Collection<ConfigAttribute> configAttributes = new HashSet<>();
        for (RoleBO role : roles) {
            if (!role.getResources().isEmpty()) {
                for (Resource resource : role.getResources()) {
                    matcher = new AntPathRequestMatcher(resource.getUrl(), resource.getMethod());
                    if (matcher.matches(request)) {
                        configAttributes.add((ConfigAttribute) () -> role.getCode());
                        break;
                    }
                }
            }
        }
        if (configAttributes.isEmpty()) {
            //判断URL 是否在资源表中
            //没有匹配到,默认是要登录才能访问
            /**
             * 针对不同系统，如果有需要游客访问的，可以将这儿设置成游客（匿名用户）。如果是纯内部系统，则将这儿设置成跳转到登录
             */
            return SecurityConfig.createList("ROLE_ANONYMOUSLY");
        }
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
