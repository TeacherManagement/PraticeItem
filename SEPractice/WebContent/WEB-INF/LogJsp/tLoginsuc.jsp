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
	.main #editBasic #BasicInfo input{
		height:30px;
		margin-bottom:5px;
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
}
//处理行程日历页面的按钮事件的函数
function editCal() {
	document.getElementById("showCal").style.display = "none";
	document.getElementById("editCal").style.display = "block";
}
function saveCal(){
	document.getElementById("CalInfo").submit;
}
function cancelCal() {
	document.getElementById("editCal").style.display = "none";
	document.getElementById("showCal").style.display = "block";
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
		<!--荣誉称号展示的页面 -->
		<div id="showHonor" class="mainpage">
			<p id="SciAcademician" >${SciAcademician}</p>
			<p id="EngAcademician" >${EngAcademician}</p>
			<p id="YangtzeScholor">${YangtzeScholor}</p>
			<p id="DrSupvisor">${DrSupvisor}</p>
			<p id="MyHonor">${MyHonor}</p>
			<button onclick="editHonor()" class="operation">修改</button>
		</div>
		<!--修改荣誉称号的页面 -->
		<div id="editHonor" class="mainpage">
			<form id="HonorInfo" action="/SEPractice/teacher/editHonorInfo">
				<!--以下为隐藏域  -->
				<!-- <input type="hidden" value="NSA" name="SciAcademician" /><br />
				<input type="hidden" value="NEA" name="EngAcademician" /><br />
				<input type="hidden" value="NYS" name="YangtzeScholor" /><br />
				<input type="hidden" value="NDS" name="DrSupvisor" /><br /> -->
				<!--以上为隐藏域  -->
				<input type="checkbox" value="SA" name="SciAcademician" />科学院院士<br />
				<input type="checkbox" value="EA" name="EngAcademician" />工程院院士<br />
				<input type="checkbox" value="YS" name="YangtzeScholor" />长江学者<br />
				<input type="checkbox" value="DS" name="DrSupvisor" />博士生导师<br />
				<textarea name="MyHonor" id="AllHonor">
					${MyHonor}
				</textarea><br />
				<button onclick="saveHonor()" class="operation">保存</button>
			</form>
			<button onclick="cancelHonor()" class="operation">取消</button>
		</div>
		<!--展示教师行程的页面 -->
		<div id="showCal" class="mainpage">
			<p>
				${AllCal}
			</p>
			<button onclick="editCal()" class="operation">修改</button>
		</div>
		<!--修改教师行程的页面 -->
		<div id="editCal" class="mainpage">
			<form id="CalInfo" action="/SEPractice/teacher/editCalInfo">
				<textarea name="AllCal" id="AllCal">
					${AllCal}
				</textarea><br />
				<button onclick="saveCal()" class="operation">保存</button>
			</form>
			<button onclick="cancelCal()" class="operation">取消</button>
		</div>
		<!--展示教师基金的页面 -->
		<div id="showFund" class="mainpage">
			<p>
				${AllFund}
			</p>
			<button onclick="editFund()" class="operation">修改</button>
		</div>
		<!--修改教师基金的页面 -->
		<div id="editFund" class="mainpage">
			<form id="FundInfo" action="/SEPractice/teacher/editFundInfo">
				<textarea name="AllFund" id="AllFund">
					${AllFund}
				</textarea><br />
				<button onclick="saveFund()" class="operation">保存</button>
			</form>
			<button onclick="cancelFund()" class="operation">取消</button>
		</div>
		<!--展示教师科研成果的页面 -->
		<div id="showAch" class="mainpage">
			<p>
				${AllAch}
			</p>
			<button onclick="editAch()" class="operation">修改</button>
		</div>
		<!--修改教师科研成果的页面 -->
		<div id="editAch" class="mainpage">
			<form id="AchInfo" action="/SEPractice/teacher/editAchInfo">
				<textarea name="AllAch" id="AllAch">
					${AllAch}
				</textarea><br />
				<button onclick="saveAch()" class="operation">保存</button>
			</form>
			<button onclick="cancelAch()" class="operation">取消</button>
		</div>
	</div>
	<div class="footer">
		<h3>教师信息管理系统小组</h3>
	</div>
</body>
</html>