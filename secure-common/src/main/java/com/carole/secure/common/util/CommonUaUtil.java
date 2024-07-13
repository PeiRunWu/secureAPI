package com.carole.secure.common.util;

import javax.servlet.http.HttpServletRequest;

import com.carole.secure.common.context.BaseContext;
import com.carole.secure.common.context.RequestContext;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;

/**
 * @author CaroLe
 * @Date 2023/11/6 21:08
 * @Description
 */
public class CommonUaUtil {

    /**
     * 获取客户端浏览器
     * 
     * @param request 请求信息
     * @return String
     */
    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        if (ObjectUtil.isEmpty(userAgent)) {
            return StrUtil.DASHED;
        } else {
            String browser = userAgent.getBrowser().getName();
            return "Unknown".equals(browser) ? StrUtil.DASHED : browser;
        }
    }

    /**
     * 获取客户端操作系统
     * 
     * @param request 请求信息
     * @return String
     */
    public static String getOs(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        if (ObjectUtil.isEmpty(userAgent)) {
            return StrUtil.DASHED;
        } else {
            String os = userAgent.getPlatform().toString();
            return BaseContext.UNKNOWN.equals(os) ? StrUtil.DASHED : os;
        }
    }

    /**
     * 获取请求代理头
     * 
     * @param request 请求信息
     * @return UserAgent
     */
    private static UserAgent getUserAgent(HttpServletRequest request) {
        String userAgentStr = ServletUtil.getHeaderIgnoreCase(request, RequestContext.USER_AGENT);
        UserAgent userAgent = UserAgentUtil.parse(userAgentStr);
        if (ObjectUtil.isNotEmpty(userAgentStr)) {
            if (BaseContext.UNKNOWN.equals(userAgent.getBrowser().getName())) {
                userAgent.setBrowser(new Browser(userAgentStr, null, StrUtil.EMPTY));
            }
        }
        return userAgent;
    }
}
