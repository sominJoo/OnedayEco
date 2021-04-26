<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환경 칼럼4</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/enviorment(list4).css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file ="/WEB-INF/views/common/containerBar.jsp"%>
<div class="main_container">
            <h1 class="main_container-h1">
                [환경일보]전기 이렇게 사용해도 될까?</h1>
            <hr class="main_container-hr">
            <br>
            <p class="text">
            	<div class="img">
                <img src="<%=request.getContextPath() %>/image/환경/전기.png" alt="">
                </div>
                <br><br>
                <h1 class="img-h1">
				이동수단
				</h1>
	
                <hr class="main_container-hr"> <br><br>
					<div class="img1">
                <img src="<%=request.getContextPath() %>/image/환경/이 현황이 유지되었을 때의 결과.png" alt="">
                </div>
                <br><br><br><br>
			<br>
            </p>
           <div class="text-div">
고도의 경제성장과 함께 대량의 에너지를 화석연료로부터 얻기 위한 방대한 이산화탄소를 배출하고 있으며 이것이 지구 온난화 문제를 야기했다.<br>
<br>
 이 현황이 유지되었을 때의 결과<br>

온실가스 2030년 37%, 2050년 52% 증가<br>

연간 이산화탄소의 배출량 1,000억 톤까지 증가<br>

→ 빙하 감소 뿐 아니라 홍수, 폭풍우, 집중호우 등의 재난 발생<br>
<br><br><br><br>
</div>
<div class="text-div2">
<img src="<%=request.getContextPath() %>/image/환경/전기절약(일러스트).jpg" alt="" />
</div>
<div class="text-div2-text">
<h4>해결방안</h4> <br>
<mark class="mark">1. 안쓰는 대기전력 차단, 낮시간대 자연광으로 생활</mark><br>

(일인당 한달 평균 849kwh의 전력사용 → 4320kg의 이산화탄소 배출)<br>

→ 한달 평균 전력사용량의 10%만 줄여도 → 배출한 이산화탄소를 흡수하기 위한 4.6그루의 소나무 보호<br>
<br>
<mark class="mark">2. 실내온도 적당히 유지</mark><br>

실내온도를 1도 낮추면 연간 231kg의 Co2 감소 → 총 2그루 이상의 소나무 보호<br>          
     </div>
            </p>
            
         </div>

    </div>

</body>

</html>           