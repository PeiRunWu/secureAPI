package com.carole.secure.gateway.funciton;

import java.util.Iterator;
import java.util.List;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import cn.dev33.satoken.fun.strategy.SaHasElementFunction;
import cn.hutool.core.collection.CollectionUtil;

/**
 * @author CaroLe
 * @Date 2023/11/6 22:24
 * @Description
 */
public class SaTokenFunction implements SaHasElementFunction {
    /**
     * Applies this function to the given arguments.
     *
     * @param list the first function argument
     * @param element the second function argument
     * @return the function result
     */
    @Override
    public Boolean apply(List<String> list, String element) {
        if (CollectionUtil.isNotEmpty(list)) {
            if (!list.contains(element)) {
                Iterator<String> var2 = list.iterator();
                String pattern;
                do {
                    if (!var2.hasNext()) {
                        return false;
                    }
                    pattern = (String)var2.next();
                } while (!vagueMatch(pattern, element));
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean vagueMatch(String pattern, String str) {
        if (pattern == null && str == null) {
            return true;
        } else if (pattern != null && str != null) {
            PathMatcher pathMatcher = new AntPathMatcher();
            return !pattern.contains("*") ? pathMatcher.match(pattern, str) : vagueMatchMethod(pattern, str);
        } else {
            return false;
        }
    }

    private static boolean vagueMatchMethod(String pattern, String str) {
        int m = str.length();
        int n = pattern.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        int i;
        for (i = 1; i <= n && pattern.charAt(i - 1) == '*'; ++i) {
            dp[0][i] = true;
        }

        for (i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (pattern.charAt(j - 1) != '*') {
                    if (str.charAt(i - 1) == pattern.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}
