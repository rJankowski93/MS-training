package com.org.rjankowski.ms.payment.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.rjankowski.ms.payment.CustomerApiClient;
import com.org.rjankowski.ms.payment.data.Cart;
import com.org.rjankowski.ms.payment.data.Customer;
import com.org.rjankowski.ms.payment.data.SaleRequest;
import com.org.rjankowski.ms.payment.repository.CartRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SaleResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private CustomerApiClient customerApiClient;

    @Test
    void processSale() throws Exception {
        SaleRequest saleRequest = new SaleRequest(1L,1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(saleRequest);

        Mockito.when(cartRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Cart(1L,Collections.emptyList())));
        Mockito.when(customerApiClient.getCustomer(ArgumentMatchers.any())).thenReturn(new ResponseEntity<>(new Customer(), HttpStatus.OK));


        mockMvc
                .perform(MockMvcRequestBuilders.post("/sale")
                .content(s).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("{\"points\":0}")));


    }
}
