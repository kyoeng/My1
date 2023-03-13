<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Spring M1</title>
    <link rel="stylesheet" type="text/css" href="resources/css/commons/common.css">
    <link rel="stylesheet" type="text/css" href="resources/css/commons/header.css">
    <link rel="stylesheet" type="text/css" href="resources/css/index.css">
    <script src="resources/js/jquery-3.2.1.min.js"></script>
    <script defer src="resources/js/header.js"></script>
  </head>

  <body>
    <header>
      <div class="header_container">
        <h1 class="title">My1</h1>

        <div class="header_nav">
          <a href="/" class="hd_nav home_btn selected">
            오늘의 일정
          </a>

          <a href="month-view" class="hd_nav monthView_btn">
            캘린더
          </a>

          <a href="board" class="hd_nav board_btn">
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


    <div class="index_container">
      <h2 class="index_date">
        ${requestScope.today}
      </h2>

      <div class="todo_container">
        <div class="todo_dis">
          <h3 class="todo_title">
            나의 일정
          </h3>

          <div class="todo_box">
            <c:forEach var="dispo" items="${requestScope.dispo}">
              <div class="todo_content">
                <span class="todo_text">${dispo.todo_content}</span>
                <button onclick="delTodo(${dispo.seq}, null)">X</button>
              </div>
            </c:forEach>
          </div>
        </div>

        <div class="todo_eve">
          <h3 class="todo_title">
            매년 예약된 나의 일정
          </h3>

          <div class="todo_box">
            <c:forEach var="eve" items="${requestScope.every}">
              <div class="todo_content">
                <span class="todo_text eve">${eve.todo_content}</span>
                <button onclick="delTodo(${eve.seq}, 'eve')">X</button>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>

      <button class="input_btn" onclick="onPopUp()">+</button>

      <c:if test="${requestScope.message != null}">
        <p class="message">${requestScope.message}</p>
      </c:if>
    </div>

    <%-- 일정 등록 팝업 --%>
    <div class="popup_container" id="input_pop">
      <form class="popup_box" action="/reg_todo" method="post">
        <h3 class="popup_date">
          ${requestScope.today}
        </h3>

        <input type="hidden" name="todo_date" value="${requestScope.today}" >

        <div style="width: 400px; height: 120px; margin: 0 auto;">
          <p style="font-size: 1.4rem; height: 20px; line-height: 20px">일정 내용</p>
          <textarea id="content_box" name="todo_content" maxlength="50"></textarea>
        </div>

        <label>
          <input type="checkbox" value="eve" name="check_eve">
          해당 날짜 반복
        </label>

        <button class="add_btn">일정 추가</button>

        <div onclick="offPopUp()" class="close_btn">X</div>
      </form>
    </div>

    <script>
      function onPopUp() {
        $('#input_pop').show();
      }

      function offPopUp() {
        $('#content_box').val('');
        $('#eve_check').prop('checked', false);
        $('#input_pop').hide();
      }

      function delTodo(seq, check) {
        if (confirm('일정을 삭제하시겠습니까?')) {
          $.ajax({
            type: 'post',
            url: 'del-todo',
            data: {
              seq: seq,
              check: check
            },
            success: () => {
              alert('삭제가 완료되었습니다.');
              location.reload();
            },
            error: () => {
              alert('삭제에 실패하였습니다.');
            }
          });
        }
      }
    </script>
  </body>
</html>
