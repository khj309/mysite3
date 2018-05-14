<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%

%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<!--헤더 시작 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!--헤더 끝 -->
		
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.servletContext.contextPath}/user/modify">
					<input type = 'hidden' name = 'no' value='${userInfo.no}'/>
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userInfo.name }">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${userInfo.email }" readonly="readonly">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test='${userInfo.gender=="male" }'>
								<label>여</label>
								<input type="radio" name="gender" value="female">
								<label>남</label>
								<input type="radio" name="gender" value="male" checked="checked">
							</c:when>
							<c:when test='${userInfo.gender=="female" }'>
								<label>여</label>
								<input type="radio" name="gender" value="female" checked="checked">
								<label>남</label>
								<input type="radio" name="gender" value="male">
							</c:when>
						</c:choose>
					</fieldset>
					
					<input type="submit" value="수정하기">
				</form>
				<c:if test='${param.result == "success" }'>
					<p>회원 정보가 수정되었습니다.</p>
				</c:if>
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