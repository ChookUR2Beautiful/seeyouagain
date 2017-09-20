var imgRoot = $("#fastfdsHttp").val();
var seats = new Array();
var addUrl="fashionTickets/add.jhtml";
// 清除ckEditor实例
if (CKEDITOR.instances['content']) {
	CKEDITOR.instances['content'].destroy(true);
}
// 初始化富文本编辑器
$("textarea#content").ckeditor({

});

$("#ld").cityLd({
	isChosen : false
});

$("#picImg").uploadImg({
	urlId : "pic",
	showImg : $('#pic').val()
});
$("#seatPicImg").uploadImg({
	urlId : "seatPic",
	showImg : $('#seatPic').val()
});
$("#logoImg").uploadImg({
	urlId : "logo",
	showImg : $('#logo').val()
});

//初始化日期
$('#saleStartTime').datetimepicker({
    format:'yyyy-mm-dd hh:ii',
    startDate: new Date(),
    language:  'zh-CN',
    //weekStart: 1,
    //todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    //minView:2,
    forceParse: 0,
    //showMeridian: 1
}).on("changeDate",function(ev){
    var transferdate=transferDate($("#saleStartTime").val());//转时间日期
    $('#saleEndTime').datetimepicker('remove');
    $('#saleEndTime').datetimepicker({
        format:'yyyy-mm-dd hh:ii',
        language:  'zh-CN',
        //minView:2,
        autoclose: 1,
        'startDate':transferdate
    }).on("changeDate",function(ev){
        var enddate=$("#saleEndTime").val();
        setEndTime(enddate);
    });
});
$('#saleEndTime').datetimepicker({
    format:'yyyy-mm-dd hh:ii',
    startDate: new Date(),
    language:  'zh-CN',
    minView:2,
    autoclose: 1
}).on("changeDate",function(ev){
    var enddate=$("#saleEndTime").val();
    setEndTime(enddate);
});
function setEndTime(enddate){
    $('#saleStartTime').datetimepicker('remove');
        $('#saleStartTime').datetimepicker({
            format:'yyyy-mm-dd hh:ii',
            language:  'zh-CN',
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            'endDate':transferDate(enddate)
    });
}


//初始化日期
$('#useStartTime').datetimepicker({
  format:'yyyy-mm-dd hh:ii',
  startDate: new Date(),
  language:  'zh-CN',
  //weekStart: 1,
  //todayBtn:  1,
  autoclose: 1,
  todayHighlight: 1,
  startView: 2,
  //minView:2,
  forceParse: 0,
  //showMeridian: 1
}).on("changeDate",function(ev){
  var transferdate=transferDate($("#useStartTime").val());//转时间日期
  $('#useEndTime').datetimepicker('remove');
  $('#useEndTime').datetimepicker({
      format:'yyyy-mm-dd hh:ii',
      language:  'zh-CN',
      //minView:2,
      autoclose: 1,
      'startDate':transferdate
  }).on("changeDate",function(ev){
      var enddate=$("#saleEndTime").val();
      setEndTime(enddate);
  });
});
$('#useEndTime').datetimepicker({
  format:'yyyy-mm-dd hh:ii',
  startDate: new Date(),
  language:  'zh-CN',
  minView:2,
  autoclose: 1
}).on("changeDate",function(ev){
  var enddate=$("#useEndTime").val();
  setUserEndTime(enddate);
});
function setUserEndTime(enddate){
  $('#useStartTime').datetimepicker('remove');
      $('#useStartTime').datetimepicker({
          format:'yyyy-mm-dd hh:ii',
          language:  'zh-CN',
          autoclose: 1,
          todayHighlight: 1,
          startView: 2,
          forceParse: 0,
          'endDate':transferDate(enddate)
  });
}


//将时间字符串转为date
function transferDate(data){
    var start_time=data;
    var newTime= start_time.replace(/-/g,"-");
    var transferdate = new Date(newTime);
    return transferdate;
}
function transferTime(str){
    var newstr=str.replace(/-/g,'-');
    var newdate=new Date(newstr);
    var time=newdate.getTime();
    return time;
}


