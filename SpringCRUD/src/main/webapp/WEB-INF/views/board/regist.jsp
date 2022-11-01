<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>
	
	tr:first-child > td:first-child {
		width: 25%;
		padding: 10px;
	}
	#add-button {
		text-align: center;
		margin-top: 5px;
	}
	
	div.btn {
		background-color: #F0F0F0;
		padding: 3px 5px;
	}
	
	.failed {
		color: tomato;
	}
	
</style>

<section>

	<form method="POST" action="/spring/registok">

		<table class="table table-bordered userinfo">
		
			<tr>
				<td rowspan="6">
					<div id="userpic">
						<img src="./resources/profiles/pic.png" class="profile">
					</div>
					<div>※사진은 회원가입 후 변경가능</div>
				</td>
				<td>아이디</td>
				<td>
					<input type="text" name="id" id="id" placeholder="8-12자 영소문자+숫자"/>
					<div class="btn btn-sm" onclick="chkid();">중복확인</div>
					<span id="id-msg">※중복확인을 해주세요</span>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="pw" id="pw"/>
					<span id="pw-msg1"></span>
				</td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td>
					<input type="password" id="pw2"/>
					<span id="pw-msg2"></span>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name" id="name"/>
					<span id="name-msg"></span>				
				</td>
			</tr>
			<tr>
				<td>생일</td>
				<td><input type="date" name="birth" id="birth" /></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td>
					<input type="text" name="nick" id="nick" placeholder="2-8자 한글,영어,숫자 조합" />
					<div class="btn btn-sm" onclick="chknick();">중복확인</div>
					<span id="nick-msg">※중복확인을 해주세요</span>				
				</td>
			</tr>
		
		</table>
		
		<input type="hidden" name="pic" id="pic"/>
		
		<input type="button" value="회원가입" class="btn btn-success" onclick = "chkform();"/>
		<input type="button" value="돌아가기" class="btn btn-primary" onclick="location.href='/spring/board'"/>
		
	</form>
	
		<!-- 아이디, 닉네임 비밀번호 안맞으면 submit안되도록 -->
		<input type="hidden" id="chknick" />
		<input type="hidden" id="chkid" />
		<input type="hidden" id="chkname" />
		<input type="hidden" id="chkpw" />
		
</section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

	function addpic(){
		
		window.open('/spring/userpic','_blank','width=450, height=200');
	}
	
	function editpic(){
		
		var filename = $("#pic").val();
		console.log(filename);
		$("#userpic").html("<img src=\"./resources/profiles/"+filename+"\" class=\"profile\">");
	}
	
	
	function resetpic(){
		
		$("#userpic").html("<img src=\"./resources/profiles/pic.png\" class=\"profile\">");
		$("#pic").val("");
	}
	
 	
	function chkid(){
		
		var id = $("#id").val();
		
		$.ajax({
			type: 'POST',
			url: '/spring/register/chkid?',
			data: 'id='+id,
			dataType:'json',
			success: function(result){					
				let temp = "";
				
				if(result.result == 1) {			
					temp = '중복된 아이디입니다';
					$("#chkid").val("false");
		
				} else if (result.matches == 'false'){
					temp = '아이디 형식을 확인해주세요'	
					$("#chkid").val("false");
					
				} else {
					
					temp = '사용가능한 아이디입니다'
					$("#chkid").val("true");
				}
				
				$("#id-msg").text('');
				$("#id-msg").text(temp);
				

			},
			error: function(a, b, c){
				console.log(a,b,c);
			}
		}); 
	}		
		
		

	
	function chknick(){
		var nick = $("#nick").val();
		
		$.ajax({
			type: 'POST',
			url: '/spring/register/chknick?',
			data: 'nick='+nick,
			dataType:'json',
			success: function(result){					
				let temp = "";
				
				if(result.result == 1) {			
					temp = '중복된 닉네임입니다';
					$("#chknick").val("false");
				
				} else if (result.matches == 'false'){
					temp = '닉네임 형식을 확인해주세요'	
					$("#chknick").val("false");

				} else {
					
					temp = '사용가능한 닉네임입니다.'
					$("#chknick").val("true");
				}
				
				$("#nick-msg").text('');
				$("#nick-msg").text(temp);
				

			},
			error: function(a, b, c){
				console.log(a,b,c);
			}
		}); 
		
	}
	
	
	
	function chkform(){
		
		if($("#pw").val() == $("#pw2").val()){
			$("#chkpw").val("true");
		} else {
			$("#chkpw").val("false");
		}
		
		
		var id 	 = $("#chkid").val();
		var name = $("#chkname").val();
		var nick = $("#chknick").val();
		var pw	 = $("#chkpw").val();
		
		console.log(id, nick, name, pw);
		

		if(id == "true" 
			&& name == "true"
			&& nick == "true"
			&& pw == "true"){
			
			$("form").submit();
		} else {
			alert("입력정보를 다시 확인해주세요!");
		}
	}
	
	
	//------------------------------------------------------------------------------------
	// 이름, 비밀번호 형식 확인 
	
	$("#pw").change(function(){
		//하나 이상의 특수문자(@$!%*#?&), 숫자, 영소문자 조합, 8자이상 12자 미만
		var pwRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[@$!%*#?&])[a-z\d@$!%*#?&]{8,12}$/;
		var pw = $("#pw").val();
		
		if(!pwRegex.test(pw)){
			$("#pw-msg1").text("비밀번호 형식을 확인하세요");
			$("#chkpw").val("false");
		}else {
			$("#pw-msg1").text("");
			$("#chkpw").val("true");
		}
	});
	
	$("#pw2").change(function(){
		var pw1 = $("#pw").val();
		var pw2 = $("#pw2").val();
		
		if(pw1 === pw2 ){
			$("#pw-msg2").text("");
			
		} else {
			$("#chkpw").val("flase");
			$("#pw-msg2").text("비밀번호가 틀립니다.");
		}
	});
	
	$("#name").change(function(){
		var regex = /^[가-힝]{2,5}$/;
		var name = $("#name").val();
		
		if(!regex.test(name)){
			$("#name-msg").text("이름형식을 확인해주세요");
			$("#chkname").val("false");
		}else {
			$("#name-msg").text("");
			$("#chkname").val("true");
		}
	});
	
	
	//아이디, 닉네임 바꿀경우 
	$("#id").change(function(){
		$("#chkid").val("false");
		$("#id-msg").text('※중복확인을 해주세요');
	});
 	
	$("#nick").change(function(){
		$("#chknick").val("false");
		$("#nick-msg").text('※중복확인을 해주세요');
	});
	
</script>