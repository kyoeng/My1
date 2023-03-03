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
		<script src="resources/js/join.js"></script>
		<script>
			let pCheck = false, p2Check = false;

			onload = function() {
				document.getElementById('password').addEventListener('focusout', () => {
					pCheck = pwRule();
				});

				document.getElementById('password2').addEventListener('focusout', () => {
					p2Check = checkPw();
				});
			}


			function updateCheck() {
				if(pCheck && p2Check) {
					return true;
				} else {
					alert('수정할 비밀번호를 완성해주세요.');
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
		
			<form class="join_form" action="/update/my-info" method="post" enctype="multipart/form-data">
				<div id="message">
					<c:if test="${ requestScope.message != null }">
						${ requestScope.message }
					</c:if>
				</div>

				<table>
					<tr>
						<td>ID</td>
						<td id="id_input">
							<input type="text" id="id" name="id" value="${ requestScope.myInfo.id }" readonly>
						</td>
					</tr>
					
					<tr>
						<td>*</td>
						<td>
							<input type="password" id="password" name="password"
								   placeholder="수정할 비밀번호" required>
						</td>
					</tr>
					
					<tr>
						<td>*</td>
						<td>
							<input type="password" id="password2" placeholder="수정할 비밀번호 확인" required>
						</td>
					</tr>
					
					<tr>
						<td>Name</td>
						<td>
							<input type="text" name="name" id="name" value="${requestScope.myInfo.name}" readonly>
						</td>
					</tr>
					
					<tr>
						<td>Phone</td>
						<td>
							<input type="text" name="phone" id="phone" value="${requestScope.myInfo.phone}" readonly>
						</td>
					</tr>
					
					<tr>
						<td>내 사진<br>( 선택 )</td>
						<td>
							<label for="file_btn">upload</label>
							<input type="file" name="upload_imageF" id="file_btn" onchange="imageView(this)">
						</td>
						
						<td>
							<img alt="Image" src="" id="upload_img">
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							<input type="submit" value="수 정 하 기" onclick="return updateCheck()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>