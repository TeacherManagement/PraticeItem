<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新用户注册</title>
<style type="text/css">
	body {
		background-image:url('../picture/hitmodel.jpg');
		background-size:cover;
	}
	.header {
		background-color:black;
		color:white;
		text-align:center;
		opacity:0.5;
	}
	.header h1{
		margin:0px;
	}
	.log{
		width:250px;
		height:320px;
		border:1px solid black;
		background-color:black;
		opacity:0.7;
		padding-top:10px;
		position:relative;
		left:75%;
		top:30px;
	}
	.log h1 {
		color:white;
		text-align:center;
		margin:0px 0px 30px 0px;
	}
	.log form {
		color:white;
	}
	.log form #tors {
		position:relative;
		left:65px;
	}
	.log form table {
		margin-left:10px;
		margin-right:10px;
		width:230px;
		text-align:center;
	}
	.log form table tr td p {
		margin:0px;
		padding:0px;
		color:red;
	}
	.log form [type=text]{
		height:25px;
		width:160px;
		float:left;
		margin-bottom:0px;
	}
	.log form [type=password]{
		height:25px;
		width:160px;
		float:left;
		margin-bottom:0px;
	}
	.log form [type=submit] {
		background-color:#006600;
		color:white;
		width:75px;
		height:35px;
		position:relative;
		left:155px;
		top:20px;
	}
	
	.log form [type=submit]:hover {
		background-color:#009966;
		color:white;
	}
	.log a:link,a:visited{
		color:white;
		text-decoration:none;
		float:right;
		position:relative;
		top:30px;
	}
	.log a:hover {
		color:green;
	}
</style>
</head>
<body>
<div class="header">
	<h1>教师信息管理系统</h1>
</div>
<div class="log">
	<h1>欢迎注册</h1>
	<form action="logup">
		<table>
			<tr>
				<td class="lable">用户名</td>
				<td><input type="text" name="username" value="${username}"></td>
			</tr>
			<tr>
				<td colspan="2"><p>用户已存在</p></td>
			</tr>
			<tr>
				<td class="label">密码</td>
				<td><input type="password" name="password" value="${password}"></td>
			</tr>
			<tr>
				<td colspan="2"><p></p></td>
			</tr>
			<tr>
				<td class="lable" style="font-size:14px">确认密码</td>
				<td><input type="password" name="verifyPassword" value="${verifyPassword}"></td>
			</tr>
			<tr>
				<td colspan="2"><p></p></td>
			</tr>
		</table>
		<div id="tors">
		<input type="radio" name="identity" value="我是老师" checked>我是老师
		<input type="radio" name="identity" value="我是学生">我是学生
		</div>
		<input type="submit" value="注册">
	</form>
	<s:a action="nologup">遇到问题？</s:a>
</div>
</body>
</html>