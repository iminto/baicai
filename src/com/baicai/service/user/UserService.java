/**
 * @Description: 用户相关service
 * @author 猪肉有毒 waitfox@qq.com  
 * @date 2015年6月4日 下午3:16:08 
 * @version V1.0  
 * 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
package com.baicai.service.user;

import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import com.alibaba.fastjson.JSON;
import com.baicai.core.BaseService;
import com.baicai.core.BaseTool;
import com.baicai.core.DaoUtil;
import com.baicai.domain.user.User;
import com.baicai.util.BeanMapUtil;


@Service
public class UserService extends BaseService {

	@SuppressWarnings("unchecked")
	public int saveUser(User user) {
		String SQL = " INSERT INTO {user}(user_id,user_name,login_pass,pay_pass,user_email,user_phone,home_tel,user_qq,user_pic,real_name,card_num,user_sex,user_age,user_edu,birth_place,live_place,user_address,p_user_id,user_type,is_email_check,is_phone_check,is_realname_check,vip_stop_time,register_time,login_time,is_hook) ";
		SQL += " VALUES(:user_id,:user_name,:login_pass,:pay_pass,:user_email,:user_phone,:home_tel,:user_qq,:user_pic,:real_name,:card_num,:user_sex,:user_age,:user_edu,:birth_place,:live_place,:user_address,:p_user_id,:user_type,:is_email_check,:is_phone_check,:is_realname_check,:vip_stop_time,:register_time,:login_time,:is_hook)";
		int i = 0;
		try {
			i = dao.getNamedParameterJdbcTemplate().update(DaoUtil.format(SQL),
					BeanMapUtil.bean2Map(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ShardedJedis jedis = dao.getRedisClient();
		if (jedis != null) {
			
			try {
				jedis.set(String.valueOf(user.getUser_id()),
						JSON.toJSONString(user));
			} catch (Exception e) {
				dao.getShardedJedisPool().returnResource(jedis);
			} finally {
				dao.getShardedJedisPool().returnResource(jedis);
			}
		}
		return i;

	}

	@SuppressWarnings("unchecked")
	public User selectUserByUsername(String username, String passwd) {
		Long userId=dao.queryForLong(DaoUtil.format("SELECT user_id FROM {user} WHERE user_name=?"), new Object[] { username});
		if(userId==null){
			return null;
		}
		String password_ = BaseTool.get_pass(userId, passwd);
		String sql = "SELECT * FROM {user} WHERE user_id=? AND login_pass= ?";
		return (User) dao.queryForBean(User.class, DaoUtil.format(sql),
				new Object[] { userId, password_ });
	}
	
	
	public User selectUserByUid(String userId) {
		String sql = "SELECT * FROM {user} WHERE user_id=? ";
		return (User) dao.queryForBean(User.class, DaoUtil.format(sql),
				new Object[] { userId});
	}

}
