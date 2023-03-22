<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Spring M1</title>
        <link rel="stylesheet" type="text/css" href="resources/css/commons/common.css">
        <link rel="stylesheet" type="text/css" href="resources/css/commons/header.css">
        <link rel="stylesheet" type="text/css" href="resources/css/board/board-detail.css">
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
            <p class="board_title">
                ${board.title}
            </p>

            <div class="writer_info_box">
                <span class="info">작성자</span>
                <span class="info_answer">${board.id}</span>
            </div>

            <div class="writer_info_box">
                <span class="info">등록일</span>
                <span class="info_answer">${board.reg_date}</span>
            </div>

            <p class="board_content">
                ${board.content}
            </p>

            <div class="comment_container">
                <p class="comment_h">댓글</p>

                <div class="comment_area">
                    <c:forEach var="cmt" items="${commnet}">
                        <div class="comment_box">
                            <span class="comment_id">${cmt.id}</span>
                            <span class="comment_text">${cmt.comments}</span>
                        </div>
                    </c:forEach>
                </div>

                <div class="write_comment">
                    <p>댓글 작성</p>
                    <input type="hidden" value="${sessionScope.loginID}" id="login_id">
                    <input type="text" id="comment_value" placeholder="댓글을 작성해보세요.">
                    <span class="comment_btn" onclick="regCmt(${board.seq})">등록</span>
                </div>
            </div>

            <c:if test="${board.id == sessionScope.loginID}">
                <span class="change_btn" onclick="onPopUp()">수정하기</span>
            </c:if>
        </main>

        <div id="write_container">
            <form class="write_box" action="reg-board" method="post">
                <p>글 수정하기</p>

                <input type="hidden" name="seq" value="${board.seq}">

                <input type="text" name="title" id="title" value="${board.title}" required>

                <div>
                    <span>작성자</span>
                    <input type="text" name="id" value="${board.id}" readonly>
                </div>

                <textarea name="content" id="content" required>${board.content}</textarea>

                <input type="submit" value="수정하기" id="write_btn">

                <span id="close_btn" onclick="offPopUp()">X</span>
            </form>
        </div>
    </body>

    <script>
        function onPopUp() {
            $('#write_container').show();
        }

        function offPopUp() {
            $('#write_container').hide();
        }

        function regCmt(seq) {
            $.ajax({
                type: 'post',
                url: 'reg-comment',
                data: {
                    board_seq: seq,
                    comments: $('#comment_value').val(),
                    id: $('#login_id').val()
                },
                success: (res) => {
                    if (res.code === '200') {
                        location.reload();
                    } else {
                        alert('fail');
                    }
                },
                error: () => {
                    alert("error");
                }
            });
        }
    </script>
</html>
