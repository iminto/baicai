package com.baicai.service.assets;

import org.springframework.stereotype.Service;

import com.baicai.core.BaseService;
import com.baicai.core.DaoUtil;
import com.baicai.domain.assets.Assets;
import com.baicai.domain.assets.Assets_cash;
import com.baicai.domain.assets.Assets_recharge;
import com.baicai.util.BeanMapUtil;
@Service
public class AssetsService extends BaseService{
	
	public Assets getAssets_info(Long user_id){
		Assets assets_info = new Assets();
		assets_info.setUser_id(user_id);
		assets_info = assets_info.find();
		return assets_info;
	}
	
	public int saveAssets(Assets assets){
		String SQL = " INSERT INTO {assets}(user_id,total_money,real_money,frost_money,have_interest,wait_interest,wait_total_money,yuebao_money,yuebao_income,yuebao_end_bearing_time,sign,is_warning) ";
		SQL+= " VALUES(:user_id,:total_money,:real_money,:frost_money,:have_interest,:wait_interest,:wait_total_money,:yuebao_money,:yuebao_income,:yuebao_end_bearing_time,:sign,:is_warning)";		
		int i=0;
		try {
			i = dao.getNamedParameterJdbcTemplate().update(DaoUtil.format(SQL), BeanMapUtil.bean2Map(assets));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	@SuppressWarnings("unchecked")
	public int SaveCash(Assets_cash cash){
		String sql = "insert into {assets_cash}(c_id,c_user_id,c_status,c_cardNum,c_bank,c_branch,c_money,c_realmoney,c_fee,c_verify_user,c_verify_time,c_verify_remark,c_addtime,c_addip,sign,is_warning)" ;
		sql+="values(:c_id,:c_user_id,:c_status,:c_cardNum,:c_bank,:c_branch,:c_money,:c_realmoney,:c_fee,:c_verify_user,:c_verify_time,:c_verify_remark,:c_addtime,:c_addip,:sign,:is_warning)";
		Integer i = 0;
		try{
			i = dao.update(DaoUtil.format(sql), BeanMapUtil.bean2Map(cash));
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	@SuppressWarnings("unchecked")
	public int SaveRecharge(Assets_recharge recharge){
		String sql="insert into {assets_recharge}(r_id,r_BillNo,r_user_id,r_status,r_money,r_realmoney,r_fee,r_type,r_recharge_type,r_return,r_verify_user,r_verify_time,r_verify_remark,r_addtime,r_addip,sign,is_warning)";
		sql+="values(:r_id,:r_BillNo,:r_user_id,:r_status,:r_money,:r_realmoney,:r_fee,:r_type,:r_recharge_type,:r_return,:r_verify_user,:r_verify_time,:r_verify_remark,:r_addtime,:r_addip,:sign,:is_warning)";
		Integer i = 0;
		try{
			i=dao.update(DaoUtil.format(sql),BeanMapUtil.bean2Map(recharge));
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
}
