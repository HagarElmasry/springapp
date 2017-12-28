package main.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserProfile implements Serializable {
	
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	

    @Column(name="TYPE", length=15, unique=true, nullable=false)
    private String type = UserProfileType.USER.getUserProfileType();
    
	@Override
	public String toString() {
		return "UserProfile [id=" + id + "]";
	} 
	 
	
}
