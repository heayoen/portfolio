<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- view.jsp -->
<style>
	.table th { width: 150px; }
	textarea[name=content] { height: 200px; resize: none; }
</style>

<section>

	<table class="table table-bordered">
		<tr>
			<th>번호</th>
			<td>${dto.contentseq}</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>${dto.nick}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.subject}</td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td>${dto.type}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${dto.text}</td>
		</tr>
		<tr>
			<th>날짜</th>
			<td>${dto.regdate}</td>
		</tr>
	</table>
	
	<div>
		<input type="button" value="돌아가기" class="btn btn-primary"
			onclick="location.href='/spring/board';">
		

		
		<c:if test="${auth == dto.userseq }">
			
			<input type="button" value="수정" class="btn btn-success"
				onclick="location.href='/spring/edit?contentseq=${dto.contentseq}';">
			
			<input type="button" value="삭제" class="btn btn-secodary"
				onclick="location.href='/spring/del?contentseq=${dto.contentseq}';">
				
		</c:if>
	</div>
	

</section>









