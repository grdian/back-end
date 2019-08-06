package grdian.backendgrdian;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import grdian.backendgrdian.entities.AppMessage;
import grdian.backendgrdian.entities.User;
import grdian.backendgrdian.repos.AppMessageRepository;
import grdian.backendgrdian.repos.UserRepository;

@Component
public class Initializer implements CommandLineRunner {

	@Autowired
	UserRepository userRepo;

	@Autowired
	AppMessageRepository messageRepo;

	// Logging and Feedback
	private Logger logger = LoggerFactory.getLogger(Initializer.class);
	private Instant startTimestamp;
	private boolean errorsDuringInitialization = false;

	@Override
	public void run(String... args) throws Exception {
		logStartOfInitializer();

		try {
			populateRepositories();
		} catch (Exception e) {
			errorsDuringInitialization = true;
			logErrorAndException(e);
		}
		logCompletionOfInitializer();
	}

	private void populateRepositories() {
		User user01 = new User("Lawrence", "Mboya", "https://picsum.photos/200", "1234567890", "mboya@gmail.com", "password");
		User user02 = new User("Nazik", "Elhaga", "https://picsum.photos/200", "0987654321", "elhag@gmail.com", "password");
		User user03 = new User("Tyler", "Conner", "https://picsum.photos/200", "0987654321", "conner@gmail.com", "password");

		userRepo.save(user01);
		userRepo.save(user02);
		userRepo.save(user03);
	}

	private void logStartOfInitializer() {
		logger.info("Starting Initializer");
		startTimestamp = Instant.now();
	}

	private void logErrorAndException(Exception ex) {
		logger.error("There was a problem in the execution of the Initializer.", ex);
	}

	private void logCompletionOfInitializer() {
		logger.info(
				"Initializer done " + checkForErrors() + ", it took " + timeToCompleteInitializer() + " ms to finish.");
	}

	private String checkForErrors() {
		if (errorsDuringInitialization) {
			return "WITH ERRORS";
		}
		return "";
	}

	private int timeToCompleteInitializer() {
		return Instant.now().compareTo(startTimestamp) / 1000000;
	}
}
