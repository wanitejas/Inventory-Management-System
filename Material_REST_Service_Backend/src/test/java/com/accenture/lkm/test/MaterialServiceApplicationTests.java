package com.accenture.lkm.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

//import com.accenture.lkm.test.controller.MaterialControllerTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterialServiceApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(MaterialServiceApplicationTests.class);

	@Test
	public void contextLoads() {
		logger.info("UNIT TEST STARTED");
		assertEquals("STRING VALUE NOT SAME!","Spring Boot", "Spring Boot");
	}

}
