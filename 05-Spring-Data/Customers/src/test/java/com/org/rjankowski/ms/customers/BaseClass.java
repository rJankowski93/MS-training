package com.org.rjankowski.ms.customers;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomersApplication.class)
public abstract class BaseClass {

    @Autowired
    CustomerResource customerResource;

    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(this.customerResource);
    }

}
