<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>
	
	tr:first-child > :first-child {
		width: 25%;
		padding: 10px;
	}
	#add-button {
		text-align: center;
		margin-top: 5px;
	}
	
</style>

<section>

	<form method="POST" action="/spring/userinfo/editok">

		<table class="table table-bordered userinfo">
		
			<tr>
				<td rowspan="5">
					<div id="userpic">
						<c:if test="${empty udto.pic}"><img src="../resources/profiles/pic.png" class="profile"></c:if>
						<c:if test="${not empty udto.pic}"><img src="../resources/profiles/${udto.pic}" class="profile"></c:if>
					</div>
					<div id="add-button">
						<input type="button" class="btn btn-sm btn-primary" value="사진 수정" onclick="addpic();" />
						<input type="button" class="btn btn-sm" value="기본이미지" onclick="resetpic();" />
					</div>
				</td>
				<td>아이디</td>
				<td>
					${udto.id }
				</td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" name="nick" id="nick" value="${udto.nick }"/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="name" value="${udto.name }"/></td>
			</tr>
			<tr>
				<td>생일</td>
				<td><input type="text" name="birth" id="birth" value="${udto.birth}"/></td>
			</tr>
			<tr>
				<td>등록일</td>
				<td>${udto.regdate}</td>
			</tr>
		
		</table>
		
		<input type="hidden" name="pic" id="pic"/>
		
		<input type="submit" value="수정완료" class="btn btn-success" />
		<input type="button" value="돌아가기" class="btn btn-primary" onclick="/spring/userinfo/reset"/>
		
	</form>

		
</section>


<script>

	function addpic(){
		
		window.open('/spring/userpic','_blank','width=450, height=200');
	}
	
	function editpic(){
		
		var filename = $("#pic").val();
		console.log(filename);
		$("#userpic").html("<img src=\"../resources/profiles/"+filename+"\" class=\"profile\">");
	}
	
	
	function resetpic(){
		
		$("#userpic").html("<img src=\"../resources/profiles/pic.png\" class=\"profile\">");
		$("#pic").val("");
	}
	
</script>