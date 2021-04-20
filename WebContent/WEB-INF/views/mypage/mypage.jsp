<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko" class="no-js">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지(4월)</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/Mypage(April).css">
    <script src="<%= request.getContextPath()%>/js/Mypage.js"></script>
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <script>
        // 움직이는 효과   toggleclass  이벤트효과 
        $(document).ready(function () {
            $(".slide-menu").click(function () {
                $(".wrapper").toggleClass("collapse");
            });
        });
    </script>
    <!-- 깜빡임 현상 줄이기 -->
    <script>
        $(function () {

            $('html').removeClass('no-js');

        });
    </script>
	<%@ include file ="/WEB-INF/views/common/header.jsp" %>
        <div class="Container-bar">
            <ul>
                <li class="Container-bar-li-left" style="font-size: 30px;"><a href="#"><span
                            class="title">Calendar</span></a>
                </li>

            </ul>
        </div>
    </div>
    <!--Calendar_wrap start-->
    <div class="calendar-wrap">
        <h2 class="month-year">2021 <span style="color: crimson;">4월</span> April
        </h2>
        <table class="calendar">
            <thead>
                <tr>
                    <th class="day-title" scope="col">Sun</th>
                    <th class="day-title" scope="col">Mon</th>
                    <th class="day-title" scope="col">Tue</th>
                    <th class="day-title" scope="col">Wed</th>
                    <th class="day-title" scope="col">Thu</th>
                    <th class="day-title" scope="col">Fri</th>
                    <th class="day-title" scope="col">Sat</th>
                </tr>
            </thead>
            <tbody>
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">31</span></td>
                    <td class="day" tabindex="0"><span class="day-img"><img src="image/캘린더(뱃지)/환경의-민족.png" alt=""
                                style="border: 0; cursor: pointer;"
                                onclick="window.open('Popup(April).html','new','width=1000, height=600,  resizable=no')"
                                ;></span>
                    </td>
                    <td class=" day " tabindex=" 0"><span class="day-number">1</span></td>
                    <td class="day " tabindex="0"><span class="day-number">2</span></td>
                    <td class="day " tabindex="0"><span class="day-number">3</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">4</span></td>
                    <td class="day" tabindex="0"><span class="day-number">5</span></td>
                    <td class="day" tabindex="0"><span class="day-number">6</span></td>
                    <td class="day" tabindex="0"><span class="day-number">7</span></td>
                    <td class="day" tabindex="0"><span class="day-number">8</span></td>
                    <td class="day" tabindex="0"><span class="day-number">9</span></td>
                    <td class="day" tabindex="0"><span class="day-number">10</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number">17</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number">24</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">25</span></td>
                    <td class="day" tabindex="0"><span class="day-number">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number">27</span></td>
                    <td class="day" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number">39</span></td>
                    <td class="day" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">1</span></td>
                </tr>
            </tbody>
        </table>
        <!--이전 버튼  fixed -->
        <a class="Previous btn" href="Mypage(March).html"><i class="fas fa-chevron-circle-left"
                style="font-size: 28px; font-weight: bold;"></i> </a>
        <!--이전 버튼  fixed -->
        <a class="next btn1" href="Mypage(May).html"><i class="fas fa-chevron-circle-right"
                style="font-size: 28px ; font-weight: bolder;"></i></a>

    </div>
    <!--Calendar_wrap end-->
    <!-- Member Change,Delete -->



    <div class="Member-li">
        <ul>
            <li class="Container-bar-li-left" style="font-size: 30px;"><a href="<%= request.getContextPath()%>/mypage/memberView"><span
                        class="title">회원정보
                        보기</span></a>
            </li>
        </ul>
    </div>
    <div class="Member-change">
        <ul>
            <li class="Container-bar-li-left" style="font-size: 30px;"><a href="Member Modify.html"><span
                        class="title">회원정보
                        수정</span></a>
            </li>
        </ul>
    </div>


    </div>

</body>
</html>