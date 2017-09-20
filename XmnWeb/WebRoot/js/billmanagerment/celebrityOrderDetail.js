$(document).ready(function() {
	initAnchorId();
});

function goBack() {
	history.back();
}

function addTempalte() {
	window.location.href = contextPath
			+ "/transportFee/manage/addView.jhtml";
}

//初始化主播下拉框
function initAnchorId(){
	$("#anchor").chosenObject({
		hideValue : "id",
		showValue : "nickname",
		url : "anchor/manage/initAnchorId.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"80%"
	});
};