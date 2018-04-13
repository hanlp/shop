package com.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.pojo.Category;
import com.shop.pojo.Product;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;

@Controller
public class PageController {
	@Autowired
	private CategoryService categoryService;

	// 商品信息service
	@Autowired
	private ProductService productService;

	@RequestMapping("/{page}")
	public String getUserList(@PathVariable String page, HttpSession session) {
		// 查询一级分类
		List<Category> categorylist = categoryService.getListCategory();
		// 把一级分类信息放入到session,因为在login或者注册等其他页面都有一级分类
		session.setAttribute("categoryList", categorylist);
		// 热门商品信息
		List<Product> hotList = productService.getHotProductList();
		session.setAttribute("hotList", hotList);
		// 最新商品信息
		List<Product> newList = productService.getNewProductList();
		session.setAttribute("newList", newList);
		return page;
	}

	@RequestMapping("/admin/{page}")
	public String showManager(@PathVariable String page) {
		return "/admin/" + page;
	}

}
