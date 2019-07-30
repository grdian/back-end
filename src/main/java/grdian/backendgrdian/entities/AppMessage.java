package grdian.backendgrdian.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AppMessage {

	@Id
	@GeneratedValue
	private Long id;

	public AppMessage()
		{
		}

}
