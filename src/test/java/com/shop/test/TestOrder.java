package com.shop.test;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.OrientationRequested;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shop.mapper.OrdersMapper;
import com.shop.pojo.Orderitem;
import com.shop.pojo.Orders;
import com.shop.pojo.Product;

public class TestOrder {

	@Test
	public void testOrder() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		OrdersMapper ordersMapper = context.getBean(OrdersMapper.class);
		List<Orderitem> orderitemlist = new ArrayList<Orderitem>();
		Product product = new Product();
		product.setPid(12);
		Orders orders = new Orders();
		/*
		 * orders.setOid(10); for (int i = 0; i < 10; i++) { //
		 * 把购物车订单项中的信息保存到我们的订单项表里 orderitem Orderitem orderitem = new
		 * Orderitem(); orderitem.setCount(i);// 购买数量 orderitem.setPid(i);//
		 * 商品id orderitem.setProduct(product); orderitem.setOrders(orders);
		 * orderitemlist.add(orderitem);// 把订单项放到list集合中 }
		 * ordersMapper.saveOrderItems(orderitemlist);
		 */

	}
}
