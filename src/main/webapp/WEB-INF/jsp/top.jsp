<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="http://localhost:8080/mango/">
				
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
					<img src="<%=request.getContextPath() %>/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
</div>	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			
			<ul>
			  <c:choose>
			  		<c:when test="${userinfo!=null }">
			  			<li id="headerLogin" class="headerLogin" style="display: list-item;">
							${userinfo.username }
							<a href="<%=request.getContextPath() %>/user/logout" >[退出]</a>|
						</li>
			  		</c:when>
			  		
			  		<c:otherwise>
			  		<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="<%=request.getContextPath() %>/login">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="<%=request.getContextPath() %>/register">注册</a>|
					</li>
			  		</c:otherwise>
			  </c:choose>
				
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="<%=request.getContextPath() %>/购物车.htm">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>