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
public class MailMan {

	@Autowired
	UserRepository userRepo;

	@Autowired
	AppMessageRepository messageRepo;

	public void sendMessageToUsers(AppMessage message) {
		Iterable<User> users = userRepo.findAll();
		for (User user : users) {
			if (user != message.getSender()) {
				user.addReceivedMessage(message);
				userRepo.save(user);
			}
		}
	}

	public Set<AppMessage> getInboxForUser(User receiver) {
		Iterable<AppMessage> allMessages = messageRepo.findAll();
		Set<AppMessage> inbox = new HashSet<AppMessage>();
		for (AppMessage message : allMessages) {
			Set<User> receivers = message.getReceivers();
			if (receivers.contains(receiver)) {
				inbox.add(message);
			}
		}
		return inbox;
	}

	public void sendSMSToUsers() {

	}

}
