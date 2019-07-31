package grdian.backendgrdian.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String firstName;
	private String lastName;
	private String imgURL;
	private String phoneNumber;
	private String emailAddress;
	private String password;

	@OneToMany(mappedBy = "sender")
	@JsonManagedReference
	private Set<AppMessage> sentMessages = new HashSet<AppMessage>();

	@ManyToMany(mappedBy = "recievers")
	private Set<AppMessage> recievedMessages = new HashSet<AppMessage>();

	public User() {
	}

	public User(String firstName, String lastName, String imgURL, String phoneNumber, String emailAddress,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.imgURL = imgURL;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public String getImgURL() {
		return imgURL;
	}

	public Set<AppMessage> getSentMessages() {
		return sentMessages;
	}

	public Set<AppMessage> getRecievedMessages() {
		return recievedMessages;
	}

//	public void addRecievedMessage(AppMessage message) {
//		recievedMessages.add(message);
//		message.getRecievers().add(this);
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

}
