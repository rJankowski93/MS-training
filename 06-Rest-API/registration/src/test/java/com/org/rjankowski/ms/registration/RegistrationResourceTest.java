package com.org.rjankowski.ms.registration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.org.rjankowski.ms:customers:+:stubs:6565"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class RegistrationResourceTest {

    @Test
    public void create_customers_from_service_customers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Customer customer = new Customer();
        customer.setActive(true);
        customer.setFirstName("example");
        customer.setLastName("example");
        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
        new RestTemplate().exchange("http://localhost:6565/customers", HttpMethod.POST, request,
                Customer[].class);
    }
}

