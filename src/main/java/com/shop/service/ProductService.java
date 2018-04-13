package com.shop.service;

import java.util.List;

import com.shop.pojo.Product;
import com.shop.util.PageBean;

public interface ProductService {
	// 查询热门商品
	public List<Product> getHotProductList();

	// 查询最新商品
	public List<Product> getNewProductList();

	// 查询分类商品信息
	public List<Product> queryCateProductList(int cid);

	// 查询商品分页信息
	public PageBean<Product> queryPageProduct(int cid, int page);

	// 查询商品详情
	public Product findById(int pid);

	// 保存商品
	public void saveProduct(Product product);
}
