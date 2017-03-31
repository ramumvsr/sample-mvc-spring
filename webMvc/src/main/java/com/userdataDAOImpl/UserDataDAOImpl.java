package com.userdataDAOImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDataDAO;
import com.hibernatefiles.UserData;

/**
 * 
 * It is DAO(Data Access Object) class which implemented all ProductData Entity
 * related operations(create,read,update,delete)
 *
 */
@Repository
public class UserDataDAOImpl implements UserDataDAO {
	@Autowired
	private SessionFactory sessionManager;
	
	@Transactional
	public void saveUserData(UserData userData) {
		
		try {

			Session session = sessionManager.getCurrentSession();

			session.save(userData);

			
		} catch (RuntimeException e) {
			System.out.println(e);
			}

	}


	

}
