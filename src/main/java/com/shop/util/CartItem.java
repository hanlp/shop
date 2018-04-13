package com.shop.util;

import com.shop.pojo.Product;

public class CartItem {
	// 加入购物车的时候,获取到商品的id,根据商品的id查询出该商品
	private Product product;// 购买的商品
	private int count;// 购买的数量
	private Double subtotal = 0d;// 小计的价格

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Double getSubtotal() {

		// 小计价格计算, 商品的单价 * 购买数量
		subtotal = product.getShopPrice() * count;
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

}
