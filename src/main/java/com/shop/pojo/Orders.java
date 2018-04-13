package com.shop.pojo;

import java.util.Date;
import java.util.List;

public class Orders {
	private Integer oid;// 订单id

	private Double total;// 价格总计

	private Date ordertime;// 订单时间

	private Integer state;// 订单状态 1.未付款,2.已付款,3.已经发货,4.已收货

	private Integer uid;// 用户id

	private String addr;// 收货地址

	private String phone;// 收货电话

	private String name;// 收货姓名
	// 订单项 (一对多的关系)
	private List<Orderitem> orderitem;
	// 和用户的关系(一对一), 保存数据 一个订单对应的一个用户
	private Users owner;

	public List<Orderitem> getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(List<Orderitem> orderitem) {
		this.orderitem = orderitem;
	}

	public Users getOwner() {
		return owner;
	}

	public void setOwner(Users owner) {
		this.owner = owner;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}