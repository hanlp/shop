package com.shop.mapper;

import com.shop.pojo.Orderitem;
import com.shop.pojo.Orders;
import com.shop.pojo.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
	int countByExample(OrdersExample example);

	int deleteByExample(OrdersExample example);

	int deleteByPrimaryKey(Integer oid);

	// 保存订单信息
	int insert(Orders record);

	// 保存订单项信息
	int saveOrderItems(List<Orderitem> list);

	// 查询订单信息
	Orders findByOrderId(int oid);

	int insertSelective(Orders record);

	List<Orders> selectByExample(OrdersExample example);

	Orders selectByPrimaryKey(Integer oid);

	int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

	int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

	int updateByPrimaryKeySelective(Orders record);

	int updateByPrimaryKey(Orders record);
}