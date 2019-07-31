package grdian.backendgrdian.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grdian.backendgrdian.entities.User;
import grdian.backendgrdian.repos.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	public UserController() {
	}

	@GetMapping("/users")
	public Iterable<User> sendCharacters() {
		return userRepo.findAll();
	}
	
	@GetMapping("/users/{id}")
	@CrossOrigin
	public User findOneUser (@PathVariable Long id) {
		return userRepo.findById(id).get();
	}

}
