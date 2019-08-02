package grdian.backendgrdian.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import grdian.backendgrdian.entities.AppMessage;
import grdian.backendgrdian.entities.User;
import grdian.backendgrdian.repos.AppMessageRepository;
import grdian.backendgrdian.repos.UserRepository;

@Service
public class MailMan implements CommandLineRunner {

	@Autowired
	UserRepository userRepo;

	@Autowired
	AppMessageRepository messageRepo;

	@Override
	public void run(String... args) throws Exception {
	}

	public void sendMessageToUsers(AppMessage message) {
		
		Iterable<User> users = userRepo.findAll();
		for (User user : users) {
			if (user != message.getSender()) {
				user.addRecievedMessage(message);
				userRepo.save(user);
			}
		}
	}

	public Set<AppMessage> getInboxForUser(User reciever) {
		Iterable<AppMessage> allMessages = messageRepo.findAll();
		Set<AppMessage> inbox = new HashSet<AppMessage>();
		for (AppMessage message : allMessages) {
			Set<User> recievers = message.getRecievers();
			if (recievers.contains(reciever)) {
				inbox.add(message);
			}
		}
		return inbox;
	}

	public void sendSMSToUsers() {

	}

}
