function defaultSetting(){
	document.getElementById("Basic").style.backgroundColor = "#3399cc";
	document.getElementById("Basic").style.color = "white";
	document.getElementById("showBasic").style.display = "block";
}
/* function blue(id){
	document.getElementById(id).style.backgroundColor="#3399cc";
	document.getElementById(id).style.color="white";
}
function white(id){
	document.getElementById(id).style.backgroundColor="white";
	document.getElementById(id).style.color="black";
	
} */
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
	document.getElementById("BasicInfo").submit;
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
function cancelFund() {
	document.getElementById("editFund").style.display = "none";
	document.getElementById("showFund").style.display = "block";
}
//处理科研成果页面的按钮事件的函数
function editAch() {
	document.getElementById("showAch").style.display = "none";
	document.getElementById("editAch").style.display = "block";
}
function cancelAch() {
	document.getElementById("editAch").style.display = "none";
	document.getElementById("showAch").style.display = "block";
}