<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%pageContext.setAttribute("newline", "\n");%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title}</h4>
					<p style="text-align: right">${postVo.regDate}</p>
					<p>
					${fn:replace(postVo.content, newline, "<br>")}
					<p>
				</div>
				<ul class="blog-list">
					
					<c:forEach items="${postList}" var="vo">
					<c:choose>
					<c:when test="${categoryClick eq true}">
					<li><a href="${pageContext.request.contextPath}/blog/${userNo}/${vo.no}/${categoryNo}">${vo.title}</a> <span>${vo.regDate}</span>	</li>
					</c:when>
					<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/blog/${userNo}/${vo.no}">${vo.title}</a> <span>${vo.regDate}</span>	</li>
					</c:otherwise>
					</c:choose>
					</c:forEach>
					
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img onerror="this.src='${pageContext.request.contextPath}/assets/images/spring-logo.jpg'" src="${pageContext.request.contextPath}/uploads/${blogVo.getLogo()}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items="${categoryList}" var="vo">
				<li><a href="${pageContext.request.contextPath}/blog/${userNo}/${postVo.no}/${vo.no}">${vo.name}</a></li>
			</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>