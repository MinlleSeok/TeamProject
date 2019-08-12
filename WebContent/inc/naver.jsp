<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<div id="naverIdLogin"></div>
<script type="text/javascript">
	    var naverLogin = new naver.LoginWithNaverId({
	        clientId: "WHKw6TzMKV_kjWOzr7Aa",
	        callbackUrl: "http://localhost:8090/mmProject/member/callback.jsp",
	        isPopup: false,
	        loginButton: {color: "green", type: 3, height: 45}
	    });
	    naverLogin.init();
</script>