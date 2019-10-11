<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="header">
	<h1>${blog.title}</h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${pageContext.servletContext.contextPath}/user/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a></li>
			</c:otherwise>
		</c:choose>
	<c:if test="${authUser.id == id}">
		<li><a id="link-blog-admin" href="${pageContext.servletContext.contextPath}/${authUser.id}/admin/basic">블로그 관리</a></li>
	</c:if>
	
	<li><a href="${pageContext.servletContext.contextPath}/${id}">블로그 메인</a></li>
	<li><a href="${pageContext.servletContext.contextPath}">메인</a></li>
	</ul>
</div>