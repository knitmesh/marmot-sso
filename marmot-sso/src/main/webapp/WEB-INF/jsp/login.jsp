<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>SSO登录接口</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- Custom Theme files -->
<link href="/../css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<!--Google Fonts-->
<link
	href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<!--Google Fonts-->

</head>
<body>
	<div class="login">
		<h2>SSO单点登录</h2>
		<div class="login-top">
			<h1>请输入账号密码</h1>
			<form action="user/login" method="post">
				<input type="hidden" name="appid" value="${appid}"> <input
					type="hidden" name="requestUrl" value="${requestUrl}"> <input
					type="text" name="userName"> <input type="text"
					name="userPassword">

				<div class="forgot">
					<a href="#">忘记密码</a> <input type="submit" value="登录">
				</div>
			</form>
		</div>
		<div class="login-bottom">
			<h3>
				新用户 &nbsp;<a href="#">注册</a>&nbsp
			</h3>
		</div>
	</div>
	<div class="copyright">
		<p>Copyright &copy; 2016. All rights reserved</p>
	</div>


</body>
</html>