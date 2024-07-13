package com.carole.secure.gateway.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carole.secure.gateway.funciton.SaTokenFunction;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaResult;

/**
 * @author CaroLe
 * @Date 2023/11/4 21:25
 * @Description
 */
@Configuration
public class SaTokenConfigure {

    public static final String PATTERN_MANAGER = "^/secure-[^/]+/(.*)";

    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
            // 拦截地址
            .addInclude("/**")
            // 登录校验 -- 拦截所有路由
            .setAuth(obj -> {
                String requestPath = SaHolder.getRequest().getRequestPath();
                Matcher matcher = Pattern.compile(PATTERN_MANAGER).matcher(requestPath);
                if (matcher.find()) {
                    requestPath = "/" + matcher.group(1);
                }
                String patternPath = requestPath;
                // 重写校验方法
                SaStrategy.instance.setHasElement(new SaTokenFunction());
                SaRouter.match("/**", "/secure-auth/user/login", r -> StpUtil.checkLogin());
                SaRouter.match(SaHolder.getRequest().getRequestPath(), "/secure-auth/user/login",
                    r -> StpUtil.checkPermission(patternPath));
            }).setError(e -> SaResult.error(e.getMessage()));
    }
}
