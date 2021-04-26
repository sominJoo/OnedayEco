<%@page import="mypage.model.vo.MypageChallenge"%>
<%@page import="mypage.model.vo.MypagePoint"%>
<%@page import="mypage.model.vo.MypageBadge"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int month = Integer.valueOf(request.getParameter("month"));	
	int date = Integer.valueOf(request.getParameter("date"));
	//null 가능
	List<MypageBadge> mBadgeList =(List<MypageBadge>)request.getAttribute("mBadgeList"); 
	List<MypagePoint> mPointList =(List<MypagePoint>)request.getAttribute("mPointList");
	List<MypagePoint> mTPointList =(List<MypagePoint>)request.getAttribute("mtPointList");
	List<MypageChallenge> mChallengeList = (List<MypageChallenge>)request.getAttribute("mChallengeList");
	
	String dateStr = date<10 ? "0"+date : Integer.toString(date);
	String m_date = month+"월 "+dateStr+"일";
%>    
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Popup.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <title>팝업창</title>
</head>

<body>
    <div class="Date">
        <hr class="hr3">
        <ul class="header">
	    <% switch(month){
	    case 1:%>
            <li>1월 01일</li>   <li>1월 02일</li>  <li>1월 03일</li> <li>1월 04일</li>    <li>1월 05일</li>
            <li>1월 06일</li>   <li>1월 07일</li>  <li>1월 08일</li> <li>1월 09일</li>    <li>1월 10일</li>
            <li>1월 11일</li>   <li>1월 12일</li>  <li>1월 13일</li> <li>1월 14일</li>    <li>1월 15일</li>
            <li>1월 16일</li>   <li>1월 17일</li>  <li>1월 18일</li> <li>1월 19일</li>    <li>1월 20일</li>
            <li>1월 21일</li>   <li>1월 22일</li>  <li>1월 23일</li> <li>1월 24일</li>    <li>1월 25일</li>
            <li>1월 26일</li>   <li>1월 27일</li>  <li>1월 28일</li> <li>1월 29일 </li>   <li>1월 30일</li>  <li>1월 31일</li>
        <% break; 
        case 2 : %>
            <li>2월 01일</li>   <li>2월 02일</li>  <li>2월 03일</li> <li>2월 04일</li>    <li>2월 05일</li>
            <li>2월 06일</li>   <li>2월 07일</li>  <li>2월 08일</li> <li>2월 09일</li>    <li>2월 10일</li>
            <li>2월 11일</li>   <li>2월 12일</li>  <li>2월 13일</li> <li>2월 14일</li>    <li>2월 15일</li>
            <li>2월 16일</li>   <li>2월 17일</li>  <li>2월 18일</li> <li>2월 19일</li>    <li>2월 20일</li>
            <li>2월 21일</li>   <li>2월 22일</li>  <li>2월 23일</li> <li>2월 24일</li>    <li>2월 25일</li>
            <li>2월 26일</li>   <li>2월 27일</li>  <li>2월 28일</li>
        <% break; 
        case 3 : %>
            <li>3월 01일</li>   <li>3월 02일</li>  <li>3월 03일</li> <li>3월 04일</li>    <li>3월 05일</li>
            <li>3월 06일</li>   <li>3월 07일</li>  <li>3월 08일</li> <li>3월 09일</li>    <li>3월 10일</li>
            <li>3월 11일</li>   <li>3월 12일</li>  <li>3월 13일</li> <li>3월 14일</li>    <li>3월 15일</li>
            <li>3월 16일</li>   <li>3월 17일</li>  <li>3월 18일</li> <li>3월 19일</li>    <li>3월 20일</li>
            <li>3월 21일</li>   <li>3월 22일</li>  <li>3월 23일</li> <li>3월 24일</li>    <li>3월 25일</li>
            <li>3월 26일</li>   <li>3월 27일</li>  <li>3월 28일</li> <li>3월 29일 </li>   <li>3월 30일</li>  <li>3월 31일</li>
        <% break; 
        case 4 : %>
            <li>4월 01일</li>   <li>4월 02일</li>  <li>4월 03일</li> <li>4월 04일</li>    <li>4월 05일</li>
            <li>4월 06일</li>   <li>4월 07일</li>  <li>4월 08일</li> <li>4월 09일</li>    <li>4월 10일</li>
            <li>4월 11일</li>   <li>4월 12일</li>  <li>4월 13일</li> <li>4월 14일</li>    <li>4월 15일</li>
            <li>4월 16일</li>   <li>4월 17일</li>  <li>4월 18일</li> <li>4월 19일 </li>   <li>4월 20일</li>
            <li>4월 21일</li>   <li>4월 22일</li>  <li>4월 23일</li> <li>4월 24일</li>    <li>4월 25일</li>
            <li>4월 26일 </li>  <li>4월 27일</li>  <li>4월 28일</li>  <li>4월 29일</li>   <li>4월 30일</li>
        <% break; 
        case 5 : %>
            <li>5월 01일</li>   <li>5월 02일</li>  <li>5월 03일</li> <li>5월 04일</li>    <li>5월 05일</li>
            <li>5월 06일</li>   <li>5월 07일</li>  <li>5월 08일</li> <li>5월 09일</li>    <li>5월 10일</li>
            <li>5월 11일</li>   <li>5월 12일</li>  <li>5월 13일</li> <li>5월 14일</li>    <li>5월 15일</li>
            <li>5월 16일</li>   <li>5월 17일</li>  <li>5월 18일</li> <li>5월 19일</li>    <li>5월 20일</li>
            <li>5월 21일</li>   <li>5월 22일</li>  <li>5월 23일</li> <li>5월 24일</li>    <li>5월 25일</li>
            <li>5월 26일</li>   <li>5월 27일</li>  <li>5월 28일</li> <li>5월 29일 </li>   <li>5월 30일</li>  <li>5월 31일</li>
        <% break; 
        case 6 : %>
            <li>6월 01일</li>   <li>6월 02일</li>  <li>6월 03일</li> <li>6월 04일</li>    <li>6월 05일</li>
            <li>6월 06일</li>   <li>6월 07일</li>  <li>6월 08일</li> <li>6월 09일</li>    <li>6월 10일</li>
            <li>6월 11일</li>   <li>6월 12일</li>  <li>6월 13일</li> <li>6월 14일</li>    <li>6월 15일</li>
            <li>6월 16일</li>   <li>6월 17일</li>  <li>6월 18일</li> <li>6월 19일</li>    <li>6월 20일</li>
            <li>6월 21일</li>   <li>6월 22일</li>  <li>6월 23일</li> <li>6월 24일</li>    <li>6월 25일</li>
            <li>6월 26일</li>   <li>6월 27일</li>  <li>6월 28일</li> <li>6월 29일 </li>   <li>6월 30일</li>
        <% break; 
        case 7 : %>
            <li>7월 01일</li>   <li>7월 02일</li>  <li>7월 03일</li> <li>7월 04일</li>    <li>7월 05일</li>
            <li>7월 06일</li>   <li>7월 07일</li>  <li>7월 08일</li> <li>7월 09일</li>    <li>7월 10일</li>
            <li>7월 11일</li>   <li>7월 12일</li>  <li>7월 13일</li> <li>7월 14일</li>    <li>7월 15일</li>
            <li>7월 16일</li>   <li>7월 17일</li>  <li>7월 18일</li> <li>7월 19일</li>    <li>7월 20일</li>
            <li>7월 21일</li>   <li>7월 22일</li>  <li>7월 23일</li> <li>7월 24일</li>    <li>7월 25일</li>
            <li>7월 26일</li>   <li>7월 27일</li>  <li>7월 28일</li> <li>7월 29일 </li>   <li>7월 30일</li>  <li>7월 31일</li>
        <% break; 
        case 8 : %>
            <li>8월 01일</li>   <li>8월 02일</li>  <li>8월 03일</li> <li>8월 04일</li>    <li>8월 05일</li>
            <li>8월 06일</li>   <li>8월 07일</li>  <li>8월 08일</li> <li>8월 09일</li>    <li>8월 10일</li>
            <li>8월 11일</li>   <li>8월 12일</li>  <li>8월 13일</li> <li>8월 14일</li>    <li>8월 15일</li>
            <li>8월 16일</li>   <li>8월 17일</li>  <li>8월 18일</li> <li>8월 19일</li>    <li>8월 20일</li>
            <li>8월 21일</li>   <li>8월 22일</li>  <li>8월 23일</li> <li>8월 24일</li>    <li>8월 25일</li>
            <li>8월 26일</li>   <li>8월 27일</li>  <li>8월 28일</li> <li>8월 29일</li>    <li>8월 30일</li>   <li>8월 31일</li>
        <% break; 
        case 9 : %>
            <li>9월 01일</li>   <li>9월 02일</li>  <li>9월 03일</li> <li>9월 04일</li>    <li>9월 05일</li>
            <li>9월 06일</li>   <li>9월 07일</li>  <li>9월 08일</li> <li>9월 09일</li>    <li>9월 10일</li>
            <li>9월 11일</li>   <li>9월 12일</li>  <li>9월 13일</li> <li>9월 14일</li>    <li>9월 15일</li>
            <li>9월 16일</li>   <li>9월 17일</li>  <li>9월 18일</li> <li>9월 19일</li>    <li>9월 20일</li>
            <li>9월 21일</li>   <li>9월 22일</li>  <li>9월 23일</li> <li>9월 24일</li>    <li>9월 25일</li>
            <li>9월 26일</li>   <li>9월 27일</li>  <li>9월 28일</li> <li>9월 29일</li>    <li>9월 30일</li>
        <% break; 
        case 10 : %>
            <li>10월 01일</li>  <li>10월 02일</li>    <li>10월 03일</li>  <li>10월 04일</li>    <li>10월 05일</li>
            <li>10월 06일</li>  <li>10월 07일</li>    <li>10월 08일</li>  <li>10월 09일</li>    <li>10월 10일</li>
            <li>10월 11일</li>  <li>10월 12일</li>    <li>10월 13일</li>  <li>10월 14일</li>    <li>10월 15일</li>
            <li>10월 16일</li>  <li>10월 17일</li>    <li>10월 18일</li>  <li>10월 19일</li>    <li>10월 20일</li>
            <li>10월 21일</li>  <li>10월 22일</li>    <li>10월 23일</li>  <li>10월 24일</li>    <li>10월 25일</li>
            <li>10월 26일</li>  <li>10월 27일</li>    <li>10월 28일</li>  <li>10월 29일 </li>   <li>10월 30일</li> <li>10월 31일</li>
        <% break; 
        case 11 : %>
            <li>11월 01일</li>  <li>11월 02일</li>    <li>11월 03일</li>  <li>11월 04일</li>    <li>11월 05일</li>
            <li>11월 06일</li>  <li>11월 07일</li>    <li>11월 08일</li>  <li>11월 09일</li>    <li>11월 10일</li>
            <li>11월 11일</li>  <li>11월 12일</li>    <li>11월 13일</li>  <li>11월 14일</li>    <li>11월 15일</li>
            <li>11월 16일</li>  <li>11월 17일</li>    <li>11월 18일</li>  <li>11월 19일</li>    <li>11월 20일</li>
            <li>11월 21일</li>  <li>11월 22일</li>    <li>11월 23일</li>  <li>11월 24일</li>    <li>11월 25일</li>
            <li>11월 26일</li>  <li>11월 27일</li>    <li>11월 28일</li>  <li>11월 29일 </li>   <li>11월 30일</li>
        <% break; 
        case 12 : %>
            <li>12월 01일</li>  <li>12월 02일</li>    <li>12월 03일</li>  <li>12월 04일</li>    <li>12월 05일</li>
            <li>12월 06일</li>  <li>12월 07일</li>    <li>12월 08일</li>  <li>12월 09일</li>    <li>12월 10일</li>
            <li>12월 11일</li>  <li>12월 12일</li>    <li>12월 13일</li>  <li>12월 14일</li>    <li>12월 15일</li>
            <li>12월 16일</li>  <li>12월 17일</li>    <li>12월 18일</li>  <li>12월 19일</li>    <li>12월 20일</li>
            <li>12월 21일</li>  <li>12월 22일</li>    <li>12월 23일</li>  <li>12월 24일</li>    <li>12월 25일</li>
            <li>12월 26일</li>  <li>12월 27일</li>    <li>12월 28일</li>  <li>12월 29일 </li>   <li>12월 30일</li> <li>12월 31일</li>
        <% break;}%>
        </ul>
        <hr class="hr3">
        <div class="scroll">
            <img src="image/팝업창/뱃지.png" alt="">
            <!-- 포인트 -->
            <span class="scroll-span">이날 성공한 챌린지</span>
            <ul>
            <%if(mPointList != null){ %>
            	<%for(MypagePoint mp :mPointList) {%>
            		<%if (mp.getChallengeTermType() == "S"){ %>
                	<li><span class="bold">[하루]</span> <%= mp.getChallengName()%> <span class="bold"><%=mp.getPoint() %>점</span></li>
           			<% } else{%>
                	<li><span class="bold">[기간]</span> <%= mp.getChallengName()%> <span class="bold"><%=mp.getPoint() %>점</span></li>
           			<% }%>
           		<% }%>
           	<% }%>
           	<!-- 팀포인트 -->
            <%if(mTPointList != null){ %>
           		<%for(MypagePoint mtp :mTPointList) {%>
                	<li><span class="bold">[팀]</span> <%= mtp.getChallengName()%> <span class="bold"><%=mtp.getPoint() %>점</span></li>
           		<% }%>
           	<% }%>
            </ul>
            <!-- 진행 챌린지 -->
            <span class="scroll-span">진행 중인 장기챌린지 </span>
            <%if(mChallengeList != null){ %>
            <ul>
            	<% for(MypageChallenge mc : mChallengeList) {%>
                	<li style="color:red;"> <%= mc.getChallengeName() %><span class="bold" >(<%=mc.getConfirmDate() %> ~ <%=mc.getEndDate() %>)</span></li>
            	<% }%>
            </ul>
           	<% }%>
        </div>
        <div class="img">
        	<!-- 해당일에 획득한 뱃지 -->
            <span class="scroll-span-badge">획득한 뱃지 </span>
            <%if(mBadgeList != null){ %>
            <ul>
            	<% for(MypageBadge mb : mBadgeList) {
            		System.out.println(mb.getBadgeImg());
            	%>
                	<li><img src="<%=request.getContextPath() %>/image/뱃지/<%= mb.getBadgeImg() %>" alt=""></li>
            	<% }%>
            </ul>
           	<% }%>
            
        </div>
    </div>

<script type="text/javascript">

//페이지 로드시
$(document).ready (function(){
	var $ele= $('li:contains("<%= m_date %>")').css('color', '#81D4FA');
	
	var $li = $('.header > li');
	$li.attr('onclick','click_date(event)');
});

//클릭 이벤트(데이터 비동기 처리)
function click_date(event){
	var $ele = $(event.target);
	$('li').css('color', 'black');	//요소 색상 초기화
	$ele.css('color', '#81D4FA');   //해당 요소 색변경
	
	var $str = $ele.text()
	var $month =$str.slice(0,1);
	var $date =$str.slice(-3,$str.length-1);
	console.log($month);
	console.log($date);
	location.href="<%= request.getContextPath()%>/mypage/mypagePopup?month="+ $month + "&date="+ $date;
	
	
	
	//비동기 처리

}
</script>
</body>

</html>