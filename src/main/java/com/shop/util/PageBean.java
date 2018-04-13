package com.shop.util;

import java.util.List;

/**
 * 
 * <p>
 * Title: PageBean
 * </p>
 * <p>
 * Description:分页工具类
 * </p>
 * <p>
 * Company: www.bochy.com
 * </p>
 * 
 * @author 涛哥
 * @date 2017年3月27日上午8:46:54
 * @version 1.0
 */
public class PageBean<T> {

	private Integer page; // 当前页
	private Integer pageSize;// 每页显示的条数
	private Integer totalCount;// 总条数
	private Integer totalPage;// 总页数
	private List<?> list;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

}
