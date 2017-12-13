<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>sso超管对应用审批</title>

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
<script type="text/javascript">
	/* $(document).ready(function(){ */
	
	function agreeApply(id) {
	/*  var value=$(this).attr('id');
	 alert(value); */
			$.ajax({
				url : "app/changeState",
				data : {
					appid : id,
					state : 1
				},
				dataType : "json",
				type : 'POST',
				success : function(data) {
						if (data["isSuccess"]) {
								alert(data["info"]);
							} else{
								alert("申请失败");
							}
					location.reload();
				}
			});
		}
		function refuseApply(id) {
			$.ajax({
				url : "app/changeState",
				data : {
					appid : id,
					state : 2
				},
				dataType : "json",
				type : 'POST',
				success : function(data) {
				if (data["isSuccess"]) {
								alert(data["info"]);
							} else{
								alert("申请失败");
							}
					location.reload();
				}
			});
		}
	/* }); */
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="navbar">
					<div class="navbar-inner" style="margin-top:20px;">
						<div class="container-fluid">
							<a data-target=".navbar-responsive-collapse"
								data-toggle="collapse" class="btn btn-navbar"> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
							</a> <a href="app/index" class="brand">权限管理系统</a>
							<div class="nav-collapse collapse navbar-responsive-collapse">
								<ul class="nav">
									<li class="active"><a href="javascript:void(0)">主页</a></li>
									<!-- <li>
									<a href="app/check">应用审批</a>
								</li> -->
								</ul>
								<ul class="nav pull-right">

									<li class="divider-vertical"></li>
									<li class="dropdown"><a data-toggle="dropdown"
										class="dropdown-toggle" href="#">个人中心<strong class="caret"></strong></a>
										<ul class="dropdown-menu">
											<li><a href="javascript:void(0)">用户信息</a></li>

											<li class="divider"></li>
											<li><a href="javascript:void(0)">退出</a></li>
										</ul></li>
								</ul>
							</div>

						</div>
					</div>

				</div>


			</div>
		</div>

		<!--应用审批列表	  开始-->
		<div class="page-header" style="text-align: center;margin-top: 50px">
			<h1>子应用审批列表</h1>
			<table class="table" style="margin-top: 100px;">
				<thead>
					<tr>
						<th>子系统ID</th>
						<th>子系统名称</th>
						<th>子系统返回地址</th>
						<th>申请时间</th>
						<th>更改时间</th>
						<th>子系统状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${apps}" var="app" varStatus="status">
						<tr class=<c:if test="${status.index==0}">"success"</c:if>
							<c:if test="${status.index==1}">"error"</c:if>
							<c:if test="${status.index==2}">"warning"</c:if>
							<c:if test="${status.index==3}">"info"</c:if>>
							<td>${app.appId}</td>
							<td>${app.appName}</td>
							<td>${app.appInfo}</td>
							<td><fmt:formatDate value="${app.createTime}" type="both"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${app.modifyTime}" type="both"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><c:if test="${app.appState==0}">不可用</c:if> <c:if
									test="${app.appState==1}">已可用</c:if></td>
							<td>
								<%-- <a class="btn btn-info  btn-large" href="app/${app.appId}/changeState/1">同意</a>  --%>
								<a class="btn btn-info  btn-large" id="${app.appId}" onclick="agreeApply(${app.appId})">同意</a>
								<%-- <button value="${app.appId}" onclick="hehe()"/> --%>
							</td>
							<td>
								<%-- <a class="btn btn-success btn-large" href="app/${app.appId}/changeState/2">拒绝/禁用</a> --%>
								<a class="btn btn-success btn-large" onclick="refuseApply(${app.appId})">拒绝/禁用</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	<!--应用审批列表	  结束-->
		
		
		
	</div>
</body>
</html>
