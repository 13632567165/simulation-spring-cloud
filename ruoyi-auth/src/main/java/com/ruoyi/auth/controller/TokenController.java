package com.ruoyi.auth.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.auth.form.LoginBody;
import com.ruoyi.auth.form.RegisterBody;
import com.ruoyi.auth.service.SysLoginService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.auth.AuthUtil;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * token 控制
 * 
 * @author ruoyi
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form)
    {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody)
    {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return R.ok();
    }

    @PostMapping("token")
    public R<?> login(HttpServletRequest request, @RequestBody Map<String,Object> param)
    {
        try {
            LoginUser loginUser = tokenService.getLoginUser(request);
            if (StringUtils.isNull(loginUser)) {
                throw new Exception("登陆已失效，请重新登陆！");
            }
            String accessToken = loginUser.getToken();
            if (StringUtils.isNull(accessToken)) {
                throw new Exception("Token已失效，请重新登陆！");
            }
            String system = (String) param.get("system");
            if (StringUtils.isNull(system)) {
                throw new Exception("系统不存在！");
            }
            List<Map<String, Object>> abilityList = new ArrayList<>();
            Map<String, Object> ability = new HashMap<>();
            ability.put("action", "manage");
            ability.put("subject", "all");
            abilityList.add(ability);

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("accessToken", accessToken);
            resultMap.put("refreshToken", accessToken);
            resultMap.put("fullName", loginUser.getSysUser().getNickName());
            resultMap.put("username", loginUser.getSysUser().getNickName());
            resultMap.put("email", loginUser.getSysUser().getEmail());
            resultMap.put("avatar", loginUser.getSysUser().getAvatar());
            resultMap.put("role", "admin");
            resultMap.put("ability", abilityList);

            return R.ok(resultMap);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }
}
