<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% pageContext.setAttribute("replace", "\n"); %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test="${postNo == 0}">
							<h4>${post[0].title}</h4>
							<p>${fn:replace(post[0].content, replace, '<br>')}<p>
						</c:when>
						<c:otherwise>
							<c:forEach items='${post}' var='main_po' varStatus='main_postStatus'>
								<c:if test="${main_po.no == postNo}">
									<h4>${main_po.title}</h4>
									<p>${fn:replace(main_po.content, replace, '<br>')}<p>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
				<ul class="blog-list">
					<c:forEach items='${post}' var='po' varStatus='postStatus'>
						<li><a href="${pageContext.servletContext.contextPath}/${id}/${po.category_no}/${po.no}">${po.title}</a> <span>${po.reg_date}</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.servletContext.contextPath}${blog.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:set var='count' value='${fn:length(category)}' />
				<c:forEach items='${category}' var='ct' varStatus='categoryStatus'>
					<li><a href="${pageContext.servletContext.contextPath}/${id}/${ct.no}">${ct.name}</a></li>
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