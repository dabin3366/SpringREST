<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"  contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Home</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('input').click(function(){
			alert("클릭");
			
			// ajax 사용해서 정보 전달 (RestController)
			var vo = { no : 100, name : "itwill"};
			$.ajax({
				url:"/restInput",
				type:"POST",
				contentType:"application/json",
				data:JSON.stringify(vo),
				success:function(){
					alert("성공");
				},
				error:function(){
					alert("실패");
				}
			});
		});
	});
</script>

<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<hr>

	<input type="button" value="정보 전송(ajax)">
</body>
</html>
