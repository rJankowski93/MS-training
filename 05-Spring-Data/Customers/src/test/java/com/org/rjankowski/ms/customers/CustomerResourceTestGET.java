package com.org.rjankowski.ms.customers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerResourceTestGET {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCustomers() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\",\"addresses\":[{\"id\":1,\"customerId\":1,\"city\":\"a\",\"street\":\"a\"},{\"id\":2,\"customerId\":1,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":2,\"firstName\":\"Jan\",\"lastName\":\"Kowalski\",\"addresses\":[{\"id\":3,\"customerId\":2,\"city\":\"a\",\"street\":\"a\"},{\"id\":4,\"customerId\":2,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":3,\"firstName\":\"Grzegorz\",\"lastName\":\"Kwiatkowski\",\"addresses\":[{\"id\":5,\"customerId\":3,\"city\":\"a\",\"street\":\"a\"},{\"id\":6,\"customerId\":3,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":4,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\",\"addresses\":[{\"id\":7,\"customerId\":4,\"city\":\"a\",\"street\":\"a\"},{\"id\":8,\"customerId\":4,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":5,\"firstName\":\"Piotr\",\"lastName\":\"Zielony\",\"addresses\":[{\"id\":9,\"customerId\":5,\"city\":\"a\",\"street\":\"a\"},{\"id\":10,\"customerId\":5,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":6,\"firstName\":\"Grzegorz\",\"lastName\":\"Rejent\",\"addresses\":[{\"id\":11,\"customerId\":6,\"city\":\"a\",\"street\":\"a\"},{\"id\":12,\"customerId\":6,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":7,\"firstName\":\"Jan\",\"lastName\":\"Nowak\",\"addresses\":[{\"id\":13,\"customerId\":7,\"city\":\"a\",\"street\":\"a\"},{\"id\":14,\"customerId\":7,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":8,\"firstName\":\"Piotr\",\"lastName\":\"Kwiatkowski\",\"addresses\":[{\"id\":15,\"customerId\":8,\"city\":\"a\",\"street\":\"a\"},{\"id\":16,\"customerId\":8,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":9,\"firstName\":\"Grzegorz\",\"lastName\":\"Kowalski\",\"addresses\":[{\"id\":17,\"customerId\":9,\"city\":\"a\",\"street\":\"a\"},{\"id\":18,\"customerId\":9,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":10,\"firstName\":\"Sebastian\",\"lastName\":\"Zielony\",\"addresses\":[{\"id\":19,\"customerId\":10,\"city\":\"a\",\"street\":\"a\"},{\"id\":20,\"customerId\":10,\"city\":\"a\",\"street\":\"a\"}]}]"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCustomerById1() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("{\"id\":1,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\",\"addresses\":[{\"id\":1,\"customerId\":1,\"city\":\"a\",\"street\":\"a\"},{\"id\":2,\"customerId\":1,\"city\":\"a\",\"street\":\"a\"}]}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCustomerById2() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("{\"id\":2,\"firstName\":\"Jan\",\"lastName\":\"Kowalski\",\"addresses\":[{\"id\":3,\"customerId\":2,\"city\":\"a\",\"street\":\"a\"},{\"id\":4,\"customerId\":2,\"city\":\"a\",\"street\":\"a\"}]}"))

                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCustomersByFirstNameAndLastName() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers?firstName=Piotr&lastName=Nowak"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\",\"addresses\":[{\"id\":1,\"customerId\":1,\"city\":\"a\",\"street\":\"a\"},{\"id\":2,\"customerId\":1,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":4,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\",\"addresses\":[{\"id\":7,\"customerId\":4,\"city\":\"a\",\"street\":\"a\"},{\"id\":8,\"customerId\":4,\"city\":\"a\",\"street\":\"a\"}]}]"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCustomersByFirstName() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers?firstName=Piotr"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\",\"addresses\":[{\"id\":1,\"customerId\":1,\"city\":\"a\",\"street\":\"a\"},{\"id\":2,\"customerId\":1,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":4,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\",\"addresses\":[{\"id\":7,\"customerId\":4,\"city\":\"a\",\"street\":\"a\"},{\"id\":8,\"customerId\":4,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":5,\"firstName\":\"Piotr\",\"lastName\":\"Zielony\",\"addresses\":[{\"id\":9,\"customerId\":5,\"city\":\"a\",\"street\":\"a\"},{\"id\":10,\"customerId\":5,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":8,\"firstName\":\"Piotr\",\"lastName\":\"Kwiatkowski\",\"addresses\":[{\"id\":15,\"customerId\":8,\"city\":\"a\",\"street\":\"a\"},{\"id\":16,\"customerId\":8,\"city\":\"a\",\"street\":\"a\"}]}]"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCustomersByLastName() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/customers?lastName=Nowak"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\",\"addresses\":[{\"id\":1,\"customerId\":1,\"city\":\"a\",\"street\":\"a\"},{\"id\":2,\"customerId\":1,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":4,\"firstName\":\"Piotr\",\"lastName\":\"Nowak\",\"addresses\":[{\"id\":7,\"customerId\":4,\"city\":\"a\",\"street\":\"a\"},{\"id\":8,\"customerId\":4,\"city\":\"a\",\"street\":\"a\"}]},{\"id\":7,\"firstName\":\"Jan\",\"lastName\":\"Nowak\",\"addresses\":[{\"id\":13,\"customerId\":7,\"city\":\"a\",\"street\":\"a\"},{\"id\":14,\"customerId\":7,\"city\":\"a\",\"street\":\"a\"}]}]"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
