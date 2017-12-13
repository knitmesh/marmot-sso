<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理员管理用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script
	src="http://cdn.bootcss.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/2.3.2/css/bootstrap.min.css">
	
</head>
<script type="text/javascript">

	function addRight() {
			$.ajax({
				url : "app/addRight",
				data:$('#addRightFrom').serialize(),
				dataType : "json",
				type : 'POST',
				success : function(data) {
						if (data["isSuccess"]) {
								alert(data["info"]);
							} else{
								alert(data["info"]);
							}
					location.reload();
				}
			});
		}
</script>
<body>

	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="navbar">
				<div class="navbar-inner" style="margin-top:20px;">
					<div class="container-fluid">
						 <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar">
						 				<span class="icon-bar"></span>
										<span class="icon-bar"></span>
										<span class="icon-bar"></span>
						</a> <a href="app/index" class="brand">权限管理系统</a>
						<div class="nav-collapse collapse navbar-responsive-collapse">
							<ul class="nav">
								<li class="active">
									<a href="javascript:void(0)">主页</a>
								</li>
								<li class="dropdown">
									 <a data-toggle="dropdown" class="dropdown-toggle" href="#">普通用户权限管理<strong class="caret"></strong></a>
									<ul class="dropdown-menu">
										<li class="divider">
										</li>
										<li class="nav-header">
											仅限管理员操作
										</li>
									</ul>
								</li>
							</ul>
							<ul class="nav pull-right">
								
								<li class="divider-vertical">
								</li>
								<li class="dropdown">
									 <a data-toggle="dropdown" class="dropdown-toggle" href="#">个人中心<strong class="caret"></strong></a>
									<ul class="dropdown-menu">
										<li>
											<a href="javascript:void(0)">用户信息</a>
										</li>
									
										<li class="divider">
										</li>
										<li>
											<a href="javascript:void(0)">退出</a>
										</li>
									</ul>
								</li>
							</ul>
						</div>
						
					</div>
				</div>
				
			</div>
			
			<div class="page-header" style="text-align: center;margin-top: 100px">
				<h1>选择用户的页面权限</h1>
			</div>
			
			<!-- 子系统页面地址  开始 -->
			<div class="page-header" style="margin-top: 50px;margin-left: 300px;">
					<form action="app/addRight" method="post" id="addRightFrom">
					<input type="hidden" name="appid" value="${appid}">
					<table>
					<ul>
							<li style="list-style-type:none;width=300px;">该子应用对应的权限页面</li>
					</ul>
					<c:forEach items="${groups}" var="group" >
							<li style="list-style-type:none;margin-top: 20px;">
										<input name="groupids" type="checkbox" value="${group.groupId}"/>
										${group.groupUrl}
						    </li> 
					</c:forEach>
					
					<tr>
						<td>输入用户名添加权限</td>
						<td><input type="text"  name="userName"></td>
					</tr>
					<tr>
						<td><input type="button" onclick="addRight()" value="提交"/></td>
						<td><input type="reset" value="重置"/></td>
					</tr>
					</table>
					</form>
			</div>
			<!-- 子系统页面地址 结束 -->
		</div>
	</div>
</div>

</body>
</html>
