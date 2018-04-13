package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.pojo.Product;
import com.shop.pojo.ProductExample;

public interface ProductMapper {
	// 查询商品分类信息
	List<Product> queryCateProductList(int cid);

	// 查询分类商品的分页展示
	// 1.分类id 2.起始记录数,3.显示多少条
	List<Product> queryProductPageList(@Param("cid") int cid, @Param("begin") int begin,
			@Param("pageSize") int pageSize);

	// 查询出总条数
	int queryProductPageCount(int cid);

	int countByExample(ProductExample example);

	int deleteByExample(ProductExample example);

	int deleteByPrimaryKey(Integer pid);

	int insert(Product record);

	int insertSelective(Product record);

	List<Product> selectByExample(ProductExample example);

	Product selectByPrimaryKey(Integer pid);

	int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

	int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

	int updateByPrimaryKeySelective(Product record);

	int updateByPrimaryKey(Product record);
}