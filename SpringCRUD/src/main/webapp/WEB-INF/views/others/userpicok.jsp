<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<script src="/spring/resources/js/jquery-1.12.4.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
<script>
	
	var filename = "${filename}";
	
	window.opener.$("#pic").val(filename);
	window.opener.editpic();	
	window.close();
	
</script>
</html>