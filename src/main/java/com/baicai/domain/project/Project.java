package com.baicai.domain.project;

import com.baicai.corewith.annotation.Key;
import com.baicai.corewith.data.Model;

/**
 * @Description: 借款标基本信息模型类
 * @author: waitfox@qq.com
 * @version: 0.01
 * @date: 2016/03/20 20:44:52
 */
public class Project extends Model {
	@Key
	private Integer id;//
	@Key
	private Long proid;// 逻辑主键
	private Long uid;// 借款人ID
	private String proname;// 借款标题
	private Integer proaccount;// 借款金额，单位分
	private Integer proaccountyes;// 已投标金额，单位分
	private Integer protimelimit;// 借款期限
	private Integer protimeuint;// 借款期限类型，1为天，2为月
	private Integer provalidtime;// 借款有效时间
	private Double proapr;// 借款利率
	private Integer prostyle;// 还款方式
	private Integer prostatus;// 标的状态 0初始1初审成功，投标中2初审失败3复审成功4复审失败5流标6撤销7正常结束
	private Integer proverifyuser;// 审标人
	private Integer proverifytime;// 初审时间
	private String proverifyremark;// 初审备注
	private Integer profullverifyuser;// 满标审核人
	private Integer profullverifytime;// 满标审核时间
	private String profullverifyremark;// 满标审核备注
	private Integer protype;// 1信用标2担保标3抵押标4秒标5定向标
	private String prodxb;// 定向标密码
	private String prodesc;// 标的详细描述
	private Integer proawardtype;// 奖励类型 1百分比2固定金额
	private Double proaward;// 奖励的具体数值
	private Integer prolowaccount;// 最低要求投资金额
	private Integer promostaccount;// 标的最高允许投标金额
	private Integer successtime;// 满标时间
	private Integer endtime;// 根据有效期算出的截止时间
	private Integer ordernum;// 投标次数
	private Integer autoratio;// 允许的自动投标比例，百分比
	private Integer repayment;// 还款金额
	private Integer repaymentyes;// 已还金额
	private String expansion;// 扩充字段
	private Integer addtime;//
	private String addip;//

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getProid() {
		return proid;
	}

	public void setProid(Long proid) {
		this.proid = proid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public Integer getProaccount() {
		return proaccount;
	}

	public void setProaccount(Integer proaccount) {
		this.proaccount = proaccount;
	}

	public Integer getProaccountyes() {
		return proaccountyes;
	}

	public void setProaccountyes(Integer proaccountyes) {
		this.proaccountyes = proaccountyes;
	}

	public Integer getProtimelimit() {
		return protimelimit;
	}

	public void setProtimelimit(Integer protimelimit) {
		this.protimelimit = protimelimit;
	}

	public Integer getProtimeuint() {
		return protimeuint;
	}

	public void setProtimeuint(Integer protimeuint) {
		this.protimeuint = protimeuint;
	}

	public Integer getProvalidtime() {
		return provalidtime;
	}

	public void setProvalidtime(Integer provalidtime) {
		this.provalidtime = provalidtime;
	}

	public Double getProapr() {
		return proapr;
	}

	public void setProapr(Double proapr) {
		this.proapr = proapr;
	}

	public Integer getProstyle() {
		return prostyle;
	}

	public void setProstyle(Integer prostyle) {
		this.prostyle = prostyle;
	}

	public Integer getProstatus() {
		return prostatus;
	}

	public void setProstatus(Integer prostatus) {
		this.prostatus = prostatus;
	}

	public Integer getProverifyuser() {
		return proverifyuser;
	}

	public void setProverifyuser(Integer proverifyuser) {
		this.proverifyuser = proverifyuser;
	}

	public Integer getProverifytime() {
		return proverifytime;
	}

	public void setProverifytime(Integer proverifytime) {
		this.proverifytime = proverifytime;
	}

	public String getProverifyremark() {
		return proverifyremark;
	}

	public void setProverifyremark(String proverifyremark) {
		this.proverifyremark = proverifyremark;
	}

	public Integer getProfullverifyuser() {
		return profullverifyuser;
	}

	public void setProfullverifyuser(Integer profullverifyuser) {
		this.profullverifyuser = profullverifyuser;
	}

	public Integer getProfullverifytime() {
		return profullverifytime;
	}

	public void setProfullverifytime(Integer profullverifytime) {
		this.profullverifytime = profullverifytime;
	}

	public String getProfullverifyremark() {
		return profullverifyremark;
	}

	public void setProfullverifyremark(String profullverifyremark) {
		this.profullverifyremark = profullverifyremark;
	}

	public Integer getProtype() {
		return protype;
	}

	public void setProtype(Integer protype) {
		this.protype = protype;
	}

	public String getProdxb() {
		return prodxb;
	}

	public void setProdxb(String prodxb) {
		this.prodxb = prodxb;
	}

	public String getProdesc() {
		return prodesc;
	}

	public void setProdesc(String prodesc) {
		this.prodesc = prodesc;
	}

	public Integer getProawardtype() {
		return proawardtype;
	}

	public void setProawardtype(Integer proawardtype) {
		this.proawardtype = proawardtype;
	}

	public Double getProaward() {
		return proaward;
	}

	public void setProaward(Double proaward) {
		this.proaward = proaward;
	}

	public Integer getProlowaccount() {
		return prolowaccount;
	}

	public void setProlowaccount(Integer prolowaccount) {
		this.prolowaccount = prolowaccount;
	}

	public Integer getPromostaccount() {
		return promostaccount;
	}

	public void setPromostaccount(Integer promostaccount) {
		this.promostaccount = promostaccount;
	}

	public Integer getSuccesstime() {
		return successtime;
	}

	public void setSuccesstime(Integer successtime) {
		this.successtime = successtime;
	}

	public Integer getEndtime() {
		return endtime;
	}

	public void setEndtime(Integer endtime) {
		this.endtime = endtime;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public Integer getAutoratio() {
		return autoratio;
	}

	public void setAutoratio(Integer autoratio) {
		this.autoratio = autoratio;
	}

	public Integer getRepayment() {
		return repayment;
	}

	public void setRepayment(Integer repayment) {
		this.repayment = repayment;
	}

	public Integer getRepaymentyes() {
		return repaymentyes;
	}

	public void setRepaymentyes(Integer repaymentyes) {
		this.repaymentyes = repaymentyes;
	}

	public String getExpansion() {
		return expansion;
	}

	public void setExpansion(String expansion) {
		this.expansion = expansion;
	}

	public Integer getAddtime() {
		return addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

}