function loadSeat(data){
	$.ajax({
		url : "fashionTickets/getSeatsByIds.jhtml",
		type : "post",
		dataType : "json",
		data:{ids:seats.toString()},
		success : function(result) {  
			if (result.success) {
				$("#seatList").html("");
				var content='';
				var num=0;
				//加载统计区间表单数据
				$.each(result.data,function(i,item){
					content +="<tr id='"+item.id+"'>"
						+ "       <td>"+item.seatName+"</td>"  				
						+ "       <td>"+item.seats+"</td>"  				
						+ "       <td>"+item.num+"</td>"  				
						+ "       <td>"+item.totalSeats+"</td>"  				
						+ "       <td>"+item.zoneRangeMin+"-"+item.zoneRangeMax+"</td>"  				
						+ '       <td><a href="javascript:;"  data-type="ajax" data-url="fashionTickets/add/seat/init.jhtml?id='+item.id+'" data-toggle="modal">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="delectSeat('+item.id+')" >删除</a></td>'; 				
						+ "</tr>" ;
						if(item.totalSeats){
							num+=item.totalSeats;
						}
				})
				$("#seatList").html(content);
				var supportSelectSeats= $("input[name='supportSelectSeats']:checked").val();
				if(supportSelectSeats==1){
					$("#totalSeats").val(num);
				}
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
}

function loadPrice(){
	$.ajax({
		url : "fashionTickets/getPricesBySeatsIds.jhtml",
		type : "post",
		dataType : "json",
		data:{seatsIds:seats.toString()},
		success : function(result) {
			if (result.success) {
				$("#priceList").html("");
				var content='';
				//加载统计区间表单数据
				$.each(result.data,function(i,item){
					content +="<tr>"
						+ "       <td>"+item.seatName+"</td>"  				
						+ "       <td>"+item.buyNum+"</td>"  				
						+ "       <td>"+item.price+"</td>"  				
						+ '       <td><a href="javascript:;"  data-type="ajax" data-url="fashionTickets/add/price/init.jhtml?id='+item.id+'" data-toggle="modal">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="delectSprice('+item.id+')" >删除</a></td>'; 				
						+ "</tr>" ;
				})
				$("#priceList").html(content);
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
}

function delectSeat(id){
	$.ajax({
		url : "fashionTickets/add/seat/delete.jhtml",
		type : "post",
		dataType : "json",
		data:{id:id},
		success : function(result) {
			if (result.success) {
				$("#"+id).remove();
				seats.remove(id);
				loadPrice();
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
}

function delectSprice(id){
	$.ajax({
		url : "fashionTickets/add/price/delete.jhtml",
		type : "post",
		dataType : "json",
		data:{id:id},
		success : function(result) {
			if (result.success) {
				loadPrice();
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
}

$("input[name='supportSelectSeats']").on("click",function(){
	$("input[name='supportSelectSeats']").attr("disabled","");
	var supportSelectSeats= $("input[name='supportSelectSeats']:checked").val();
	if(supportSelectSeats==0){
		if(seats.length==0){
			$.ajax({
				url : "fashionTickets/add/seat/add.jhtml",
				type : "post",
				dataType : "json",
				data:{seatName:"默认"},
				success : function(result) {
					if (result.success) {
						seats.push(result.data.id);
					} else {
						showSmReslutWindow(result.success, result.msg);
					}
				}
			});
		}
		$("#seatsBody").remove();
	}else{
		$("#totalSeats").attr("readonly","readonly");
	}
	$("#details").show();
});

Array.prototype.indexOf = function(val) {
for (var i = 0; i < this.length; i++) {
if (this[i] == val) return i;
}
return -1;
};

Array.prototype.remove = function(val) {
var index = this.indexOf(val);
if (index > -1) {
this.splice(index, 1);
}
};

validate("editFrom",{
	rules : {
		title:{
			required : true
		},
		address : {
			required : true
		},
		longitude : {
			required : true
		},
		latitude:{
			required : true
		},
		totalSeats:{
			required : true
		},
		content:{
			required : true
		},
		saleStartTime:{
			required : true
		},
		saleEndTime:{
			required : true
		},
		useStartTime:{
			required : true
		},
		useEndTime:{
			required : true
		},
		tel:{
			required : true
		}
	},
	messages:{
	}
},save);

function save(){
	if(!$("#pic").val()){
		showWarningWindow("warning","请选择活动图片!",9999);
		return;
	}
	if($("#seatsBody").length){
		if(!$("#seatList").find("tr").length){
			showWarningWindow("warning","请添加定义座位!",9999);
			return;
		}
	}
	if($("#seatPic").length){
		if(!$("#seatPic").val()){
			showWarningWindow("warning","座位说明图片!",9999);
			return;
		}
	}
	if(!$("#priceList").find("tr").length){
		showWarningWindow("warning","请添加门票售价!",9999);
		return;
	}else{
		debugger;
		//必须有张数是一的价格
		var choose=true;
		var trs=$("#seatList").find("tr");
		var seatName='';
		if($("#seatsBody").length){
			choose=false;
			var l=0;
			$.each(trs,function(i,item){
				var ll=l+0;
				seatName= $(item).find("td").eq(0).text();
				$.each($("#priceList").find("tr"),function(i,item1){
					if($(item1).find("td").eq(0).text()==seatName&&$(item1).find("td").eq(1).text()==1){
						l++;
						return;
					}
				});
				if(l<=ll){
					choose=true;
					return false;
				}
			});
			
		}else{
			$.each($("#priceList").find("tr"),function(i,item1){
				if($(item1).find("td").eq(1).text()==1){
					choose=false
					return;
				}
			});
		}
		if(choose){
			showWarningWindow("warning",seatName+"座位必须添加单张的价格!",9999);
			return;
		}
	}
	if(!$("#logo").val()){
		showWarningWindow("warning","座位说明图片!",9999);
		return;
	}
	var data = $('#editFrom').serializeArray();
	data=jsonFromt(data);
	if(!data.province||!data.city){
		showWarningWindow("warning","请选择省市!",9999);
		return;
	}
	data.fids=seats.toString();
	data.content=$("#content").val();
	data.supportSelectSeats= $("input[name='supportSelectSeats']:checked").val();
	if($("#onlyCoin").get(0).checked) {
		data.onlyCoin=$("#onlyCoin").val();
	}
	if($("#limitEveryone").get(0).checked) {
		data.limitEveryone=$("#limitEveryone").val();
	}
	$.ajax({
		type : 'post',
		url : addUrl,
		data : data,
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			//$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			if(data.success){
				console.log(data.success)
				window.location.href = "fashionTickets/init.jhtml";
			}else{
				showSmReslutWindow(data.success, data.msg);
			}
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
}

