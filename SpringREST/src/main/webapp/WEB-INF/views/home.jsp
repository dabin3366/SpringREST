<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"  contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Home</title>
	
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('input').click(function(){
			alert("클릭!");
			
			// ajax 사용해서 정보 전달 (RestController)
			var vo = { no :100, name:"ITWILL" };
			
			$.ajax({
				url:"/restInput",
				type:"POST",
				contentType:"application/json",
				//data: vo,
				data: JSON.stringify(vo),
				success:function(){
					alert(" 성공! ");
				},
				error:function(err){
					alert(" 실패! ");
					console.log(err);
				}
			});
			
			
			
			
		});
	});
</script>

	
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<hr>
	<input type="button" value="정보 전송(ajax)">



</body>
</html>
