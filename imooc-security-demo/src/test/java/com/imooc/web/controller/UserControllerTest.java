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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                 .param("username","jojo")
                 .param("age","18")
                 .param("ageTo","60")
                 .param("xxx","yyy")
                 .contentType(MediaType.APPLICATION_JSON_UTF8))
                 .andExpect(MockMvcResultMatchers.status().isOk()) ////期望返回状态码是isOk(), 就是200
                 .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))//返回结果是集合，元素的数量是3
                 .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 查询id为1 的user信息
     * @throws Exception
     */
    @Test
    public void whenGetInfoSuccess() throws Exception{
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                                       .contentType(MediaType.APPLICATION_JSON_UTF8))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }

    /**
     * 查询id 非数字
     * @throws Exception
     */
    @Test
    public void whenGetInfoFail() throws Exception {
          mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                           .contentType(MediaType.APPLICATION_JSON_UTF8))
                           .andExpect(MockMvcResultMatchers.status().is4xxClientError());


    }

    @Test
    public void whenCreateSuccess() throws Exception{

        Date date = new Date();
        System.out.println(date.getTime());
        String content ="{\"username\":\"xixi\",\"password\":null,\"birthday\":"+date.getTime()+"}";
         String reuslt = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(content))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                                .andReturn().getResponse().getContentAsString();
         System.out.println(reuslt);
    }

    @Test
    public void whenUpdateSuccess() throws Exception{

        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(date.getTime());
        String content ="{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":\"+date.getTime()+\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                               .contentType(MediaType.APPLICATION_JSON_UTF8)
                               .content(content))
                               .andExpect(MockMvcResultMatchers.status().isOk())
                               .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                               .andReturn().getResponse().getContentAsString();
           System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
               .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
