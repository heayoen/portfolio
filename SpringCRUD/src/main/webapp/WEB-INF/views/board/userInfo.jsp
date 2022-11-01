<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>
	
	table {height: 200px}

	#userpic {
		width: 25%;
		padding: 10px;
	}
	
	img {
		width: 200px;
	}
	
	
	.profile {
		height: 80%;
	}
	
</style>

<section>

	<table class="table table-bordered">
	
		<tr>
			<td id="userpic" rowspan="5">
				<c:if test="${empty udto.pic}"><img src="./resources/profiles/pic.png" class="profile"></c:if>
				<c:if test="${not empty udto.pic}"><img src="./resources/profiles/${udto.pic}" class="profile"></c:if>	
			</td>
			<td>아이디</td>
			<td>${udto.id}</td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td>${udto.nick}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${udto.name}</td>
		</tr>
		<tr>
			<td>생일</td>
			<td>${udto.birth}</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>${udto.regdate}</td>
		</tr>
	
	</table>
	
	<input type="button" value="수정하기" class="btn btn-success" onclick="location.href='/spring/userinfo/edit'"/>
	<input type="button" value="돌아가기" class="btn btn-primary" onclick="location.href='/spring/board'"/>
		
</section>


<script>




</script>