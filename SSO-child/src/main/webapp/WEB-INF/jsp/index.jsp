<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>子系统信息展示页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/2.3.2/css/bootstrap.min.css">

  </head>
  
  <body>
    	<div class="container-fluid" style="margin-top: 100px;">
	<div class="row-fluid">
		<div class="span12">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="javascript:void(0)">首页</a>
				</li>
				<li>
					<a href="javascript:void(0)">资料</a>
				</li>
				<li class="disabled">
					<a href="javascript:void(0)">信息</a>
				</li>
				<li class="dropdown pull-right">
					 <a href="#" data-toggle="dropdown" class="dropdown-toggle">下拉<strong class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li>
							<a href="javascript:void(0)">操作1</a>
						</li>
						<li>
							<a href="javascript:void(0)">操作2</a>
						</li>
						<li class="divider">
						</li>
						<li>
							<a href="user/logout">退出</a>
						</li>
					</ul>
				</li>
			</ul>
			<table class="table" style="margin-top: 200px;">
				<thead>
					<tr>
						<th style="width: 100px;text-align: center;">
							编号
						</th>
						<th style="width: 700px;text-align: center;">
							连接
						</th>
						<th style="width: 100px;text-align: center;">
							可点击的连接
						</th>
					</tr>
				</thead>
				<tbody>
					<tr class="success">
						<td style="width: 100px;text-align: center;">
							1
						</td>
						<td style="width: 700px;text-align: center;">
							<a href="user/1">user/1</a>
						</td>
						<td style="width: 100px;text-align: center;">
							有权限的连接
						</td>
					</tr>
					<tr class="error">
						<td style="width: 100px;text-align: center;">
							2
						</td>
						<td style="width: 700px;text-align: center;">
							<a href="user/2">user/2</a>
						</td>
						<td style="width: 100px;text-align: center;">
							无权限的连接
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
  </body>
</html>
