package com.hibernatefiles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MvcUserData")
public class UserData {
	
	@Id
	@SequenceGenerator(initialValue=1,allocationSize=10,name="SUB_SEQ")
	@GeneratedValue(generator="SUB_SEQ")
	@Column(name="USER_ID")
	private int userId;	
	@Column(name="USER_NAME")
	private String userName;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
