<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Spring M1</title>
		<link rel="stylesheet" type="text/css" href="resources/css/commons/common.css">
		<link rel="stylesheet" type="text/css" href="resources/css/member/login.css">
	</head>
	
	<body>
		<div class="login_container">
			<h1 class="logo_box">
				<span>My1</span>
			</h1>
		
			<form action="login" method="post" class="login_box">
				<table>
					<tr>
						<td colspan="2"><input type="text" name="id" placeholder="아이디를 입력하세요."></td>
					</tr>
					
					<tr>
						<td colspan="2"><input type="password" name="password" placeholder="비밀번호를 입력하세요."></td>
					</tr>
					
					<tr>
						<th colspan="2"><input type="submit" value="로 그 인"></th>
					</tr>
				</table>
			</form>
			
			<div class="link_box">
				<div class="join_box">
					<span>아직 계정이 없으신가요?</span>
					<a href="join">가입하기</a>			
				</div>
				
				<div class="find_box">
					<span>기억이 나지 않으신가요?</span>
					<a href="find">찾아보기</a>
				</div>
			</div>


			<c:if test="${ requestScope.message != null }">
				<div class="message">
					${ requestScope.message }
				</div>
			</c:if>
		</div>
	</body>
</html>