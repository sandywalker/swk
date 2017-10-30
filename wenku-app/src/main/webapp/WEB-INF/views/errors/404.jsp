<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../../taglibs.jsp"%>
<c:set var="ctx" value="<%=request.getContextPath()%>" scope="request" />
<c:set var="host" value="${ctx}" scope="request" />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- 无响应式布局的CSS -->
	<!--[if lt IE 9]>
	<script src="${ctx}/resources/js/html5.js"></script>
	<![endif]-->
	<style>
		.alert{
			margin:100px auto;
			width: 800px;
			/*border:1px solid #ddd;*/
			/*background:#eee;*/
			padding:50px;
			text-align: center;
			font-family:PingFang SC, Lantinghei SC, Microsoft Yahei, Hiragino Sans GB, Microsoft Sans Serif, WenQuanYi Micro Hei, sans;
		}
		.logo img{
			width:180px;
		}

	</style>
<title>搜文库 404</title>
<body>
	<div class="alert alert-info text-center" style="">
		<p><a href="${ctx}/" class="logo"><img src="${ctx}/resources/img/logo.png" alt="" ></a></p>
		<p>Sorry，页面不见了！ 代码：404  &nbsp;<a href="#" class="link-warning" onclick="history.back();return false;">点击这里</a> 返回上一页，或者 <a class="link-warning" href="${ctx}/">进入主页</a></p>
	</div>
</body>
</html>
