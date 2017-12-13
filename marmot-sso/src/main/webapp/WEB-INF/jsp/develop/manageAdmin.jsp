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
    
    <title>开发者管理管理员</title>
    
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
			function deleteGroup(id) {
			$.ajax({
				url : "app/deleteGroup",
				data : {
					groupid : id,
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
		
		/* $("#doSerach").click(function() {
			$.ajax({
				url : "app/findAdmin",
				data : {
					"name" : $("#serachName").val()
				},
				dataType : "json",
				type : 'POST',
				success : function(data) {
					if (data["isSuccess"]) {
						alert(data["data"]["userId"]);
					} else {
						alert("申请失败");
					}
				}
			});
		}); */
		
		function deleteAdmin(id) {
			$.ajax({
				url : "app/deleteAdmin",
				data : {
					userid : id,
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
		function insertGroup() {
			$.ajax({
				url : "app/insertGroup",
				data:$('#insertGroupForm').serialize(),
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
		function insertAdmin() {
			$.ajax({
				url : "app/insertAdmin",
				data:$('#insertAdminForm').serialize(),
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
	</script>
  </head>
  
  <body>
   <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<!-- 头部  开始 -->
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
								<li>
									<a id="modal-2015" href="#modal-container-2015" role="button" data-toggle="modal">添加子页面</a>
								</li>
								<li>
									<a id="modal-2016" href="#modal-container-2016" role="button" data-toggle="modal">添加管理员</a>
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
			<!-- 头部  结束 -->
			<div style="text-align: center"><h2>【注意】：应用唯一标识ID=${appid}</h2></div>
			<!-- 子系统页面地址  开始 -->
			<div class="page-header" style="text-align: center;margin-top: 50px">
				<h2>子系统各个页面地址  </h2>
				<table class="table">
				<thead>
					<tr>
						<th style="width: 300px;">
							页面编号
						</th>
						<th style="width: 600px;">
							页面地址
						</th>
						<th style="width: 200px;">
							删除
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${groups}" var="group" varStatus="status">
						<tr class=<c:if test="${status.index==0}">"success"</c:if>
							<c:if test="${status.index==1}">"error"</c:if>
							<c:if test="${status.index==2}">"warning"</c:if>
							<c:if test="${status.index==3}">"info"</c:if>>
							<td>${group.groupId}</td>
							<td>${group.groupUrl}</td>
							<td>
								<a class="btn btn-info  btn-large"  onclick="deleteGroup(${group.groupId})">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<!-- 子系统页面地址 结束 -->
			
			<!-- 查找管理员  开始 -->
		<!-- 	<div >
			<form action="" class="form-search"  id="serchForm">
				<input class="input-medium search-query" type="text"  id="serachName" /> <button type="button" id="doSerach" class="btn">查找</button>
			</form>
				<div id="serchAdmin"></div>
			</div> -->
			<!-- 查找管理员  结束 -->
			
			<!-- 管理员管理  开始 -->
			<div class="page-header" style="text-align: center;margin-top:50px">
				<h2>管理员管理</h2>
				<table class="table">
				<thead>
					<tr>
						<th style="width: 300px;">
							管理员ID
						</th>
						<th style="width: 600px;">
							管理员姓名
						</th>
						<th style="width: 200px;">
							删除管理员
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${admins}" var="admin" varStatus="status">
						<tr class=<c:if test="${status.index==0}">"success"</c:if>
							<c:if test="${status.index==1}">"error"</c:if>
							<c:if test="${status.index==2}">"warning"</c:if>
							<c:if test="${status.index==3}">"info"</c:if>>
							<td>${admin.userId}</td>
							<td>${admin.userName}</td>
							<td>
								<a class="btn btn-info  btn-large"  onclick="deleteAdmin(${admin.userId})">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<!-- 管理员管理 结束 -->
			
			
		</div>
	</div>
	
	
	<!--添加子页面  开始-->
		<div id="modal-container-2015" class="modal hide fade" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3 id="myModalLabel">添加子页面</h3>
			</div>
			<div class="modal-body">
				<form action="app/insertGroup" method="post" id="insertGroupForm">
						<input type="hidden" name="appid" value="${appid}">
						<input type="text" name="url">
						<input   data-dismiss="modal" aria-hidden="true" type="submit" value="提交"  onclick="insertGroup()">
				</form>
			</div>
			<!-- <div class="modal-footer">
				<button id="" class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			</div> -->
		</div>
		<!--添加子页面  结束-->
		
		
		
		<!--添加管理员  开始-->
		<div id="modal-container-2016" class="modal hide fade" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3 id="myModalLabel">添加管理员</h3>
			</div>
			<div class="modal-body">
				<form action="app/insertAdmin" method="post" id="insertAdminForm">
						<input type="hidden" name="appid" value="${appid}">
						<input type="text" name="userName">
						<input   data-dismiss="modal" aria-hidden="true" type="submit" value="提交"  onclick="insertAdmin()">
				</form>
			</div>
			<!-- <div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			</div> -->
		</div>
		<!--添加管理员  结束-->
		
  </body>
</html>
