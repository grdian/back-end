package grdian.backendgrdian.webLayerTest;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import grdian.backendgrdian.controllers.UserController;
import grdian.backendgrdian.entities.AppMessage;
import grdian.backendgrdian.entities.User;
import grdian.backendgrdian.entities.postmodels.InboxRequestPostModel;
import grdian.backendgrdian.entities.postmodels.MessagePostModel;
import grdian.backendgrdian.repos.UserRepository;

@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)

public class UserWebLayerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserRepository userRepo;
	
	private AppMessage appMessage;
	private InboxRequestPostModel inboxRequestPostModel;
	private MessagePostModel messagePostModel;
	private User user;
	
	private ObjectMapper mapper = new ObjectMapper();

	
	
	@Before
	public void setup() {
		user = new User ("firstName", "lastName", "imgURL", "phoneNumber", "emailAddress",
				 "password");
	}
	
	@Test
	public void fetchAllUsers() throws Exception {
	when(userRepo.findAll()).thenReturn(Collections.singletonList(user));
	mockMvc.perform(get("/api/users")).andExpect(status().isOk())
	.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(content().json("[{}]"))
	.andExpect(content().json(mapper.writeValueAsString(Collections.singletonList(user)), true));
	}
	
	@Test
	public void fetchSingleAlbum()throws Exception {
		when(userRepo.findById(1L)).thenReturn(Optional.of(user));
		mockMvc.perform(get("/api/users/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(content().json("{}"))
				.andExpect(content().json(mapper.writeValueAsString(user), true));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
