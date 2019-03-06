<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
var valid = {
		init:function(){
			$('#blog-id').change(this.onIdInputTextChanged.bind(this));
			$('#btn-checkId').click(this.onCheckIdButtonClicked.bind(this));
			$('.join-form').submit(this.onFormSubmit.bind(this));
		},
		onFormSubmit: function() {
			//1. 이름
			var $inputTextName = $( "#name" );
			if( $inputTextName.val() === "" ) {
				alert( "이름은 필수 항목입니다." );
				$inputTextName.focus();
				return false;
			}

			//3. 아이디 중복 체크 여부
			if( $('#img-checkId').is( ":visible" ) === false ) {
				alert( "아이디 중복 체크를 해 주세요." );
				return false;
			}
			
			//4. 비밀번호
			var $inputPassword = $( "#password" );
			if( $inputPassword.val() === "" ) {
				alert( "비밀번호는 필수 항목입니다." );
				$inputPassword.focus();
				return false;
			}
			
			//5. 약관동의
			var $inputCheckBoxAgree = $( "#agree-prov" );
			if( $inputCheckBoxAgree.is( ":checked" ) === false ) {
				alert( "가입 약관에 동의 하셔야 합니다." );
				$inputCheckBoxAgree.focus();
				return false;
			}		
			// valid!
			return true;				
		},
		onCheckIdButtonClicked:function(event){
			//event 타깃 가져오는 방법
			console.log(event.currentTarget);
			var $id = $("#blog-id");
			if($id.val() == ""){
				alert('id가 비어있습니다.');
				$id.focus();
				return;
			}
			$.ajax( {
				url : "${pageContext.request.contextPath }/user/checkId?id=" + $id.val(),
				type: "get",
				dataType: "json",
				data: "",
				success: function( response ) {
					if(response.result == "fail"){
						console.error(response.message);
						return;
					}if( response.data == true ) {
						alert( "이미 존재하는 이메일 입니다. 다른 이메일을 사용해 주세요." );
						// id 입력 창 비우고 포커싱
						$id.val( "" ).focus();
					} else {
						$('#img-checkId').show();
						$('#btn-checkId').hide();
					}},	
				error: function( jqXHR, status, error ){
					console.error( status + " : " + error );
				}
			} );	
		},
		onIdInputTextChanged: function() {
			$('#img-checkId').hide();
			$('#btn-checkId').show();	
		}
}


$(function(){
	valid.init();
});
</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/menu.jsp"/>
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value=""><br/>
			<strong><p style="color:red"><form:errors path="name"/></p></strong>
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"><br/>
			<strong><p style="color:red"><form:errors path="id"/></p></strong>
			<input id="btn-checkId" type="button" value="id 중복체크">
			<img id="img-checkId" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" /><br/>
			<strong><p style="color:red"><form:errors path="password"/></p></strong>
			

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
