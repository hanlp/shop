<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>蔬菜 - Powered By Jiyun Team</title>
		<meta name="author" content="Mango Team">
		<meta name="copyright" content="Mango">
			<meta name="keywords" content="蔬菜">
			<meta name="description" content="蔬菜">
<link href="<%=request.getContextPath() %>/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/css/product.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
<body>  
<%@include file="top.jsp" %>
<%@include file="header.jsp" %>

<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<c:forEach items="${cateList }" var="cat">
						<dl>
							<dt>
								<a href="<%=request.getContextPath() %>/image/蔬菜 - Powered By Mango Team.htm">${cat.cname }</a>
							</dt>
							    <c:forEach items="${cat.csList }" var="cs">
									<dd>
										<a >${cs.csname }</a>
									</dd>
								</c:forEach>	
						</dl>
				</c:forEach>		
			</div>
		</div>
		<div class="span18 last">
			
			<form id="productForm" action="<%=request.getContextPath() %>/image/蔬菜 - Powered By Mango Team.htm" method="get">
				<input type="hidden" id="brandId" name="brandId" value="">
				<input type="hidden" id="promotionId" name="promotionId" value="">
				<input type="hidden" id="orderType" name="orderType" value="">
				<input type="hidden" id="pageNumber" name="pageNumber" value="1">
				<input type="hidden" id="pageSize" name="pageSize" value="20">
					
				<div id="result" class="result table clearfix">
						<ul>
						<c:forEach items="${proList.list}" var="prolist">
						<li>
										<a href="<%=request.getContextPath() %>/product/findById?pid=${prolist.pid}">
											<img src="<%=request.getContextPath() %>/${prolist.image}" width="170" height="170"  style="display: inline-block;">
											<span style='color:green'>
											 ${prolist.pname}
											</span>
											<span class="price">
												积云价： ￥${prolist.shopPrice}
											</span>
											 
										</a>
									</li>
							</c:forEach>		
						</ul>
				</div>
				
				
	<div class="pagination">
	 第${pages }页/共${proList.totalPage }页
	
				<!-- 判断首页、上一页显示及设置页码超链接 -->
		<c:if test="${pages!=1 }">
			<a class="firstPage"
				href="${pageContext.request.contextPath }/category/queryByPageCid?cid=${cid }&page=1"></a>
			<a class="firstPage"
				href="${pageContext.request.contextPath }/category/queryByPageCid?cid=${cid }&page=${pages-1 }"><span
				class="previousPage">&nbsp;</span> </a>
		</c:if>
				<c:forEach begin="1" end="${proList.totalPage }" step="1" var="i">
				                            <!-- 获取不到分类cid    解决方案,把cid首次查询时候放到作用域中 -->
				  <a href="<%=request.getContextPath() %>/category/queryByPageCid?cid=${cid}&page=${i}">${i }</a>
				</c:forEach>
				
				<!-- 显示下一页和尾页 -->
  <c:if test="${pages!= proList.totalPage }">
		<a class="nextPage" href="${pageContext.request.contextPath }/category/queryByPageCid?cid=${cid }&page=${pages+1 }">&nbsp;</a>
	    <a class="lastPage" href="${pageContext.request.contextPath }/category/queryByPageCid?cid=${cid }&page=${proList.totalPage }">&nbsp;</a>
  </c:if>
	</div>
			</form>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="<%=request.getContextPath() %>/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a >SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</body></html>