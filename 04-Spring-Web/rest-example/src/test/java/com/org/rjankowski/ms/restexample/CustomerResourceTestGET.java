package com.org.rjankowski.ms.restexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerResourceTestGET {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCustomers() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\"},{\"id\":2,\"firstName\":\"Adam\",\"lastName\":\"Kowalski\"},{\"id\":3,\"firstName\":\"Sebastian\",\"lastName\":\"Czarny\"}]"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCustomerById1() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("{\"id\":1,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\"}"))

                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCustomerById2() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("{\"id\":2,\"firstName\":\"Adam\",\"lastName\":\"Kowalski\"}"))

                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCustomerById5() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers/5"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getCustomersByFirstNameAndLastName() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers?firstName=Piotr&lastName=Nowak"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\"}]"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCustomersByFirstName() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers?firstName=Piotr"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\"}]"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCustomersByLastName() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers?lastName=Nowak"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\"}]"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
