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
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<!-- 
<script>
	$(function() {
		$("#link-blog-admin").on('click',function() {
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/${authUser.id}/admin/basic",
				type:"get",
				dataType:"json",
				data:"${authUser.id}",
				success:function(data){
						location.href = "${pageContext.request.contextPath}/${authUser.id}/admin/basic";
				}, error:function(error){
					alert("error" + error);
				}
			});
		});
	});

</script>
 -->
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blog.title}</h1>
			<ul>
				<c:choose>
					<c:when test="${empty authUser }">
						<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
				<c:if test="${authUser.id == id}">
					<li><a id="link-blog-admin" href="${pageContext.servletContext.contextPath}/${authUser.id}/admin/basic">블로그 관리</a></li>
				</c:if>
			</ul>
		</div>
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
						<li><a href="${pageContext.request.contextPath}/${id}/${po.category_no}/${po.no}">${po.title}</a> <span>${po.reg_date}</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:set var='count' value='${fn:length(category)}' />
				<c:forEach items='${category}' var='ct' varStatus='categoryStatus'>
					<li><a href="${pageContext.request.contextPath}/${id}/${ct.no}">${ct.name}</a></li>
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