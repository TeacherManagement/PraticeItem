<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教师主页</title>
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
		background:url("../picture/hitbg.jpg") no-repeat;
		background-size:cover;
		width:100%;
	}
	.main #teacherTitle {
		width:100%;
		height:200px;
		background:url("../picture/hitlogo2.jpg") no-repeat;
		position:relative;
		left:10%;
		opacity:0.8;
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
<!-- <script src="myfunc.js"></script> -->
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
		}
		if ("Honor" != ele){
			document.getElementById("Honor").style.background = "white";
			document.getElementById("Honor").style.color = "black";
			document.getElementById("showHonor").style.display = "none";
		}
		if ("Cal" != ele){
			document.getElementById("Cal").style.background = "white";
			document.getElementById("Cal").style.color = "black";
			document.getElementById("showCal").style.display = "none";
		}
		if ("Fund" != ele){
			document.getElementById("Fund").style.background = "white";
			document.getElementById("Fund").style.color = "black";
			document.getElementById("showFund").style.display = "none";
		}
		if ("Ach" != ele){
			document.getElementById("Ach").style.background = "white";
			document.getElementById("Ach").style.color = "black";
			document.getElementById("showAch").style.display = "none";
		}
	}
</script>
</head>

<body onload="defaultSetting()">
	<div class="header">
		<h3>教师主页 ${username}</h3>
	</div>
	<div class="main">
		<div id="teacherTitle">
			
		</div>
		<!--导航栏 -->
		<ul>
			<li><button onclick="show('Basic')" id="Basic">基本信息</button></li>
			<li><button onclick="show('Honor')" id="Honor">荣誉称号</button></li>
			<li><button onclick="show('Cal')"  id="Cal">行程日历</button></li>
			<li><button onclick="show('Fund')" id="Fund">基金</button></li>
			<li><button onclick="show('Ach')" id="Ach">科研成果</button></li>
			
			<div class="clear"> </div>
		</ul>
		<!--基本信息展示页 -->
		<div id="showBasic" class="mainpage">
			<table>
				<tr>
					<th>姓名</th>
					<td><p>${name}</p></td>
				</tr>
				<tr>
					<th>学校</th>
					<td><p>${school}</p></td>
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
				<tr>
					<th>教师主页</th>
					<td><p>${homepage }</p></td>
				</tr>
			</table>
		</div>
		
		<!--荣誉称号展示的页面 -->
		<div id="showHonor" class="mainpage">
			<p id="SciAcademician" >${SciAcademician}</p>
			<p id="EngAcademician" >${EngAcademician}</p>
			<p id="YangtzeScholor">${YangtzeScholor}</p>
			<p id="DrSupvisor">${DrSupvisor}</p>
			<p id="MyHonor">${MyHonor}</p>
		</div>
		<!--展示教师行程的页面 -->
		<div id="showCal" class="mainpage">
			<p>
				${AllCal}
			</p>
		</div>
		<!--展示教师基金的页面 -->
		<div id="showFund" class="mainpage">
			<p>
				${AllFund}
			</p>
		</div>
		
		<!--展示教师科研成果的页面 -->
		<div id="showAch" class="mainpage">
			<p>
				${AllAch}
			</p>
		</div>
	</div>
	<div class="footer">
		<h3>教师信息管理系统小组</h3>
	</div>
</body>
</html>