package grdian.backendgrdian.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import grdian.backendgrdian.repos.AppMessageRepository;
import grdian.backendgrdian.repos.UserRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class JPAWiringTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	AppMessageRepository messageRepo;

	@Autowired
	UserRepository userRepo;

	@Test
	public void canCreateAndLoadUser() {
		User testSender = new User("Lawrence", "Mboya", "imgUrl", "1234567890", "someone@gmail.com", "password");
		userRepo.save(testSender);
		User loadedUser = userRepo.findById(testSender.getId()).get();
		assertThat(loadedUser, is(notNullValue()));
		assertEquals(testSender, loadedUser);
	}

	@Test
	public void userShouldHaveMessageAfterLoad() {
		User testSender = new User("Lawrence", "Mboya", "imgUrl", "1234567890", "someone@gmail.com", "password");
		userRepo.save(testSender);

		AppMessage messageToSend = new AppMessage(0, "Message body", testSender);
		messageRepo.save(messageToSend);

		entityManager.flush();
		entityManager.clear();

		User loadedUser = userRepo.findById(testSender.getId()).get();
		AppMessage loadedMessage = messageRepo.findById(messageToSend.getId()).get();
		assertThat(loadedUser.getSentMessages(), containsInAnyOrder(loadedMessage));
	}

	@Test
	public void messageShouldHaveRecievers() {
		User testSender = new User("Lawrence", "Mboya", "imgUrl", "1234567890", "someone@gmail.com", "password");
		userRepo.save(testSender);
		User testReciever = new User("Nazik", "Elhaga", "imgUrl", "0987654321", "someoneelse@gmail.com",
				"anotherpassword");
		userRepo.save(testReciever);

		AppMessage messageToSend = new AppMessage(0, "Message body", testSender);
		messageToSend.addReciever(testReciever);
		messageRepo.save(messageToSend);

		entityManager.flush();
		entityManager.clear();

//		User loadedUser = userRepo.findById(testSender.getId()).get();
		AppMessage loadedMessage = messageRepo.findById(messageToSend.getId()).get();
		User loadedReciever = userRepo.findById(testReciever.getId()).get();
		assertThat(loadedMessage.getRecievers(), containsInAnyOrder(loadedReciever));

	}

}
