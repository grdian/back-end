package grdian.backendgrdian.controllers;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grdian.backendgrdian.entities.AppMessage;
import grdian.backendgrdian.entities.User;
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
	public void sendNewMessage(@RequestBody String jsonBody, HttpServletResponse response)
			throws JSONException, IOException {
		JSONObject json = (JSONObject) JSONParser.parseJSON(jsonBody);

		Long senderId = Long.parseLong(json.getString("senderId"));
		String body = json.getString("body");
		User sender = userRepo.findById(senderId).get();

		AppMessage message = new AppMessage(body, sender);
		messageRepo.save(message);
		mailMan.sendMessageToUsers(message);
	}

	@GetMapping("/messages/inbox/{id}")
	public Set<AppMessage> sendInboxToUser(@PathVariable() Long id) {
		User receiver = userRepo.findById(id).get();
		Set<AppMessage> inbox = mailMan.getInboxForUser(receiver);
		return inbox;
	}

	@PatchMapping("messages/{id}/resolve")
	public void resolveAlert(@PathVariable() Long id, HttpServletResponse response) throws Exception {
		Optional potentialMessage = messageRepo.findById(id);
		if (potentialMessage.isPresent()) {
			AppMessage message = (AppMessage) potentialMessage.get();
			message.setResolved(true);
			messageRepo.save(message);
		}
		response.sendRedirect("/api/messages/" + id);
	}

	@PatchMapping("messages/{id}/comment")
	public void addCommentToMessage(@PathVariable() Long id, @RequestBody String commentUpdate,
			HttpServletResponse response) throws Exception {

		Optional<AppMessage> potentialMessage = messageRepo.findById(id);
		AppMessage message;

		if (potentialMessage.isPresent()) {
			message = (AppMessage) potentialMessage.get();
		} else {
			throw new Exception("No such message to comment on.");
		}

		JSONObject json = (JSONObject) JSONParser.parseJSON(commentUpdate);
		String comment = json.getString("comment");

		message.addComment(comment);
		messageRepo.save(message);
		response.sendRedirect("/api/messages/" + id);
	}

}
