<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String saveId =null;
	Cookie[] cookies =request.getCookies();
	if(cookies != null ){
		for(Cookie c : cookies){
		String name =c.getName();
		String value = c.getValue();
		System.out.println(name + " : " + value);
		if("saveId".equals(name))
			saveId = value;
		}
	}
	%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/Login.css">
<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<!--Alert 문구 디자인 추가 -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<!-- 카카오 API  -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<!-- 네이버 API javaScript SDK -->
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js"
	charset="utf-8">
    </script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<!--  googleAPI -->
<meta name="google-signin-client_id"
	content="316922979251-8ur0ssmroe9utethkm88kgbtlhs7a9lj.apps.googleusercontent.com">
<script src="<%=request.getContextPath() %>/js/Login.js" charset="utf-8"></script>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!-- msg, LoginMember 포함 -->
<script>
<% if(msg != null) { %> 
	alert("<%= msg %>"); 
<% } %>
</script>

	<div class="Container-bar">
		<ul>
			<li class="Container-bar-li-left" style="font-size: 30px;"><a
				href="#"><span class="title">로그인</span></a></li>
		</ul>
	</div>
	
	<div class="Login">
		<h2 class="Login-h2">Login</h2>
		<% if(loginMember ==null) { %>
		<form id="loginFrm" action="<%=request.getContextPath()%>/login/login" method="POST">
			<!-- LoginServlet -->
			<div class="Board">
				<span class="Board-icon"><i class="far fa-id-badge"	style="font-size: 80px;"></i></span>
				<div class="form">
					<input type="text" name="memberId" id="memberId" placeholder="ID"
						  value="<%= saveId != null ? saveId : "" %>">
				</div>
			</div>
			<div class="Board-Pw">
				<span class="Board-icon1"><i class="fas fa-key"
					style="font-size: 80px;"></i> </span>
				<div class="form-Pw">
					<input type="password" id="memberPw" name="memberPw" placeholder="●●●●">
				</div>
			</div>
			<div class="Board-chk">
				<input type="checkbox" name="saveId" id="saveId"
				<%= saveId != null ? "checked" : ""%>/>
				<label><span class="Board-chk-span">아이디저장(Save ID)</span></label>
			</div>
		</form>
		
		<div class="Board-chk-sns">
			<div class="SNS-h3">
				<h2 class="SNS">소셜로그인</h2>
			         <!-- 구글 로그인 -->
            <div class="Google">
               <div class="g-signin2" data-onsuccess="onSignIn"></div>
               <script>
               function onSignIn(googleUser) {
                  var profile = auth2.currentUser.get()
                           .getBasicProfile();
                  var id = "G_" + profile.getId();
                  var name = profile.getName();
                  var nickname = profile.getName();
                  var email = profile.getEmail();
                  var mobile = null;
                  snsloginMember(id, name, email, nickname, mobile);

               }
               </script>
            </div>
				<!-- 카카오 로그인 -->
				<div class="SNS_img" id=" kakaoLogin">
					<a onclick="kakaoLogin();"><img
						src="<%=request.getContextPath() %>/image/로그인/KakaoTalk.png" style="height:35px; width: 35px;"></a>
				</div>
		
				<!-- 네이버 로그인-->
				<div id="naverIdLogin" class="naver">
					<script type="text/javascript">
						var naverLogin = new naver.LoginWithNaverId({
							clientId:"eLBlBHe_gYjPyfWTIjf9",
							callbackUrl:"http://localhost:9090/onedayeco/login/callback",
							isPopup:true,
 							loginButton : {color: "green", type:1, height:35, width:35}
						});
						naverLogin.init();
					</script>
				</div>
			</div>
			<div class="btn-wrapper">
				<div class="btn">
	                 <input type="button" name="Login" id="Login" value="LOGIN" onclick="loginFrmSubmit();">
				</div>
		        <div class="btn1">
		            <a href="<%=request.getContextPath()%>/login/member_sing_up">
		                <input type="button" name="Member" id="Member" value="Sing up" >
		            </a>
		        </div>
	        </div>
			<%} else{%>
			<h1 class="loginLable-h1">사용자정보</h1>
			<img class="loginLable-img"
				src="<%=request.getContextPath() %>/image/로그인/로그아웃.png" alt="" />
			<div id="loginLable">
				<h2 class="loginLable-h2"><%= loginMember.getMemberNickname() %>
					님 로그인하셨습니다.
				</h2>
				<% if((char)loginMember.getMemberId().charAt(0) == 'G') {%>
				<!-- 로그인 한 멤버가 구글 멤버일때 -->
				<input type="button" value="로그아웃" onclick="signOut();" />
				<%} else{%>
				<input type="button" value="로그아웃" onclick="logout(); " />
				
				<%}%>
			</div>
			<%}%>
		</div>
	</div>


<script type="text/javascript">
	function loginFrmSubmit(){
 		$("#loginFrm").submit();
		console.log("loginFrm 제출");
	}
	function logout(){
		console.log("<%=request.getContextPath()%>"+"/logout");
		location.href="<%=request.getContextPath()%>"+"/logout";
	}   			
	var _auth2;
	window.onload = function(){
		console.log("gapi load 실행")
		if(!gapi.auth2){
			gapi.load('auth2',function(){
				_auth2= gapi.auth2.init({
			        clientId: "316922979251-8ur0ssmroe9utethkm88kgbtlhs7a9lj.apps.googleusercontent.com",
			        scope: 'profile email'							
				});
			});
			console.log('auth init!');
	  	}
	}
			
	function signOut() {
		_auth2.signOut().then(function () {
	      console.log('User signed out.');
	      logout();
	    });
		_auth2.disconnect();
	}
	
    function onSignIn() {
		var auth2 = gapi.auth2.getAuthInstance();
		if (auth2.isSignedIn.get()) {
			var profile = auth2.currentUser.get().getBasicProfile();
			var id = "G_" + profile.getId();
			var name = profile.getName();
			var nickname = profile.getName();
			var email = profile.getEmail();
			var mobile = null;
			snsloginMember(id, name, email, nickname, mobile);
		}
	}

	$(function() {
		/*
		 * 로그인 폼 유효성 검사 
		 */
		$("#loginFrm").submit(function() {
			var $memberId = $(memberId);
			var $memberPw = $(memberPw);
			if (/^.{4,}$/.test($memberId.val()) == false) {
				alert("유효한 아이디를 입력하세요.");
				$memberId.select();
				return false;
			}
			if (/^.{4,}$/.test($memberPw.val()) == false) {
				alert("유효한 비밀번호를 입력하세요.");
				$memberPw.select();
				return false;
			}
		});
	});
</script>
<!--  googleAPI -->
<script src="https://apis.google.com/js/platform.js" async defer></script>
</body>

</html>