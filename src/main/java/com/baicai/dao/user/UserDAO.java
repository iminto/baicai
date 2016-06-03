package com.baicai.dao.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baicai.core.database.BaseDAO;
import com.baicai.domain.user.User;

@Component
public class UserDAO {
	@Autowired
	private BaseDAO<User> dao;
	
	public long save(User user){
		long id=dao.insert(user,true);
		return id;
	}
}
