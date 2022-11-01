<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<link rel="stylesheet" href="/spring/resources/css/bootstrap.css">
<script src="/spring/resources/js/jquery-1.12.4.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 사진 첨부</title>
<style>

	.table {
		width : 90%;
		margin: 30px auto;
	}
	
	table td:first-child {
		width : 30%;
	}
	
	.buttons {
		margin-top: 20px;
		text-align: center;
	}
	
	h2 {
		margin-left: 20px;
		margin-top: 30px;
	}
	
	div.btn {
		margin-left: 5px;
		margin-top: 4px;
	}
	
	#picname { margin-top : 10px;}
	.error { 
		color: tomato;
		font-size: 0.85em;
	}
	
</style>
</head>
<body>

	<h2>사진 첨부</h2>
		
	<form method = "POST" action="/spring/adduserpic" enctype="multipart/form-data">
		<table class="table table-bordered">
			<tr>
				<td>
					<label for="pic">
						<div class="btn btn-primary">사진 선택</div>
					</label>
				</td>
				<td><div id="picname">프로필 사진 등록</div></td>
			</tr>
		</table>
		
		<input type="file" name="pic" id="pic" hidden style="display:none;"/>
		
		<div class="buttons">
			<input type="button" value="사진 수정" class="btn btn-success btn-sm" id="sub"/>
			<input type="button" value="취소" class="btn btn-sm" onclick="window.close();"/>
		</div>
	</form>
	
	<input type="hidden" id="chkform"/>
		
</body>

<script>

	// 사진 파일만 저장 
	
	$("#pic").change(function(){
		
		var fileValue = $('#pic').val().split("\\");
		var name = fileValue[fileValue.length-1]; // 파일명
		
		// jpg, jpeg, png만 가능하도록 
		var filetype = name.substr(name.lastIndexOf('.'));
		if(filetype == ".jpg"
				|| filetype == ".jpeg"
				|| filetype == ".png") {
			
			$("#picname").html(name);
			$("#chkform").val("true");
			
		} else {
			$("#picname").html('<i class = "error">사진 형식의 파일만 업로드 가능합니다.</i>');
			$("#chkform").val("false");
			
		}
		
	});
	
	$("#sub").click(function(){
		if($("#chkform").val()=="true"){
			alert("확인용");
			$("form").submit();
		}else {
			alert("파일형식을 확인하세요");
		}
	});	

</script>
</html>