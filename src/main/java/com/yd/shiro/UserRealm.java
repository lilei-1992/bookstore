package com.yd.shiro;

import com.yd.model.User;
import com.yd.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 把AuthenticationToken实质为UsernamePasswordToken，直接转换即可
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        User user = userService.getUser(usernamePasswordToken.getUsername());          // 通过service查询用户名是否存在
        if (user == null)
            throw new UnknownAccountException("用户不存在！");
        System.out.println("doGetAuthenticationInfo username=" + user.getUsername());
        System.out.println("doGetAuthenticationInfo password=" + user.getPassword());
        // 保存加密设置
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());    // 以用户名为加密盐值
        String realmName = getName();                                              // 当前realm对象的name，调用父类的getName()方法即可
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, realmName);
        // 在没有加盐的情况下，三个参数就可以进行初步的简单认证信息对象的包装
//        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getSimpleName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
