package com.accenture.lkm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorServiceApplicationTests {

	Logger logger = LoggerFactory.getLogger(VendorServiceApplicationTests.class);
	
	@Test
	public void contextLoads() {
		logger.info("UNIT TEST STARTED");
		assertEquals("STRING VALUE NOT SAME!","Spring Boot", "Spring Boot");
	}

}
