<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Spring M1</title>
		<link rel="stylesheet" type="text/css" href="resources/css/commons/common.css">
		<link rel="stylesheet" type="text/css" href="resources/css/member/joinForm.css">
		<script src="resources/js/jquery-3.2.1.min.js"></script>
		<script src="resources/js/ajax.js"></script>
		<script src="resources/js/join.js"></script>
		<script>
			let iCheck = false, pCheck = false, p2Check = false,
				nCheck = false, phoneCheck = false;

			onload = function() {
				document.getElementById('password').addEventListener('focusout', () => {
					pCheck = pwRule();
				});

				document.getElementById('password2').addEventListener('focusout', () => {
					p2Check = checkPw();
				});

				document.getElementById('name').addEventListener('focusout', () => {
					nCheck = checkName();
				});

				document.getElementById('phone').addEventListener('focusout', () => {
					phoneCheck = checkPhone();
				})
			}


			function joinCheck() {
				if(iCheck && pCheck && p2Check && nCheck && phoneCheck) {
					return true;
				} else {
					alert('필수 입력사항 확인 후 재시도 바랍니다.');
					return false;
				}
			}

			// 업로드한 이미지 파일 미리보기
			function imageView(input) {
				if(input.files && input.files[0]) {
					let reader = new FileReader();

					reader.onload = function(e) {
						document.getElementById('upload_img').src = e.target.result;
					};

					reader.readAsDataURL(input.files[0]);
				}
			}
		</script>
	</head>
	
	<body>
		<div class="join_container">
			<h1 class="logo_box">
				<a href="/">My1</a>
			</h1>
		
			<form class="join_form" method="post" enctype="multipart/form-data">
				<div id="message">
					<c:if test="${ requestScope.message != null }">
						${ requestScope.message }
					</c:if>
				</div>

				<table>
					<tr>
						<td>*</td>
						<td id="id_input">
							<input type="text" id="id" name="id" placeholder="아이디를 입력하세요." required>
							<input type="button" id="check_btn" value="중복 확인" onclick="idCheck()">
						</td>
					</tr>
					
					<tr>
						<td>*</td>
						<td>
							<input type="password" id="password" name="password"
								   placeholder="비밀번호 생성(숫자, 특수문자 1개씩 무조건 포함)" required>
						</td>
					</tr>
					
					<tr>
						<td>*</td>
						<td>
							<input type="password" id="password2" placeholder="비밀번호 확인" required>
						</td>
					</tr>
					
					<tr>
						<td>*</td>
						<td>
							<input type="text" name="name" id="name" placeholder="성함" required>
						</td>
					</tr>
					
					<tr>
						<td>*</td>
						<td>
							<input type="text" name="phone" id="phone" placeholder="Phone Number('-' 없이)" required>
						</td>
					</tr>
					
					<tr>
						<td>내 사진<br>( 선택 )</td>
						<td>
							<label for="file_btn">upload</label>
							<input type="file" name="myimageF" id="file_btn" onchange="imageView(this)">
						</td>
						
						<td>
							<img alt="Image" src="" id="upload_img">
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							<input type="submit" value="가 입 하 기" onclick="return joinCheck()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>