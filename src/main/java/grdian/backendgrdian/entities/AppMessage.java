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
	private Set<User> receivers = new HashSet<User>();

	private String body;

	private boolean resolved = false;

	private String comments = "";

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

	public Set<User> getReceivers() {
		return receivers;
	}

	public String getBody() {
		return body;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	public void addComment(String comment) {
		this.comments += comment;
	}

	public String getComments() {
		return comments;
	}

	public void addReceiver(User receiver) {
		receivers.add(receiver);
		receiver.getReceivedMessages().add(this);
	}

}
