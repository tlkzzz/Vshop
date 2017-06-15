package com.Vshop.supplier.utils.sessionKey;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.Vshop.core.common.Constants;
import com.Vshop.core.common.SpringContextUtil;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.Supplier;
import com.Vshop.service.module.goods.service.SupplierService;
import com.Vshop.service.module.member.service.MemberService;

/**
 * @author llf
 * @Package com.Vshop.seller.utils.sessionKey
 * @Description:
 * @date 2015/3/2 14:38
 */
public class CacheUtils {

    /**
     * 获取SessionUser
     * @return
     */
    public static CacheUser getCacheUser() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        if(null != session) {
            CacheUser cacheUser;
            Subject currentUser = SecurityUtils.getSubject();
            cacheUser = (CacheUser) session.getAttribute(Constants.USER_SESSION_KEY);  
            if (cacheUser==null) {
                cacheUser = CacheUtils.initCacheUser(currentUser.getPrincipal().toString());
            }
            return cacheUser;
        }else{
            return null;
        }
    }

    /**
     * 根据卖家用户名初始化数据
     * @param memberName
     * @return
     */
    public static CacheUser initCacheUser(String memberName) {
        MemberService memberService = SpringContextUtil.getBean(MemberService.class);
//        StoreService storeService = SpringContextUtil.getBean(StoreService.class);
        SupplierService supplierService = SpringContextUtil.getBean(SupplierService.class);
        Member member = memberService.findMemberByName(memberName);
//        Store store = storeService.findByMemberId(member.getMemberId());
        Supplier supplier = supplierService.findByMemberId(member.getMemberId());
        
        CacheUser cacheUser = new CacheUser();
        cacheUser.setMember(member);
//        cacheUser.setStore(store);
        cacheUser.setSupplier(supplier);
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        if (null != session) {
            session.setAttribute(Constants.USER_SESSION_KEY, cacheUser);
        } else {
            throw new RuntimeException("CacheUser初始化失败");
        }
        return cacheUser;
    }

    /**
     * 清除用户数据
     */
    public static void cleanCacheUser() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute(Constants.USER_SESSION_KEY);
    }
}
