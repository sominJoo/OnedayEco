<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Login.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!--Alert 문구 디자인 추가 -->
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    
    <!-- 카카오 API  -->
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    
    <!-- 네이버 API javaScript SDK -->
    <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8">
    </script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <!--  googleAPI -->
    <meta name="google-signin-client_id"
        content="316922979251-8ur0ssmroe9utethkm88kgbtlhs7a9lj.apps.googleusercontent.com">
    <script src="<%=request.getContextPath() %>/js/Login.js" charset="utf-8"></script>

	<%@ include file ="/WEB-INF/views/common/header.jsp" %>       
        <script type="text/javascript">
			function logout(){
				location.href = "<%= request.getContextPath()%>/logout";
			}    
			
			function signOut(){
				var auth2 = gapi.auth2.getAuthInstance();
				auth2.signOut().then(function(){
					console.log("signout");
				});
				auth2.disconnect();
			}
			function onLoad(){
				gapi.load('auth2',function(){
					gapi.auth2.init();
				});
			}
		</script>	
        <div class="Container-bar">
            <ul>
                <li class="Container-bar-li-left" style="font-size: 30px;"><a href="#"><span
                            class="title">로그인</span></a>
                </li>
            </ul>
        </div>
        <div class="Login">
            <h2 class="Login-h2">
                Login </h2>
        
        	<% if(loginMember ==null) { %>

            <div class="Board">
                <span class="Board-icon"><i class="far fa-id-badge" style="font-size: 100px;"></i></span>

                <div class="form">
                    <input type="text" id="userName" placeholder="ID">
                </div>
            </div>
            <div class="Board-Pw">
                <span class="Board-icon1"><i class="fas fa-key" style="font-size: 100px;"></i> </span>
                <div class="form-Pw">
                    <input type="text" id="userPassword" placeholder="●●●●">
                </div>
                <div class="Board-chk">
                    <label><input type="checkbox" name="submit" value=" Board-chk"><span
                            class="Board-chk-span">아이디저장(Save ID)</span></label>
                </div>
            </div>
            
            <div class="Board-Pw">
                            
                <div class="SNS-h3">
                    <h2 class="SNS">소셜로그인</h2>
                    <!-- 구글 api -->
                    <div class="Google">
                        <div class="g-signin2" onclick="onSignIn();">
                            <script>
                                function onSignIn() {
                                	var auth2= gapi.auth2.getAuthInstance();
                                	if(auth2.isSignedIn.get()){
                                        var profile = auth2.currentUser.get().getBasicProfile();
                                        var id = "G_"+profile.getId();
                                        var name = profile.getName();
                                        var nickname = profile.getName();
                                        var email =  profile.getEmail();
                                        var mobile = null;
                                        snsloginMember(id,name,email,nickname,mobile);                                		
                                	}
                                }

                            </script>

                        </div>
                    </div>
 					<div class="SNS_img" id=" kakaoLogin">
                        <a onclick="kakaoLogin();"><img src="<%=request.getContextPath() %>/image/로그인/KakaoTalk.png" alt=""></a>
                    </div>
                    
                    <!-- 네이버 로그인-->
                    <div id="naverIdLogin" class="naver">
						<script type="text/javascript">
							var naverLogin = new naver.LoginWithNaverId({
								clientId:"eLBlBHe_gYjPyfWTIjf9",
								callbackUrl:"http://localhost:9090/onedayeco/login/callback",
								isPopup:true,
								loginButton : {color: "green", type:1, height:40}
							});
							naverLogin.init();
						</script>
		
		                <h4 class="Find-Id">아이디 찾기(Find Id)</h4>
		                <h4>비밀번호 찾기(Find Pw)</h4>
                    </div> 
                </div>
                
                
                <div class="btn">
                    <input type="button" name="Login" id="Login" onclick="Sing_UP();" value="로그인">
                </div>
                <div class="btn1">
                    <a href="Member_Sing_Up.html">
                        <input type="button" name="Member" id="Member" value="회원가입">
                    </a>
                </div>
            </div>
            <%} else{%>
				<div id="loginLable">
					<p><%= loginMember.getMemberNickname() %> 님 로그인하셨습니다.</p>
					<% if((char)loginMember.getMemberId().charAt(0) == 'G') {%>	<!-- 로그인 한 멤버가 구글 멤버일때 -->
					<input type="button" value="로그아웃" onclick="signOut();"  />	
					
            		<%} else{%>
					<input type="button" value="로그아웃" onclick="logout(); " />	
           			<%}%>
				</div>
            <%}%>
           
        </div>


    
	    <!--  googleAPI -->
	   	<script src="https://apis.google.com/js/platform.js" async defer></script>
</body>

</html>