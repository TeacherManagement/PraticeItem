<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
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
		background:url("${pageContext.request.contextPath}/picture/hitbg.jpg") no-repeat;
		background-size:cover;
		width:100%;
	}
	.main #teacherTitle {
		width:100%;
		height:200px;
		background:url("${pageContext.request.contextPath}/picture/hitlogo2.jpg") no-repeat;
		position:relative;
		left:13%;
		opacity:0.8;
	}
	.main #photo {
		position:absolute;
		top:58px;
	}
	.main #showCal #bookForm{
		display:none;
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
//预约教师功能相关
function bookTeacher(){
	document.getElementById("bookForm").style.display="block";
	document.getElementById("bookButton").style.display="none";
}
function sureBook(){
	ele = document.getElementById("bookForm").getElementsByTagName("input");
	for (i=0;i<ele.length;i++){
		if (ele[i].value == "")
		{
			alert("所有项均为必填");
			return false;
		}
	}
	if (!confirm("确定对该教师发出预约申请吗？"))
		return false;
	document.getElementById("bookForm").submit();
}
</script>
<script type = "text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
</head>

<body onload="defaultSetting()">
	<div class="header">
		<h3>教师主页 ${visited}</h3>
	</div>
	<div class="main">
		<div id="teacherTitle">
			
		</div>
		<div id="photo">
		<img src="../images/${visited }.jpg" width="160px" height="170px">
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
					<td><a href = "${homepage }" target="_blank">${homepage }</a></td>
				</tr>
			</table>
		</div>
		
		<!--荣誉称号展示的页面 -->
		<%ArrayList<String> teacherhonors = (ArrayList<String>)request.getAttribute("MyHonor"); %>
		<div id="showHonor" class="mainpage">
			<p id="SciAcademician" >${SciAcademician}</p>
			<p id="EngAcademician" >${EngAcademician}</p>
			<p id="YangtzeScholor">${YangtzeScholor}</p>
			<p id="DrSupvisor">${DrSupvisor}</p>
			<table>
				<tr><th>时间</th><th>荣誉奖励</th></tr>
				<%for (int i = 0;i < teacherhonors.size()/3;i++) {%>
					<tr>
						<td><%=teacherhonors.get(3*i+1) %></td>
						<td><%=teacherhonors.get(3*i+2) %></td>
					</tr>
				<%} %>
			</table>
		</div>
		<!--展示教师行程的页面 -->
		<div id="showCal" class="mainpage">
			
			<!-- 展示老师所有的行程，teachercals获取有关教师行程的一切信息 -->
			<% ArrayList<String> teachercals = (ArrayList<String>)request.getAttribute("ALLCal"); %>
			<% ArrayList<String> teachercalsid = (ArrayList<String>)request.getAttribute("AllCalID");%>
			<table>
				<tr><th>日期</th><th>预约时间</th><th>结束时间</th><th>活动</th><th>预约状态</th></tr>
				<%for (int i = 0;i < teachercals.size()/5;i++) {%>
					<tr>
						<td><%out.println(teachercals.get(5*i+0)); %></td>
						<td><%out.println(teachercals.get(5*i+1)); %></td>
						<td><%out.println(teachercals.get(5*i+4)); %></td>
						<td><%out.println(teachercals.get(5*i+2)); %></td>
						<%if (teachercals.get(5*i+3) == null) {%>
							<td>非预约</td>
						<%} 
						else if(teachercals.get(5*i+3).equals("0")) {%>
							<td>申请预约</td>
						<%} else{%>
							<td>接受预约</td>
						<%} %>
					</tr>
				<%} %>
			</table>
			<button id="bookButton" onclick="bookTeacher()">预约该教师</button>
			<form action="SEPractice/student/bookTeacher" id="bookForm">
				<hr />
				预约日期：<input type="text" name="bookDate" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',readOnly:true})"/>
				预约时间<input type="text" name="bookStart" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm',readOnly:true})"/>
				结束时间<input type="text" name="bookEnd" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm',readOnly:true})"/>
				备注<input type="text" name="bookExtra"/>
				<button onclick="sureBook();return false;">预约申请</button>
			</form>
		</div>
		<%ArrayList<String> AllFund = (ArrayList<String>)request.getAttribute("AllFund"); %>
		<!--展示教师基金的页面 -->
		<div id="showFund" class="mainpage">
			<%if (AllFund != null) {%>
				<%for (int i=0;i < AllFund.size()/5;i++) {%>
					<table>
						<tr><th colspan="2"><%=AllFund.get(5*i+1) %></th></tr>
						<tr><th>基金来源</th><td><%=AllFund.get(5*i+2) %></td></tr>
						<tr><th>获得日期</th><td><%=AllFund.get(5*i+3) %></td></tr>
						<tr><th>基金数额</th><td><%=AllFund.get(5*i+4) %></td></tr>
					</table>
				<%} %>
			<%} %>
		</div>
		<%ArrayList<String> AllAch = (ArrayList<String>)request.getAttribute("AllAch"); %>		
		<!--展示教师科研成果的页面 -->
		<div id="showAch" class="mainpage">
			<%if (AllAch != null) {%>
				<%for (int i=0;i < AllAch.size()/9;i++) {%>
					<table>
						<tr><th colspan="2"><%=AllAch.get(9*i+1) %></th></tr>
						<tr><th>项目来源</th><td><%=AllAch.get(9*i+2) %></td></tr>
						<tr><th>开始时间</th><td><%=AllAch.get(9*i+3) %></td></tr>
						<tr><th>结束时间</th><td><%=AllAch.get(9*i+4) %></td></tr>
						<tr><th>担任角色</th><td><%=AllAch.get(9*i+5) %></td></tr>
						<tr><th>项目类别</th><td><%=AllAch.get(9*i+6) %></td></tr>
						<tr><th>项目经费</th><td><%=AllAch.get(9*i+7) %></td></tr>
						<tr><th>项目状态</th><td><%=AllAch.get(9*i+8) %></td></tr>
					</table>
				<%} %>
			<%} %>
		</div>
	</div>
	<div class="footer">
		<h3>教师信息管理系统小组</h3>
	</div>
</body>
</html>