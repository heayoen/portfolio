<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- list.jsp -->
<section>
	
	<table class="table table-bordered">
		<tr>
			<th>번호</th>
			<th>카테고리</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
		</tr>
		<c:forEach items="${list}" var="bdto" >
		<tr>
			<td>${bdto.contentseq}</td>
			<td>${bdto.type }</td>
			<td><a href="/spring/view?contentseq=${bdto.contentseq}">${bdto.subject }</a></td>
			<td>${bdto.nick }</td>
			<td>${bdto.regdate }</td>
		</tr>
		</c:forEach>
	</table>
	
	<c:if test="${not empty auth}">
	<div style="text-align: right;">
		<input type="button" value="쓰기" class="btn btn-primary"
			onclick="location.href='/spring/add';">
	</div>
	</c:if>
	
</section>















