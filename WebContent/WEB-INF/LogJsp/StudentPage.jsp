<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生主页</title>
<style type="text/css">
	.header {
		height:50px;
		background-color:#330033;
	}
	.header h3 {
		color:white;
		margin:0px;
		padding:0px;
		position:relative;
		left:15%;
	}
	.main {
		background:url("${pageContext.request.contextPath}/picture/hitbg.jpg") no-repeat;
		background-size:cover;
		width:100%;
	}
	.main #photo {
		position:absolute;
		top:58px;
	}
	.main #studentTitle {
		width:100%;
		height:200px;
		background:url("${pageContext.request.contextPath}/picture/hitlogo2.jpg") no-repeat;
		position:relative;
		left:13%;
		opacity:0.8;
	}
	.main .operation {
		background-color:white;
		width:80px;
		height:40px;
		font-family: 楷体;
		font-size:20px;
	}
	.main .mainpage textarea {
		width:300px;
		height:100px;
	}
	
	.main .operation:hover {
		background-color:#339999;
		color:white;
	}
	.main ul {
		list-style:none;
	}
	.main ul .clear {
		clear:both;
	}
	.main ul li {
		margin-right:5px;
		width:100px;
		height:46px;
		float:left;
	}
	.main ul li button {
		width:100px;
		height:46px;
		background-color:white;
		border-color:white;
		border-radius: 8px;
	}
	.main ul li:hover button {
		background-color:#3399cc;
		color:white;
	}
	.main .mainpage {
		background-color:white;
		opacity:0.8;
		display:none;
	}
	.main #showBasic table {
		
	}
	.footer {
		height:40px;
		background-color:#330000;
	}
	.footer h3 {
		color:white;
		position:relative;
		top:25%;
		left:40%;
	}
</style>
<script>
function defaultSetting(){
	document.getElementById("Basic").style.backgroundColor = "#3399cc";
	document.getElementById("Basic").style.color = "white";
	document.getElementById("showBasic").style.display = "block";
}
function show(ele){
		var x = document.getElementById(ele);
		x.style.background = "#3399cc";
		x.style.color = "white";
		x.style.borderColor = "white";
		document.getElementById("show"+ele).style.display = "block";
		if ("Basic" != ele){
			document.getElementById("Basic").style.background = "white";
			document.getElementById("Basic").style.color = "black";
			document.getElementById("showBasic").style.display = "none";
			document.getElementById("editBasic").style.display = "none";
		}
		if ("LearningDir" != ele){
			document.getElementById("LearningDir").style.background = "white";
			document.getElementById("LearningDir").style.color = "black";
			document.getElementById("showLearningDir").style.display = "none";
			document.getElementById("editLea").style.display = "none";
		}
	}
</script>
</head>

<body onload="defaultSetting()">
	<div class="header">
		<h3>学生${visited }主页</h3>
	</div>
	<div class="main">
		<div id="studentTitle">
			
		</div>
		<div id="photo">
		<img src="${pageContext.request.contextPath}/images/${visited }.jpg" width="160px" height="170px">
		</div>
		<!--导航栏 -->
		<ul>
			<li><button onclick="show('Basic')" id="Basic">基本信息</button></li>
			<li><button onclick="show('LearningDir')" id="LearningDir">TA的预约</button></li>
			<div class="clear"></div>
		</ul>
		<!--基本信息展示页 -->
		<div id="showBasic" class="mainpage">
			<table>
				<tr>
					<th>姓名</th>
					<td><p>${name }</p></td>
				</tr>
				<tr>
					<th>学校</th>
					<td><p>${school }</p></td>
				</tr>
				<tr>
					<th>性别</th>
					<td><p>${sex }</p></td>
				</tr>
				<tr>
					<th>籍贯</th>
					<td><p>${address }</p></td>
				</tr>
				<tr>
					<th>院系</th>
					<td><p>${department }</p></td>
				</tr>
				<tr>
					<th>专业</th>
					<td><p>${major}</p></td>
				</tr>
				<tr>
					<th>电话</th>
					<td><p>${telephone }</p></td>
				</tr>
			</table>
		</div>
		<%ArrayList<String> AllLea = (ArrayList<String>)request.getAttribute("AllLea"); %>
		<!--展示学生预约老师的页面 -->
		<div id="showLearningDir" class="mainpage">
			<%if (AllLea != null) {%>
				<table>
					<tr>
						<th>预约老师</th>
						<th>预约日期</th>
						<th>预约时间</th>
						<th>结束时间</th>
						<th>事项</th>
						<th>状态</th>
					</tr>
				<%for (int i=0;i < AllLea.size()/7;i++) {%>
					<tr>
						<td><%=AllLea.get(7*i+2) %></td>
						<td><%=AllLea.get(7*i+3) %></td>
						<td><%=AllLea.get(7*i+4) %></td>
						<td><%=AllLea.get(7*i+5) %></td>
						<td><%=AllLea.get(7*i+6) %></td>
						<%if (AllLea.get(7*i+1).equals("1")) {%>
							<td>未被接受</td>
						<%} else {%>
							<td><b>已被接受</b></td>
						<%} %>
					</tr>
				<%} %>
				</table>
			<%} %>
		</div>
		
	</div>
	
	
	<div class="footer">
		<h3>教师信息管理系统小组</h3>
	</div>
</body>
