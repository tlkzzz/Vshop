package com.Vshop.seller.utils.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.Vshop.core.entity.base.Account;

/**
 * Created by yansheng on 2014/7/26.
 */
public class SysUserFilter extends PathMatchingFilter {

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        Account account = (Account) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("user_name", account);
        return true;
    }
}
