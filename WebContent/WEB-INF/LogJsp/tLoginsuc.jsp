<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
		top:15px;
	}
	.main {
		background:url("../picture/hitbg.jpg") no-repeat;
		background-size:cover;
		width:100%;
	}
	#photo {
		position:absolute;
		top:58px;
	}
	.main #teacherTitle {
		width:100%;
		height:200px;
		background:url("../picture/hitlogo2.jpg") no-repeat;
		position:relative;
		left:13%;
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
	.main .operation {
		background-color:white;
		width:80px;
		height:40px;
		font-family: 楷体;
		font-size:20px;
	}
	/* .main #showBasic table {
		
	} */
	.main #editBasic #BasicInfo input{
		height:30px;
		margin-bottom:5px;
	}
	/* .main #showHonor table th{
		border:1px solid black;
		margin:0;
	}
	.main #showHonor table td{
		border:1px solid black;
		margin:0;
	} */
	.main #editHonor #addHonorForm{
		display:none;
	}
	.main #showCal .studentBooks{
		float:left;
		display:none;
	}
	.main #showCal .detailButton{
		float:left;
	}
	.main #editCal [onclick="addCal()"]{
		font-size:16px;
	}
	.main #editCal [onclick="saveCal()"]{
		display:none;
		position:relative;
		left:960px;
		top:-30px;
	}
	.main #editCal [onclick="cancelCal()"]{
		position:relative;
		left:500px;
	}
	.main #editCal #addCalButton{
		position:relative;
		left:500px;
	}
	.main #editCal #AddCal{
		display:none;
	}
	.main #editCal #originalCal .calsOperationOption {
		text-decoration:none;
	}
	.main #editCal #balCal{
		display:none;
	}
	.main #editAch .AchForm{
		display:none;
	}
	.main .operation:hover {
		background-color:#339999;
		color:white;
	}
	.main .mainpage {
		background-color:white;
		opacity:0.8;
		display:none;
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
	var honor = "${SciAcademician}";
	if (honor == "科学院院士")
		document.getElementById("inputSciAcademician").checked = true;
	honor = "${EngAcademician}";
	if (honor == "工程院院士")
		document.getElementById("inputEngAcademician").checked = true;
	honor = "${YangtzeScholor}";
	if (honor == "长江学者")
		document.getElementById("inputYangtzeScholor").checked = true;
	honor = "${DrSupvisor}";
	if (honor == "博士生导师")
		document.getElementById("inputDrSupvisor").checked = true;
}
function show(ele){
		var x = document.getElementById(ele);
		x.style.background = "#3399cc";
		x.style.color = "white";
		x.style.borderColor = "white";
		document.getElementById("show"+ele).style.display = "block";
		document.getElementById("edit"+ele).style.display = "none";
		if ("Basic" != ele){
			document.getElementById("Basic").style.background = "white";
			document.getElementById("Basic").style.color = "black";
			document.getElementById("showBasic").style.display = "none";
			document.getElementById("editBasic").style.display = "none";
		}
		if ("Honor" != ele){
			document.getElementById("Honor").style.background = "white";
			document.getElementById("Honor").style.color = "black";
			document.getElementById("showHonor").style.display = "none";
			document.getElementById("editHonor").style.display = "none";
		}
		if ("Cal" != ele){
			document.getElementById("Cal").style.background = "white";
			document.getElementById("Cal").style.color = "black";
			document.getElementById("showCal").style.display = "none";
			document.getElementById("editCal").style.display = "none";
		}
		if ("Fund" != ele){
			document.getElementById("Fund").style.background = "white";
			document.getElementById("Fund").style.color = "black";
			document.getElementById("showFund").style.display = "none";
			document.getElementById("editFund").style.display = "none";
		}
		if ("Ach" != ele){
			document.getElementById("Ach").style.background = "white";
			document.getElementById("Ach").style.color = "black";
			document.getElementById("showAch").style.display = "none";
			document.getElementById("editAch").style.display = "none";
		}
	}
