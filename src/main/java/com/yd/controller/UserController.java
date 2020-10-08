package com.yd.controller;

import com.yd.model.User;
import com.yd.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody User user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return error("用户名不能为空");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return error("密码不能为空");
        }
        user.setPassword(md5(user.getUsername(), user.getPassword()));
        if (userService.register(user) <= 0) {
            return error("服务器错误，请稍后再试");
        }
        return success(null);
    }

    @GetMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {                                                 // 满足shiro的可认证状态
            UsernamePasswordToken upToken = new UsernamePasswordToken(username, password);    // shiro权限认证类型
            upToken.setRememberMe(true);                                                     // 用户登录时效性
            try {
                currentUser.login(upToken);    // 调用realm认证用户权限
                return "登录成功";
            } catch (IncorrectCredentialsException ice) {
                System.out.println("用户名/密码不匹配！");
            } catch (LockedAccountException lae) {
                System.out.println("账户已被冻结！");
            } catch (UnknownAccountException uae) {
                System.out.println("账户不存在");
            } catch (AuthenticationException e) {
                System.out.println(e.getMessage());
            }
        }
        return "已被认证，不需要重复登录";
    }

    // 注册时，进行shiro加密，返回加密后的结果，如果在加入shiro之前，存在用户密码不是此方式加密的，那么将无法登录
    // 使用用户名作为盐值
    private String md5(String username, String password) {
        String hashAlgorithmName = "MD5";                   // 加密方式
        ByteSource salt = ByteSource.Util.bytes(username);  // 以账号作为盐值
        int hashIterations = 11;                            // 加密11次
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

}
