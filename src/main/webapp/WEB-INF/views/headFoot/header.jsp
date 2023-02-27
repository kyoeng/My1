<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<header>
	<div class="header_container">
		<a href="home" class="home_btn">
			<img alt="logo" src="resources/images/home.png" width="40px" height="40px" class="home_img">
			<span>오늘 일정</span>
		</a>

		<a href="month-view" class="monthView_btn">
			<img alt="cal_img" src="resources/images/calendar.png" width="40px" height="40px">
			<span>캘린더</span>
		</a>

		<a href="board" class="board_btn">
			<img alt="board_img" src="resources/images/board.png" width="40px" height="40px">
			<span>게시판</span>
		</a>

		<a href="my-page" class="myPage_btn">
			<img alt="my_img" src="${ sessionScope.myImg }" width="40px" height="40px">
			<span>마이페이지</span>
		</a>

		<a href="info" class="info_btn">
			<img alt="info_img" src="resources/images/information.png" width="40px" height="40px">
			<span>Info</span>
		</a>
	</div>
</header>


