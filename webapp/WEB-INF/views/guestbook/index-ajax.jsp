<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-ajax.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var isEnd = false;
var messageBox = function( title, message, callback ) {
	$( "#dialog-message" ).attr( "title", title );
	$( "#dialog-message p" ).text( message );
	
	$( "#dialog-message" ).dialog({
		modal: true,
		buttons: {
			 "확인": function() {
				$( this ).dialog( "close" );
			}
		},
		close: callback || function(){}
	});	
}

var render = function( mode, vo ){
	var html = 
		"<li data-no='" + vo.no + "'>" +
		"<strong>" + vo.name + "</strong>" +
		"<p>" + vo.content.replace(/\n/gi, "<br>") +  "</p>" +
		"<strong></strong>" +
		"<a href='#' data-no='" + vo.no + "'>삭제</a>" + 
		"</li>";
		
	if( mode == true ) {
		$("#list-guestbook").prepend( html );
	} else {
		$("#list-guestbook").append( html );
	}
	//$("#list-guestbook")[ mode ? "prepend" : "append" ](html);
}

$(function(){
	// 삭제 시 비밀번호 입력 모달 다이알로그
	var deleteDialog = $( "#dialog-delete-form" ).dialog({
      autoOpen: false,
      modal: true,
      buttons: {
		"삭제": function(){
			var password = $("#password-delete").val();
			var no = $("#hidden-no").val();
			
			console.log( password + ":" + no );
			
			// ajax 통신
			$.ajax({
				url: "/mysite3/api/guestbook/delete",
				type: "post",
				dataType: "json",
				data:"no=" + no + "&password=" + password,
				success: function(response){
					if( response.result == "fail" ) {
						console.log( response.message );
						return;
					}
					
					if( response.data == -1 ) {
						$(".validateTips.normal").hide();
						$(".validateTips.error").show();
						$("#password-delete").val("");
						return;
					}
					
					$("#list-guestbook li[data-no=" + response.data + "]").remove();
					deleteDialog.dialog( "close" );
				}
			});
		},
        "취소": function() {
        	deleteDialog.dialog( "close" );
        }
      },
      close: function() {
			$("#password-delete").val( "" );
			$("#hidden-no").val( "" );
			$(".validateTips.normal").show();
			$(".validateTips.error").hide();
			
      }
    });
 	
	
	//Live Event Listener
	$(document).on( "click", "#list-guestbook li a", function(event){
		event.preventDefault();

		var no = $(this).data("no");
		$( "#hidden-no" ).val( no );
		deleteDialog.dialog( "open" );
	});
	
	$("#add-form").submit( function(event){
		event.preventDefault();
		
		/*
		var queryString = $(this).serialize();
		$.ajax({
			url: "/mysite3/api/guestbook/insert",
			type: "post",
			dataType: "json",
			data: queryString,
			success: function(response){
				render( true, response.data );
				$("#add-form")[0].reset();	
			}
		});
		*/
		
		var data = {};
		$.each( $(this).serializeArray(), function(index, o){
			data[ o.name ] = o.value;
		});
		
		if( data["name"] == '' ) {
			messageBox(
				"메세지 등록",
				"이름이 비어 있습니다.", function(){
					$("#input-name").focus();		
				});
			return;
		}

		if( data["password"] == '' ) {
			messageBox(
				"메세지 등록", 
				"비밀번호가 비어 있습니다.",
				function(){
					$("#input-password").focus();		
				});
			
			return;
		}

		if( data["content"] == '' ) {
			messageBox(
				"메세지 등록",
				"내용이 비어 있습니다.", function(){
					$("#tx-content").focus();		
				} );
			return;
		}
		
		$.ajax({
			url: "/mysite3/api/guestbook/insert",
			type: "post",
			dataType: "json",
			contentType: "application/json",
			data: JSON.stringify( data ),
			success: function(response){
				render( true, response.data );
				$("#add-form")[0].reset();	
			}
		});
		
	});
	
	$("#btn-fetch").click(function(){
		if( isEnd == true ) {
			return;
		}
		var startNo = $( "#list-guestbook li" ).last().data("no") || 0;
		$.ajax({
			url:"/mysite3/api/guestbook/list?no=" + startNo,
			type: "get",
			dataType: "json",
			success: function(response){
				// 성공유무
				if( response.result != "success" ) {
					console.log( response.message );
					return;
				}
				
				// 끝 감지
				if( response.data.length < 5 ) {
					isEnd = true;
					$( "#btn-fetch" ).prop( "disabled", true );
				}
				
				// render
				$.each( response.data, function( index, vo ){
					render( false, vo );
				});
			}
		});
	});	
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" name="name" id="input-name" placeholder="이름">
					<input type="password" name="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" name="content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook"></ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<button id="btn-fetch">가져오기</button>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>