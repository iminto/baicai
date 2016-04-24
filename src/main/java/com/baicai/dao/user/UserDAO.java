package com.baicai.dao.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baicai.core.BaseDAO;
import com.baicai.domain.user.User;

@Component
public class UserDAO {
	@Autowired
	private BaseDAO<User> dao;
	
	public int save(User user){
		return dao.insert(user);
	}
}
