<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Spring M1</title>
        <link rel="stylesheet" type="text/css" href="resources/css/commons/common.css">
        <link rel="stylesheet" type="text/css" href="resources/css/commons/header.css">
        <link rel="stylesheet" type="text/css" href="resources/css/calendar/month-view.css">
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

                    <a href="month-view" class="hd_nav monthView_btn selected">
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

        <div class="calendar_container">
            <%-- 날짜 네비게이션 --%>
            <div class="calendar_nav">
                <%-- 이전년도 --%>
                <a href="month-view?year=${ todayInfo.searchYear - 1 }&month=${ todayInfo.searchMonth }">&lt;&lt;</a>
                <%-- 이전 달 --%>
                <a href="month-view?year=${ todayInfo.beforeYear }&month=${ todayInfo.beforeMonth }">&lt;</a>

                <%-- 현재년월 --%>
                <span id="yyyymm">
                    ${ todayInfo.searchYear }
                    -
                    <c:if test="${ todayInfo.searchMonth < 9 }">0</c:if>${ todayInfo.searchMonth + 1 }
                </span>

                <%-- 다음 달 --%>
                <a href="month-view?year=${ todayInfo.afterYear }&month=${ todayInfo.afterMonth }">&gt;</a>
                <%-- 다음년도 --%>
                <a href="month-view?year=${ todayInfo.searchYear + 1 }&month=${ todayInfo.searchMonth }">&gt;&gt;</a>
            </div>

            <table class="calendar_box" style="border-collapse: collapse;">
                <thead>
                    <tr>
                        <td class="sun_day">일</td>
                        <td>월</td>
                        <td>화</td>
                        <td>수</td>
                        <td>목</td>
                        <td>금</td>
                        <td class="sat_day">토</td>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="dateList" items="${ requestScope.dateList }" varStatus="date_status">
                        <c:choose>
                            <%-- 캘린더 첫번째는 무조건 일요일부터 시작 --%>
                            <c:when test="${ date_status.index == 0 }">
                                <tr>
                                    <td class="sun_day td_box">
                                        <div class="date sun">
                                                ${ dateList.date }
                                        </div>

                                        <div class="schedule">

                                        </div>

                                        <c:if test="${ dateList.date != null && dateList.date != '' }">
                                            <button onclick="onPopUp(${ dateList.date })" class="td_btn"></button>
                                        </c:if>
                                    </td>
                            </c:when>

                            <%-- varStatus의 index가 나머지 7 했을 때 0이면 토요일 줄 바꿔주기 ( 오늘 날짜면 클래스 추가 ) --%>
                            <c:when test="${ date_status.index % 7 == 0 && dateList.value == 'today'}">
                                </tr>
                                <tr>
                                    <td class="sun_day td_box today">
                                        <div class="date sun">
                                            ${ dateList.date }
                                        </div>

                                        <div class="schedule">

                                        </div>

                                        <c:if test="${ dateList.date != null && dateList.date != ''}">
                                            <button onclick="onPopUp(${ dateList.date })" class="td_btn"></button>
                                        </c:if>
                                    </td>
                            </c:when>

                            <%-- 오늘 날짜인 경우 --%>
                            <c:when test="${ dateList.value == 'today' }">
                                <td class="today td_box">
                                    <div class="date today">
                                        ${ dateList.date }
                                    </div>

                                    <div class="schedule">

                                    </div>

                                    <c:if test="${ dateList.date != null && dateList.date != '' }">
                                        <button onclick="onPopUp(${ dateList.date })" class="td_btn"></button>
                                    </c:if>
                                </td>
                            </c:when>

                            <%-- 토요일인 경우 --%>
                            <c:when test="${ date_status.index % 7 == 6 }">
                                <td class="sat_day td_box">
                                    <div class="date sat">
                                        ${ dateList.date }
                                    </div>

                                    <div class="schedule">

                                    </div>

                                    <c:if test="${ dateList.date != null && dateList.date != '' }">
                                        <button onclick="onPopUp(${ dateList.date })" class="td_btn"></button>
                                    </c:if>
                                </td>
                            </c:when>

                            <%-- 일요일인 경우 --%>
                            <c:when test="${ date_status.index % 7 == 0}">
                                </tr>
                                <tr>
                                    <td class="sun_day td_box">
                                        <div class="date sun">
                                            ${ dateList.date }
                                        </div>

                                        <div class="schedule">

                                        </div>

                                        <c:if test="${ dateList.date != null && dateList.date != '' }">
                                            <button onclick="onPopUp(${ dateList.date })" class="td_btn"></button>
                                        </c:if>
                                    </td>
                            </c:when>

                            <%-- 평일인 경우 --%>
                            <c:otherwise>
                                <td class="normal_day td_box">
                                    <div class="date">
                                        ${ dateList.date }
                                    </div>

                                    <div class="schedule">

                                    </div>

                                    <c:if test="${ dateList.date != null && dateList.date != '' }">
                                        <button onclick="onPopUp(${ dateList.date })" class="td_btn"></button>
                                    </c:if>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="popup_container">
            <div class="popup_box">
                <h3 class="popup_date"></h3>

                <div style="width: 400px; height: 120px; margin: 0 auto;">
                    <p style="font-size: 1.4rem; height: 20px; line-height: 20px">일정 내용</p>
                    <textarea id="content_box" maxlength="50"></textarea>
                </div>

                <label>
                    <input type="checkbox" value="eve" id="eve_check">
                    해당 날짜 반복
                </label>

                <button class="add_btn">일정 추가</button>

                <button onclick="offPopUp()" class="close_btn">X</button>
            </div>
        </div>

        <script>
            const yyyy = ${todayInfo.searchYear};

            let mmTemp = ${todayInfo.searchMonth + 1};
            if (mmTemp < 9) mmTemp = '0' + mmTemp;

            const mm = mmTemp;

            function onPopUp(d) {
                if (d < 10) d = '0' + d;
                let date = yyyy + '-' + mm + '-' + d;

                $('.popup_date').text(date);
                $('.popup_container').show();
            }

            function offPopUp() {
                $('#content_box').val('');
                $('#eve_check').prop('checked', false);
                $('.popup_container').hide();
            }
        </script>
    </body>
</html>
