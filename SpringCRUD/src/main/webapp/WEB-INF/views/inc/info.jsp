<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
	  
	<c:if test="${not empty auth}">
	  <div id="info">
	  	<a href="/spring/logout">로그아웃</a>
	  	<a href="/spring/userinfo">회원정보</a>
	  	<a href="/spring/mylist">작성글관리</a>
	  </div>
	</c:if>  
	
	
	<c:if test="${empty auth}">
	  <div id="info">
	  	<a href="/spring/login">로그인</a>
	  	<a href="/spring/register">회원가입</a>
	  </div>
	</c:if>	