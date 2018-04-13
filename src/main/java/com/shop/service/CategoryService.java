package com.shop.service;

import java.util.List;

import com.shop.pojo.Category;

public interface CategoryService {

	public List<Category> getListCategory();

	// 查询所有一级和二级分类
	public List<Category> queryListCategory();
}
