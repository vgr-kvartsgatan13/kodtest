package se.kvartsgatan.vgr;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VgrApplicationTests {
	Logger logger = LoggerFactory.getLogger(VgrApplicationTests.class);

	@Test
	void contextLoads() {
		logger.info("context is loaded");
	}

}
