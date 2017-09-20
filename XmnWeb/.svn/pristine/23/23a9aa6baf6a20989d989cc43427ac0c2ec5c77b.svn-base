$(document).ready(function() {
	showPicORHide();
});
function showPicORHide(type){
	var bannerStyle = type?type:$("#checkBannerStyle").val();
	bannerStyle = parseInt(bannerStyle);
	if(bannerStyle === 0){
		$("#pic1Div").css("display",'block');
		$("#sortid1").html('');
		$("#pic2Div").css("display",'none');
	}else if(bannerStyle === 1){
		$("#pic1Div").css("display",'block');
		$("#pic2Div").css("display",'block');
	}else if(bannerStyle === 2){
		$("#pic1Div").css("display",'block');
		$("#sortid1").html('');
		$("#pic2Div").css("display",'none');
	}else {
		$("#pic1Div").css("display",'none');
		$("#pic2Div").css("display",'none');
	}
}