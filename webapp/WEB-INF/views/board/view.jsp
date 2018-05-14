<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
<link href="${pageContext.servletContext.contextPath}/assets/css/comment.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<!--헤더 시작 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!--헤더 끝 -->
		
		<div id="content">
			<!-- 게시물보기 영역시작  -->
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">글 내용</td>
						<td>
							<div class="view-content">
								<c:out value='${fn:replace(vo.content, newLine, "<br>")}' escapeXml="true"></c:out>
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath}/board/list?${boardParam.queryString}">글목록</a>
					<c:if test="${authUser.no == vo.userNo}">
						<a href="${pageContext.servletContext.contextPath}/board/modify/${vo.no}?${boardParam.queryString}">글수정</a>
					</c:if>
					<c:if test="${not empty authUser}">
						<a href="${pageContext.servletContext.contextPath}/board/write/?parentno=${vo.no}${boardParam.queryString}">답글쓰기</a>
					</c:if>
				</div>
			</div>
			<!-- 게시물 보기 영역 끝 -->
			
			<!-- 댓글 영역 시작 -->
			<div id="comment">
				<form action="${pageContext.servletContext.contextPath}/comment/add" method="post">
					<input type="hidden" name="pageNo" value="${boardParam.pageNo }">
					<input type="hidden" name="searchType" value="${boardParam.searchType }">
					<input type="hidden" name="searchValue" value="${boardParam.searchValue }">
					<input type="hidden" name="boardNo" value="${vo.no}">
					
					
					<table>
						<tr>
							<th>댓글 작성</th>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<c:set var ="count" value="${fn:length(vo.comment) }"/>
					<c:forEach items="${vo.comment}" var ="comment" varStatus="status">  
					<li>
						<table>
							<tr>
								<td>[${status.index+1}]</td>
								<td>${comment.userName}</td>
								<td>${comment.regDate}</td>
								<td>
									<c:if test="${comment.userNo == authUser.no }">
										<a href="${pageContext.servletContext.contextPath}/comment/delete/${comment.no}?${boardParam.queryString}">삭제</a>
									</c:if>
								</td>
							</tr>
							<tr>
								<td colspan=4>
								${fn:replace(comment.content, newLine, "<br>")}
								</td>
							</tr>
						</table>
						<br>
					</li>
					</c:forEach>
				</ul>
			</div>
			<!-- 댓글 영역 끝 -->
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