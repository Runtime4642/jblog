<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<ul class="menu">
		<c:choose>
			<c:when test="${empty authUser}">
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a><li>
				<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a><li>
			</c:when>
			<c:otherwise>
				<c:if test="${authuser.getRole() eq 'ADMIN'}">
					<li><a href="${pageContext.request.contextPath}/admin">관리자페이지</a><li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			<c:if test="${not empty blogVo && blogVo.getUserNo() eq authUser.getNo()}">
				<li><a href="${pageContext.request.contextPath}/blogAdmin/blog-admin-basic">내블로그관리</a></li>
			</c:if>
				<li><a href="${pageContext.request.contextPath}/blog/${authUser.getNo()}">내블로그</a></li>
				<li>${authUser.getName()}님안녕하세요 ^^;</li>
			</c:otherwise>

		</c:choose>
	</ul>