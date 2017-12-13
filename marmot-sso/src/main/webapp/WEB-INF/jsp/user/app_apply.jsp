<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>普通用户应用申请</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script
	src="http://cdn.bootcss.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/2.3.2/css/bootstrap.min.css">

<script type="text/javascript">
	$(function() {

		$("#applySubmit").click(
				function() {
					$.ajax({
						type : "POST",
						url : "app/apply",
						data : $('#appApplyForm').serialize(),// 你的formid
						success : function(data) {
							if (data["isSuccess"]) {
								alert(data["info"]);
							} else{
								alert("申请失败");
							}
						}
					});
				});

		/* $("#modal-2016").click(
				function() {
					$.ajax({
						type : 'GET',
						url : "app/state",
						data : {
							userid : $("#developId").val()
						},
						dataType : 'json',
						success : function(data) {
							if (data["isSuccess"]) {
								$('#applyRueult').html(
										"申请成功" + "应用ID：" + data["data"]);
							} else {
								$('#applyRueult').html(data["error"]);
							}
						}
					});
				}); */
	});
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
							</a> <a href="javascript:void(0)" class="brand">权限管理系统</a>
							<div class="nav-collapse collapse navbar-responsive-collapse">
								<ul class="nav">
									<li class="active"><a href="app/index">主页</a></li>
									<li><a id="modal-2015" href="#modal-container-2015"
										role="button" data-toggle="modal">应用申请</a></li>
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

				<div class="hero-unit" style="margin-top:200px;">
					<h1>欢迎进入APP申请系统</h1>
					<p>
						随着计算机技术的进步，管理系统也从传统的管理科学的范畴延伸到了软件技术的范畴。随着国际的salesforce、Oracle、国内的八百客、神码、管理123、百会、金智软件、金蝶、用友、速达、管家婆等一批知名的管理系统建设商的兴起，“管理系统”更多地以ERP、SOA、SAAS等字眼出现在普及的各种场合.
					</p>
				</div>

			</div>
		</div>



		<!--应用申请  开始-->
		<div id="modal-container-2015" class="modal hide fade" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3 id="myModalLabel">申请成为子应用</h3>
			</div>
			<div class="modal-body">
				<form action="app/apply" method="post" style="margin-left:100px;"
					id="appApplyForm">
					<table>
						<thead>
							<tr>
								<td>默认调回地址</td>
								<td><input type="text" name="appInfo" /></td>
							</tr>
							<tr>
								<td>应用名</td>
								<td><input type="text" name="appName" /></td>
							</tr>
							<tr>
								<td>开发人员手机</td>
								<td><input type="text" name="appPhone" /></td>
							</tr>
							<tr>
								<td>申请用户ID</td>
								<td><input type="text" name="userid" id="developId"
									value="${userid}" /></td>
							</tr>
						</thead>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" data-dismiss="modal"
					aria-hidden="true" id="applySubmit">保存</button>
			</div>
		</div>
		<!--应用申请  结束-->

		<!--申请结果  开始-->
		<!-- <div id="modal-container-2016" class="modal hide fade" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3 id="myModalLabel">应用申请结果</h3>
			</div>
			<div class="modal-body">
				<table>
					<thead>
						<tr>
							<h2><p id="applyRueult"></p></h2>
						</tr>
					</thead>
				</table>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			</div>
		</div> -->
		<!--申请结果  结束-->


		<%-- <!--添加页面和管理员    开始-->
		<div id="modal-container-2017" class="modal hide fade" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3 id="myModalLabel">添加子页面和管理员</h3>
			</div>
			<div class="modal-body">
				<form action="app/insertPage" method="post" style="margin-left:100px;"
					id="appApplyForm">
					<table>
						<thead>
							<tr>
								<td>添加页面连接</td>
								<td><input type="text" name="urls" /></td>
							</tr>
							<tr>
								<td>添加管理员</td>
								<td><input type="text" name="userName"/></td>
							</tr>
							<input type="hidden" name="userid" value="${userid}"/>
						</thead>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" data-dismiss="modal"
					aria-hidden="true" id="applySubmit">保存</button>
			</div>
		</div>
		<!--添加页面  结束--> --%>
	</div>
</body>
</html>
