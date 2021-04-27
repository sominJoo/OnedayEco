<%@page import="onedayeco.article.model.vo.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<%
	Article article= (Article)request.getAttribute("article");
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인 페이지 </title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css">

    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		
	<%@ include file ="/WEB-INF/views/common/header.jsp" %>
<%
   String[] rndList = (String[])request.getAttribute("rndList");
   String a = null;
   if((String)request.getAttribute("s") != null)
      a = (String)request.getAttribute("s");
%>
<script>
$(function(){
   <%if(a == null) { %>
      var form = document.forms["RndChallengeFrm"];
      form.submit();
   <% } %>
});
</script>
<form
   action="<%= request.getContextPath() %>/challenge/RandomChallenge"
   name="RndChallengeFrm"
   method="POST">
</form>

        <!--image slider start-->
        <div class="slider">
            <div class="slides">
                <!--radio buttons start-->
                <input type="radio" name="radio-btn" id="radio1">
                <input type="radio" name="radio-btn" id="radio2">
                <input type="radio" name="radio-btn" id="radio3">
                <input type="radio" name="radio-btn" id="radio4">
                <input type="radio" name="radio-btn" id="radio5">
                <!--radio buttons end-->
                <!--slide images start-->
                <!-- 일회용품 -->
                <div class="slide first">
                    <a href="<%=request.getContextPath()%>/community/enviroment3"><img src="<%= request.getContextPath() %>/image/메인페이지/배너m_일회용품.png" alt=""></a>
                </div>
                <!-- 배너전기 -->
                <div class="slide">
                    <a href="<%=request.getContextPath()%>/community/enviroment4"><img src="<%= request.getContextPath() %>/image/메인페이지/배너m_전기.png" alt=""></a>
                </div>
                <!-- img 파일 추가예정  -->
                <div class="slide">
                    <a href="<%=request.getContextPath()%>/community/enviroment5"><img src="<%= request.getContextPath() %>/image/메인페이지/배너m_제로웨이스트.png" alt=""></a>
                </div>
                <!-- img 파일 추가예정  -->
                <div class="slide">
                    <a href="<%=request.getContextPath()%>/community/enviroment1"><img src="<%= request.getContextPath() %>/image/메인페이지/배너m_음식.png" alt=""></a>
                </div>
                <!-- img 파일 추가예정  -->
                <div class="slide">
                    <a href="<%=request.getContextPath()%>/community/enviroment2"><img src="<%=request.getContextPath()%>/image/메인페이지/배너m_이동수단.png" alt=""></a>
                </div>
                <!--slide images end-->
                <!--automatic navigation start-->
                <div class="navigation-auto">
                    <div class="auto-btn1"></div>
                    <div class="auto-btn2"></div>
                    <div class="auto-btn3"></div>
                    <div class="auto-btn4"></div>
                    <div class="auto-btn5"></div>
                </div>
                <!--automatic navigation end-->
            </div>
            <!--manual navigation start-->
            <div class="navigation-manual">
                <label for="radio1" class="manual-btn"></label>
                <label for="radio2" class="manual-btn"></label>
                <label for="radio3" class="manual-btn"></label>
                <label for="radio4" class="manual-btn"></label>
                <label for="radio5" class="manual-btn"></label>
            </div>
            <!--manual navigation end-->
        </div>
        <!--image slider end-->
         <!--챌린지  start-->
        <div class="Box">
           <h1 class="Box-h1">
           <img  class ="Box-h1-img" src="<%=request.getContextPath() %>/image/메인페이지/챌린지.png" alt="" />챌린지</h1>
        
       <% 
         if(rndList != null) { 
            for(String s : rndList){
      %>
         <h3><%= s %></h3>
      <% 
            }
          }
       %> 
               </div>

        <!-- 챌린지 end -->
        <!-- 인기게시물 start -->
        <div class=" Box1">
         <h1>article</h1>
            <h2 class="Box1-h2"><img src="<%=request.getContextPath() %>/image/메인페이지/글번호(페이지).png" alt="" /><a href="<%=request.getContextPath()%>/board/boardView">Current Article</a></h2>
            <h2 class="Box1-h2"><img src="<%=request.getContextPath() %>/image/메인페이지/조회수(페이지).png" alt="" /><a href="<%=request.getContextPath()%>/board/boardCount">Hot Article</a></h2>
        	
        </div>
        <!-- 인기게시물 end -->

        <script type="text/javascript">
            var counter = 1;
            setInterval(function () {
                document.getElementById('radio' + counter).checked = true;
                counter++;
                if (counter > 5) {
                    counter = 1;
                }
            }, 5000);
        </script>
        
      
</body>

</html>