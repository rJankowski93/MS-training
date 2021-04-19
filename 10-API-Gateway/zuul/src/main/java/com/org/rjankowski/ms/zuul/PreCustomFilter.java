package com.org.rjankowski.ms.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class PreCustomFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return context.getRequest().getRequestURI().startsWith("/api/v1/registration");
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        String method = requestContext.getRequest().getMethod();
        String contentType = requestContext.getRequest().getContentType();
        String body = null;
        try {
            ServletInputStream inputStream = requestContext.getRequest().getInputStream();
            body = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("BODY:");
        System.out.println(body);
        System.out.println("METHOD: " + method);
        System.out.println("CONTENT TYPE: " + contentType);
        return null;
    }
}
