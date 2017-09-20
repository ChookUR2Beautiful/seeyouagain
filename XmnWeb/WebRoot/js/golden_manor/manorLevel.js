var recordList;
var initListUrl = "manor/level/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
		' > 黄金庄园 > <a href="experienceofficer/activity/init.jhtml" target="right">庄园等级管理</a> ',
		'userSpan', true);

	initList();
	
	initCouponList();
	
	
});

//初始化等级列表
function initList() {

	recordList = $("#recordList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

}


function initCouponList(){
	console.info("当前类型:"+$("#ctype").val());
	$("#cid").chosenObject2({
		hideValue : "cid",
		showValue : "cname",
		showType:"multiple",//选项显示形式
		showParams:["cname"],
		otherParams:{ctype:$("#ctype").val()},
		url : "manor/level/init/coupon.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

function success(data, obj) {
	var callbackParam = "isBackButton=true&callbackParam="
	+ getFormParam($("#searchForm").serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
//		caption : captionInfo,
		checkable : false,
		// 操作列
		handleCols : {
			title : "操作", // 标题
			queryPermission : [ "update"], // 不需要选择checkbox处理的权限
			width : 60, // 宽度
			// 当前列的中元素
			cols : [
				{
					title : "修改", // 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "manor/level/update/init.jhtml",
							width : "auto",
							title : "修改等级信息"
						},
						param : [ "id" ],
						permission : "update"
					}
				}
			]
		},
		cols : [ {
			title : "等级编号",
			name : "no",
			type : "string",
			width : 40
		}, {
			title : "关卡级别",
			name : "name",
			type : "string",
			width : 120
		}, {
			title : "玫瑰花田(朵)",
			name : "roses",
			type : "int",
			width : 80
		}, {
			title : "兰花田(朵)",
			name : "orchids",
			type : "int",
			width : 80
		}, {
			title : "葵花田(朵)",
			name : "sunflowers",
			type : "int",
			width : 80
		}, {
			title : "日收益花蜜(滴)",
			name : "dailyNectar",
			type : "int",
			width : 80
		}, {
			title : "logo",
			name : "levelLogo",
			type : "string",
			width : 40,
			customMethod : function(value, data) {
				if(!data.levelLogo){
					return "-";
				}
				return "<img src='"+imgRoot+data.levelLogo+"'height='60' width='60' >";
			}
		}, {
			title : "等级图",
			name : "levelPic",
			type : "string",
			width : 40,
			customMethod : function(value, data) {
				if(!data.levelLogo){
					return "-";
				}
				return "<img src='"+imgRoot+data.levelPic+"'height='60' width='60' >";
			}
		}]
	}, permissions);
	
}

var chosenData = {};
$.fn.chosenObject2 = function selectInfo(param){
	console.info("---init chosen...");
	var $this = $(this);
	var isInit = true;
	var defaults = {
		id : "zoneid",//当前页面select框的id
		hideValue : "bid",//实际传到后台进行筛选的值
		showValue : "title",//下拉框显示的值
		showType:"",//选项显示形式
		showParams:[],//showType为multiple时生效,phone|nickname
		otherParams:{},	//默认的其他筛选条件
		url : "",//"common/business/businessInfo.jhtml",//请求数据的url
		filterVal : "",//过滤的值
		limit : 5,//分页参数
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:true,//是否使用历史已加载数据
		isMultiple : false,//是否支持多选
		isInit : true,
	    defaultValue:"--请选择--"//默认的显示值
	};
	var opts = $.extend(defaults, param);
	//传到后台的参数	
	var params = {"limit":opts.limit,"filterVal":opts.filterVal};
	//获取数据
	var getData = function(search){
		console.info("---init...:"+isInit);
		var key = opts.url;
		if(isInit){
			isInit = false;
			params[opts.hideValue] = $this.attr("initValue");
		}else{
			params[opts.hideValue] = undefined;
		}
		if(search){
			params[opts.showValue] = search;
		}
		params = Object.assign(params,opts.otherParams);
		key += JSON.stringify(params);
		if(chosenData[key] && opts.isHistorical){
			return chosenData[key];
		}else{
			$.ajax({
				type: "POST",
				url : opts.url,
				async : false,
				dataType : "json",
				data: params,
				success : function(data){
					chosenData[key] = data.content;
				}
			});
			return chosenData[key];
		}
	};
	
	//拼装数据
	var build = function(search){
		var data = getData(search);
		if(data){
			if(opts.isMultiple){
				$this.attr("multiple","multiple");
				$this.empty();//清空选项
				for(var i=0;i<data.length;i++){
					var hide = data[i][opts.hideValue];
					var show = data[i][opts.showValue];
					//如果含有编号则拼装编号
					if(opts.isCode){
						$this.append('<option value="'+ hide +'" '+(hide ==$this.attr("initValue")?'selected':'')+'>'+"["+hide+"]"+ show +'</option>');
					}else{
						$this.append('<option value="'+ hide +'" '+(hide ==$this.attr("initValue")?'selected':'')+'>'+ show +'</option>');
					}
				}
			}else{
				$this.empty().append('<option value="">'+opts.defaultValue+'</option>');
				for(var i=0;i<data.length;i++){
					var hide = data[i][opts.hideValue];
					var show = data[i][opts.showValue];
					
					var content="";
					var dataVal="";
					$.each(opts.showParams, function(index, value, array) {
						dataVal=data[i][value]==undefined?"":data[i][value];
						content+="["+dataVal+"]";
					});
					//如果含有编号则拼装编号
					if(opts.isCode){
						if(opts.showType=="multiple"){
							$this.append('<option value="'+ hide +'" '+(hide ==$this.attr("initValue")?'selected':'')+'>'+"["+hide+"]"+ content +'</option>');
						}else{
							$this.append('<option value="'+ hide +'" '+(hide ==$this.attr("initValue")?'selected':'')+'>'+"["+hide+"]"+ show +'</option>');
						}
						
					}else{
						if(opts.showType=="multiple"){
							$this.append('<option value="'+ hide +'" '+(hide ==$this.attr("initValue")?'selected':'')+'>'+ content +'</option>');
						}else{
							$this.append('<option value="'+ hide +'" '+(hide ==$this.attr("initValue")?'selected':'')+'>'+ show +'</option>');
						}
					}
					
				}
			}
		}
		
	};
	build();
	
	
	//判断是否可以模糊搜索
	if(opts.isChosen){
		$this.chosen({
				search_contains : true,
				allow_single_deselect : false,
				remote_function : function(search){
					if(!opts.isMultiple){
						build(search);
					}
				},
				width: opts.width?opts.width:"90%"
		});
	}
	$.extend($this, {
		initData : build
	});
	return $this;
};

