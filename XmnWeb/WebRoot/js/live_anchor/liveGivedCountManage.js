var formId="searchForm";
$(function() {
	inserTitle(
			' > 打赏分红 > <a href="liveGivedGift/manage/init.jhtml" target="right">打赏总数统计</a> ',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	//日期控件初始化
	liveDateInit();
	
	//导出
	$("#export").click(function(){
		var path="liveGivedCount/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	
	
	

});

/**
 * 加载页面数据
 */
function pageInit(){
	
		//加载概况
		generalCount();
		
		//加载礼物打赏统计
		giftCount();
		
		//加载主播获得打赏统计
		anchorCount();
		
		//加载鸟豆打赏区间统计
		birdBeanZoneCount();
		
		//加载打赏时间统计
		timeZoneCount();
       
}

/**
 * 礼物打赏统计
 */
function giftCount(){
	
	var url;
	var suffix = ".jhtml";
	url = "liveGivedCount/manage/load/giftCount" + suffix;
	var dataform = $("#" + formId).serializeArray();
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt(dataform),
		dataType : 'json',
		async:true,
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(data) {
			if (data.success) {
				var chartData=data.data;
				//定义数据
//			    var chartData = [
//			        {name : '黄瓜',value : 700,color:'#a5c2d5'},
//			        {name : '香蕉',value : 500,color:'#cbab4f'},
//			        {name : '牛奶',value : 1200,color:'#76a871'},
//			        {name : '包子',value : 1200,color:'#76a871'},
//			        {name : '牛排',value : 1500,color:'#a56f8f'},
//			        {name : '鲍鱼',value : 1300,color:'#c12c44'},
//			        {name : '帝王蟹',value : 1500,color:'#a56f8f'},
//			        {name : '满汉全席',value : 1800,color:'#9f7961'},
//			        {"color":"#a5c2d5","name":"皇冠","value":1000}
//			    ];
				var chart = new iChart.Column2D({
			        render : 'canvasDiv',//渲染的Dom目标,canvasDiv为Dom的ID
			        data: chartData,//绑定数据
			        width : 1000,//设置宽度，默认单位为px
			        height : 352,//设置高度，默认单位为px
			        coordinate:{//配置自定义坐标轴
			            scale:[{//配置自定义值轴
			                position:'left',//配置左值轴
			                start_scale:0,//设置开始刻度为0
			                end_scale:250000,//设置结束刻度为26
			                scale_space:50000//设置刻度间距
			            }]
			        },
			        column_width:40,
			        column_space:60,
			        tip:{enable:true},
			        label : {
			            fontfamily:"Microsoft YaHei",
			            fontsize:12,
			            color : '#666666'
			        }
			    });
				//利用自定义组件构造左侧说明文本
		        chart.plugin(new iChart.Custom({
		            drawFn:function(){
		                //计算位置
		                var coo = chart.getCoordinate(),
		                        x = coo.get('originx'),
		                        y = coo.get('originy');
		                //在左上侧的位置，渲染一个单位的文字
		                chart.target.textAlign('start')
		                        .textBaseline('bottom')
		                        .textFont('600 16px Verdana')
		                        .fillText('鸟币',x-40,y-12,false,'#2e2e2e');

		            }
		        }));
			    chart.draw();
			}else{
				showSmReslutWindow(data.success, data.msg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	});
	
	
}

/**
 * 打赏时间统计
 */
function timeZoneCount(){
	
	var url;
	var suffix = ".jhtml";
	url = "liveGivedCount/manage/load/timeZoneCount" + suffix;
	var dataform = $("#" + formId).serializeArray();
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt(dataform),
		dataType : 'json',
		async:true,
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(data) {
			if (data.success) {
				var chartData=data.data;
				//定义数据
			    /*var data4 = [
			        {name : '00:00-9:00',value : 1300,color:'#c12c44'},
			        {name : '9:00-12:00',value : 700,color:'#a5c2d5'},
			        {name : '12:00-15:00',value : 500,color:'#cbab4f'},
			        {name : '15:00-18:00',value : 1200,color:'#76a871'},
			        {name : '18:00-21:00',value : 1200,color:'#76a871'},
			        {name : '21:00-24:00',value : 1500,color:'#a56f8f'},
			    ];*/
			    var chart2 = new iChart.Column2D({
			        render : 'canvasDiv4',//渲染的Dom目标,canvasDiv为Dom的ID
			        data: chartData,//绑定数据
			        width : 800,//设置宽度，默认单位为px
			        height : 352,//设置高度，默认单位为px
			        coordinate:{//配置自定义坐标轴
			            scale:[{//配置自定义值轴
			                position:'left',//配置左值轴
			                start_scale:0,//设置开始刻度为0
			                end_scale:90000,//设置结束刻度为26
			                scale_space:20000//设置刻度间距
			            }]
			        },
			        tip:{enable:true},
			        column_width:45,
			        column_space:60,
			        
			    });
			    //利用自定义组件构造左侧说明文本
			    chart2.plugin(new iChart.Custom({
			        drawFn:function(){
			            //计算位置
			            var coo = chart2.getCoordinate(),
			                    x = coo.get('originx'),
			                    y = coo.get('originy');
			            //在左上侧的位置，渲染一个单位的文字
			            chart2.target.textAlign('start')
			                    .textBaseline('bottom')
			                    .textFont('600 11px Verdana')
			                    .fillText('鸟币',x-40,y-12,false,'#254d70');

			        }
			    }));
			    //调用绘图方法开始绘图
			    chart2.draw();
			}else{
				showSmReslutWindow(data.success, data.msg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	});
	
	
}

/**
 * 主播获得打赏统计
 */
function anchorCount(){
	
	var url;
	var suffix = ".jhtml";
	url = "liveGivedCount/manage/load/anchorCount" + suffix;
	var dataform = $("#" + formId).serializeArray();
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt(dataform),
		dataType : 'json',
		async:true,
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(data) {
			if (data.success) {
				var chartData=data.data;
				//定义数据
				/*var chartData = [
				             {name : 'IE',value : 35.75,color:'#9d4a4a'},
				             {name : 'Chrome',value : 29.84,color:'#5d7f97'},
				             {name : 'Firefox',value : 24.88,color:'#97b3bc'},
				             {name : 'Safari',value : 6.77,color:'#a5aaaa'},
				             {name : 'Opera',value : 2.02,color:'#778088'},
				             {name : 'Other',value : 0.73,color:'#6f83a5'}
				         ];*/
		         var live=new iChart.Pie2D({
		             render : 'canvasDiv2',
		             data: chartData,
		             sub_option : {
		                 mini_label_threshold_angle : 50,//迷你label的阀值,单位:角度
		                 mini_label:{//迷你label配置项
		                     fontsize:12,
		                     fontweight:600,
		                     color : '#ffffff'
		                 },
		                 label : {
		                     background_color:null,
		                     sign:false,//设置禁用label的小图标
		                     padding:'0 4',
		                     border:{
		                         enable:false,
		                         color:'#666666'
		                     },
		                     fontsize:11,
		                     fontweight:600,
		                     color : '#4572a7'
		                 },
		                 tip:{enable:true},
		                 border : {
		                     width : 0,
		                     color : '#ffffff'
		                 },
		                 listeners:{
		                     parseText:function(d, t){
//		                         return d.get('name')+d.get('ratio')+"%";//自定义label文本
		                         return d.get('name');//自定义label文本
		                     }
		                 }
		             },
		             align:'center',//右对齐
		             width : 570,
		             height : 350,
		             radius:160
		         });
		         live.draw();
		         
		         
		         //构建主播获得打赏统计表格
		         buildAnchorCountTable(chartData);

			}else{
				showSmReslutWindow(data.success, data.msg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	});
}


/**
 * 构建主播获得打赏统计表格
 */
function buildAnchorCountTable(data){
	var content="";
	$.each(data, function(index, value, array) {
		content+="<tr>"
			   + "    <td>"+value.name+"</td>" 
			   + "    <td>"+value.ratio+"%</td>" 
			   + "    <td>"+value.birdCoin+"</td>"
			   + " </tr>";
	});
	$("#anchorCountTd").html(content);
}



/**
 * 鸟豆打赏区间人数统计
 */
function birdBeanZoneCount(){
	
	var url;
	var suffix = ".jhtml";
	url = "liveGivedCount/manage/load/birdBeanZoneCount" + suffix;
	var dataform = $("#" + formId).serializeArray();
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt(dataform),
		dataType : 'json',
		async:true,
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(data) {
			if (data.success) {
				var chartData=data.data;
				/*var data3 = [
				              {name : 'IE',value : 35.75,color:'#9d4a4a'},
				              {name : 'Chrome',value : 29.84,color:'#5d7f97'},
				              {name : 'Firefox',value : 24.88,color:'#97b3bc'},
				              {name : 'Safari',value : 6.77,color:'#a5aaaa'},
				              {name : 'Opera',value : 2.02,color:'#778088'},
				              {name : 'Other',value : 0.73,color:'#6f83a5'}
				          ];*/
				  var live2=new iChart.Pie2D({
				      render : 'canvasDiv3',
				      data: chartData,
				      sub_option : {
				          mini_label_threshold_angle : 100,//迷你label的阀值,单位:角度
				          mini_label:{//迷你label配置项
				              fontsize:12,
				              fontweight:600,
				              color : '#ffffff'
				          },
				          label : {
				              background_color:null,
				              sign:false,//设置禁用label的小图标
				              padding:'0 4',
				              border:{
				                  enable:false,
				                  color:'#666666'
				              },
				              fontsize:11,
				              fontweight:600,
				              color : '#4572a7'
				          },
				          tip:{enable:true},
				          border : {
				              width : 0,
				              color : '#ffffff'
				          },
				          listeners:{
				              parseText:function(d, t){
				                  return d.get('title');//自定义label文本
				              }
				          }
				      },
				      align:'center',//右对齐
				      width : 570,
				      height : 350,
				      radius:160
				  });
				  live2.draw();
				  
				  //build鸟豆打赏区间人数统计表格
				  buildBirdBeanZoneCountTable(chartData);
				  
			}else{
				showSmReslutWindow(data.success, data.msg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	});
	
}


/**
 * 构建鸟豆打赏区间人数统计表格
 * @param data
 */
function buildBirdBeanZoneCountTable(data){
	var content="";
	$.each(data,function(index,value,array){
		content+=" <tr>"
		       + " 	<td>"+value.title+"</td>"
		       + "  <td>"+value.ratio+"%</td>" 
               + "  <td>"+value.people+"</td>"
               + "  <td>"+value.perCapita+"</td>"
		       + " </tr>";
	});
	
	$("#birdBeanZoneCountTd").html(content);
}


 /**
  * 直播日期控件初始化
  */
 function liveDateInit(){
	 $('.form_datetime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd'
		});
	 
	 $('input[name="startTime"]').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 0,
			minuteStep :30,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd',
			startDate : new Date(),
			endDate: $("input[name='endTime']").val()
		}).on("changeDate",function() {
				$("input[name='endTime']").datetimepicker("setStartDate",$("input[name='startTime']").val());
			});
		
		$('input[name="endTime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 0,
			minuteStep :30,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd',
			startDate: $("input[name='startTime']").val()
		}).on( "changeDate", function() {
					$("input[name='startTime']").datetimepicker("setEndDate", $("input[name='endTime']").val());
				});
 }
 
 /**
  * 加载统计概况
  */
 function generalCount(){
	var url;
	var suffix = ".jhtml";
	url = "liveGivedCount/manage/load/generalCount" + suffix;
	var dataform = $("#" + formId).serializeArray();
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt(dataform),
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(data) {
			if (data.success) {
				var content='';
				//加载统计区间表单数据
				 content +="<tr>"
		                 + "       <td>"+data.data.startTime+"至"+data.data.endTime+"</td>"				//统计时间区间
		                 + "       <td>"+data.data.birdBean+"</td>"  				//打赏鸟豆
		                 + "       <td>"+data.data.money+"</td>"				 	//打赏金额
		                 + "       <td>"+data.data.liverNum+"</td>"	 				//打赏人数
		                 + "       <td>"+data.data.perCapitaLiver+"</td>"  			//人均打赏金额
		                 + "       <td>"+data.data.anchorNum+"</td>"  				//主播人数
		                 + "       <td>"+data.data.perCapitaAnchor+"</td>"			//主播人均获得
		                 + "       <td><a href='liveGivedGift/manage/init.jhtml'>查看流水</a></td>"
		                 + "</tr>" ;
		        $("#generalDiv").html(content);
			}else{
				showSmReslutWindow(data.success, data.msg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	});
 }
 
 
//查询全部
$("#querySubmit").click(function(){
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	pageInit();
});	

//重置
$("#queryReset").click(function(){
	$("#timeType").val("1");
	$("#startTime").val("");
	$("#endTime").val("");
	
	pageInit();
});	

/**
 * 查询时间修改触发事件
 */
$("#timeType").change(function(){
	var timeType=$("#timeType").val();
	if(timeType==4){
		$("#timeDiv").css("display","block");
	}else{
		$("#timeDiv").css("display","none");
		$("#startTime").val("");
		$("#endTime").val("");
	}
});


/**
 * 自定义校验方法
 */
function validateCustomer(){
	var result=true;
	var timeType=$("#timeType").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	if(timeType=='4' && (startTime == null || startTime==""||endTime==null ||endTime=="")){
		showWarningWindow("warning","请选择时间区间!",9999);
		rsult=false;
		return ;
	}
	
	return result;
}
 