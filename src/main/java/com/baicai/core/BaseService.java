package com.baicai.core;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {
	@Autowired
	protected BaseDAO dao;
	
}
