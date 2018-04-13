package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shop.mapper.ProductMapper;
import com.shop.pojo.Product;
import com.shop.pojo.ProductExample;
import com.shop.pojo.ProductExample.Criteria;
import com.shop.service.ProductService;
import com.shop.util.PageBean;

/**
 * 
 * <p>
 * Title: ProductServiceImpl
 * </p>
 * <p>
 * Description: 商品信息service
 * </p>
 * <p>
 * Company: www.bochy.com
 * </p>
 * 
 * @author 涛哥
 * @date 2017年3月24日上午11:23:53
 * @version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	/**
	 * 
	 * <p>
	 * Title: getProductList
	 * </p>
	 * <p>
	 * Description:查询热门商品信息
	 * </p>
	 * 
	 * @return
	 * @see com.shop.service.ProductService#getProductList()
	 */
	public List<Product> getHotProductList() {
		ProductExample example = new ProductExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsHotEqualTo(1);
		PageHelper.startPage(1, 10);// 分页 从第一条显示,显示10条记录
		List<Product> list = productMapper.selectByExample(example);
		return list;
	}

	/**
	 * 
	 * <p>
	 * Title: getNewProductList
	 * </p>
	 * <p>
	 * Description:查询最新上架商品
	 * </p>
	 * 
	 * @return
	 * @see com.shop.service.ProductService#getNewProductList()
	 */
	public List<Product> getNewProductList() {
		ProductExample example = new ProductExample();
		// Criteria criteria = example.createCriteria();
		example.setOrderByClause("pdate");
		PageHelper.startPage(1, 10);
		List<Product> list = productMapper.selectByExample(example);
		return list;
	}

	/**
	 * 
	 * <p>
	 * Title: queryCateProductList
	 * </p>
	 * <p>
	 * Description:查询一级分类商品信息
	 * </p>
	 * 
	 * @param cid
	 * @return
	 * @see com.shop.service.ProductService#queryCateProductList(int)
	 */
	public List<Product> queryCateProductList(int cid) {

		return productMapper.queryCateProductList(cid);
	}

	/**
	 * 
	 * <p>
	 * Title: queryPageProduct
	 * </p>
	 * <p>
	 * Description: 商品分类信息分页
	 * </p>
	 * 
	 * @param cid
	 * @param page
	 * @return
	 * @see com.shop.service.ProductService#queryPageProduct(int, int)
	 */
	public PageBean<Product> queryPageProduct(int cid, int page) {
		// 1.查询出的数据封装到pagebean的list成员变量中.
		PageBean<Product> pb = new PageBean<Product>();
		int pageSize = 12;// 每页显示的条数
		pb.setPageSize(pageSize);
		// 查询总条数
		int totalCount = productMapper.queryProductPageCount(cid);
		pb.setTotalCount(totalCount);
		// 计算总页数
		int totalPage = (totalCount + pageSize - 1) / pageSize;
		pb.setTotalPage(totalPage);
		// 计算起始记录
		int begin = (page - 1) * pageSize;
		pb.setPage(begin);
		// 查询商品信息
		List<Product> list = productMapper.queryProductPageList(cid, begin, pageSize);
		pb.setList(list);
		return pb;
	}

	/**
	 * 
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description: 查询商品详情
	 * </p>
	 * 
	 * @param pid
	 * @return
	 * @see com.shop.service.ProductService#findById(int)
	 */
	public Product findById(int pid) {
		return productMapper.selectByPrimaryKey(pid);
	}

	// 保存商品信息
	public void saveProduct(Product product) {
		productMapper.insert(product);
	}

}
