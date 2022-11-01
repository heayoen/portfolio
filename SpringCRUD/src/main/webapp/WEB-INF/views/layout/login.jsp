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
		<h3>로그인</h3>
		<form method="POST" action="/spring/loginok">
			<table >
				<tr>
					<td>
						<input type="text"  name="id" class="form form-control"  placeholder="id"/>
					</td>
					<td rowspan="2">
						<input type="submit" value="로그인" class="btn btn-primary btn-sm" />
						<input type="button" value="돌아가기" class="btn btn-secondary btn-sm" onclick="location.href='/spring/board';" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="password"  name="pw" class="form form-control" placeholder="password"/>
					</td>
				</tr>
			</table>
					
		</form>
		<c:if test="${result == 'failed' }">
			<span style="color: red;">일치하는 정보가 없습니다. 다시 시도해주세요</span>
		</c:if>
		<br />
		<a href="/spring/register">회원가입</a>
		<a href="/spring/find">아이디/비밀번호 찾기</a>
	</div>

</body>

<script>

</script>

</html>