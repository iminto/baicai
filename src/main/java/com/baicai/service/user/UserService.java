package com.baicai.service.user;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baicai.core.BaseDAO;
import com.baicai.dao.user.UserDAO;
import com.baicai.domain.user.User;
import com.baicai.util.CommonUtil;
import com.baicai.util.TimeHepler;
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
		String sql="SELECT count(*) FROM {user} WHERE username=?";
		int i=dao.queryForInt(sql, new Object[]{userName});
		return i>0;
	}
	
	public boolean existUserByEmail(String email){
		String sql="SELECT count(*) FROM {user} WHERE email=?";
		int i=dao.queryForInt(sql, new Object[]{email});
		return i>0;
	}
	
	public int saveUser(User u,HttpServletRequest request){
		String password=CommonUtil.encrypt(u.getUsername(), u.getLoginpass());
		u.setLoginpass(password);
		u.setPaypass(password);//默认支付密码和登陆密码一样
		u.setRegistertime(TimeHepler.getUnixTime());
		u.setRegisterip(request.getRemoteHost());
		int result=userDAO.save(u);
		return result;
	}
	
	public User login(User u){
		String sql="SELECT * FROM {user} WHERE username=? AND loginpass=? ";
		String password=CommonUtil.getPassword(u.getUsername(), u.getLoginpass());
		User user=dao.queryForBean(User.class, sql, new Object[]{u.getUsername(),password});
		if(user!=null){
			//登陆成功，记录时间,不记录在user表（因为这样无法查询历史，需要一张专门的表来记录）
		}
		return user;
	}
	
}
