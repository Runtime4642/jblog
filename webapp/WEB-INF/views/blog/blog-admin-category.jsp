<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>

<script type="text/javascript">
var cnt=1;
var onload = {
		init: function(){
			$('.admin-category-add').submit(this.onFormSubmit.bind(this));
			this.getCategoryList();
			//$('#deleteImg').click(this.onDeleteImgClick.bind(this)); //<--왜안먹는지..?
			$(document).on("click", "#deleteImg", this.onDeleteImgClick.bind(this));
		},
		onFormSubmit: function(){
			$.ajax({
				url:"${pageContext.request.contextPath }/categoryAdmin/category",
				type:"post",
				dataType:"json",
				data:"name="+$('input[name=name]').val()+"&description="+$('input[name=description]').val(),
				success:function(response){
					onload.render(response.data,2)
				},
				error: function( jqXHR, status, error ){
					console.error( status + " : " + error );
				}
				
			});
		},
		onDeleteImgClick:function(event){
			event.preventDefault();
			console.log(event.currentTarget);
			console.log($(event.currentTarget).data("no"));
			$.ajax({
				url:"${pageContext.request.contextPath }/categoryAdmin/delete",
				type:"post",
				dataType:"json",
				data:"no="+$(event.currentTarget).data("no"),
				success:function(response){
					if(response.data != null)
						$('.admin-cat tr[data-no='+response.data+']').remove();
				},
				error: function( jqXHR, status, error ){
					console.error( status + " : " + error );
				}
				
			});	
		},
		getCategoryList:function(){
			$.ajax({
				url:"${pageContext.request.contextPath }/categoryAdmin/getcategory",
				type:"get",
				dataType:"json",
				data:"",
				success:function(response){
					$.each( response.data, function( index, vo ){
						onload.render( vo, 1 );
					} );
				},
				error: function( jqXHR, status, error ){
					console.error( status + " : " + error );
				}
				
			});
		},
		render: function(vo,mode){
			var htmls = "<tr data-no="+vo.no+"><td>"
			+ cnt++ +"</td><td>"
			+ vo.name +"</td><td>"
			+ vo.count +"</td><td>"
			+ vo.description +"</td><td>"
			+ "<a href='' data-no='"+vo.no+"' id='deleteImg'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></a></td>"
			+ "</tr>"
			if (mode == 1) {
				$(".admin-cat").append(htmls);
			}
			if (mode == 2) {
				$(".admin-cat").prepend(htmls);
			}
			
		}
};
$(function(){
	onload.init();
});	
	
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp" >
					<c:param name="menu" value="category"/>
				</c:import>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
				</table>
      	
      			<form class="admin-category-add">
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table>
		      	</form> 
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