//处理基本信息页面的按钮事件的函数
function editBasic(){
	document.getElementById("showBasic").style.display = "none";
	document.getElementById("editBasic").style.display = "block";
}
function saveBasic(){
	//document.getElementById("BasicInfo").action="teacher/editBasicInfo?username=${username }";
	document.getElementById("BasicInfo").submit();
}
function cancelBasic() {
	document.getElementById("editBasic").style.display = "none";
	document.getElementById("showBasic").style.display = "block";
}
//处理荣誉称号页面的按钮事件的函数
function editHonor(){
	document.getElementById("editHonor").style.display = "block";
	document.getElementById("showHonor").style.display = "none";
	//document.getElementById("AllHonor").value = "暂未填写";
}
function saveHonor(){
	document.getElementById("HonorInfo").submit;
}
function cancelHonor() {
	document.getElementById("editHonor").style.display = "none";
	document.getElementById("showHonor").style.display = "block";
	document.getElementById("showBasic").style.display = "none";
	document.getElementById("addHonorButton").style.display="block";
	document.getElementById("addHonorForm").style.display = "none";
}
function addHonor(){
	document.getElementById("addHonorButton").style.display="none";
	document.getElementById("addHonorForm").style.display = "block";
}
function sureAddHonor(){
	ele = document.getElementById("addHonorForm").getElementsByTagName("input");
	for (i=0;i<ele.length;i++){
		if (ele[i].value == "")
		{
			alert("所有项均为必填");
			return false;
		}
	}
	if (!confirm("确定添加这一条荣誉奖励吗？"))
		return false;
	//document.getElementById("addHonorForm").action="SEPractice/teacher/addHonorInfo";
	//alert(document.getElementById("addHonorForm").action);
	document.getElementById("addHonorForm").submit();
}
function changeHonor(){
	if (!confirm("确认对荣誉所做的修改吗？"))
		return false;
	this.parentNode.submit();
}
function delHonor(){
	if (!confirm("确定删除该荣誉记录吗？"))
		return false;
	this.parentNode.submit();
}
//处理行程日历页面的按钮事件的函数
function addCal(){
	document.getElementById("AddCal").style.display="block";
	document.getElementById("savecal").style.display="block";
	document.getElementById("addCalButton").style.display = "none";
}
function editCal() {
	document.getElementById("showCal").style.display = "none";
	document.getElementById("editCal").style.display = "block";
	document.getElementById("addCalButton").style.display = "block";
	document.getElementById("AddCal").style.display = "none";
	document.getElementById("savecal").style.display = "none";
}
function studentDetail(id,buttonID){
	button = document.getElementById(buttonID);
	if (button.innerHTML == "详情"){
		button.innerHTML = "收起";
		document.getElementById(id).style.display = "block";
	}
	else{
		button.innerHTML = "详情";
		document.getElementById(id).style.display = "none";
	}
}
function saveCal(){
	ele = document.getElementById("AddCal").getElementsByTagName("input");
	for (i=0;i<ele.length;i++){
		if (ele[i].value == "")
		{
			alert("所有项均为必填");
			break;
		}
	}
	document.getElementById("AddCal").submit();
}
function delCal(){
	if (confirm("确定删除该行程吗？"))
	{
		this.submit();
	}
	else
		return false;
}
function relCal(){
	if (confirm("确定发布该行程吗？"))
		this.submit();
	else
		return false;
}
function cancelrelCal(){
	if (confirm("确定取消发布该行程吗？"))
	{
		this.submit();
	}
	else
		return false;
}
function acceptCal(){
	if (confirm("确定接受该预约吗？"))
		this.submit();
	else
		return false;
}
function rejectCal(){
	if (confirm("确定拒绝该预约吗？"))
		this.submit();
	else
		return false;
}
function cancelCal() {
	document.getElementById("editCal").style.display = "none";
	document.getElementById("showCal").style.display = "block";
	document.getElementById("AddCal").style.display="none";
	document.getElementById("savecal").style.display="none";
}
function batchCalProcess(){
	document.getElementById("balCal").style.display = "block";
	document.getElementById("originalCal").style.display = "none";
	document.getElementById("batchPro").style.display = "none";
}
function cancelBatchPro(){
	document.getElementById("balCal").style.display = "none";
	document.getElementById("originalCal").style.display = "block";
	document.getElementById("batchPro").style.display = "block";
}
function batchCalDel(){
	if (confirm("确定删除所有选中的行程吗？"))
	{
		var request = "/SEPractice/teacher/batchCalDel";
		document.getElementById("batchCalForm").action = request;
		//alert(document.getElementById("batchCalForm").action);
		document.getElementById("batchCalForm").submit();
	}
}
function batchCalRel(){
	if (confirm("确定发布选中的所有行程吗"))
	{
		var request = "/SEPractice/teacher/batchCalRel";
		document.getElementById("batchCalForm").action = request;
		//alert(document.getElementById("batchCalForm").action);
		document.getElementById("batchCalForm").submit();
	}
}
function batchCalCancel(){
	if (confirm("确定取消发布所选中的行程吗？"))
	{
		var request = "/SEPractice/teacher/batchCalCancelRel";
		document.getElementById("batchCalForm").action = request;
		//alert(document.getElementById("batchCalForm").action);
		document.getElementById("batchCalForm").submit();
	}
}
//处理基金页面的按钮事件的函数
function editFund() {
	document.getElementById("showFund").style.display = "none";
	document.getElementById("editFund").style.display = "block";
}
function saveFund(){
	document.getElementById("FundInfo").submit;
}
function cancelFund() {
	document.getElementById("editFund").style.display = "none";
	document.getElementById("showFund").style.display = "block";
}
//处理科研成果页面的按钮事件的函数
function editAch() {
	document.getElementById("showAch").style.display = "none";
	document.getElementById("editAch").style.display = "block";
}
function saveAch(){
	document.getElementById("AchInfo").submit;
}
function cancelAch() {
	document.getElementById("editAch").style.display = "none";
	document.getElementById("showAch").style.display = "block";
}
function changeAch(id){
	if (document.getElementById("changeAchButton"+id).innerHTML == "修改")
	{
		realid = "AchInfo"+id;
		document.getElementById(realid).style.display="block";
		realid = "AchName"+id;
		document.getElementById(realid).style.display="none";
		realid = "changeAchButton"+id;
		document.getElementById(realid).innerHTML = "取消";
	}
	else{
		realid = "AchInfo"+id;
		document.getElementById(realid).style.display="none";
		realid = "AchName"+id;
		document.getElementById(realid).style.display="block";
		realid = "changeAchButton"+id;
		document.getElementById(realid).innerHTML = "修改";
	}
	
}
</script>
<script type = "text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
</head>
<body onload="defaultSetting()">
	<div class="header">
		<h3>教师 ${username}主页</h3>
	</div>
	<div id="photo">
		<img src="../images/${username }.jpg" width="160px" height="170px">
		<form action="fileUpload.action" method="post" enctype="multipart/form-data">
   			<input type="file" name="photo"><br>
   			<input type="submit" value="更新头像">
		</form>
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
					<td><a href = "${homepage }" target = "_blank">${homepage }</a></td>
				</tr>
			</table>
			<button onclick="editBasic()" class="operation" style="position:relative;left:300px;">修改</button>
		</div>
		<!--修改基本信息的页面 -->
		<div id="editBasic" class="mainpage">
			<form id="BasicInfo" action="/SEPractice/teacher/editBasicInfo">
				&nbsp;&nbsp;&nbsp;姓名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name" value="${name}"></input><br />
				&nbsp;&nbsp;&nbsp;学校&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="school" value="${school}"></input><br />
				&nbsp;&nbsp;&nbsp;性别&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="male" style="height:15px" checked>男
														<input type="radio" name="sex" value="female"style="height:15px">女<br />
				&nbsp;&nbsp;&nbsp;籍贯&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="address" value="${address}"></input><br />
				&nbsp;&nbsp;&nbsp;院系&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="department" value="${department}"></input><br />
				&nbsp;&nbsp;&nbsp;专业&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="major" value="${major}"></input><br />
				&nbsp;&nbsp;&nbsp;电话&nbsp;&nbsp;&nbsp;&nbsp;<input type="tel" name="telephone" value="${telephone}"></input><br />
				教师主页<input type="url" name="homepage" value="${homepage}"></input><br />
				<button  onclick="saveBasic()" class="operation" style="position:relative;left:300px;">保存</button>
			</form>
			<button onclick="cancelBasic()" class="operation" style="position:absolute;left:400px;top:645px">取消</button>
		</div>
		<%ArrayList<String> teacherhonors = (ArrayList<String>)request.getAttribute("MyHonor"); %>
		<!--荣誉称号展示的页面 -->
		<div id="showHonor" class="mainpage">
			<p id="SciAcademician" >${SciAcademician}</p>
			<p id="EngAcademician" >${EngAcademician}</p>
			<p id="YangtzeScholor">${YangtzeScholor}</p>
			<p id="DrSupvisor">${DrSupvisor}</p>
			<table>
				<tr><th>时间</th><th>荣誉奖励</th></tr>
				<%if (teacherhonors != null) {%>
				<%for (int i = 0;i < teacherhonors.size()/3;i++) {%>
					<tr>
						<td><%=teacherhonors.get(3*i+1) %></td>
						<td><%=teacherhonors.get(3*i+2) %></td>
					</tr>
				<%} %>
				<%} %>
			</table>
			<button onclick="editHonor()" class="operation">修改</button>
		</div>
		<!--修改荣誉称号的页面 -->
		<div id="editHonor" class="mainpage">
		
			<%--修改教师四大名誉称号的信息 --%>
			<form id="HonorInfo" action="/SEPractice/teacher/editHonorInfo">
				<input id="inputSciAcademician" type="checkbox" value="SA" name="SciAcademician" />科学院院士<br />
				<input id="inputEngAcademician" type="checkbox" value="EA" name="EngAcademician" />工程院院士<br />
				<input id="inputYangtzeScholor" type="checkbox" value="YS" name="YangtzeScholor" />长江学者<br />
				<input id="inputDrSupvisor" type="checkbox" value="DS" name="DrSupvisor" />博士生导师<br />
				<button onclick="saveHonor()"  style="position:relative;left:100px">保存</button>
			</form>
			<hr/>
			<table>
				<tr><th></th><th></th><th>时间</th><th>荣誉奖励</th></tr>
				<%if (teacherhonors != null) {%>
				<%for (int i=0;i < teacherhonors.size()/3;i++) {%>
					<tr>
						<td>
							<form action="/SEPractice/teacher/delHonorInfo">
								<input type="hidden" name="honorID" value="<%=teacherhonors.get(3*i)%>">
								<button onclick="delHonor();return false;">删除</button>
							</form>
						</td>
						<td colspan="3">
							<form action="/SEPractice/teacher/changeHonorInfo">
								<input type="hidden" name="honorID" value="<%=teacherhonors.get(3*i)%>">
								<input type="text" name="honorDate" value="<%=teacherhonors.get(3*i+1) %>" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy年',readOnly:true})"/>
								<input type="text" name="honorItem" value="<%=teacherhonors.get(3*i+2) %>"/>
								<button onclick="changeHonor();return false;">确认修改</button>
							</form>
						</td>

					</tr>
				<%} %>
				<%} %>
			</table>
			<button onclick="addHonor()" class="operation" id="addHonorButton">添加</button>
			<form action="/SEPractice/teacher/addHonorInfo" id="addHonorForm">
				时间：<input type="text" name="honorDate" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy年',readOnly:true})" />
				荣誉奖励：<input type="text" name="honorItem" />
				<button onclick="sureAddHonor();return false;">确定添加</button>
			</form>
			
			
			<hr/>
			<br/><button onclick="cancelHonor()" class="operation" style="position:relative;left:600px">取消</button>
		</div>
		
		<!-- 展示老师所有的行程，teachercals获取有关教师行程的一切信息 -->
		<% ArrayList<String> teachercals = (ArrayList<String>)request.getAttribute("ALLCal"); %>
		<% ArrayList<String> teachercalsid = (ArrayList<String>)request.getAttribute("AllCalID");%>
		<!--展示教师行程的页面 -->
		<div id="showCal" class="mainpage">
			<table>
				<tr>
					<th>日期</th>
					<th>时间</th>
					<th>结束时间</th>
					<th>活动</th>
					<th>发布状态</th>
					<th>预约状态</th>
					<th></th>
				</tr>
				<%if (teachercals != null) {%>
				<%for (int i = 0;i < teachercals.size()/11;i++) {%>
					<tr>
						<td><%out.println(teachercals.get(11*i+0)); %></td>
						<td><%out.println(teachercals.get(11*i+1)); %></td>
						<td><%out.println(teachercals.get(11*i+5)); %></td>
						<td><%out.println(teachercals.get(11*i+2)); %></td>
						<%if (teachercals.get(11*i+3) == null || teachercals.get(11*i+3).equals("0")) {%>
							<td>未发布</td>
						<%} else{%>
							<td>已发布</td>
						<%} %>
						<%if (teachercals.get(11*i+4) == null) {%>
							<td>非预约</td>
						<%} 
						else if(teachercals.get(11*i+4).equals("0")) {%>
							<td><b>申请预约</b></td>
							<td>
								<button id="dB<%=teachercalsid.get(i)%>" class="detailButton" onclick="studentDetail('<%="bookID"+teachercalsid.get(i)%>','<%="dB"+teachercalsid.get(i)%>')">详情</button>
								<div id="bookID<%=teachercalsid.get(i)%>" class="studentBooks">
									<a href="/SEPractice/teacher/showStudent?StudentUser=<%=teachercals.get(11*i+6) %>" target="_blank"><%=teachercals.get(11*i+6) %></a>
									&nbsp;<%=teachercals.get(11*i+7) %>
									&nbsp;<%=teachercals.get(11*i+8) %>
									&nbsp;<%=teachercals.get(11*i+9) %>
									&nbsp;<%=teachercals.get(11*i+10) %>
								</div>
							</td>
						<%} else{%>
							<td>接受预约</td>
							<td>
								<button id="dB<%=teachercalsid.get(i)%>" class="detailButton" onclick="studentDetail('<%="bookID"+teachercalsid.get(i)%>','<%="dB"+teachercalsid.get(i)%>')">详情</button>
								<div id="bookID<%=teachercalsid.get(i)%>" class="studentBooks">
									<a href="/SEPractice/teacher/showStudent?StudentUser=<%=teachercals.get(11*i+6) %>" target="_blank"><%=teachercals.get(11*i+6) %></a>
									&nbsp;<%=teachercals.get(11*i+7) %>
									&nbsp;<%=teachercals.get(11*i+8) %>
									&nbsp;<%=teachercals.get(11*i+9) %>
									&nbsp;<%=teachercals.get(11*i+10) %>
								</div>
							</td>
						<%} %>
					</tr>
				<%} %>
				<%} %>
			</table>
			<button onclick="editCal()" class="operation">修改</button>
		</div>
		<!--修改教师行程的页面 -->
		<div id="editCal" class="mainpage">
			<button id="batchPro" onclick="batchCalProcess()">批量处理</button>
			<%--批量处理页面 --%>
			<div id="balCal">
				<button onclick="cancelBatchPro()">取消</button>
				<form id="batchCalForm" action="/SEPractice/teacher/batchCal">
					<table>
						<tr>
							<th>选择</th>
							<th>日期</th>
							<th>时间</th>
							<th>结束时间</th>
							<th>活动</th>
							<th>发布状态</th>
							<th>预约状态</th>
						</tr>
						<%if (teachercals != null) {%>
						<%for (int i = 0;i < teachercals.size()/11;i++) {%>
						<tr>
							<td><input type="checkbox" name="batchCals" value="<%=teachercalsid.get(i)%>"/></td>
							<td><%out.println(teachercals.get(11*i+0)); %></td>
							<td><%out.println(teachercals.get(11*i+1)); %></td>
							<td><%out.println(teachercals.get(11*i+5)); %></td>
							<td><%out.println(teachercals.get(11*i+2)); %></td>
							<%if (teachercals.get(11*i+3) == null || teachercals.get(11*i+3).equals("0")) {%>
								<td>未发布</td>
							<%} else{%>
								<td>已发布</td>
							<%} %>
							<%if (teachercals.get(11*i+4) == null) {%>
								<td>非预约</td>
							<%} 
							else if(teachercals.get(11*i+4).equals("0")) {%>
								<td>申请预约</td>
							<%} else{%>
								<td>接受预约</td>
							<%} %>
						</tr>
				<%} %>
				<%} %>
					</table>
				</form>
				<button onclick="batchCalDel()">删除</button>
				<button onclick="batchCalRel()">发布</button>
				<button onclick="batchCalCancel()">取消发布</button>
			</div>
			<table id = "originalCal">
				<tr><th>日期</th><th>时间</th><th>结束时间</th><th>活动</th><th>发布状态</th><th>处理预约</th><th></th></tr>
				<%if (teachercals != null) {%>
				<%for (int i = 0;i < teachercals.size()/11;i++) {%>
					<tr>
						<td><%out.println(teachercals.get(11*i+0)); %></td>
						<td><%out.println(teachercals.get(11*i+1)); %></td>
						<td><%out.println(teachercals.get(11*i+5)); %></td>
						<td><%out.println(teachercals.get(11*i+2)); %></td>
						<%if (teachercals.get(11*i+3) == null || teachercals.get(11*i+3).equals("0")) {%>
							<%
							String safront = "<a href=\"/SEPractice/teacher/releaseCalInfo.action?calID=";
							String sarear = "\" onclick=\"relCal();return false;\" class=\"calsOperationOption\">发布</a>";
							String element = safront+teachercalsid.get(i)+sarear;
							%>
							<td><%out.println(element); %></td>
						<%} else{%>
							<%
							String safront = "<a href=\"/SEPractice/teacher/cancelrelCalInfo.action?calID=";
							String sarear = "\" onclick=\"cancelrelCal();return false;\" class=\"calsOperationOption\">取消发布</a>";
							String element = safront+teachercalsid.get(i)+sarear;
							%>
							<td><%out.println(element); %></td>
						<%} %>
						<%if (teachercals.get(11*i+4) == null) {%>
							<td>非预约</td>
						<%} 
						else if(teachercals.get(11*i+4).equals("0")) {%>
							<%
							String safront = "<a href=\"/SEPractice/teacher/acceptCalInfo.action?calID=";
							String sarear = "\" onclick=\"acceptCal();return false;\" class=\"calsOperationOption\">接受预约</a>";
							String element = safront+teachercalsid.get(i)+sarear;
							%>
							<td><%out.println(element); %></td>
						<%} else{%>
							<%
							String safront = "<a href=\"/SEPractice/teacher/rejectCalInfo.action?calID=";
							String sarear = "\" onclick=\"rejectCal();return false;\" class=\"calsOperationOption\">拒绝预约</a>";
							String element = safront+teachercalsid.get(i)+sarear;
							%>
							<td><%out.println(element); %></td>
						<%} %>
						<%
							String safront = "<a href=\"/SEPractice/teacher/deleteCalInfo.action?calID=";
							String sarear = "\" onclick=\"delCal();return false;\" class=\"calsOperationOption\">删除行程</a>";
							String element = safront+teachercalsid.get(i)+sarear;
							//System.out.println(element);
						%>
						<td><%out.println(element); %></td>
					</tr>
				<%} %>
				<%} %>
			</table>
			<button onclick="addCal()" class="operation" id="addCalButton">添加行程</button>
			<form id="AddCal" action="/SEPractice/teacher/editCalInfo">
					选择日期<input type="text" name="newDate" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',readOnly:true})"/>
					选择时间<input type="text" name="newTime" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm',readOnly:true})"/>
					结束时间<input type="text" name="newEndTime" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm',readOnly:true})"/>
					行程活动<input type="text" name="newBea"/>
			</form>
			<button onclick="saveCal()" class="operation" id="savecal">保存</button>
			<br /><button onclick="cancelCal()" class="operation" id="cancelcal">取消</button>
		</div>
		<%--取出老师的基金信息 --%>
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
			<button onclick="editFund()" class="operation">修改</button>
		</div>
		<!--修改教师基金的页面 -->
		<div id="editFund" class="mainpage">
			<%if (AllFund != null) {%>
			<%for (int i=0;i < AllFund.size()/5;i++) {%>
			<form id="FundInfo" action="/SEPractice/teacher/editFundInfo">
				<input type="hidden" name="fundID" value="<%=AllFund.get(5*i) %>"/>
				<table>
				<tr>
				<th>基金名称</th>
				<td><input type="text" name="fundName" value="<%=AllFund.get(5*i+1) %>"/></td>
				</tr>
				<tr>
				<th>基金来源</th>
				<td><input type="text" name="fundSource" value="<%=AllFund.get(5*i+2) %>" /></td>
				</tr>
				<tr>
				<th>获得日期</th>
				<td><input type="text" name="fundDate" value="<%=AllFund.get(5*i+3) %>" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',readOnly:true})"/></td>
				</tr>
				<tr>
				<th>基金数额</th>
				<td><input type="text" name="fundMoney" value="<%=AllFund.get(5*i+4) %>"/></td>
				</tr>
				</table>
				<button onclick="saveFund()" class="operation" style="font-size:16px">确认修改</button>
			</form>
			<form action="/SEPractice/teacher/delFundInfo">
				<input type="hidden" name="fundID" value="<%=AllFund.get(5*i) %>"/>
				<button style="position:relative;left:200px;">删除</button>
			</form>
			<%} %>
			<%} %>
			<hr/>
			<form action="/SEPractice/teacher/addFundInfo">
				<table>
				<tr>
				<th>基金名称</th>
				<td><input type="text" name="fundName"/></td>
				</tr>
				<tr>
				<th>基金来源</th>
				<td><input type="text" name="fundSource"/></td>
				</tr>
				<tr>
				<th>获得日期</th>
				<td><input type="text" name="fundDate" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',readOnly:true})"/></td>
				</tr>
				<tr>
				<th>基金数额</th>
				<td><input type="text" name="fundMoney"/></td>
				</tr>
				</table>
				<button style="position:relative;left:200px;">添加</button>
			</form>
			<hr />
			<button onclick="cancelFund()" class="operation" style="position:relative;left:250px;">取消</button>
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
			<button onclick="editAch()" class="operation">修改</button>
		</div>
		<!--修改教师科研成果的页面 -->
		<div id="editAch" class="mainpage">
			<%if (AllAch != null) {%>
			<%for (int i=0;i < AllAch.size()/9;i++) {%>
			<br /><button id="changeAchButton<%=AllAch.get(9*i)%>" onclick="changeAch('<%=AllAch.get(9*i)%>')">修改</button>
			<b id="AchName<%=AllAch.get(9*i)%>" ><%=AllAch.get(9*i+1) %></b>
			<form action="/SEPractice/teacher/delAchInfo">
				<input type="hidden" name="achID" value="<%=AllAch.get(9*i)%>">
				<button style="position:relative;left:200px;">删除</button>
			</form>
			<form id="AchInfo<%=AllAch.get(9*i)%>" class="AchForm" action="/SEPractice/teacher/editAchInfo">
				<input type="hidden" name="achID" value="<%=AllAch.get(9*i)%>">
				<table>
				<tr><th>项目名称：</th>
				<td><input type="text" name="achName" value="<%=AllAch.get(9*i+1)%>"/></td></tr>
				<tr><th>项目来源：</th>
				<td><input type="text" name="achSource" value="<%=AllAch.get(9*i+2)%>"/></td></tr>
				<tr><th>起始时间：</th>
				<td>
				<input type="text" name="achStart" value="<%=AllAch.get(9*i+3)%>" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',readOnly:true})"/>
				</td></tr>
				<tr><th>结束时间：</th>
				<td>
				<input type="text" name="achEnd" value="<%=AllAch.get(9*i+4)%>" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',readOnly:true})"/>
				</td></tr>
				<tr><th>担任角色：</th>
				<td><input type="text" name="achRole" value="<%=AllAch.get(9*i+5)%>"/></td></tr>
				<tr><th>项目类别：</th>
				<td><input type="text" name="achType" value="<%=AllAch.get(9*i+6)%>"/></td></tr>
				<tr><th>项目经费：</th>
				<td><input type="text" name="achMoney" value="<%=AllAch.get(9*i+7)%>"/></td></tr>
				<tr><th>项目状态：</th>
				<td><input type="text" name="achState" value="<%=AllAch.get(9*i+8)%>"/></td></tr>
				</table>
				<button onclick="saveAch()">确认修改</button>
			</form>
			<%} %>
			<%} %>
			<hr/>
			<form action="/SEPractice/teacher/addAchInfo">
				项目名称：<input type="text" name="achName" />
				<br/>项目来源：<input type="text" name="achSource" />
				<br/>起始时间：<input type="text" name="achStart" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',readOnly:true})"/>
				<br/>结束时间：<input type="text" name="achEnd" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',readOnly:true})"/>
				<br/>担任角色：<input type="text" name="achRole" />
				<br/>项目类别：<input type="text" name="achType" />
				<br/>项目经费：<input type="text" name="achMoney" />
				<br/>项目状态：<input type="text" name="achState" />
				<br/><input type="submit" value="添加" />
			</form>
			<hr />
			<button onclick="cancelAch()" class="operation">取消</button>
		</div>
	</div>
	<div class="footer">
		<h3>教师信息管理系统小组</h3>
	</div>
</body>
</html>