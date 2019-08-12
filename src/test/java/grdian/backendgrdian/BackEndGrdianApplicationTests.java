package grdian.backendgrdian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import grdian.backendgrdian.utility.SmsSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackEndGrdianApplicationTests {

	@Autowired
	SmsSender smsSender;
	@Test
	public void contextLoads() {
	}

	@Test
	public void sendsSmsMessage() {
		//Enter recipient Phone Number into argument below
		smsSender.sendSms("");
	}
}
