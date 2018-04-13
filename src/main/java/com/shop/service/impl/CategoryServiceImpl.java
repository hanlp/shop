package com.shop.service.impl;

import java.util.List;

import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryElement;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.CategoryMapper;
import com.shop.pojo.Category;
import com.shop.pojo.CategoryExample;
import com.shop.service.CategoryService;

/**
 * 
 * <p>
 * Title: CategoryServiceImpl
 * </p>
 * <p>
 * Description: 商品分类
 * </p>
 * <p>
 * Company: www.bochy.com
 * </p>
 * 
 * @author 涛哥
 * @date 2017年3月24日上午10:48:19
 * @version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * 
	 * <p>
	 * Title: getListCategory
	 * </p>
	 * <p>
	 * Description:查询一级分类
	 * </p>
	 * 
	 * @return
	 * @see com.shop.service.CategoryService#getListCategory()
	 */
	public List<Category> getListCategory() {
		CategoryExample example = new CategoryExample();

		return categoryMapper.selectByExample(example);
	}

	/**
	 * 
	 * <p>
	 * Title: queryListCategory
	 * </p>
	 * <p>
	 * Description:查询所有一级分类和二级分类
	 * </p>
	 * 
	 * @return
	 * @see com.shop.service.CategoryService#queryListCategory()
	 */
	public List<Category> queryListCategory() {

		return categoryMapper.queryCategory();
	}

}
