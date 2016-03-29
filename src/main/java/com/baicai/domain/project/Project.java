package com.baicai.domain.project;

import com.baicai.core.Model;

/**
 * @Description: 借款标基本信息模型类
 * @author: waitfox@qq.com
 * @version: 0.01
 * @date: 2016/03/20 20:44:52
 */
public class Project extends Model{
	private Integer id;//
	private long proId;// 逻辑主键
	private long uid;//借款人
	private String proName;// 借款标题
	private Integer proAccount;// 借款金额，单位分
	private Integer proAccountYes;// 已投标金额，单位分
	private Integer proTimeLimit;// 借款期限
	private Integer proTimeUint;// 借款期限类型，1为天，2为月
	private Integer proValidTime;// 借款有效时间
	private Double proApr;// 借款利率
	private Integer proStyle;// 还款方式
	private Integer proStatus;// 标的状态 0初始1初审成功，投标中2初审失败3复审成功4复审失败5流标6撤销7正常结束
	private Integer proVerifyUser;// 审标人
	private Integer proVerifyTime;// 初审时间
	private String proVerifyRemark;// 初审备注
	private Integer proFullVerifyUser;// 满标审核人
	private Integer proFullVerifyTime;// 满标审核时间
	private String proFullVerifyRemark;// 满标审核备注
	private Integer proType;// 1信用标2担保标3抵押标4秒标5定向标
	private String proDxb;// 定向标密码
	private String proDesc;// 标的详细描述
	private Integer proAwardType;// 奖励类型 1百分比2固定金额
	private Double proAward;// 奖励的具体数值
	private Integer proLowAcount;// 最低要求投资金额
	private Integer proMostAccount;// 标的最高允许投标金额
	private Integer successTime;// 满标时间
	private Integer endTime;// 根据有效期算出的截止时间
	private Integer orderNum;// 投标次数
	private Integer autoRatio;// 允许的自动投标比例，百分比
	private Integer repayment;// 还款金额
	private Integer repaymentYes;// 已还金额
	private String expansion;// 扩充字段
	private Integer addtime;//
	private String addip;//

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getProId() {
		return proId;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public void setProId(long proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Integer getProAccount() {
		return proAccount;
	}

	public void setProAccount(Integer proAccount) {
		this.proAccount = proAccount;
	}

	public Integer getProAccountYes() {
		return proAccountYes;
	}

	public void setProAccountYes(Integer proAccountYes) {
		this.proAccountYes = proAccountYes;
	}

	public Integer getProTimeLimit() {
		return proTimeLimit;
	}

	public void setProTimeLimit(Integer proTimeLimit) {
		this.proTimeLimit = proTimeLimit;
	}

	public Integer getProTimeUint() {
		return proTimeUint;
	}

	public void setProTimeUint(Integer proTimeUint) {
		this.proTimeUint = proTimeUint;
	}

	public Integer getProValidTime() {
		return proValidTime;
	}

	public void setProValidTime(Integer proValidTime) {
		this.proValidTime = proValidTime;
	}

	public Double getProApr() {
		return proApr;
	}

	public void setProApr(Double proApr) {
		this.proApr = proApr;
	}

	public Integer getProStyle() {
		return proStyle;
	}

	public void setProStyle(Integer proStyle) {
		this.proStyle = proStyle;
	}

	public Integer getProStatus() {
		return proStatus;
	}

	public void setProStatus(Integer proStatus) {
		this.proStatus = proStatus;
	}

	public Integer getProVerifyUser() {
		return proVerifyUser;
	}

	public void setProVerifyUser(Integer proVerifyUser) {
		this.proVerifyUser = proVerifyUser;
	}

	public Integer getProVerifyTime() {
		return proVerifyTime;
	}

	public void setProVerifyTime(Integer proVerifyTime) {
		this.proVerifyTime = proVerifyTime;
	}

	public String getProVerifyRemark() {
		return proVerifyRemark;
	}

	public void setProVerifyRemark(String proVerifyRemark) {
		this.proVerifyRemark = proVerifyRemark;
	}

	public Integer getProFullVerifyUser() {
		return proFullVerifyUser;
	}

	public void setProFullVerifyUser(Integer proFullVerifyUser) {
		this.proFullVerifyUser = proFullVerifyUser;
	}

	public Integer getProFullVerifyTime() {
		return proFullVerifyTime;
	}

	public void setProFullVerifyTime(Integer proFullVerifyTime) {
		this.proFullVerifyTime = proFullVerifyTime;
	}

	public String getProFullVerifyRemark() {
		return proFullVerifyRemark;
	}

	public void setProFullVerifyRemark(String proFullVerifyRemark) {
		this.proFullVerifyRemark = proFullVerifyRemark;
	}

	public Integer getProType() {
		return proType;
	}

	public void setProType(Integer proType) {
		this.proType = proType;
	}

	public String getProDxb() {
		return proDxb;
	}

	public void setProDxb(String proDxb) {
		this.proDxb = proDxb;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public Integer getProAwardType() {
		return proAwardType;
	}

	public void setProAwardType(Integer proAwardType) {
		this.proAwardType = proAwardType;
	}

	public Double getProAward() {
		return proAward;
	}

	public void setProAward(Double proAward) {
		this.proAward = proAward;
	}

	public Integer getProLowAcount() {
		return proLowAcount;
	}

	public void setProLowAcount(Integer proLowAcount) {
		this.proLowAcount = proLowAcount;
	}

	public Integer getProMostAccount() {
		return proMostAccount;
	}

	public void setProMostAccount(Integer proMostAccount) {
		this.proMostAccount = proMostAccount;
	}

	public Integer getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Integer successTime) {
		this.successTime = successTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getAutoRatio() {
		return autoRatio;
	}

	public void setAutoRatio(Integer autoRatio) {
		this.autoRatio = autoRatio;
	}

	public Integer getRepayment() {
		return repayment;
	}

	public void setRepayment(Integer repayment) {
		this.repayment = repayment;
	}

	public Integer getRepaymentYes() {
		return repaymentYes;
	}

	public void setRepaymentYes(Integer repaymentYes) {
		this.repaymentYes = repaymentYes;
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