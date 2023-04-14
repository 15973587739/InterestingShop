package cn.interestingshop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BaseOrder implements Serializable {

	private Integer id;			//ID
	private String orderNo;		//订单号
	private Integer userId;		//用户id
	private String userAddress;	//收货地址
	private Date createTime;	//创建时间
	private Float amount;			//订单总计价格
	
	private String account;		//登录名称
	private List<OrderInfo> orderInfoList;//订单明细列表

	public List<OrderInfo> getOrderInfoList() {
		return orderInfoList;
	}

	public void setOrderInfoList(List<OrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public int hashCode() {		
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BaseOrder){
			if(((BaseOrder)obj).id==id){
				return true;
			}			
		}	
		return false;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
