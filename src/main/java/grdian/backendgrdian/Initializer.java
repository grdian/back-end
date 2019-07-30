package grdian.backendgrdian;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
	public void run(String... args) throws Exception
		{
		logStartOfInitializer();

		try
			{
			populateRepositories();
			}
		catch (Exception e)
			{
			errorsDuringInitialization = true;
			logErrorAndException(e);
			}
		logCompletionOfInitializer();
		}

	private void populateRepositories()
		{
		// Create and save placeholder entities here
		}

	private void logStartOfInitializer()
		{
		logger.info("Starting Initializer");
		startTimestamp = Instant.now();
		}

	private void logErrorAndException(Exception ex)
		{
		logger.error("There was a problem in the execution of the Initializer.", ex);
		}

	private void logCompletionOfInitializer()
		{
		logger.info("Initializer done " + checkForErrors() + ", it took " + timeToCompleteInitializer() + " ms to finish.");
		}

	private String checkForErrors()
		{
		if (errorsDuringInitialization)
			{
			return "WITH ERRORS";
			}
		return "";
		}

	private int timeToCompleteInitializer()
		{
		return Instant.now().compareTo(startTimestamp) / 1000000;
		}
}
