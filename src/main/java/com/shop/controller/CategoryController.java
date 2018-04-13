package com.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.pojo.Category;
import com.shop.pojo.Product;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;
import com.shop.util.PageBean;

/**
 * 
 * <p>
 * Title: CategoryController
 * </p>
 * <p>
 * Description:分类信息
 * </p>
 * <p>
 * Company: www.bochy.com
 * </p>
 * 
 * @author 涛哥
 * @date 2017年3月24日下午2:28:57
 * @version 1.0
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	/**
	 * 
	 * <p>
	 * Title: queryCategory
	 * </p>
	 * <p>
	 * Description: 查询二级分类
	 * </p>
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String queryCategory(Model model) {
		List<Category> list = categoryService.queryListCategory();
		model.addAttribute("cateList", list);
		return "list";
	}

	/**
	 * 
	 * <p>
	 * Title: queryCateProductList
	 * </p>
	 * <p>
	 * Description:根据商品一级分类查询商品信息
	 * </p>
	 * 
	 * @param cid
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryByCid")
	public String queryCateProductList(int cid, Model model) {
		// 左边导航分类
		List<Category> list = categoryService.queryListCategory();
		model.addAttribute("cateList", list);
		// 商品分类信息
		List<Product> products = productService.queryCateProductList(cid);
		model.addAttribute("proList", products);
		return "list";

	}

	/**
	 * 
	 * <p>
	 * Title: queryCatePageProductList
	 * </p>
	 * <p>
	 * Description:查询分类商品分页信息
	 * </p>
	 * 
	 * @param cid
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryByPageCid") // RequestParam(value="page",dufaultValue="1")
	public String queryCatePageProductList(int cid, int page, Model model, HttpSession session) {
		// 左边导航分类
		List<Category> list = categoryService.queryListCategory();
		session.setAttribute("cateList", list);// 数据共享
		// model.addAttribute("cateList", list);
		// 商品分类信息
		// List<Product> products = productService.queryCateProductList(cid);
		model.addAttribute("cid", cid);// 设置分类id,用于分页
		model.addAttribute("pages", page);
		PageBean<Product> products = productService.queryPageProduct(cid, page);
		model.addAttribute("proList", products);
		return "list";

	}

}
