<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
		background:url("../picture/hitbg.jpg") no-repeat;
		background-size:cover;
		width:100%;
	}
	.main #studentTitle {
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
<script>
function defaultSetting(){
	document.getElementById("Basic").style.backgroundColor = "#3399cc";
	document.getElementById("Basic").style.color = "white";
	document.getElementById("showBasic").style.display = "block";
	var exeNameSearch = "${exeNameSearch}";
	var exeFilterSearch = "${exeFilterSearch}";
	if (exeNameSearch == "eNS") {
		document.getElementById("Basic").style.backgroundColor = "white";
		document.getElementById("Basic").style.color = "black";
		document.getElementById("TeacherSearch").style.backgroundColor = "#3399cc";
		document.getElementById("TeacherSearch").style.color = "white";
		document.getElementById("showBasic").style.display = "none";
		document.getElementById("showTeacherSearch").style.display = "block";
		document.getElementById("NameSearchResults").style.display = "block";
		var NameList = "${NameList}";
		var ele,textNode;
		for(i = 0;i < NameList.size();i++){
			ele = document.createElement("a");
			textNode = document.createTextNode(NameList.get(i));
			ele.appendChild(textNode);
			document.getElementById("NameSearchResults").appendChild(ele);
		}
	}
	if (exeFilterSearch == "eFS") {
		document.getElementById("Basic").style.backgroundColor = "white";
		document.getElementById("Basic").style.color = "black";
		document.getElementById("TeacherSearch").style.backgroundColor = "#3399cc";
		document.getElementById("TeacherSearch").style.color = "white";
		document.getElementById("showBasic").style.display = "none";
		document.getElementById("seniorSea").style.display = "block";
		document.getElementById("FilterSearchResults").style.display = "block";
	}
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
		if ("TeacherSearch" != ele){
			document.getElementById("TeacherSearch").style.background = "white";
			document.getElementById("TeacherSearch").style.color = "black";
			document.getElementById("showTeacherSearch").style.display = "none";
			document.getElementById("seniorSea").style.display = "none";
		}
		if ("LearningDir" != ele){
			document.getElementById("LearningDir").style.background = "white";
			document.getElementById("LearningDir").style.color = "black";
			document.getElementById("showLearningDir").style.display = "none";
			document.getElementById("editLea").style.display = "none";
		}
	}
//处理基本信息页面的按钮事件的函数
function editBasic(){
	document.getElementById("showBasic").style.display = "none";
	document.getElementById("editBasic").style.display = "block";
}
function saveBasic(){
	//document.getElementById("BasicInfo").action="teacher/editBasicInfo?username=${username }";
	//alert('已经到这了');
	document.getElementById("BasicInfo").submit();
}
function cancelBasic() {
	document.getElementById("editBasic").style.display = "none";
	document.getElementById("showBasic").style.display = "block";
}
//处理教师检索的按钮事件的函数
function seniorSea(){
	document.getElementById("seniorSea").style.display = "block";
	document.getElementById("showTeacherSearch").style.display = "none";
	//document.getElementById("AllHonor").value = "暂未填写";
}
function seaName(){
	document.getElementById("nameSea").submit;
}
function cancelSea() {
	document.getElementById("seniorSea").style.display = "none";
	document.getElementById("showTeacherSearch").style.display = "block";
}
//处理科研成果页面的按钮事件的函数
function editLea() {
	document.getElementById("showLearningDir").style.display = "none";
	document.getElementById("editLea").style.display = "block";
}
function saveLea(){
	document.getElementById("LeaInfo").submit;
}
function cancelLea() {
	document.getElementById("editLea").style.display = "none";
	document.getElementById("showLearningDir").style.display = "block";
}

</script>
</head>

