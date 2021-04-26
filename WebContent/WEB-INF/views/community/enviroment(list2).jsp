<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환경 칼럼2</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/enviorment(list2).css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file ="/WEB-INF/views/common/containerBar.jsp"%>
<div class="main_container">
            <h1 class="main_container-h1">
                [환경일보]내 차마련, 괜찮지 않아요.</h1>
            <hr class="main_container-hr">
            <br>
            <p class="text">
            	<div class="img">
                <img src="<%=request.getContextPath() %>/image/환경/이동수단.png" alt="">
                </div>
                <br><br>
                <h1>
				이동수단
				</h1>
	
                <hr class="main_container-hr"> <br>
			
			<br>
            </p >
            <div class="main_container-text ">
자동차를 사용하면 연료의 미연소 부분 및 기타 연소 생성물이 대기 중으로 방출됨으로써 환경을 오염시키는 대기오염이 발생한다.<br>
이산화탄소의 증가로 인하여 지구의 온난화, 프레온 가스로 인한 오존층의 파괴 등이 문제시되고 있다.<br>
주요 오염물질의 2014년 대기 중 농도는 미세먼지(PM10) 51.4㎍/㎥, 초미세먼지(PM2.5) 33.0㎍/㎥, 이산화질소(NO₂) 30.0ppb, 오존(O₃) 75.0ppb로 높아지면서 세계보건기구(WHO) 권고기준(미세먼지 20㎍/㎥, 초미세먼지 10㎍/㎥, 이산화질소 21ppb, 오존 50ppb)을 크게 넘어서는 것으로 분석됨.<br>	
</div>			
<br><br><br>
<div class="main_container-text1 ">
<h4>해결방안</h4><br />
<img src="<%=request.getContextPath() %>/image/환경/환경의날(자전거).JPG" alt="" />
<div class="main_container-text2">
중형차 기준 가득 기름을 채우면 101kg의 이산화 탄소가 배출이 된다.
<br>자가용 대신 <mark class="main_container-mark"> 자전거 이용 또는 걸으면 배출한 이산화 탄소를 흡수 하기 위한 1그루의 소나무를 보호할 수 있다.</mark> 
</div>           
     </div>       
            </p>
            
         </div>

    </div>

</body>

</html>           