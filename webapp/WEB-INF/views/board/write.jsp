<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<!--헤더 시작 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!--헤더 끝 -->
		
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.servletContext.contextPath}/board/write">
					<input type = "hidden" name = "uno" value = "${authUser.no}"/>
					<input type = "hidden" name = "parentno" value = "${parentBoard.no}"/>
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td>
								<c:choose>
								<c:when test="${not empty parentBoard}">
									<input type="text" name="title" value="${parentBoard.title}">
								</c:when>
								<c:otherwise>
									<input type="text" name="title" value="">
								</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<c:choose>
								<c:when test="${not empty parentBoard}">
									<textarea id="content" name="content">${parentBoard.content}"</textarea>
								</c:when>
								<c:otherwise>
									<textarea id="content" name="content"></textarea>
								</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath}/board/list?${boardParam.queryString}">취소</a>
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
			
		<!--네비게이션 시작 -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"></c:param>
		</c:import>
		<!--네비게이션 끝 -->
		
		<!--풋터 시작 -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!--풋터 끝 -->
		
	</div>
</body>
</html>