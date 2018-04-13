<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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