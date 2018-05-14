<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
<script src ="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js" type = "text/javascript"></script>
<script>
	$(function(){
		$("#check-email").click( function(){
			var email = $("#email").val();
			if(email == ""){
				alert("이메일을 입력해주세요.")
				return ;
			}
			console.log(email);
			
			var xmlRequest = new XMLHttpRequest();
			
			$.ajax( {
			    url : "${pageContext.servletContext.contextPath}/api/user/checkemail",
			    type: "get",
			    dataType: "json",
			    data: {"email":email},
				//contentType: "application/json",
			    success: function(response){
			       console.log( response );
			       if(response.result != "success"){
			    	   console.log(response.message)
			    	   return;
			       }
			       
			       if(response.data == "exist"){
			    	   alert("이미 사용중인 이메일");
			    	   $("#email").val("").focus();
			    	   return;
			       }
			       $("#check-email").toggle();
			       $("#img-check").toggle();
			      
			    },
			    error: function( jqXHR, status, error ){
			    	console.error( "Sorry ^^; " );
			    	console.error( status + "^^ : " + error );
			    }

			   });
			});
	});
	
</script>
</head>
<body>
	<div id="container">
		
		<!--헤더 시작 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!--헤더 끝 -->
		
		<div id="content">
			<div id="user">

				<form:form 
					modelAttribute  ="userVo"
					id="join-form" 
					name="joinForm" 
					method="post" 
					action="${pageContext.servletContext.contextPath}/user/join">
				
					<label class="block-label" for="name">이름</label>
					<form:input path="name"/>
					<p style="color:red; padding:0px; text-align : left;">
        					<strong>
        					<form:errors path="name"/>
        					</strong>
        			</p>
					
					
					<label class="block-label" for="email">이메일</label>
					<form:input path="email" />
					<input type="button" id="check-email" value="id 중복체크">
					<img id = "img-check" style="display:none;" src="${pageContext.servletContext.contextPath}/assets/images/check.png"/>
					<p style="color:red; padding:0px; text-align : left;">
        					<strong>
        					<form:errors path="email"/>
        					</strong>
        			</p>
					
					<label class="block-label">패스워드</label>
					<form:password path="password"/>
					
					<p style="color:red; padding:0px; text-align : left;">
        					<strong>
        					<form:errors path="password"/>
        					</strong>
        			</p>
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <form:radiobutton path="gender" value="female" checked="checked"/>
						<label>남</label> <form:radiobutton  path="gender" value="male"/>
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
			
		<!--네비게이션 시작 -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
		</c:import>
		<!--네비게이션 끝 -->
		
		<!--풋터 시작 -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!--풋터 끝 -->
		
	</div>
</body>
</html>