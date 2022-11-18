package com.org.rjankowski.ms.zuul;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomersFilter extends ZuulFilter {

    private int countHeader8088 = 0;
    private int countHeader8089 = 0;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        boolean isCustomerService = currentContext.getRequest().getRequestURI().startsWith("/api/v1/customers");
        return isCustomerService;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        List<Pair<String, String>> headers = currentContext.getZuulResponseHeaders().stream()
                .filter(s -> s.first().equals("x-port"))
                .collect(Collectors.toList());
        if (headers.isEmpty()) {
            return null;
        }
        String header = headers.get(0).second();
        System.out.println("**************************");
        System.out.println("x-port: " + header);
        if (Integer.valueOf(header) == 8088) {
            countHeader8088++;
        }
        if (Integer.valueOf(header) == 8089) {
            countHeader8089++;
        }
        System.out.println("Port == 8088: " + countHeader8088);
        System.out.println("Port == 8089: " + countHeader8089);

        return null;
    }
}
