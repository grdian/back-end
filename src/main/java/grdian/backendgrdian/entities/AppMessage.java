package grdian.backendgrdian.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AppMessage {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JsonIgnore
	private User sender;

	@ManyToMany
	@JsonIgnore
	private Set<User> recievers = new HashSet<User>();

	private String body;

	public AppMessage() {
	}

	public AppMessage(String body, User sender) {
		this.sender = sender;
		this.body = body;
	}

	public Long getId() {
		return id;
	}

	public User getSender() {
		return sender;
	}

	public Set<User> getRecievers() {
		return recievers;
	}

	public String getBody() {
		return body;
	}

	public void addReciever(User reciever) {
		recievers.add(reciever);
		reciever.getRecievedMessages().add(this);
	}

}
