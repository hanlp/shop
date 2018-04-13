package com.shop.service;

import com.shop.pojo.Orders;

public interface OrderService {
	public void saveOrder(Orders order);

	public Orders findByOrderId(int oid);

	public void updateOrder(Orders orders);
}
