package grdian.backendgrdian.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grdian.backendgrdian.entities.AppMessage;
import grdian.backendgrdian.entities.User;
import grdian.backendgrdian.entities.postmodels.InboxRequestPostModel;
import grdian.backendgrdian.entities.postmodels.MessagePostModel;
import grdian.backendgrdian.repos.AppMessageRepository;
import grdian.backendgrdian.repos.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AppMessageController {

	@Autowired
	private AppMessageRepository messageRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private MailMan mailMan;

	public AppMessageController() {
	}

	@GetMapping("/messages")
	public Iterable<AppMessage> sendCharacters() {
		return messageRepo.findAll();
	}

	@GetMapping("/messages/{id}")
	@CrossOrigin
	public AppMessage findOneMessage(@PathVariable Long id) {
		return messageRepo.findById(id).get();
	}

	@PostMapping("/messages")
	public void sendNewMessage(@RequestBody MessagePostModel messageModel) {
		User sender = userRepo.findById(messageModel.getSenderId()).get();
		String body = messageModel.getBody();
		AppMessage message = new AppMessage(body, sender);
		messageRepo.save(message);
		message = mailMan.sendMessageToUsers(message);
	}

	@PostMapping("/messages/inbox")
	public Set<AppMessage> sendInboxToUser(@RequestBody InboxRequestPostModel inboxRequestPostModel) {
		Long recieverId = inboxRequestPostModel.getRecieverId();
		User reciever = userRepo.findById(recieverId).get();
		Set<AppMessage> inbox = mailMan.getInboxForUser(reciever);
		return inbox;
	}

}
