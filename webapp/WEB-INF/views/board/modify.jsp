<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html">
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
				<form class="board-form" method="post" action="${pageContext.servletContext.contextPath}/board/modify/${board.no}" accept-charset="UTF-8">
					<input type='hidden' name ='pageNo' value ='${boardParam.pageNo}'/>
					<input type='hidden' name ='searchType' value ='${boardParam.searchType}'/>
					<input type='hidden' name ='searchValue' value ="${boardParam.searchValue}"/>
					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글수정</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="${board.title}"></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content">${board.content}</textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath}/board/view/${board.no}?${boardParam.queryString}">취소</a>
						<input type="submit" value="수정">
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