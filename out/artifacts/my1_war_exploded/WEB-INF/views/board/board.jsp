<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Spring M1</title>
        <link rel="stylesheet" type="text/css" href="resources/css/commons/common.css">
        <link rel="stylesheet" type="text/css" href="resources/css/commons/header.css">
        <script defer src="resources/js/header.js"></script>
    </head>

    <body>
        <header>
            <div class="header_container">
                <h1 class="title">My1</h1>

                <div class="header_nav">
                    <a href="home" class="hd_nav home_btn">
                        오늘의 일정
                    </a>

                    <a href="month-view" class="hd_nav monthView_btn">
                        캘린더
                    </a>

                    <a href="board" class="hd_nav board_btn selected">
                        게시판
                    </a>
                </div>

                <div class="myPage_btn">
                    <img alt="my_img" src="${ sessionScope.myImg }" width="40px" height="40px">
                    <span class="my_btn">▼</span>

                    <ul class="my_nav">
                        <li>
                            <a href="">내 정보</a>
                        </li>

                        <li>
                            <a href="">로그아웃</a>
                        </li>
                    </ul>
                </div>
            </div>
        </header>


        <main>
        </main>
    </body>
</html>
