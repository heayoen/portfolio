<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- add.jsp -->
<style>
	.table th { width: 150px; }
	
	table tr td #txtarea {
		height: 400px;
		overflow: auto;
	}
	
</style>

<section>
	
	<c:if test="${not empty dto.contentseq}">
		<form method="POST"  action="/spring/editok">
	</c:if>
	
	<c:if test="${empty dto.contentseq}">
		<form method="POST"  action="/spring/addok">
	</c:if>
		
			<table class="table table-bordered">
				<tr>
					<th>제목</th>
					<td><input type="text" name="subject" id="subject" class="form-control" value="${dto.subject }" required></td>
					<td>
						<select name="categoryseq" class="form-control">
								<option value="1">잡담</option>
								<option value="2">질문</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="2">
						<div id="txtarea" contentEditable="true">${dto.text }</div>
						
					</td>
				</tr>
				<tr>
					<th>사진첨부</th>
					<td id="picname"></td>
					<td>
						<label for="pic">
						<div class="btn btn-primary btn-sm" id="addpic"><i class="fa-solid fa-image"></i></div>
						</label>
					</td>
				</tr>
			</table>
			
			<div>
				<input type="button" value="돌아가기" class="btn btn-secodary"
					onclick="history.back();">
				<input type="button" value="등록" class="btn btn-primary" onclick="add();"/>
			</div>
			
			<input type="file" name="pic" id="pic" hidden style="display:none;"/>
			<input type="hidden" name="contentseq" value="${dto.contentseq }" />
			<textarea rows="" cols="" name="text" hidden></textarea>
		
		</form>
		

</section>





<script>
	
	
	function add(){
		let text = $('#txtarea').html();
		$('textarea').val(text);
		
		$('form').submit();
		
	}
	
	//json으로 업로드 해야하는데 ... 
	function addpic(){
		
		let text = $('#txtarea').html();
		
		let fileValue = $('#pic').val().split("\\");
		let filename = fileValue[fileValue.length-1]; // 파일명
		
		
		$('#picname').html(filename);
		
		
		let formdata = new FormData(); //업로드 파일 저장 객체
	
		let pic = $('#pic')[0].files;
		
		formdata.append("pic", pic);
		
		$.ajax({
			
			url : "/spring/addpic",
            type : "POST",
            enctype:'multipart/form-data',
            processData : false ,
            contentType : false ,
            data : formdata ,
            success : function (response) {
            	alert('전송성공');
            },
			error: function(a,b,c){
				console.log(a,b,c);
			}
			
		});
		

	}

	

</script>