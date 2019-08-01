//package grdian.backendgrdian.entities;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
//@Entity
//public class ChatRoom {
//	@Id
//	@GeneratedValue
//	private Long id;
//	
//	private String urgencyLevel;
//	private String location; //Google API field
//	private boolean chatOpening;
//	
//	@OneToMany (mappedBy = "users")
//	private Set<User> users = new HashSet<User>();
//	
//	@OneToMany (mappedBy = "chatRoom")
//	private Set<AppMessage> messages = new HashSet<AppMessage>();
//	
//	public ChatRoom() {
//		
//	}
//	
//	public ChatRoom(String urgencyLevel, String location, boolean chatOpening) {
//		this.urgencyLevel = urgencyLevel;
//		this.location = location;
//		this.chatOpening = chatOpening;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public String getUrgencyLevel() {
//		return urgencyLevel;
//	}
//
//	public String getLocation() {
//		return location;
//	}
//
//	public boolean isChatOpening() {
//		return chatOpening;
//	}
//
//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public Set<AppMessage> getMessages() {
//		return messages;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (chatOpening ? 1231 : 1237);
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((location == null) ? 0 : location.hashCode());
//		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
//		result = prime * result + ((urgencyLevel == null) ? 0 : urgencyLevel.hashCode());
//		result = prime * result + ((users == null) ? 0 : users.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ChatRoom other = (ChatRoom) obj;
//		if (chatOpening != other.chatOpening)
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (location == null) {
//			if (other.location != null)
//				return false;
//		} else if (!location.equals(other.location))
//			return false;
//		if (messages == null) {
//			if (other.messages != null)
//				return false;
//		} else if (!messages.equals(other.messages))
//			return false;
//		if (urgencyLevel == null) {
//			if (other.urgencyLevel != null)
//				return false;
//		} else if (!urgencyLevel.equals(other.urgencyLevel))
//			return false;
//		if (users == null) {
//			if (other.users != null)
//				return false;
//		} else if (!users.equals(other.users))
//			return false;
//		return true;
//	}
//	
//	
//}
