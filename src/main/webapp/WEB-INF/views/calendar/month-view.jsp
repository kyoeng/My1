<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Spring M1</title>
        <link rel="stylesheet" type="text/css" href="resources/css/commons/common.css">
        <link rel="stylesheet" type="text/css" href="resources/css/commons/header.css">
        <link rel="stylesheet" type="text/css" href="resources/css/calendar/month-view.css">
    </head>

    <body>
        <jsp:include page="../headFoot/header.jsp"/>

        <div class="calendar_container">
            <%-- 날짜 네비게이션 --%>
            <div class="calendar_nav">
                <%-- 이전년도 --%>
                <a href="month-view?year=${ todayInfo.searchYear - 1 }&month=${ todayInfo.searchMonth }">&lt;&lt;</a>
                <%-- 이전 달 --%>
                <a href="month-view?year=${ todayInfo.beforeYear }&month=${ todayInfo.beforeMonth }">&lt;</a>

                <%-- 현재년월 --%>
                <span>
                    ${ todayInfo.searchYear } .

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
                                            <a href="" class="td_link"></a>
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
                                            <a href="" class="td_link"></a>
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
                                        <a href="" class="td_link"></a>
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
                                        <a href="" class="td_link"></a>
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
                                            <a href="" class="td_link"></a>
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
                                        <a href="" class="td_link"></a>
                                    </c:if>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
