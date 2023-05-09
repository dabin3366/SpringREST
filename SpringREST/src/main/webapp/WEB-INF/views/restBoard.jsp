<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#btnC").click(function(){
			alert(" 글쓰기 버튼 클릭! ");
			var title = $('#title').val();
			var writer = $("#writer").val();
			var content = $("#content").val();
			
			// 전달할 데이터
			var board ={
					"title" : title,
					"writer" : writer,
					"content" : content
			};
			
			//alert(board);
			//console.log(board);

			
			$.ajax({
				url : "${contextPath}/boards",
				type : "POST",
				contentType : "application/json",
				data : JSON.stringify(board),
				success:function(data){
					$('#divC').append("<font color='red'><h2>글쓰기 성공</h2></font>");
					alert("성공 : "+data);
				}, 
				error:function(){
					alert("실패");
				}
			});
		});// 글쓰기
		
		$("#btnR").click(function(){
			alert(" 특정 글 조회! ");
			
			var bno = 496;
			//   /boards/100	
			$.ajax({
				url : "./boards/"+bno,
				success : function(data){
					alert("성공 "+data);
					console.log(data);
					$('#divR').append("bno:"+data.bno+"<br>");
					$('#divR').append("title:"+data.title+"<br>");
					$('#divR').append("content:"+data.content+"<br>");
					$('#divR').append("writer:"+data.writer+"<br>");
					$('#divR').append("data:"+ new Date(data.regdate)+"<br>");
					
					// 업데이트용
					$('#uBno').val(data.bno);
					$('#uTitle').val(data.title);
					$('#uWriter').val(data.writer);
					$('#uContent').val(data.content);
				
				},
				error : function(){
					alert("실패");
				}
			}); 
		});// read
		
		$("#btnList").click(function(){
			alert(" List 클릭! ");
			$.ajax({
				url : "./boards/1/list",
				success : function(data){
					alert("성공  : 	"+data);
					
					$(data).each(function(i,obj){
						$("#divList").append(obj.bno+" : "+obj.title+"<hr>");
					});
					
				},
				error : function(){
					alert("실패");
				}
			});
		});// list
		
		
		$('#btnU').click(function(){
			alert(" 수정 클릭 !");
			// 수정할 정보 Obj -> JSON 전달
			var updateVO = {
				"bno" : $('#uBno').val(),
				"title" : $('#uTitle').val(),
				"writer" : $('#uWriter').val(),
				"content" : $('#uContent').val()
			};
			
			console.log(updateVO);
			
			// ajax -> REST컨트롤러 호출
			$.ajax({
				url : "./boards/"+ $('#uBno').val(),
				type : "PUT",
				contentType : "application/json",
				data : JSON.stringify(updateVO),
				success:function(data){
					alert("성공 : "+data);					
				},
				error:function(){
					alert("실패");
				}
			});
		}); //update
		
		
		$('#btnD').click(function(){
			
			// 글삭제     /boards/100		(DELETE)
			$.ajax({
				url : "./boards/502",
				type : "DELETE",
				success : function(data){
					alert("성공 : "+data);	
				},
				error : function(){
					alert("실패");
				}
				
				
			});
			
		});
		
		
		
	});//jquery
</script>


</head>
<body>
		<h1>restBoard.jsp</h1>
		
		<h2> 글쓰기 (Create) </h2>
		
		제목 : <input type="text" id="title"><br>
		글쓴이 : <input type="text" id="writer"><br>
		내용 : <textarea rows="5" cols="15" id="content"></textarea><br>
		
		<input type="button" value="글쓰기" id="btnC">	
		
		<div  id="divC">
		</div>
		<hr>
		<h2> 글 조회 (Read) </h2>
		
		<input type="button" value="특정 글 조회" id="btnR">
		<div id="divR">
		</div>
		
		<hr>
		<h2> 글 목록 조회 (Read_ALL) </h2>
		
		<input type="button" value="글 목록 조회" id="btnList">
		<div id="divList">
		</div>
		
		<hr>
		
		<h2> 글 정보 수정 (Update) </h2>
		
		글번호 : <input type="text" id="uBno" readonly> <br>
		제목 : <input type="text" id="uTitle"><br>
		이름 : <input type="text" id="uWriter"><br>
		내용 : <textarea rows="5" cols="15" id="uContent"></textarea>
		
		<input type="button" value="정보 수정하기" id="btnU">
		<hr>
		
		<h2> 글 정보 삭제 (Delete) </h2>
		
		<input type="button" value="글 삭제하기" id="btnD">
		
		
		
		

</body>
</html>