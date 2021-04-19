package com.org.rjankowski.ms.zuul;

import com.google.common.net.HttpHeaders;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

@Component
public class PostCustomFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_FORWARD_FILTER_ORDER;

    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return context.getRequest().getRequestURI().startsWith("/api/v1/registration");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestUrl = ctx.getRequest().getRequestURL().toString();
        int serverPort = ctx.getRequest().getServerPort();
        InputStream responseDataStream = ctx.getResponseDataStream();
        String body = null;
        try {
            body = StreamUtils.copyToString(responseDataStream, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(requestUrl);
        System.out.println(serverPort);
        System.out.println(body);


        return null;
    }


}
