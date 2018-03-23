package com.spannering.spanneringapp;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DoorsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllDoors() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/getAllDoors").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("this is a static list of doors")));
    }

    @Test
    public void addDoor() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/addDoor")
                .contentType(MediaType.TEXT_PLAIN)
                .content("thisIsADoorName"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("123")));
    }
}