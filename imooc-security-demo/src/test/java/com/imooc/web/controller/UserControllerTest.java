package com.imooc.web.controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    /**
     *  @Before ：每次在执行测试用例 @test之前运行
     */
    @Before
    public void setup(){
          mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                 .param("username","jojo")
                 .contentType(MediaType.APPLICATION_JSON_UTF8))
                 .andExpect(MockMvcResultMatchers.status().isOk()) ////期望返回状态码是isOk(), 就是200
                 .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));//返回结果是集合，元素的数量是3
    }



}