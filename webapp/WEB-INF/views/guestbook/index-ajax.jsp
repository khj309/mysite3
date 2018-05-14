<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
//jQuery plugin
(function($){
	$.fn.hello = function(){
		console.log("hello().....");
		let $element = $(this);
		console.log($element.attr("id")+" : hello!");
	}
})(jQuery);


let ejsListItem = new EJS({
  url:"${pageContext.servletContext.contextPath}/assets/js/ejs/templates/listitem.ejs"
});

let lastNo=0;
let isEnd = false;
var messageBox = function(title, message, callback){
	$("#dialog-message").attr("title", title);
	$("#dialog-message").text(message);
	
	$("#dialog-message").dialog({
	      modal: true,
	      buttons: {
	        "확인": function() {
		          	$( this ).dialog( "close" );
	        }
	      },
	      close:callback|| function(){}
	});
}
$(function(){
	var deleteDialog = $( "#dialog-delete-form" ).dialog({
	    autoOpen: false,
	    height: 150,
	    width: 350,
	    modal: true,
	    buttons: {
	    	"삭제" : function(){
	    		var param="?passwd="+$("#password-delete").val();
	    		$.ajax({
    				url:"${pageContext.servletContext.contextPath}/api/guestbook/"+$("#hidden-no").val()+param,
					type:"delete",
					success:function(response){
						if(response.data=="success"){
							$("#list-guestbook li[data-no="+$("#hidden-no").val()+"]").remove();
							deleteDialog.dialog( "close" );
						}
						else if(response.data == "failed"){
							messageBox("메세지 등록", "삭제에 실패 했습니다.");
						}
						
					}
				});
	    		
	    	},
		  	"취소" : function(){
		  		console.log("cancel....");
		  		deleteDialog.dialog( "close" );
		 	}
	   	},
	    close: function() {
	    	console.log("close....");
	    	$("#hidden-no").val('');
	    	$("#password-delete").val('');
	   	}
	});
	
	
	//Live Event Listener
	$(document).on("click", "#list-guestbook li a", function(evt){
		evt.preventDefault();
		
		var $delBtn = $(this); 
		var targetNo=$delBtn.data("no");
		
		$("#hidden-no").val(targetNo);
		deleteDialog.dialog("open");
		
	});
	
	$("#add-form").submit(function(evt){
		evt.preventDefault();
		
		/* JSON 스트링 */
		var data = {};
		var arr = $(this).serializeArray();
		$.each(arr, function(index, item){
			data[item.name] = item.value;
		})
		
		
		//validation
		if(data["name"] == ''){
			messageBox("메세지 등록", "이름이 비어있습니다.", function(){
				$("#input-name").focus();
			});
			return false;
		}
		if(data["password"] == ''){
			messageBox("메세지 등록", "비밀번호가 비어있습니다.",function(){
				$("#input-password").focus();
			});
			return false;
		}
		if(data["content"] == ''){
			messageBox("메세지 등록", "내용이 비어있습니다.",function(){
				$("#tx-content").focus();
			});
			return false;
		}
		
		$.ajax({
			url:"${pageContext.servletContext.contextPath}/api/guestbook",
			type:"post",
			dataType:"json",
			data:JSON.stringify(data),
			contentType:"application/json",
			success:function(response){
				renderGuestbook(true, response.data);
			}
		});
		
		/* 쿼리 스트링 
		$.ajax({
			url:"/mysite3/api/guestbook",
			type:"post",
			datatype:"json",
			data:$(this).serialize(),
			success:function(response){
				renderGuestbook(true, response.data);
			}
		});
		 */
	});
	
	
	$("#btn-fetch").click(function(){
		fetchList();
	});
	
	$(window).scroll(function(){
		let $win=$(this);
		let scrollTop = $win.scrollTop();
		let windowHeight = $win.height();
		let documentHeight = $(document).height();
		
		/* console.log(scrollTop);
		console.log(windowHeight);
		console.log(documentHeight); */
		
		if(scrollTop + windowHeight +30 > documentHeight){
			fetchList();
		}
	});
	
	//최초리스트 가져오기
	fetchList();
	
	
	//플러그인 테스트
	$("#container").hello();
});
var fetchList = function(){
	lastNo = $("#list-guestbook li").last().data("no") || 0;
	$.ajax({
		url:"${pageContext.servletContext.contextPath}/api/guestbook/list?lastNo="+lastNo,
		type:"get",
		dataType:"json",
		success:function(response){
			if(response.result != "success"){
				console.log(response.message);
				return false;
			}
			//끝 감지
			if(response.data.length < 5){
				console.log("끝임");
				isEnd=true;
				$("#btn-fetch").hide();		
			}
			$.each(response.data, function(index, vo){
				renderGuestbook(false, vo);
			});
			
		},
		error:function(){}
	});
}
var renderGuestbook = function(position, vo){
	//render guestbook data
		var html = ejsListItem.render(vo);
			
			/* "<li data-no='"+vo.no+"'>"+
			"<strong>"+vo.name+"</strong>"+
			"<p>"+vo.content.replace(/\n/gi, '<br>')+"</p>"+
			"<a class='img-del href='' data-no='"+vo.no+"'>삭제</a>"+ 
			"</li>"; */
			
		if(position == true){
			$("#list-guestbook").prepend(html);
		}else if(position == false){
			$("#list-guestbook").append(html);	
		}
}

</script>
</head>
<body>
	<div id="container">
		
		<!--헤더 시작 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!--헤더 끝 -->
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" name="name" placeholder="이름">
					<input type="password" id="input-password" name="password" placeholder="비밀번호">
					<textarea id="tx-content" name ="content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">
					<%-- <c:set var ="count" value="${fn:length(list) }"/>
					<c:forEach items="${list}" var ="vo" varStatus="status">
					<li data-no='${vo.no}'>
						<strong>${vo.name}</strong>
							<c:out value="${fn:replace(vo.content, newLine, '<br>')}" escapeXml="true"/>
							<p>
							<c:out value="${fn:replace(vo.content, newLine,'<br>')}" escapeXml="false"></c:out>
							</p>
						<a class="img-del" href='' data-no='${vo.no}'>삭제</a> 
					</li>
					</c:forEach> --%>
				</ul>
				<button id='btn-fetch'>가져 오기</button>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" name="passwd" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" name = "no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<!--네비게이션 시작 -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook"></c:param>
		</c:import>
		<!--네비게이션 끝 -->
		
		<!--풋터 시작 -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!--풋터 끝 -->
	</div>
</body>
</html>