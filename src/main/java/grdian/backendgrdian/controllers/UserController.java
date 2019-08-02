package grdian.backendgrdian.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public User findOneUser(@PathVariable Long id) {
		return userRepo.findById(id).get();
	}

	@PostMapping("/users")
	public void createNewUser(@RequestBody String body, HttpServletResponse response)
			throws JSONException, IOException {
		JSONObject json = (JSONObject) JSONParser.parseJSON(body);
		System.out.println(body);
		String firstName = json.getString("firstName");
		String lastName = json.getString("lastName");
		String imgURL = json.getString("imgURL");
		String phoneNumber = json.getString("phoneNumber");
		String emailAddress = json.getString("emailAddress");
		String password = json.getString("password");

		userRepo.save(new User(firstName, lastName, imgURL, phoneNumber, emailAddress, password));
		response.sendRedirect("/api/users");
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable() Long id, HttpServletResponse response) throws IOException {
		userRepo.deleteById(id);
		response.sendRedirect("/api/users");
	}

	@GetMapping("/users/login/{id}")
	public boolean loginUser(@PathVariable Long id) {
		Optional<User> user = userRepo.findById(id);
		return user.isPresent();
	}

	@PostMapping("/users/login")
	@CrossOrigin
	public boolean loginUser(@RequestBody String body, HttpServletResponse response) throws JSONException, IOException {
		JSONObject json = (JSONObject) JSONParser.parseJSON(body);
		String emailAddress = json.getString("emailAddress").toLowerCase();

		User foundUser = userRepo.findByEmailAddress(emailAddress);
		if (foundUser != null) {
			return true;
		}
		return false;
	}
}
