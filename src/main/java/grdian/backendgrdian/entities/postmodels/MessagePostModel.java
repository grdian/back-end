package grdian.backendgrdian.entities.postmodels;

public class MessagePostModel {

	private Long senderId;

	private String body;

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
