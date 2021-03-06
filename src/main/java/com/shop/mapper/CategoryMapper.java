package com.shop.mapper;

import com.shop.pojo.Category;
import com.shop.pojo.CategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
	// 查询二级分类列表
	public List<Category> queryCategory();

	int countByExample(CategoryExample example);

	int deleteByExample(CategoryExample example);

	int deleteByPrimaryKey(Integer cid);

	int insert(Category record);

	int insertSelective(Category record);

	List<Category> selectByExample(CategoryExample example);

	Category selectByPrimaryKey(Integer cid);

	int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

	int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

	int updateByPrimaryKeySelective(Category record);

	int updateByPrimaryKey(Category record);
}