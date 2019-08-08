package grdian.backendgrdian.webLayerTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
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

import grdian.backendgrdian.controllers.AppMessageController;
import grdian.backendgrdian.controllers.MailMan;
import grdian.backendgrdian.entities.AppMessage;
import grdian.backendgrdian.entities.User;
import grdian.backendgrdian.repos.AppMessageRepository;
import grdian.backendgrdian.repos.UserRepository;


@WebMvcTest(AppMessageController.class)
@RunWith(SpringRunner.class)

public class AppMessageWebLayerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	AppMessageRepository appMessageRepo;
	
	@MockBean
	UserRepository userRepo;
	
	@MockBean
	MailMan mailMan;

	private AppMessage appMessage;
	private User user;
	
	private ObjectMapper mapper = new ObjectMapper();

	
	@Before
	public void setup() {
	user = new User ("firstName", "lastName", "imgURL", "phoneNumber", "emailAddress",
				 "password");	
	appMessage = new AppMessage ("body", user);
	
	}
	
	@Test
	public void placeHolder() {
		assertTrue(true);
	}
	
	@Test
	public void fetchAllMessages() throws Exception {
		when(appMessageRepo.findAll()).thenReturn(Collections.singletonList(appMessage));
		mockMvc.perform(get("/api/messages")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(content().json("[{}]"))
				.andExpect(content().json(mapper.writeValueAsString(Collections.singletonList(appMessage)), true));}
	
	@Test
	public void fetchSingleMessage()throws Exception {
		when(appMessageRepo.findById(1L)).thenReturn(Optional.of(appMessage));
		mockMvc.perform(get("/api/messages/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(content().json("{}"))
				.andExpect(content().json(mapper.writeValueAsString(appMessage), true));
	}
	
}
