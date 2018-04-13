package com.shop.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {

	// 1.购物列表
	// 2.价格总计
	private Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();

	private Double totalCount = 0d; // 商品价格总计

	public Double getTotalCount() {
		return totalCount;
	}

	// 添加购物车功能
	public void addCart(CartItem cartItem) {

		// 判断购物车中是否有该商品
		// 如果有的情况 //map key值就是商品的id
		CartItem item = map.get(cartItem.getProduct().getPid());
		// 说明购物车里已经存放的该商品
		if (item != null) {
			item.setCount(item.getCount() + cartItem.getCount());
		} else {// 如果购物车里没有该商品
			map.put(cartItem.getProduct().getPid(), cartItem);// 往购物车中新增一个购物项
		}
		System.out.println(cartItem.getSubtotal() + "======");
		totalCount += cartItem.getSubtotal();// 计算出商品总价格
		System.out.println(totalCount + "=====22222222222222");
	}

	// 展示购物车
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	// 删除购物项
	public void remove(int pid) {
		map.remove(pid);
	}

	// 清空购物车
	public void cleanCart() {
		map.clear();
	}

}
