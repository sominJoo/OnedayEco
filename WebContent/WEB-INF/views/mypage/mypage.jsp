<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	Calendar now = Calendar.getInstance();
	//int month = now.get(Calendar.MONTH)+1;
	int month = request.getParameter("month") != null ? Integer.valueOf(request.getParameter("month")) :  now.get(Calendar.MONTH)+1;
	String[] month_arr ={"January" , "February", "March", "April", "May", "June", "July", "August", "September","October","November", "December"};
%>
<!DOCTYPE html>
<html lang="ko" class="no-js">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지(4월)</title>
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
    case 1: %>    
           <tbody id ="1">
            <tr class="week">
                <td class="day prev-mon" tabindex="0"><span class="day-number">28</span></td>
                <td class="day prev-mon" tabindex="0"><span class="day-number">29</span></td>
                <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                <td class="day prev-mon" tabindex="0"><span class="day-number">31</span></td>
                <td class="day" tabindex="0"><span class="day-img"><img src="image/캘린더(뱃지)/환경의-민족.png" alt=""
                            style="border: 0; cursor: pointer;"
                            onclick="window.open('Popup(January).html','new','width=1000, height=600,  resizable=no')"
                            ;></span>
                <td class="day " tabindex="0"><span class="day-number">1</span></td>
                <td class="day " tabindex="0"><span class="day-number">2</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number">3</span></td>
                <td class="day" tabindex="0"><span class="day-number">4</span></td>
                <td class="day" tabindex="0"><span class="day-number">5</span></td>
                <td class="day" tabindex="0"><span class="day-number">6</span></td>
                <td class="day" tabindex="0"><span class="day-number">7</span></td>
                <td class="day" tabindex="0"><span class="day-number">8</span></td>
                <td class="day" tabindex="0"><span class="day-number">9</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number">10</span></td>
                <td class="day" tabindex="0"><span class="day-number">11</span></td>
                <td class="day" tabindex="0"><span class="day-number">12</span></td>
                <td class="day" tabindex="0"><span class="day-number">13</span></td>
                <td class="day" tabindex="0"><span class="day-number">14</span></td>
                <td class="day" tabindex="0"><span class="day-number">15</span></td>
                <td class="day" tabindex="0"><span class="day-number">16</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number">17</span></td>
                <td class="day" tabindex="0"><span class="day-number">18</span></td>
                <td class="day" tabindex="0"><span class="day-number">19</span></td>
                <td class="day" tabindex="0"><span class="day-number">20</span></td>
                <td class="day" tabindex="0"><span class="day-number">21</span></td>
                <td class="day" tabindex="0"><span class="day-number">22</span></td>
                <td class="day" tabindex="0"><span class="day-number">23</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number">24</span></td>
                <td class="day" tabindex="0"><span class="day-number">25</span></td>
                <td class="day" tabindex="0"><span class="day-number">26</span></td>
                <td class="day" tabindex="0"><span class="day-number">27</span></td>
                <td class="day" tabindex="0"><span class="day-number">28</span></td>
                <td class="day" tabindex="0"><span class="day-number">29</span></td>
                <td class="day next-mon" tabindex="0"><span class="day-number">30</span></td>
            </tr>
        </tbody>
    <% break;
    case 2:%>
       	<tbody id ="2">
            <tr class="week">
                <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                <td class="day prev-mon" tabindex="0"><span class="day-number">1</span></td>
                <td class="day prev-mon" tabindex="0"><span class="day-number">2</span></td>
                <td class="day" tabindex="0"><span class="day-img"><img src="image/캘린더(뱃지)/환경의-민족.png" alt=""
                            style="border: 0; cursor: pointer;"
                            onclick="window.open('Popup(February).html','new','width=1000, height=600,  resizable=no')"
                            ;></span>
                </td>
                <td class="day " tabindex="0"><span class="day-number">4</span></td>
                <td class="day " tabindex="0"><span class="day-number">5</span></td>
                <td class="day " tabindex="0"><span class="day-number">6</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number">7</span></td>
                <td class="day" tabindex="0"><span class="day-number">8</span></td>
                <td class="day" tabindex="0"><span class="day-number">9</span></td>
                <td class="day" tabindex="0"><span class="day-number">10</span></td>
                <td class="day" tabindex="0"><span class="day-number">11</span></td>
                <td class="day" tabindex="0"><span class="day-number">12</span></td>
                <td class="day" tabindex="0"><span class="day-number">13</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number">14</span></td>
                <td class="day" tabindex="0"><span class="day-number">15</span></td>
                <td class="day" tabindex="0"><span class="day-number">16</span></td>
                <td class="day" tabindex="0"><span class="day-number">17</span></td>
                <td class="day" tabindex="0"><span class="day-number">18</span></td>
                <td class="day" tabindex="0"><span class="day-number">19</span></td>
                <td class="day" tabindex="0"><span class="day-number">20</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number">21</span></td>
                <td class="day" tabindex="0"><span class="day-number">22</span></td>
                <td class="day" tabindex="0"><span class="day-number">23</span></td>
                <td class="day" tabindex="0"><span class="day-number">24</span></td>
                <td class="day" tabindex="0"><span class="day-number">25</span></td>
                <td class="day" tabindex="0"><span class="day-number">26</span></td>
                <td class="day" tabindex="0"><span class="day-number">27</span></td>
            </tr>
            <tr class="week">
                <td class="day" tabindex="0"><span class="day-number">28</span></td>
                <td class="day" tabindex="0"><span class="day-number">1</span></td>
                <td class="day" tabindex="0"><span class="day-number">2</span></td>
                <td class="day" tabindex="0"><span class="day-number">3</span></td>
                <td class="day" tabindex="0"><span class="day-number">4</span></td>
                <td class="day" tabindex="0"><span class="day-number">5</span></td>
                <td class="day next-mon" tabindex="0"><span class="day-number">6</span></td>
            </tr>
        </tbody>
    
    <% break;
    case 3:%>
		<tbody id ="3">
			<tr class="week">
				<td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
				<td class="day prev-mon" tabindex="0"><span class="day-number">1</span></td>
				<td class="day prev-mon" tabindex="0"><span class="day-number">2</span></td>
				<td class="day" tabindex="0"><span class="day-img"><img
						src="image/캘린더(뱃지)/환경의-민족.png" alt=""
						style="border: 0; cursor: pointer;"
						onclick="window.open('Popup(March).html','new','width=1000, height=600,  resizable=no')";></span>
				</td>
				<td class="day " tabindex="0"><span class="day-number">4</span></td>
				<td class="day " tabindex="0"><span class="day-number">5</span></td>
				<td class="day " tabindex="0"><span class="day-number">6</span></td>
			</tr>
			<tr class="week">
				<td class="day" tabindex="0"><span class="day-number">7</span></td>
				<td class="day" tabindex="0"><span class="day-number">8</span></td>
				<td class="day" tabindex="0"><span class="day-number">9</span></td>
				<td class="day" tabindex="0"><span class="day-number">10</span></td>
				<td class="day" tabindex="0"><span class="day-number">11</span></td>
				<td class="day" tabindex="0"><span class="day-number">12</span></td>
				<td class="day" tabindex="0"><span class="day-number">13</span></td>
			</tr>
			<tr class="week">
				<td class="day" tabindex="0"><span class="day-number">14</span></td>
				<td class="day" tabindex="0"><span class="day-number">15</span></td>
				<td class="day" tabindex="0"><span class="day-number">16</span></td>
				<td class="day" tabindex="0"><span class="day-number">17</span></td>
				<td class="day" tabindex="0"><span class="day-number">18</span></td>
				<td class="day" tabindex="0"><span class="day-number">19</span></td>
				<td class="day" tabindex="0"><span class="day-number">20</span></td>
			</tr>
			<tr class="week">
				<td class="day" tabindex="0"><span class="day-number">21</span></td>
				<td class="day" tabindex="0"><span class="day-number">22</span></td>
				<td class="day" tabindex="0"><span class="day-number">23</span></td>
				<td class="day" tabindex="0"><span class="day-number">24</span></td>
				<td class="day" tabindex="0"><span class="day-number">25</span></td>
				<td class="day" tabindex="0"><span class="day-number">26</span></td>
				<td class="day" tabindex="0"><span class="day-number">27</span></td>
			</tr>
			<tr class="week">
				<td class="day" tabindex="0"><span class="day-number">28</span></td>
				<td class="day" tabindex="0"><span class="day-number">29</span></td>
				<td class="day" tabindex="0"><span class="day-number">30</span></td>
				<td class="day" tabindex="0"><span class="day-number">31</span></td>
				<td class="day" tabindex="0"><span class="day-number">1</span></td>
				<td class="day" tabindex="0"><span class="day-number">2</span></td>
				<td class="day next-mon" tabindex="0"><span class="day-number">3</span></td>
			</tr>
		</tbody>


    <% break;
    case 4:%>
       <tbody id ="4">
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
    <% break;
    case 5:%>
            <tbody id ="5">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">25</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">26</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">27</span></td>
                    <td class="day" tabindex="0"><span class="day-img"><img src="image/캘린더(뱃지)/환경의-민족.png" alt=""
                                style="border: 0; cursor: pointer;"
                                onclick="window.open('Popup(May).html','new','width=1000, height=600,  resizable=no')"
                                ;></span>
                    </td>
                    <td class="day " tabindex="0"><span class="day-number">29</span></td>
                    <td class="day " tabindex="0"><span class="day-number">30</span></td>
                    <td class="day " tabindex="0"><span class="day-number">1</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">2</span></td>
                    <td class="day" tabindex="0"><span class="day-number">3</span></td>
                    <td class="day" tabindex="0"><span class="day-number">4</span></td>
                    <td class="day" tabindex="0"><span class="day-number">5</span></td>
                    <td class="day" tabindex="0"><span class="day-number">6</span></td>
                    <td class="day" tabindex="0"><span class="day-number">7</span></td>
                    <td class="day" tabindex="0"><span class="day-number">8</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">9</span></td>
                    <td class="day" tabindex="0"><span class="day-number">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number">15</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number">22</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number">25</span></td>
                    <td class="day" tabindex="0"><span class="day-number">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number">27</span></td>
                    <td class="day" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">29</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day" tabindex="0"><span class="day-number">31</span></td>
                </tr>
            </tbody>
    <% break;
    case 6:%>
            <tbody id ="6">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">1</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">2</span></td>
                    <td class="day" tabindex="0"><span class="day-img"><img src="image/캘린더(뱃지)/환경의-민족.png" alt=""
                                style="border: 0; cursor: pointer;"
                                onclick="window.open('Popup(june).html','new','width=1000, height=600,  resizable=no')"
                                ;></span>
                    </td>
                    <td class="day " tabindex="0"><span class="day-number">4</span></td>
                    <td class="day " tabindex="0"><span class="day-number">5</span></td>
                    <td class="day " tabindex="0"><span class="day-number">6</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">7</span></td>
                    <td class="day" tabindex="0"><span class="day-number">8</span></td>
                    <td class="day" tabindex="0"><span class="day-number">9</span></td>
                    <td class="day" tabindex="0"><span class="day-number">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number">13</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number">20</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number">25</span></td>
                    <td class="day" tabindex="0"><span class="day-number">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number">27</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day" tabindex="0"><span class="day-number">1</span></td>
                    <td class="day" tabindex="0"><span class="day-number">2</span></td>
                    <td class="day" tabindex="0"><span class="day-number">3</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">4</span></td>
                </tr>
            </tbody>
    <% break;
    case 7:%>
            <tbody id ="7">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">27</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day" tabindex="0"><span class="day-img"><img src="image/캘린더(뱃지)/환경의-민족.png" alt=""
                                style="border: 0; cursor: pointer;"
                                onclick="window.open('Popup(July).html','new','width=1000, height=600,  resizable=no')"
                                ;></span>
                    </td>
                    <td class="day " tabindex="0"><span class="day-number">1</span></td>
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
                    <td class="day" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">31</span></td>
                </tr>
            </tbody>
    <% break;
    case 8:%>
            <tbody id ="8">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">1</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">2</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">3</span></td>
                    <td class="day" tabindex="0"><span class="day-img"><img src="image/캘린더(뱃지)/환경의-민족.png" alt=""
                                style="border: 0; cursor: pointer;"
                                onclick="window.open('Popup(August).html','new','width=1000, height=600,  resizable=no')"
                                ;></span>
                    </td>
                    <td class="day " tabindex="0"><span class="day-number">5</span></td>
                    <td class="day " tabindex="0"><span class="day-number">6</span></td>
                    <td class="day " tabindex="0"><span class="day-number">7</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">8</span></td>
                    <td class="day" tabindex="0"><span class="day-number">9</span></td>
                    <td class="day" tabindex="0"><span class="day-number">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number">14</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number">21</span></td>
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
    <% break;
    case 9:%>
            <tbody id ="9">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">1</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">2</span></td>
                    <td class="day" tabindex="0"><span class="day-img"><img src="image/캘린더(뱃지)/환경의-민족.png" alt=""
                                style="border: 0; cursor: pointer;"
                                onclick="window.open('Popup(March).html','new','width=1000, height=600,  resizable=no')"
                                ;></span>
                    </td>
                    <td class="day " tabindex="0"><span class="day-number">4</span></td>
                    <td class="day " tabindex="0"><span class="day-number">5</span></td>
                    <td class="day " tabindex="0"><span class="day-number">6</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">7</span></td>
                    <td class="day" tabindex="0"><span class="day-number">8</span></td>
                    <td class="day" tabindex="0"><span class="day-number">9</span></td>
                    <td class="day" tabindex="0"><span class="day-number">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number">13</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number">20</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number">25</span></td>
                    <td class="day" tabindex="0"><span class="day-number">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number">27</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day" tabindex="0"><span class="day-number">31</span></td>
                    <td class="day" tabindex="0"><span class="day-number">1</span></td>
                    <td class="day" tabindex="0"><span class="day-number">2</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">3</span></td>
                </tr>
            </tbody>    
    <% break;
    case 10:%>            
    		<tbody id ="10">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">26</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">27</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day" tabindex="0"><span class="day-img"><img src="image/캘린더(뱃지)/환경의-민족.png" alt=""
                                style="border: 0; cursor: pointer;"
                                onclick="window.open('Popup(October).html','new','width=1000, height=600,  resizable=no')"
                                ;></span>
                    </td>
                    <td class="day " tabindex="0"><span class="day-number">30</span></td>
                    <td class="day " tabindex="0"><span class="day-number">1</span></td>
                    <td class="day " tabindex="0"><span class="day-number">2</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">3</span></td>
                    <td class="day" tabindex="0"><span class="day-number">4</span></td>
                    <td class="day" tabindex="0"><span class="day-number">5</span></td>
                    <td class="day" tabindex="0"><span class="day-number">6</span></td>
                    <td class="day" tabindex="0"><span class="day-number">7</span></td>
                    <td class="day" tabindex="0"><span class="day-number">8</span></td>
                    <td class="day" tabindex="0"><span class="day-number">9</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number">16</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number">23</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number">25</span></td>
                    <td class="day" tabindex="0"><span class="day-number">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number">27</span></td>
                    <td class="day" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day next-mon" tabindex="0"><span class="day-number">30</span></td>
                </tr>
            </tbody>
    
    <% break;
    case 11:%>            
    	<tbody id ="11">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">1</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">2</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">3</span></td>
                    <td class="day" tabindex="0"><span class="day-img"><img src="image/캘린더(뱃지)/환경의-민족.png" alt=""
                                style="border: 0; cursor: pointer;"
                                onclick="window.open('Popup(November).html','new','width=1000, height=600,  resizable=no')"
                                ;></span>
                    </td>
                    <td class="day " tabindex="0"><span class="day-number">5</span></td>
                    <td class="day " tabindex="0"><span class="day-number">6</span></td>
                    <td class="day " tabindex="0"><span class="day-number">7</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">8</span></td>
                    <td class="day" tabindex="0"><span class="day-number">9</span></td>
                    <td class="day" tabindex="0"><span class="day-number">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number">11</span></td>
                    <td class="day" tabindex="0"><span class="day-number">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number">14</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number">18</span></td>
                    <td class="day" tabindex="0"><span class="day-number">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number">21</span></td>
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
    
    <% break;
    case 12:%>
            <tbody id ="12">
                <tr class="week">
                    <td class="day prev-mon" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day prev-mon" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day" tabindex="0"><span class="day-img"><img src="image/캘린더(뱃지)/환경의-민족.png" alt=""
                                style="border: 0; cursor: pointer;"
                                onclick="window.open('Popup(December).html','new','width=1000, height=600,  resizable=no')"
                                ;></span>
                    </td>
                    <td class="day " tabindex="0"><span class="day-number">2</span></td>
                    <td class="day " tabindex="0"><span class="day-number">3</span></td>
                    <td class="day " tabindex="0"><span class="day-number">4</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">5</span></td>
                    <td class="day" tabindex="0"><span class="day-number">6</span></td>
                    <td class="day" tabindex="0"><span class="day-number">7</span></td>
                    <td class="day" tabindex="0"><span class="day-number">8</span></td>
                    <td class="day" tabindex="0"><span class="day-number">9</span></td>
                    <td class="day" tabindex="0"><span class="day-number">10</span></td>
                    <td class="day" tabindex="0"><span class="day-number">11</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">12</span></td>
                    <td class="day" tabindex="0"><span class="day-number">13</span></td>
                    <td class="day" tabindex="0"><span class="day-number">14</span></td>
                    <td class="day" tabindex="0"><span class="day-number">15</span></td>
                    <td class="day" tabindex="0"><span class="day-number">16</span></td>
                    <td class="day" tabindex="0"><span class="day-number">17</span></td>
                    <td class="day" tabindex="0"><span class="day-number">18</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">19</span></td>
                    <td class="day" tabindex="0"><span class="day-number">20</span></td>
                    <td class="day" tabindex="0"><span class="day-number">21</span></td>
                    <td class="day" tabindex="0"><span class="day-number">22</span></td>
                    <td class="day" tabindex="0"><span class="day-number">23</span></td>
                    <td class="day" tabindex="0"><span class="day-number">24</span></td>
                    <td class="day" tabindex="0"><span class="day-number">25</span></td>
                </tr>
                <tr class="week">
                    <td class="day" tabindex="0"><span class="day-number">26</span></td>
                    <td class="day" tabindex="0"><span class="day-number">27</span></td>
                    <td class="day" tabindex="0"><span class="day-number">28</span></td>
                    <td class="day" tabindex="0"><span class="day-number">29</span></td>
                    <td class="day" tabindex="0"><span class="day-number">30</span></td>
                    <td class="day" tabindex="0"><span class="day-number">31</span></td>
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
            <li class="Container-bar-li-left" style="font-size: 30px;"><a href="Member Modify.html"><span
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

		location.href="<%request.getContextPath()%>/mypage?month="+previousMonth;
 		//그 전 tbody 
		
		//그전 
		$month.text(previousMonth);
		$monthEng.text(month_arr[previousMonth-1]);
		console.log($("#"+previousMonth));
    	var $tbody_add = $("#"+previousMonth);
    	console.log($tbody_add);
    	$tbody_add.appendTo(".calendar");
    }
    
    
    function next(){
		//month에 해당하는 tbody
		var $month = $("#month_span");
		var $monthEng= $("#month-eng");
    	var $tbody = $("#"+$month.text());
    	console.log($tbody);
    	
    	var nextMonth = Number($month.text()) +1;
    	if(nextMonth  == 13 ){
    		alert("12월까지 준비되어있습니다");
    		return;
    	}
    	console.log(nextMonth);
    	console.log(month_arr[nextMonth]);
    	
		$month.text(nextMonth);
		$monthEng.text(month_arr[nextMonth]);
    }
    </script>

</body>
</html>