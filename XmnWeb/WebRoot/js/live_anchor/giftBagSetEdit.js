var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			giftNums  :{
				required :true,
				digits:true,
				range:[1,99999]
			}
		},
		message:{
			giftNums:{
				required:"请输入数量",
				digits:"数量必须为数字类型",
				range:"数量须设定为1-99999之间的整数"
			}
		}
	}, liveGiftBagSetSave);
	
});




/**
 * 保存礼包礼物信息
 */
function liveGiftBagSetSave() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	url = "liveGift/manage/updateGiftBagSet" + suffix;
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	
	//if (jsonTextInit != jsonText) {//修改了数据才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					giftBagSetList.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	/*} else {
		$('#prompt').hide();
		$('#triggerModal').modal('hide');
		showSmReslutWindow(false, "没做任何修改！");
	}*/
}


