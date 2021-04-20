<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src ="<%=request.getContextPath() %>/js/jquery-3.6.0.js"></script>

<!-- 네이버 API javaScript SDK -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"> </script>


<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<style>
.wrapper{
	text-align: center;
}
</style>
</head>

<body>
	<div class="wrapper">
	
	</div>
	
	
	<!-- 네이버 로그인 callback -->
	<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId({
		clientId:"eLBlBHe_gYjPyfWTIjf9",
		callbackUrl:"http://localhost:9090/onedayeco/login/callback",
        isPopup: false,
        callbackHandle: true
	});
    naverLogin.init();
	window.addEventListener('load',function(){
		naverLogin.getLoginStatus(function(status){
			if(status){
				//const token = naverLogin.oauthParams.access_token;
				//const accessToken =naverLogin.accessToken.accessToken;
				const accessToken =naverLogin.accessToken.accessToken;
				const id = "N_"+naverLogin.user.getId();
			    const name = naverLogin.user.getName();
			    const email = naverLogin.user.getEmail();
			    const nickname = naverLogin.user.getNickName();
			    const mobile = naverLogin.user.getMobile();
			    
			    //필수정보 체크
			    if(name == undefined || name == null){
			    	naverReprompt();
			    	return;
			    }
			    else if(email == undefined || email == null){
			    	naverReprompt();
			    	return;
			    }
			    else if(nickname == undefined || nickname == null){
			    	naverReprompt();
			    	return;
			    }
			    else if(mobile == undefined || mobile == null){
			    	naverReprompt();
			    	return;
			    }
			    //비동기로 db전송
			    $.ajax({
			    	url:"<%=request.getContextPath()%>/login",
			    	method :"POST",
  					data :{
  						id : id,
  						name : name,
  						email : email,
  						nickname : nickname,
  						mobile : mobile
					},
                    success : function(data){
                    	console.log(data);
        			    $(".wrapper").append("<h2>로그인 성공</h2>");
        			    
                        //팝업창이 아닌 최상위(팝업을 연 url)에 location 지정        			    
        			    top.opener.location="<%= request.getContextPath() %>/login/snsLogin?id="+id;
        			    window.close();

                    },
            		error : function(xhr, status, err){
            			console.log(xhr, status, err);
            		}

			    });
			}
			else{
			    $(".wrapper").append("<h2>로그인 실패</h2>");
			}
		});
	});
	
	function naverReprompt(){
    	alert("필수 정보제공을 동의해주세요");
    	naverLogin.reprompt();
	}
	
	</script>

</body>
</html>