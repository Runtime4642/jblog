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
	<!-- 현재 보고 있는 창을 반환해주기위한 url 값 세팅 -->
	<c:choose>
		<c:when test="${categoryClick eq true}">
			<c:set var="url" value="/blog/${userNo}/${postVo.no}/${categoryNo}"/>
		</c:when>
		<c:otherwise>
			<c:set var="url" value="/blog/${userNo}/${postVo.no}"/>
		</c:otherwise>
	</c:choose>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<form class="blog-content-box" method="post" action ="${pageContext.request.contextPath}/blog/writeComment">
					<div class="title">
					<h4>${postVo.title}</h4>
					</div>
					<div class="date">
					<p>${postVo.regDate}</p>
					</div>
					<div class="content">
					<p>
					${fn:replace(postVo.content, newline, "<br>")}
					<p>
					</div>
					<div class="comment-header"><p>댓글창</p></div>
					<ul class="comment-main">
					<c:forEach items="${commentList}" var="vo">
					<span id="comment-date">${vo.regDate}</span>
					<li>${vo.content}
					<a href="${pageContext.request.contextPath}/blog/delete?no=${vo.no}&url=${url}"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a>
					</li>
					</c:forEach>
					</ul>
					<div class="comment-input">
					<input type="text" name="content" id="comment-text"/>
					<input type="submit" value="댓글입력" id="comment-input-button"/>
					<input type="hidden" value="${postVo.no}" name="postNo"/>
					<input type="hidden" value="${url}" name="url"/>
					</div>
					
					
					</form>
				</div>
				<ul class="blog-list">
					<h2>리스트</h2>
					<c:forEach items="${postList}" var="vo">
					<c:choose>
					<c:when test="${categoryClick eq true}">
					<li><a href="${pageContext.request.contextPath}/blog/${userNo}/${vo.no}/${categoryNo}">${vo.title}</a></li>
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