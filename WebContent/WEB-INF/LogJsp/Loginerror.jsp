<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆错误</title>
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
		height:270px;
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
	.log form [type=submit] {
		background-color:#006600;
		color:white;
		width:75px;
		height:35px;
		position:relative;
		left:155px;
		top:30px;
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
		top:50px;
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
	<h1>欢迎登陆</h1>
	<form>
		<table>
			<tr>
				<td class="lable">用户名</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td colspan="2"><p></p></td>
			</tr>
			<tr>
				<td class="lable">密码</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td colspan="2"><p>用户名或密码错误</p></td>
			</tr>
		</table>
		<input type="submit" value="登陆">
	</form>
	<s:a action="nologup">还没注册？先注册</s:a>
</div>
</body>
</html>