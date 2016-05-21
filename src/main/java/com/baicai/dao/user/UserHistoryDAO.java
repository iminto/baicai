package com.baicai.dao.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baicai.core.BaseDAO;
import com.baicai.domain.user.UserHistory;

@Component
public class UserHistoryDAO {
	@Autowired
	private BaseDAO<UserHistory> dao;
	
	public int save(UserHistory userHistory){
		userHistory.setAddtime(new Date());
		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
	    HttpServletRequest request = sra.getRequest(); 
	    userHistory.setAddip(request.getRemoteAddr());
		return dao.insert(userHistory);
	}
}
