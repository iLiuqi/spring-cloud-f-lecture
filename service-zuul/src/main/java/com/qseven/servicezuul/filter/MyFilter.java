package com.qseven.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);

    /*
     * pre: 路由之前
     * routing: 路由时
     * post: 路由之后
     * error: 发送错误调用
     * */
    @Override
    public String filterType() {
        return "pre";
    }

    /*
     * 优先级
     * */
    @Override
    public int filterOrder() {
        return 0;
    }

    /*
     * 是否过滤
     * */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /*
     * 过滤的具体逻辑
     * */
    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        LOGGER.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));

        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            LOGGER.warn("token is empty");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);

            try {
                currentContext.getResponse().getWriter().write("token is empty!!!");
            } catch (IOException ignored) {

            }

            return null;
        }

        LOGGER.info("ok");
        return null;
    }

}
