<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>购物车</title>
<meta name="author" content="Mango Team">
<meta name="copyright" content="Mango">
<link href="<%=request.getContextPath() %>/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/css/cart.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>

</head>
<body>
<%@include file="top.jsp" %>
<%@include file="header.jsp" %>

	<div class="container cart">
		<div class="span24">
			<div class="step step1">
				
			</div>
				<table>
					<tbody><tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${cart.cartItems }" var="item">
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22">
								<img src="<%=request.getContextPath() %>/${item.product.image}">
							</td>
							<td>
								<a target="_blank">${item.product.pname}</a>
							</td>
							<td>
								￥${item.product.shopPrice}
							</td>
							<td class="quantity" width="60">
								<input type="text" name="quantity" value="${item.count }" maxlength="4" onpaste="return false;">
								<div>
									<span class="increase">&nbsp;</span>
									<span class="decrease">&nbsp;</span>
								</div>
							</td>
							<td width="140">
								<span class="subtotal">￥${item.subtotal}</span>
							</td>
							<td>
								<a href="<%=request.getContextPath() %>/cart/delCart?pid=${item.product.pid}" class="delete">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody></table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
							<em>
								登录后确认是否享有优惠
							</em>
					赠送积分: <em id="effectivePoint">${cart.totalCount }</em>
					商品金额: <strong id="effectivePrice">￥${cart.totalCount }元</strong>
				</div>
				<div class="bottom">
					<a href="javascript:;" id="clear" class="clear">清空购物车</a>
					<a href="<%=request.getContextPath() %>/order/saveOrder" id="submit" class="submit">提交订单</a>
				</div>
		</div>
	</div>

</body></html>