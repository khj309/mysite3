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
</head>
<body>
	<div id="container">
		
		<!--헤더 시작 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!--헤더 끝 -->
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath}/board/list" method="get">
					<input type="hidden" name="searchType" value="all">
					
					<c:if test="${not empty boardParam }">
						<input type="hidden" name="pageNo" value="1">
					</c:if>
					
					<input type="text" id="kwd" name="searchValue" value="${boardParam.searchValue}" placeholder ="제목+내용 검색">
					<input type="submit" value="찾기">
				</form>
				
				<!-- 게시글 리스트 시작  -->	
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list}" var="board" varStatus="stat">
					<tr>
						<td>${(pb.totalBoardCount - ((boardParam.pageNo-1) * boardParam.countPerPage))-stat.index }</td>
						<c:choose>
						<c:when test='${board.status == "deleted" }'>
						<td style="text-align:left;" colspan = '5'>
							삭제된 게시글 입니다.
						</td>
						</c:when>
						<c:otherwise>
							<td style="text-align:left; padding-left:${20*board.depth}px;">
						<a href="${pageContext.servletContext.contextPath}/board/view/${board.no}?${boardParam.queryString}">
							<c:if test="${board.orderNo != 0}">
								<img src="${pageContext.servletContext.contextPath}/assets/images/reply.png"/>
							</c:if>
							${board.title}
						</a>
						</td>
						<td>${board.writer}</td>
						<td>${board.viewCnt}</td>
						<td>${board.regDate}</td>
						<td>
							<c:if test="${authUser.no == board.userNo}"> 
								<a href="${pageContext.servletContext.contextPath}/board/delete/${board.no}?${boardParam.queryString}" class="del">삭제</a>
							</c:if>
						</td>
						</c:otherwise>
						</c:choose>
						
					</tr>
					</c:forEach>
				</table>
				<!-- 게시글 리스트 끝  -->	
			
				<div class="pager">
					<ul>
					
					<c:choose>
					<c:when test="${pb.hasPreviousPagination }">
						<li><a href="${pageContext.servletContext.contextPath}/board/list?${boardParam.shortQueryString}&pageNo=${pb.startPageNo-1}">◀</a></li>
					</c:when>
					<c:otherwise>
						<li><a>◀</a></li>
					</c:otherwise>
					</c:choose>
					
					<c:forEach begin="${pb.startPageNo}" end="${pb.endPageNo}" varStatus="stat">
						<%-- 인덱스${stat.index}&nbsp; 
						파람페이지번호${boardParam.pageNo}&nbsp;
						빌더총페이지${pb.countTotalPage}&nbsp;	 --%>
						<c:choose>
						<c:when test="${stat.index == boardParam.pageNo}">
						<li class="selected">${stat.index}</li>
						</c:when>
						<c:when test="${stat.index > pb.countTotalPage}">
						<li>${stat.index}</li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageContext.servletContext.contextPath}/board/list?${boardParam.shortQueryString}&pageNo=${stat.index}">${stat.index}</a></li>
						</c:otherwise>
						</c:choose>
					</c:forEach>
						<c:choose>
						<c:when test="${pb.hasNextPagination }">
							<li><a href="${pageContext.servletContext.contextPath}/board/list?${boardParam.shortQueryString}&pageNo=${pb.endPageNo+1}">▶</a></li>
						</c:when>
						<c:otherwise>
							<li><a>▶</a></li>
						</c:otherwise>
						</c:choose>
					</ul>
				</div>				
				<div class="bottom">
					<c:if test="${not empty authUser}"> 
						<a href="${pageContext.servletContext.contextPath}/board/write?${boardParam.queryString}" id="new-book">글쓰기</a>
					</c:if>
				</div>				
			</div>
			<!-- 게시글리스트 끝 -->
			
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