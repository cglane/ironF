package com.theironyard;

import com.theironyard.services.DonationRepo;
import com.theironyard.services.ProjectRepo;
import com.theironyard.services.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.AssertTrue;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IronFundApplication.class)
@WebAppConfiguration
public class IronFundApplicationTests {

	@Autowired
	UserRepo users;

	@Autowired
	DonationRepo donations;

	@Autowired
	ProjectRepo projects;

	@Autowired
	WebApplicationContext wap;

	MockMvc mockMvc;

	@org.junit.Before
	public void before(){
	users.deleteAll();
	donations.deleteAll();
	projects.deleteAll();
	mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();

	}
	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(
			MockMvcRequestBuilders.post("/login")
				.param("username","test")
				.param("password","test")

		);
		assertTrue(users.count()==1);
	}
	@Test
	public void testAdd() throws Exception {
		mockMvc.perform(
		MockMvcRequestBuilders.post("/create")
            .sessionAttr("username", "Nathan")
			.param("title", "Test")
			.param("balance", "11.0")
			.param("description", "Test")
			.param("finishDate", "2015-11-16T22:23:48")
			.param("startDate", "2015-11-16T22:23:48")
			.param("goal", "11.0")


		);
		assertTrue(projects.count()==1);

	}

}