<body onload="defaultSetting()">
	<div class="header">
		<h3>学生${username }主页</h3>
	</div>
	<div class="main">
		<div id="studentTitle">
			
		</div>
		<!--导航栏 -->
		<ul>
			<li><button onclick="show('Basic')" id="Basic">基本信息</button></li>
			<li><button onclick="show('TeacherSearch')" id="TeacherSearch">教师检索</button></li>
			<li><button onclick="show('LearningDir')" id="LearningDir">学习方向</button></li>
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
			<button onclick="editBasic()" class="operation" style="position:relative;left:300px;">修改</button>
		</div>
		<!--修改基本信息的页面 -->
		<div id="editBasic" class="mainpage">
			<form id="BasicInfo" action="/SEPractice/student/editBasicInfo">
				&nbsp;&nbsp;&nbsp;姓名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name" value="${name}"></input><br />
				&nbsp;&nbsp;&nbsp;学校&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="school" value="${school}"></input><br />
				&nbsp;&nbsp;&nbsp;性别&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="male" style="height:15px" checked>男
														<input type="radio" name="sex" value="female"style="height:15px">女<br />
				&nbsp;&nbsp;&nbsp;籍贯&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="address" value="${address}"></input><br />
				&nbsp;&nbsp;&nbsp;院系&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="department" value="${department}"></input><br />
				&nbsp;&nbsp;&nbsp;专业&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="major" value="${major}"></input><br />
				&nbsp;&nbsp;&nbsp;电话&nbsp;&nbsp;&nbsp;&nbsp;<input type="tel" name="telephone" value="${telephone}"></input><br />
				<button onclick="saveBasic()" class="operation" style="position:relative;left:300px;">保存</button>
			</form>
			<button onclick="cancelBasic()" class="operation" style="position:absolute;left:400px;top:605px">取消</button>
		</div>
		<!--教师检索的页面 -->
		<div id="showTeacherSearch" class="mainpage">
			<form id="nameSea" action="/SEPractice/student/nameSearch">
				教师名字：<input type="text" name="teacherName" style="height:35px;" placeholder="教师姓名" value="${teacherName}">
				<button onclick="seaName()" name="exeNameSearch" value="eNS" class="operation" style="position:relative;top:5px;">搜索</button>
			</form>
			<div id="NameSearchResults" class="mainpage">
				检索结果<br />
				<s:iterator value = "NameList" var = 'n'>
		  			<s:url action="/SEPractice/student/showTeacherPage" var="urlTag" >
	   		 			<s:param name="requestParam"><s:property value = 'n'/></s:param>
					</s:url>
					<s:a href="%{urlTag}" style="text-decoration:none" target="_blank"><s:property value = 'n'/></s:a><br>
				</s:iterator>
			</div>
			<button onclick="seniorSea()" class="operation" style="width:160px;">高级搜索</button>
		</div>
		<!--教师高级检索的页面 -->
		<div id="seniorSea" class="mainpage">
			<form id="filterOption" action="/SEPractice/student/filterSearch">
				<!-- <input list="options" multiple>
					<datalist id="options">
						<option value="科学院院士">
						<option value="工程院院士">
						<option value="长江学者">
						<option value="博士生导师">
					</datalist>  -->
					<h3>筛选条件</h3>
					<input type="checkbox" name="SciAcademician" value="SA">科学院院士
					<input type="checkbox" name="EngAcademician" value="EA">工程院院士
					<input type="checkbox" name="YangtzeScholor" value="YS">长江学者
					<input type="checkbox" name="DrSupvisor" value="DS">博士生导师
				<button onclick="saveSea()" name="exeFilterSearch" value="eFS" class="operation">搜索</button>
			</form>
			<div id="FilterSearchResults" class="mainpage">
				筛选结果
			</div>
			<button onclick="cancelSea()" class="operation">取消</button>
		</div>
		<!--展示学生学习方向的页面 -->
		<div id="showLearningDir" class="mainpage">
			<p>
				${AllLea}
			</p>
			<button onclick="editLea()" class="operation">修改</button>
		</div>
		<!--修改学习方向的页面 -->
		<div id="editLea" class="mainpage">
			<form id="LeaInfo" action="/SEPractice/student/editLeaInfo">
				<textarea name="AllLea" id="AllLea">
					${AllLea}
				</textarea><br />
				<button onclick="saveLea()" class="operation">保存</button>
			</form>
			<button onclick="cancelLea()" class="operation">取消</button>
		</div>
	</div>
	
	
	<div class="footer">
		<h3>教师信息管理系统小组</h3>
	</div>
</body>