package grdian.backendgrdian.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grdian.backendgrdian.entities.AppMessage;
import grdian.backendgrdian.repos.AppMessageRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AppMessageController {

	@Autowired
	private AppMessageRepository messageRepo;

	public AppMessageController() {
	}

	@GetMapping("/messages")
	public Iterable<AppMessage> sendCharacters() {
		return messageRepo.findAll();
	}

}
