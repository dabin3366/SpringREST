<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#btnC").click(function(){
			alert("글쓰기 버튼 클릭");
			var title = $('#title').val();
			var writer = $('#writer').val();
			var content = $('#content').val();
			
			// 전달할 데이터
			var board = {"title":title,"writer":writer,"content":content};
			
			$.ajax({
				url: "${contextPath}/boards",
				type:"post",
				contentType:"application/json",
				data:JSON.stringify(board),
				success:function(data){
					alert("성공"+data);
					$('#divC').append("<font color='red'><h2>글쓰기 성공</h2></font>");
				},error:function(){
					alert("실패");
				}
			});
		}); // btnC
		
		$("#btnR").click(function(){
			alert("특정 글조회!!!!!");
			$.ajax({
				url: "./boards/100",
				success:function(data){
					alert("성공");
					$('#divR').append("bno : "+data.bno+"<br>");
					$('#divR').append("title : "+data.title+"<br>");
					$('#divR').append("writer : "+data.writer+"<br>");
					$('#divR').append("content : "+data.content+"<br>");
					$('#divR').append("regdate : "+new Date(data.regdate)+"<br>");
				},error:function(){
					alert("실패");
				}
			});
		});// btnR
		
		$("#btnList").click(function(){
			alert("list click");
			$.ajax({
				url: "./boards/2/list",
				success:function(data){
					alert("성공");
					$(data).each(function(i,obj){
						$('#divList').append(obj.bno+" : "+obj.title+"<hr>");
					});
				},error:function(){
					alert("실패");
				}
			});
		});
		
	}); // Jquery
	
</script>
</head>
<body>
	<h1>restBoard.jsp</h1>
	
	<h2>글쓰기 (Create)</h2>
	제목 : <input type="text" id="title"> <br>
	글쓴이 : <input type="text" id="writer"> <br>
	내용 : <textarea rows="5" cols="15" id="content"></textarea> <br>
	
	<input type="button" value="글쓰기" id="btnC">
	<div id="divC"></div>
	
	<hr>
	<h2>글 조회 (Read)</h2>
	
	<input type="button" value="특정 글 조회" id="btnR">
	<div id="divR"></div>
	<hr>
	
	<h2>글 목록 조회 (Read_ALL)</h2>
	
	<input type="button" value="글 목록 조회" id="btnList">
	<div id="divList"></div>
	
	
</body>
</html>