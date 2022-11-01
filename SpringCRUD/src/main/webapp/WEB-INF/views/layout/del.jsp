<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>  \
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<link rel="stylesheet" href="/spring/resources/css/bootstrap.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	div {
		margin: 100px auto;
		padding: 20px;
		border: 1px solid black;
		border-radius: 20px;
		text-align: center;
		width: 500px;
		height: 250px;
	}
	
	table {
		width : 80%;
		margin: 10px auto;
	}
	
	td { padding: 5px;}
	
	a {
		font-size: 0.9em;
		margin: 0 10px;
	}
	
</style>
</head>
<body>

	<div>
		<h3>정말로 삭제하시겠습니까?</h3>
		
		<table>
			<tr>
				<td>글제목 :</td>
				<td>${dto.subject }</td>
			</tr>

		</table>
		
		
		<a href="/spring/delok?contentseq=${dto.contentseq}">삭제하기</a>
		<a href="/spring/view?contentseq=${dto.contentseq}">돌아가기</a>
	</div>

</body>

<script>

</script>

</html>