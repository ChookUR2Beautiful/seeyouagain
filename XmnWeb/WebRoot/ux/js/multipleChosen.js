
/**
 * 多选下拉框
 */
var chosenData = {};
$.fn.multipleChosen = function selectInfo(param){
	var $this = $(this);
	var isInit = true;
	var defaults = {
		id : "zoneid",//当前页面select框的id
		hideValue : "bid",//实际传到后台进行筛选的值
		showValue : "title",//下拉框显示的值
		url : "",//"common/business/businessInfo.jhtml",//请求数据的url
		filterVal : "",//过滤的值 (eg:common/business/businessInfo.jhtml?filterVal="")
		limit : 50,//分页参数
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:true,//是否使用历史已加载数据
		isMultiple : false,//是否支持多选
		addData : "",//多选下拉框新添加的option数据项(isMultiple为true时生效)
	    defaultValue:"--请选择--",//默认的显示值
	    needJoinValue:true  //value的值只需要编号
	};
	var opts = $.extend(defaults, param);
	//传到后台的参数	
	var params = {"limit":opts.limit , "filterVal":opts.filterVal};
	//获取数据
	var getData = function(search){
//		debugger;
		var key = opts.url;
		if(isInit){
			isInit = false;
		}else{
			params[opts.hideValue] = undefined;
		}
		if(search){
			params[opts.showValue] = search;
		}
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
//		debugger;
		var data = getData(search);
		if(data){
			if(opts.isMultiple){
				$this.attr("multiple","multiple");
				var initValue=$this.attr("initValue");//初始化数据
				//TODO 多选下拉框赋值
				var addData = opts.addData;
				$this.empty();//清空选项
				for(var i=0;i<data.length;i++){
					var selected='';
					var hide = data[i][opts.hideValue];
					var show = data[i][opts.showValue];
					if(initValue){
						var initValueArray=initValue.split(',');
						for(var k=0;k<initValueArray.length;k++){
							if(hide==initValueArray[k]){
								selected='selected';
								break;
							}
						}
					}
//					debugger;
					if(opts.needJoinValue){
						//如果含有编号则拼装编号
						if(opts.isCode){
							$this.append('<option value="'+ hide + '_' + show +'" ' + selected +'>'+"["+hide+"]"+ show +'</option>');
						}else{
							$this.append('<option value="'+ hide + '_' + show +'" ' + selected +'>'+ show +'</option>');
						}
					
					}else{
						//如果含有编号则拼装编号
						if(opts.isCode){
							$this.append('<option value="'+ hide +'" ' + selected +'>'+"["+hide+"]"+ show +'</option>');
						}else{
							$this.append('<option value="'+ hide +'" ' + selected +'>'+ show +'</option>');
						}
						
					}
				}
				
				if(addData){
					var optionsToAppend=addData.split(',');
					for(var j=0;j<optionsToAppend.length;j++){
						var hide = undefined;
						var show = optionsToAppend[j];
						$this.append('<option value="'+ hide + '_' + show +'" ' + 'selected' +'>'+ show +'</option>');
					}
				}
				
			}else{
				
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

