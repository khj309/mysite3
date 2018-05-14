<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="navigation">
	<c:choose>
		<c:when test='${param.menu=="main" }'>
			<ul>
				<li class="selected"><a href="${pageContext.servletContext.contextPath}/main">김형주</a></li>
				<%-- <li><a href="${pageContext.servletContext.contextPath}/guestbook/list">방명록</a></li> --%>
				<li><a href="${pageContext.servletContext.contextPath}/guestbook/ajax">방명록</a></li> 
				<li><a href="${pageContext.servletContext.contextPath}/board/list">게시판</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/gallery/list">갤러리</a></li>
			</ul>
		</c:when>
		<c:when test='${param.menu=="guestbook"}'>
			<ul>
				<li><a href="${pageContext.servletContext.contextPath}/main">김형주</a></li>
				<li class="selected"><a href="${pageContext.servletContext.contextPath}/guestbook/ajax">방명록</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/board/list">게시판</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/gallery/list">갤러리</a></li>
			</ul>
		</c:when>
		<c:when test='${param.menu=="board" }'>
			<ul>
				<li><a href="${pageContext.servletContext.contextPath}/main">김형주</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/guestbook/ajax">방명록</a></li>
				<li class="selected"><a href="${pageContext.servletContext.contextPath}/board/list">게시판</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/gallery/list">갤러리</a></li>
			</ul>
		</c:when>
		<c:when test='${param.menu=="gallery" }'>
			<ul>
				<li><a href="${pageContext.servletContext.contextPath}/main">김형주</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/guestbook/ajax">방명록</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/board/list">게시판</a></li>
				<li class="selected"><a href="${pageContext.servletContext.contextPath}/gallery/list">갤러리</a></li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul>
				<li><a href="${pageContext.servletContext.contextPath}/main">김형주</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/guestbook/ajax">방명록</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/board/list">게시판</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/gallery/list">갤러리</a></li>
			</ul>
		</c:otherwise>
	</c:choose>
</div>