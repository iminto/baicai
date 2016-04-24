package com.baicai.service.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baicai.core.BaseDAO;
import com.baicai.core.DaoUtil;
import com.baicai.dao.user.UserDAO;
import com.baicai.domain.user.User;
/**
 * 
* @Description: 前台用户相关的服务
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年4月24日 下午8:57:12 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
@Service
@Transactional
public class UserService {
	@Autowired
	protected BaseDAO<User> dao;
	@Autowired
	private UserDAO userDAO;
	
	public boolean existUserByName(String userName){
		String sql="SELECT count(*) FROM {user} WHERE userName=?";
		int i=dao.queryForInt(DaoUtil.format(sql), new Object[]{userName});
		return i>0;
	}
	
	public boolean existUserByEmail(String email){
		String sql="SELECT count(*) FROM {user} WHERE email=?";
		int i=dao.queryForInt(DaoUtil.format(sql), new Object[]{email});
		return i>0;
	}
	
	public int saveUser(User u){
		int result=userDAO.save(u);
		return result;
	}
	
}
