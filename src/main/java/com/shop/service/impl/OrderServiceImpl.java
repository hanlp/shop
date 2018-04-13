package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.OrdersMapper;
import com.shop.pojo.Orders;
import com.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersMapper ordersMapper;

	/**
	 * 
	 * <p>
	 * Title: saveOrder
	 * </p>
	 * <p>
	 * Description: 保存订单信息
	 * </p>
	 * 
	 * @param order
	 */
	public void saveOrder(Orders order) {
		ordersMapper.insert(order);

		// 体现出了事务, 订单表数据没有问题的话,订单项表插入的时候出现了异常, 两张表的数据同时回滚
		// 保存订单项数据
		ordersMapper.saveOrderItems(order.getOrderitem());
	}

	/**
	 * 
	 * <p>
	 * Title: findByOrderId
	 * </p>
	 * <p>
	 * Description:根据订单号查询订单信息
	 * </p>
	 * 
	 * @param oid
	 * @return
	 * @see com.shop.service.OrderService#findByOrderId(int)
	 */

	public Orders findByOrderId(int oid) {

		return ordersMapper.findByOrderId(oid);
	}

	// 修改用户
	public void updateOrder(Orders orders) {
		ordersMapper.updateByPrimaryKey(orders);

	}
}
