package net.blissmall.puff.test.user;

import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.test.TestPuffBootApplication;
import net.blissmall.puff.vo.user.RegistryLoginVo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * user相关接口的测试
 * @Author : zhuzhenglin
 * @Date : 16/8/17 17:41
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,classes = {TestPuffBootApplication.class})
@ActiveProfiles("dev")
public class UserSerivceImplTest {

//    @Resource(name = "testRestTemplate")
//    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Resource
    private UserService userService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void end(){

    }

    @Test
    @Transactional
    @Rollback
    public void testUserAdd(){
        RegistryLoginVo registryLoginVo = new RegistryLoginVo();
        registryLoginVo.setUsername("pigo");
        registryLoginVo.setPassword("hahaha");
        registryLoginVo.setAuthType(RegistryLoginVo.AuthType.MOBILEPHONE);
        String uuid = userService.addUser(registryLoginVo);
        Assert.assertNotNull("添加用户时异常。。。",uuid);
    }
    @Test
    public void testIndex() throws Exception{
        mockMvc.perform(get("/login"))
                .andExpect(view().name("login"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
