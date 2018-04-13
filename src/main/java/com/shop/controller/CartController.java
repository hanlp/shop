package com.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.pojo.Product;
import com.shop.service.ProductService;
import com.shop.util.Cart;
import com.shop.util.CartItem;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private ProductService productService;

	// 添加购物车的方法
	// 先从session里取数据
	private Cart getCart(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		// 如果从session中没有取出购物车信息
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;

	}

	// 添加购物车
	@RequestMapping("/addCart")
	public String addCart(int pid, int count, HttpSession session) {
		Product product = productService.findById(pid);
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);// 商品信息
		cartItem.setCount(count);// 购买商品数量

		Cart cart = getCart(session);// 从session中取数据
		cart.addCart(cartItem); // 把购物项设置到session中
		return "cart";// 返回购物车页面
	}

	// 删除购物车的购物项
	@RequestMapping("/delCart")
	public String delCart(int pid, HttpSession session) {
		Cart cart = getCart(session);
		cart.remove(pid);
		return "cart";
	}
}
