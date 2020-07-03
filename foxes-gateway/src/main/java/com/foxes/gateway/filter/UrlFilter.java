package com.foxes.gateway.filter;

/**
 * @author GaoZeXi
 */
public class UrlFilter {

    //所有需要传递令牌的地址
    public static String filterPath="" +
            "/api/user/**," +
            "/api/returnOrderItem/**";

    public static boolean hasAuthorize(String url){

        String[] split = filterPath.replace("**", "").split(",");

        for (String value : split) {

            if (url.startsWith(value)){
                return true; //代表当前的访问地址是需要传递令牌的
            }
        }

        return false; //代表当前的访问地址是不需要传递令牌的
    }
}
