<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>
	$(function() {
		$(".delete-admin-category").on('click', function() {
			var cate_no = $(this).attr('id');
			var obg = $(this);

			$.ajax({
				url:"${pageContext.servletContext.contextPath}/${authUser.id}/admin/delete",
				type:"get",
				dataType:"json",
				data: {"cate_no":cate_no},
				success:function(data) {
					if(data.equals("success")) {
						$(obg).parent().parent().remove();
					}
				}, error:function(error) {
					alert("error" + error);
				}
			});
		});
		
		$("#insert-admin-category").on('click', function() {
			var cate_name = $("#cate_name").val();
			var cate_desc = $("#cate_desc").val();
			
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/${authUser.id}/admin/insert",
				type:"get",
				dataType:"json",
				data:{"cate_name":cate_name, "cate_desc":cate_desc},
				success:function(data) {
					$("#cate_name").val("");
					$("#cate_desc").val("");
					
					updateTable(data);
				}, error:function(error) {
					alert("error" + error);
				}
			});
		});
	});
	
	function updateTable(cate_list) {
		$(".origin-tbody").remove();
		$(".new-tbody").remove();
		$newTbody = $("<tbody class='new-tbody'></tbody>")
		$(".admin-cat").append($newTbody)
		
		var length = cate_list.length;
		
		for(var cate in cate_list) {
			$newTbody.append(
					"<tr>" +
					"<td>" + length + "</td>" +
					"<td>" + cate_list[cate].name + "</td>" +
					"<td>" + cate_list[cate].post_count + "</td>" +
					"<td>" + cate_list[cate].description + "</td>" +
					"<td>" + "<img src='${pageContext.servletContext.contextPath}/assets/images/delete.jpg'>" + "</td>" +
					"</tr>"
			);
			
			length--;
		}
	}
</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/admin_header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id}/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		
		      		<tbody class="origin-tbody">
			      		<c:set var="count" value="${fn:length(cate_list)}" />
						<c:forEach items="${cate_list}" var="ca_li" varStatus="status">
							<tr>
								<td>${count-status.index}</td>
								<td>${ca_li.name}</td>
								<td>${ca_li.post_count}</td>
								<td>${ca_li.description}</td>
								<td><a class="delete-admin-category" href="" id="${ca_li.no}"><img src="${pageContext.servletContext.contextPath}/assets/images/delete.jpg"></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="cate_name" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="cate_desc" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" id="insert-admin-category" value="카테고리 추가"></td>
		      		</tr>		      		
		      	</table>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>