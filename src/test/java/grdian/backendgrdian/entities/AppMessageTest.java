package grdian.backendgrdian.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import grdian.backendgrdian.repos.AppMessageRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AppMessageTest {

	@Autowired
	AppMessageRepository appMessageRepo;

	@Test
	public void canCreateAppMessageInstance()
		{
		AppMessage underTest = new AppMessage();
		assertThat(underTest, is(notNullValue()));
		}

}
