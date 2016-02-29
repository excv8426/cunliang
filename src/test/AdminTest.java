package test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={WebConfig.class})
public class AdminTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired 
	private MockHttpSession session;
	
	private MockMvc mockMvc;
	
	private String loginname="admin1";
	
	@Before
	public void init(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		session.setAttribute("loginname", loginname);
		session.setAttribute("authority", "2");
	}
	
	@Test
	public void beforelogin() throws Exception {
		mockMvc.perform(post("/checkLoginuser").param("loginname", "admin1").param("password", "123456"))
		.andExpect(status().isOk()).andExpect(content().string("{\"success\":\"success\",\"authority\":\"1\"}"));
		
	}
	
	@Test
	public void testgetClients1() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").session(session))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testgetClients2() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").param("kehujingli", "佟凤英").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").param("kehujingli", "佟凤英").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testgetClients3() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").param("shifougaojiazhi", "高价值").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").param("shifougaojiazhi", "高价值").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testgetClients4() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").param("chanpinbaohancbss", "3G-46元基本套餐C").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").param("chanpinbaohancbss", "3G-46元基本套餐C").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testgetClients5() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").param("heyueleixing", "").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").param("heyueleixing", "").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testgetClients6() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").param("dianhahaoma", "13031885500").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").param("dianhahaoma", "13031885500").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testgetClients7() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").param("shituizhengce", "首推Iphone6直降800政策，次推4G存费赠费单卡合约").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").param("shituizhengce", "首推Iphone6直降800政策，次推4G存费赠费单卡合约").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testgetClients8() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").param("quanwangyewuleixing", "3G手机").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").param("quanwangyewuleixing", "3G手机").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testgetClients9() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").param("rongheleixing", "").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").param("rongheleixing", "").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testgetClients10() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").param("kaihuriqilo", "2000-01-01").param("kaihuriqihi", "2010-01-01").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").param("kaihuriqilo", "2000-01-01").param("kaihuriqihi", "2010-01-01").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testgetClients11() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").param("zaiwangshichanglo", "10").param("zaiwangshichanghi", "100").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").param("zaiwangshichanglo", "10").param("zaiwangshichanghi", "100").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testgetClients12() throws Exception {
		mockMvc.perform(post("/loginuser/default/getClientscount").param("fentanqianshourulo", "10").param("fentanqianshouruhi", "100").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
		mockMvc.perform(post("/loginuser/default/getClients").param("fentanqianshourulo", "10").param("fentanqianshouruhi", "100").param("maxresult", "20").param("firstresult", "0").session(session))
		.andExpect(status().isOk());
	}
}
