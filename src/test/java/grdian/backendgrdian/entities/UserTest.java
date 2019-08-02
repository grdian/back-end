package grdian.backendgrdian.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import grdian.backendgrdian.repos.UserRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserTest {

	@Autowired
	UserRepository userRepo;

	@Test
	public void canCreateUserInstance() {
		User underTest = new User();
		assertThat(underTest, is(notNullValue()));
	}

	@Test
	public void canFindAUserByEmail() {
		User underTest = new User("joe", "peschi", "img", "0987654321", "joe@gmail.com", "password");
		userRepo.save(underTest);
		String emailAddress = "joe@gmail.com";

		User loadedUser = userRepo.findByEmailAddress(emailAddress);
		assertThat(underTest, is(loadedUser));
	}

}
