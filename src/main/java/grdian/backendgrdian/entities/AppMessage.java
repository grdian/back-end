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

	private int type;

	private String body;

//	private Long senderIdPostman;

	public AppMessage() {
	}

	public AppMessage(int type, String body, User sender) {
		super();
		this.sender = sender;
		this.type = type;
		this.body = body;
//		this.senderIdPostman = sender.getId();
	}

	public Long getId() {
		return id;
	}

//	public Long getSenderId() {
//		return senderIdPostman;
//	}

	public User getSender() {
		return sender;
	}

	public Set<User> getRecievers() {
		return recievers;
	}

	public int getType() {
		return type;
	}

	public String getBody() {
		return body;
	}

	public void addReciever(User reciever) {
		recievers.add(reciever);
		reciever.getRecievedMessages().add(this);
	}

}
