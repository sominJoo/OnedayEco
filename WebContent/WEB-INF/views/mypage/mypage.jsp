<%@page import="mypage.model.vo.MypagePoint"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mypage.model.vo.MypageBadge"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Calendar now = Calendar.getInstance();
	int month = request.getParameter("month") != null ? Integer.valueOf(request.getParameter("month")) :  now.get(Calendar.MONTH)+1;
	String[] month_arr ={"January" , "February", "March", "April", "May", "June", "July", "August", "September","October","November", "December"};

	//뱃지 
	List<MypageBadge> badgeList = (List<MypageBadge>)request.getAttribute("badgeList");
	List<MypagePoint> pointList = (List<MypagePoint>)request.getAttribute("pointList");
	List<MypagePoint> tPointList = (List<MypagePoint>)request.getAttribute("tPointList");
	for(MypageBadge m :badgeList){
		System.out.println(m);
	}
	
%>
<!DOCTYPE html>
<html lang="ko" class="no-js">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/Mypage(April).css">
    <script src="<%= request.getContextPath()%>/js/Calendar.js"></script>
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">

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
    
    
    <!-- 4월 Calendar_wrap start-->
    <div class="calendar-wrap april">
    	<h2 class="month-year">2021 <span style="color: crimson;" id="month_span"><%= month %></span>  <span id="month-eng"><%= month_arr[month-1] %></span></h2>
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
            
    <% switch(month){
    case 1:%>    
    		
           <tbody id ="1">
            <tr class="week">
                <td class="day prev-mon" tabindex="0"><span class="day-number">27</span></td>
                <td class="day prev-mon" tabindex="0"><span class="day-number">28</span></td>
                <td class="day prev-mon" tabindex="0"><span class="day-number">29</span></td>
                <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                <td class="day prev-mon" tabindex="0"><span class="day-number">31</span>
                <td class="day " tabindex="0"><span class="day-number day-click">01</span></td>
                <td class="day " tabindex="0"><span class="day-number day-click">02</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number day-click">03</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">04</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">05</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">06</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">07</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">08</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">09</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number day-click">10</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">11</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">12</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">13</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">14</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">15</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">16</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number day-click">17</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">18</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">19</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">20</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">21</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">22</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">23</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number  day-click">24</span></td>
                <td class="day" tabindex="0"><span class="day-number  day-click">25</span></td>
                <td class="day" tabindex="0"><span class="day-number  day-click">26</span></td>
                <td class="day" tabindex="0"><span class="day-number  day-click">27</span></td>
                <td class="day" tabindex="0"><span class="day-number  day-click">28</span></td>
                <td class="day" tabindex="0"><span class="day-number  day-click">29</span></td>
                <td class="day next-mon" tabindex="0"><span class="day-number">30</span></td>
            </tr>
        </tbody>
    <% break;
    case 2:%>
       	<tbody id ="2">
            <tr class="week">
                <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                <td class="day " tabindex="0"><span class="day-number day-click">01</span></td>
                <td class="day " tabindex="0"><span class="day-number day-click">02</span></td>
                <td class="day " tabindex="0"><span class="day-number day-click">03</span></td>
                <td class="day " tabindex="0"><span class="day-number day-click">04</span></td>
                <td class="day " tabindex="0"><span class="day-number day-click">05</span></td>
                <td class="day " tabindex="0"><span class="day-number day-click">06</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number day-click">07</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">08</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">09</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">10</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">11</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">12</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">13</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number day-click">14</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">15</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">16</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">17</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">18</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">19</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">20</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number day-click">21</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">22</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">23</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">24</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">25</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">26</span></td>
                <td class="day" tabindex="0"><span class="day-number day-click">27</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number day-click">28</span></td>
                <td class="day next-mon" tabindex="0"><span class="day-number">01</span></td>
                <td class="day next-mon" tabindex="0"><span class="day-number">02</span></td>
                <td class="day next-mon" tabindex="0"><span class="day-number">03</span></td>
                <td class="day next-mon" tabindex="0"><span class="day-number">04</span></td>
                <td class="day next-mon" tabindex="0"><span class="day-number">05</span></td>
                <td class="day next-mon" tabindex="0"><span class="day-number">06</span></td>
            </tr>
        </tbody>
    
    <% break;
    case 3:%>
		<tbody id ="3">
			<tr class="week">
				<td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
				<td class="day " tabindex="0"><span class="day-number day-click">01</span></td>
				<td class="day " tabindex="0"><span class="day-number day-click">02</span></td>
				<td class="day " tabindex="0"><span class="day-number day-click">03</span></td>
				<td class="day " tabindex="0"><span class="day-number day-click">04</span></td>
				<td class="day " tabindex="0"><span class="day-number day-click">05</span></td>
				<td class="day " tabindex="0"><span class="day-number day-click">06</span></td>
			</tr>
			<tr class="week">
				<td class="day" tabindex="0"><span class="day-number day-click">07</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">08</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">09</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">10</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">11</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">12</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">13</span></td>
			</tr>
			<tr class="week">
				<td class="day" tabindex="0"><span class="day-number day-click">14</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">15</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">16</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">17</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">18</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">19</span></td>
				<td class="day" tabindex="0"><span class="day-number day-click">20</span></td>
			</tr>
			<tr class="week">
				<td class="day" tabindex="0"><span class="day-number  day-click">21</span></td>
				<td class="day" tabindex="0"><span class="day-number  day-click">22</span></td>
				<td class="day" tabindex="0"><span class="day-number  day-click">23</span></td>
				<td class="day" tabindex="0"><span class="day-number  day-click">24</span></td>
				<td class="day" tabindex="0"><span class="day-number  day-click">25</span></td>
				<td class="day" tabindex="0"><span class="day-number  day-click">26</span></td>
				<td class="day" tabindex="0"><span class="day-number  day-click">27</span></td>
			</tr>
			<tr class="week">
				<td class="day" tabindex="0"><span class="day-number  day-click">28</span></td>
				<td class="day" tabindex="0"><span class="day-number  day-click">29</span></td>
				<td class="day" tabindex="0"><span class="day-number  day-click">30</span></td>
				<td class="day" tabindex="0"><span class="day-number  day-click">31</span></td>
				<td class="day next-mon" tabindex="0"><span class="day-number">01</span></td>
				<td class="day next-mon" tabindex="0"><span class="day-number">02</span></td>
				<td class="day next-mon" tabindex="0"><span class="day-number">03</span></td>
			</tr>
		</tbody>


    <% break;
    case 4:%>
       <tbody id ="4">
           <tr class="week">
               <td class="day prev-mon" tabindex="0"><span class="day-number">29</span></td>
               <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
               <td class="day prev-mon" tabindex="0"><span class="day-number">31</span></td>
               <td class="day prev-mon" tabindex="0"><span class="day-number">31</span></td>
               <td class="day" tabindex=" 0"><span class="day-number  day-click">01</span></td>
               <td class="day " tabindex="0"><span class="day-number  day-click">02</span></td>
               <td class="day " tabindex="0"><span class="day-number  day-click">03</span></td>
           </tr>
           <tr class="week">
               <td class="day" tabindex="0"><span class="day-number day-click">04</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">05</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">06</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">07</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">08</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">09</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">10</span></td>
           </tr>
           <tr class="week">
               <td class="day" tabindex="0"><span class="day-number day-click">11</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">12</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">13</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">14</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">15</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">16</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">17</span></td>
           </tr>
           <tr class="week">
               <td class="day" tabindex="0"><span class="day-number day-click">18</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">19</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">20</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">21</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">22</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">23</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">24</span></td>
           </tr>
           <tr class="week">
               <td class="day" tabindex="0"><span class="day-number day-click">25</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">26</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">27</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">28</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">39</span></td>
               <td class="day" tabindex="0"><span class="day-number day-click">30</span></td>
               <td class="day next-mon" tabindex="0"><span class="day-number">01</span></td>
           </tr>
       </tbody>
    <% break;
    case 5:%>
            <tbody id ="5">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">25</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">26</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">27</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day " tabindex="0"><span class="day-number  day-click">01</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">02</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">03</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">04</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">05</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">06</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">07</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">08</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">09</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">15</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">22</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">25</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">27</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">29</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">30</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">31</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">01</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">02</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">03</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">04</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">05</span></td>
                </tr>
            </tbody>
    <% break;
    case 6:%>
            <tbody id ="6">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">31</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">01</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">02</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">03</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">04</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">05</span></td>
                </tr>
                <tr class="week">
                    <td class="day " tabindex="0"><span class="day-number day-click">06</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">07</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">08</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">09</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">12</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">19</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">25</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">26</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">27</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">29</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">30</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">01</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">02</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">03</span></td>
                </tr>
            </tbody>
    <% break;
    case 7:%>
            <tbody id ="7">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">27</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">01</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">02</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">03</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">04</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">05</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">06</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">07</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">08</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">09</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">10</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">17</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">24</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">25</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">27</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">29</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">30</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">31</span></td>
                </tr>
            </tbody>
    <% break;
    case 8: %>
            <tbody id ="8">
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number  day-click">01</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">02</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">03</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">04</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">05</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">06</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">07</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">08</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">09</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">14</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">21</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">25</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">27</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">28</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">29</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">30</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">31</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">01</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">02</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">03</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">04</span></td>
                </tr>
            </tbody>
    <% break;
    case 9:%>
            <tbody id ="9">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">31</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">01</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">02</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">04</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">05</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">06</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">07</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">08</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">09</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">11</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">18</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">25</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">27</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">29</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">30</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">01</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">02</span></td>
                </tr>
            </tbody>    
    <% break;
    case 10:%>            
    		<tbody id ="10">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">26</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">27</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">01</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">02</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">03</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">04</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">05</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">06</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">07</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">08</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">09</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">16</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">23</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">25</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">27</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">29</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">30</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">31</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">01</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">02</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">03</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">04</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">05</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">06</span></td>
                </tr>
            </tbody>
    
    <% break;
    case 11:%>            
    	<tbody id ="11">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">31</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">01</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">02</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">03</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">04</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">05</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click">06</span></td>
                </tr>
                <tr class="week">
                    <td class="day " tabindex="0"><span class="day-number day-click">07</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">08</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">09</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">13</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">20</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">25</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">27</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number day-click">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">39</span></td>
                    <td class="day" tabindex="0"><span class="day-number day-click">30</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">1</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">2</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">3</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">4</span></td>
                </tr>
            </tbody>
    
    <% break;
    case 12:%>
            <tbody id ="12">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click" >01</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click" >02</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click" >03</span></td>
                    <td class="day " tabindex="0"><span class="day-number day-click" >04</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number  day-click">05</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">06</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">07</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">08</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">09</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">11</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number  day-click">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">18</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number  day-click">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">25</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number  day-click">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">27</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">29</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">30</span></td>
                    <td class="day" tabindex="0"><span class="day-number  day-click">31</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">1</span></td>
                </tr>
            </tbody>
    <% break; }%>
        </table>
	 	<!--이전 버튼  fixed -->
	    <a class="Previous btn" onclick="previous();"><i class="fas fa-chevron-circle-left"
	           style="font-size: 28px; font-weight: bold;"></i> </a>
	    <!--이전 버튼  fixed -->
	    <a class="next btn1" onclick="next();"><i class="fas fa-chevron-circle-right"
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
            <li class="Container-bar-li-left" style="font-size: 30px;"><a href="<%= request.getContextPath()%>/mypage/memberModify"><span
                        class="title">회원정보
                        수정</span></a>
            </li>
        </ul>
    </div>
    </div>
    
    <script>
	var month_arr =["January" , "February", "March", "April", "May", "June", "July", "August", "September","October","November", "December"];
    function previous(){	
		//month에 해당하는 tbody
		var $month = $("#month_span");
    	var previousMonth = Number($month.text())-1;
    	if(previousMonth == 0 ){
    		alert("1월까지 준비되어있습니다");
    		return;
    	};
		location.href ="<%= request.getContextPath()%>/mypage?month=" + previousMonth;

    }
    
    
    function next(){
		//month에 해당하는 tbody
		var $month = $("#month_span");    	
    	var nextMonth = Number($month.text()) +1;
    	if(nextMonth  == 13 ){
    		alert("12월까지 준비되어있습니다");
    		return;
    	}
		location.href ="<%= request.getContextPath()%>/mypage?month=" + nextMonth;
    }
    
    
    //페이지 로드 완료 시 뱃지
    $(document).ready (function(){
    	<%
    	String badge_date = "";
    	for(MypageBadge m :badgeList){
    		String sdf = new SimpleDateFormat("yyyy-MM-dd").format(m.getBadgeDate());
    		badge_date = sdf.substring(sdf.length()-2, sdf.length());
    		
    	%>
    		$date = $('span:contains("<%= badge_date %>")');
    		$date.removeClass('day-number');
    		$date.removeClass('day-click').addClass('day-img');

    		var htmlString = '<img src="<%=request.getContextPath()%>/image/뱃지/<%=m.getBadgeImg()%>"'
    						+' style=" height : 2.5em; border: 0; cursor: pointer;"'
    						+' onclick= "openPopup(event);"'
    						+ 'data-date= "<%= badge_date %>"'
    						+'>';
 			$date.html(htmlString);

    	<%}%>		
    	
    	$('.day-click').attr('onclick', 'openPopup_date(event)');
    	
    	<%
    	String point_date = "";
    	for(MypagePoint mp :pointList){
    		String sdf = new SimpleDateFormat("yyyy-MM-dd").format(mp.getPointDate());
    		point_date = sdf.substring(sdf.length()-2, sdf.length());
    	%>
			$('span:contains("<%= point_date %>")').css('color','#81D4FA');

    	<%}%>		
    	<%
    	String team_point_date = "";
    	for(MypagePoint mp :tPointList){
    		String sdf = new SimpleDateFormat("yyyy-MM-dd").format(mp.getPointDate());
    		team_point_date = sdf.substring(sdf.length()-2, sdf.length());
    	%>
			$('span:contains("<%= team_point_date %>")').css('color','#81D4FA');

    	<%}%>	
    });
    
    var openPopup= function(event){
		//클릭된 이미지, 클릭된 이미지의 date 가져오기
   		var $ele= $(event.target);
		console.log($ele);
		var url ="<%=request.getContextPath() %>/mypage/mypagePopup?month="+<%= month%>  + "&date="+$ele.data('date');
		var title= "DetailPopup";
    
		window.open(url, title, "width=1000, height=600,  resizable=no");
    }
    
    //단순 날짜
    var openPopup_date= function(event){
		//클릭된 이미지, 클릭된 이미지의 date 가져오기
   		var $ele= $(event.target);
		console.log($ele.text());
		var url ="<%=request.getContextPath() %>/mypage/mypagePopup?month="+<%= month%>  + "&date="+$ele.text();
		var title= "DetailPopup";
    
		window.open(url, title, "width=1000, height=600,  resizable=no");
    }
    </script>

</body>
</html>