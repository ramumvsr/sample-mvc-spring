package com.userdataserviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernatefiles.ResponseData;
import com.hibernatefiles.User;
import com.hibernatefiles.UserData;
import com.userdataDAOImpl.UserDataDAOImpl;
import com.userdataservice.UserDataService;

/**
 * 
 * This ProductDataService class can provide services for all ProductData
 * operations
 *
 */
@Service
public class UserDataServiceImpl implements UserDataService {

	@Autowired
	private UserDataDAOImpl userDataDAOImpl;
	

	
	public ResponseData addUserData(User user) {
		
	    UserData userData = new UserData();

		ResponseData responseData = new ResponseData();

		try {
			userData.setUserId(user.getuId());

			userData.setUserName(user.getuName());
			userDataDAOImpl.saveUserData(userData);
		} catch (Exception error) {
			responseData.setStatus("FAILURE");
			responseData.setMessage("Error in adding ProductData Checklist");

			
		}
		return responseData;
	}

	

}
