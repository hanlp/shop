package com.shop.util;

public class ShopMsgResult {
	// 1.三个参数,1.状态,200成功 500失败 2.提示信息title, 3.封装提示信息.
	private int states;
	private String title;
	private String msg;

	public int getStates() {
		return states;
	}

	public void setStates(int states) {
		this.states = states;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
