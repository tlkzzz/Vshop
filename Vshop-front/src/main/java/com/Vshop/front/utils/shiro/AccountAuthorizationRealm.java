package com.Vshop.front.utils.shiro;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.Vshop.core.auth.shiro.UsernamePasswordToken;
import com.Vshop.core.common.Encodes;
import com.Vshop.core.entity.base.Member;
import com.Vshop.service.module.member.service.MemberService;

/**
 * 管理员的认证,角色,权限控制
 */
public class AccountAuthorizationRealm extends AuthorizingRealm {

    @Resource
    private MemberService memberService;
//    private String name = "AccountAuthorizationRealm";
//
//    public String getName() {
//        return name;
//    }

    /**
     * 查询获得用户信息
     * AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）
     *
     * AuthenticationInfo有两个作用：
     * 1、如果Realm 是AuthenticatingRealm 子类，则提供给AuthenticatingRealm 内部使用的
     *    CredentialsMatcher进行凭据验证；（如果没有继承它需要在自己的Realm中自己实现验证）；
     * 2、提供给SecurityManager来创建Subject（提供身份信息）；
     *
     * @param authcToken
     * @return
     * @throws org.apache.shiro.authc.AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Member member = memberService.findMemberByName(token.getUsername());
        if (member==null) {
            throw new UnknownAccountException();//用户不存在
        }else {
        	byte[] salt = Encodes.decodeHex(member.getMemberPasswd().substring(0,16));
            return new SimpleAuthenticationInfo(new Principal(member, token.isMobileLogin()), 
        			member.getMemberPasswd(), ByteSource.Util.bytes(salt), getName());
        }
    }

    /**
     * 表示根据用户身份获取授权信息
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*String loginName = (String) principalCollection.getPrimaryPrincipal();
        Account account = accountDao.findByLoginName(loginName);
        List<Role> roleList = roleDao.getRoleByAcctId(account.getId());
        Set<String> roleNameList = Sets.newHashSet();
        for (Role role : roleList) {
            roleNameList.add(role.getRoleName());
        }
        List<Permission> permissionList = permissionDao.getPermByRoleList(roleList);
        Set<String> permNameList = Sets.newHashSet();
        for (Permission permission : permissionList) {
            permNameList.add(permission.getPermName());
        }*/
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("front");
//        info.setStringPermissions(permNameList);
        return info;
    }
    
    /**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String id; // 编号
		private String loginName; // 登录名
		private String name; // 姓名
		private boolean mobileLogin; // 是否手机登录
		
//		private Map<String, Object> cacheMap;

		public Principal(Member user, boolean mobileLogin) {
			this.id = user.getMemberId() + "";
			this.loginName = user.getMemberName();
			this.name = user.getMemberName();
			this.mobileLogin = mobileLogin;
		}

		public String getId() {
			return id;
		}

		public String getLoginName() {
			return loginName;
		}

		public String getName() {
			return name;
		}

		public boolean isMobileLogin() {
			return mobileLogin;
		}

		public String toString() {
			return loginName;
		}

	}

}
