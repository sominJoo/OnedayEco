/* 카카오  API */
window.Kakao.init("3f0d652148f8984db7971245d77e040b");

var contextPath = "${pageContext.request.contextPath}";
	
function kakaoLogin() {
    Kakao.Auth.login({
        scope: 'profile,account_email,gender',
        success: function (authObj) {
        	console.log(JSON.stringify(authObj));

        	Kakao.API.request({
                url: '/v2/user/me',
                success: res => {
                    const kakao_account = res.kakao_account;
                    var id = "K_"+res.id;
                    var email = kakao_account.email;
                    var name =kakao_account.profile.nickname ==null ? "이름없음" :  kakao_account.profile.nickname;
                    var nickname =kakao_account.profile.nickname ==null ? "이름없음" :  kakao_account.profile.nickname;
                    var mobile= null;
    			    //비동기로 db전송
                    snsloginMember(id,name,email,nickname,mobile);
                }
            });
        },
    	fail :function(error){              
    		alert(JSON.stringify(error));
    	}
    });
}
/* 2173bb391b0a91ded25693c6149528c0  Java Script */
function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}

/*JS Alert 로그인  디자인 */
function Sing_UP() {
    Swal.fire({
        position: 'top-bottom',
        icon: 'success',
        title: 'Your work has been saved',
        showConfirmButton: false,
        timer: 1500
    })
}

function snsloginMember(id,name,email,nickname,mobile){
    $.ajax({
    	url: getContextPath()+"/login",
    	method :"POST",
			data :{
				id : id,
				name : name,
				email : email,
				nickname : nickname,
				mobile : mobile
		},
        success : function(data){            
		    location.href=getContextPath()+"/login/snsLogin?id="+id;
        },
		error : function(xhr, status, err){
			console.log(xhr, status, err);
		}

    });
}
