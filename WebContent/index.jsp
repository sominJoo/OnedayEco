<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인 페이지 </title>
    <link rel="stylesheet" href="css/index.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		
	<%@ include file ="/WEB-INF/views/common/header.jsp" %>

        <!--image slider start
        <div class="slider">
            <div class="slides">
                <input type="radio" name="radio-btn" id="radio1">
                <input type="radio" name="radio-btn" id="radio2">
                <input type="radio" name="radio-btn" id="radio3">
                <input type="radio" name="radio-btn" id="radio4">
                <input type="radio" name="radio-btn" id="radio5">
                <div class="slide first">
                    <img src="image/메인페이지/배너m_일회용품.png" alt="">
                </div>
                <div class="slide">
                    <img src="image/메인페이지/배너m_전기.png" alt="">
                </div>
                <div class="slide">
                    <img src="image/메인페이지/배너m_제로웨이스트.png" alt="">
                </div>
                <div class="slide">
                    <img src="image/메인페이지/배너m_음식.png" alt="">
                </div>
                <div class="slide">
                    <img src="image/메인페이지/배너m_이동수단.png" alt="">
                </div>
                <div class="navigation-auto">
                    <div class="auto-btn1"></div>
                    <div class="auto-btn2"></div>
                    <div class="auto-btn3"></div>
                    <div class="auto-btn4"></div>
                    <div class="auto-btn5"></div>
                </div>
            </div>
            <div class="navigation-manual">
                <label for="radio1" class="manual-btn"></label>
                <label for="radio2" class="manual-btn"></label>
                <label for="radio3" class="manual-btn"></label>
                <label for="radio4" class="manual-btn"></label>
                <label for="radio5" class="manual-btn"></label>
            </div>
        </div>
        -->
        <!--image slider end-->
        <!--챌린지  start-->
        <div class=" Box">
        </div>
        <!-- 챌린지 end -->
        <!-- 인기게시물 start -->
        <div class=" Box1">
            <h1>인기 게시물</h1>
            <h2 class="Box1-h2">1.리스트 항목</h2>
            <h2 class="Box1-h2">2.리스트 항목</h2>
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