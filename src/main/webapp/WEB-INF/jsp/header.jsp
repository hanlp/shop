<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="<%=request.getContextPath() %>/index.htm">首页</a>
						|
					</li>
					<c:forEach items="${categoryList }" var="cate">
						<li>                     
							<a href="<%=request.getContextPath() %>/category/queryByPageCid?cid=${cate.cid}&page=1">${cate.cname }</a>
							|
						</li>
					</c:forEach>
					
		</ul>
	</div>