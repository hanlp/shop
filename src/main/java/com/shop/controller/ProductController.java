package com.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.shop.pojo.Product;
import com.shop.service.ProductService;
import com.shop.util.UUIDUtils;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 
	 * <p>
	 * Title: findByPid
	 * </p>
	 * <p>
	 * Description: 查询商品详情
	 * </p>
	 * 
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping("/findById")
	public String findByPid(int pid, Model model) {
		Product product = productService.findById(pid);
		model.addAttribute("product", product);
		return "detail";
	}

	// 商品管理页面
	@RequestMapping("/list")
	public String queryAll() {

		return "/admin/product/list";
	}

	// 跳转添加商品页面
	@RequestMapping("/addItem")
	public String addItem() {
		return "/admin/product/add";
	}

	/* 添加商品 */
	@RequestMapping("/saveProduct")
	public String saveProduct(HttpServletRequest request, Product product, MultipartFile picFile)
			throws IllegalStateException, IOException {

		/***************** 处理上传图片 *****************/
		if (picFile != null) {
			// 上传文件名称
			String originalName = picFile.getOriginalFilename();
			// 为了保证名称不可以重复
			String newFileName = "";
			// 文件名不为空
			if (originalName != null && !"".equals(originalName)) {
				// 文件保存路径 D:/devloper/tomcat/webapps/shop/products/
				// 获取到当当前项目的路径
				String dirPath = request.getSession().getServletContext().getRealPath("/products");

				System.out.println("商品目录==========：" + dirPath);
				// 新的文件名称 例如: 123.jpg UUIDUtils.getUUID()_.jag
				newFileName = UUIDUtils.getUUID() + "_" + originalName.substring(originalName.lastIndexOf("."));
				// 创建保存图片的File
				String realPath = dirPath + "/" + product.getCsid() + "/" + newFileName;
				File file = new File(realPath);
				// 如果文件或者目录不存在，则自动创建
				if (!file.exists()) {
					file.mkdirs();
				}
				// 保存图片
				picFile.transferTo(file);
			}
			System.out.println(product);
			// 为product对象设置图片
			product.setImage("products/" + product.getCsid() + "/" + newFileName);// 数据库里保存的路径
			// 设置商品库存
			product.setNum(500);// 图片上传
			// 商品创建时间
			product.setPdate(new Date());
			/**************** 保存商品信息 *******************/
			productService.saveProduct(product);
			// 重定向
			return "redirect:/adminFindAll?page=1";
		}
		// 返回商品列表页面
		return "admin/product/list";
	}
}
