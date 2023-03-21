<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Spring M1</title>
        <link rel="stylesheet" type="text/css" href="resources/css/commons/common.css">
        <link rel="stylesheet" type="text/css" href="resources/css/commons/header.css">
        <link rel="stylesheet" type="text/css" href="resources/css/board/board.css">
        <script src="resources/js/jquery-3.2.1.min.js"></script>
        <script defer src="resources/js/header.js"></script>
    </head>

    <body>
        <header>
            <div class="header_container">
                <h1 class="title">My1</h1>

                <div class="header_nav">
                    <a href="/" class="hd_nav home_btn">
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
                            <a href="my-info">내 정보</a>
                        </li>

                        <li>
                            <a href="logout">로그아웃</a>
                        </li>
                    </ul>
                </div>
            </div>
        </header>


        <main>
            <span id="write_board" onclick="onPopUp()">
                글 작성
            </span>

            <span id="message">
                <c:if test="${requestScope.message != null}">
                    ${requestScope.message}
                </c:if>
            </span>

            <form action="board" method="get">
                <select name="check" id="check_select">
                    <option value="id">아이디</option>
                    <option value="title">제목</option>
                </select>

                <input type="text" name="keyword">

                <button id="submit_btn">검색</button>
            </form>


            <div class="board_line" style="margin-top: 20px">
                <span class="b_seq">글번호</span>
                <span class="b_title">글제목</span>
                <span class="b_id">작성자</span>
                <span class="b_date">작성일</span>
            </div>

            <c:forEach var="board" items="${requestScope.boardList}">
                <a href="" class="board_line board_click">
                    <span class="b_seq">${board.seq}</span>
                    <span class="b_title">${board.title}</span>
                    <span class="b_id">${board.id}</span>
                    <span class="b_date">${board.reg_date}</span>
                </a>
            </c:forEach>
        </main>

        <div id="write_container">
            <form class="write_box" action="reg-board" method="post">
                <p>글 작성하기</p>

                <input type="text" name="title" id="title" placeholder="제목을 입력하세요" required>

                <div>
                    <span>작성자</span>
                    <input type="text" name="id" value="${requestScope.id}" readonly>
                </div>

                <textarea name="content" id="content" placeholder="내용을 입력하세요" required></textarea>

                <input type="submit" value="글 등록" id="write_btn">

                <span id="close_btn" onclick="offPopUp()">X</span>
            </form>

        </div>
    </body>

    <script>
        function onPopUp() {
            $('#write_container').show();
        }

        function offPopUp() {
            $('#title').val('');
            $('#content').val('');
            $('#write_container').hide();
        }
    </script>
</html>
