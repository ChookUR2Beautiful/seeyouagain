//联动 js
var ldData = {};
(function(){
	function ld($thisDom, param){
		var defaults = {
			url : "common/area/getAll.jhtml",//获取数据的URL
			dataId : "id",//数据ID
			dataPid : "pid",//数据父ID
			rootPid : 0,//根节点PID
			dataTitle : "请配置dataTitle属性",//显示出来的字符，其中以${title}输出data中的title字段
			showConfig : [],//select参数[{name:"aa",tipTitle:"无值时显示的数据"width:"宽度"},{name:"aa",tipTitle:"无值时显示的数据"width:"宽度"}]
			
			//--以上为必选参数
			valueAttr : "initValue",//初始值属性
			defaultValue : -1,//省默认值
			isDisabled : false,//是否设为不可用
			isReadOnly : false,//是否设为只读
			isChosen : false,//是否增加联想框
			isMultiple : false,//区域是否支持多选
			separator : ";",//开启多选时设置默认值使用的分隔符
			commonChange : function($dom, level){},//联动条触发change事件后额外掉用的方法
			lastChange : function($dom){},//区选择后的回调方法
			initFinish : function(){}//初始化完成以后回调方法
		};
		var opts = $.extend(defaults, param);
		
		//获取宽度
		var width = (Math.round(100/opts.showConfig.length) - 1) + "%";
		
		//获取title字符串
		var getTitle = function(obj, tempStr){
			var res = "";
			var i = 0;
			while(tempStr.indexOf("${", i) >= 0){
				var start = tempStr.indexOf("${", i);
				var end = tempStr.indexOf("}", i);
				if(start > i){
					res += tempStr.substring(i, start);
				}
				var el = tempStr.substring(start + 2, end);
				if(obj[el]){
					res += obj[el];
				}
				i = end + 1;
			}
			return res += tempStr.substring(i);
		}
		
		//构建option的html(dataArray:数据数组,defaultStr:默认文字,current:当先选中的数据ID)
		var buildOption = function(dataArray,defaultStr,current){
			var html = [];
			html.push('<option value="">'+defaultStr+'</option>');
			if(dataArray && $.isArray(dataArray) && dataArray.length > 0){
				for(var i = 0;i < dataArray.length; i++){
//					console.info(getTitle(dataArray[i],opts.dataTitle));
					if(current == dataArray[i][opts.dataId]){
						html.push('<option value="'+dataArray[i][opts.dataId]+'" selected>'+getTitle(dataArray[i],opts.dataTitle)+'</option>');
					}else{
						html.push('<option value="'+dataArray[i][opts.dataId]+'">'+getTitle(dataArray[i],opts.dataTitle)+'</option>');
					}
					
				}
			}
			return html.join(" ");
		}
		
		//通过ID获取数据
		var getObj = function(id){
			if(id != -1){
				for(var i = 0; i < getData().length; i ++){
					if(getData()[i][opts.dataId] == id){
						return getData()[i];
					}
				}
			}
			return undefined;
		}
		
		//通过父ID获取子集
		var getChildren = function(pid){
			var res = [];
			for(var i = 0; i < getData().length; i ++){
				if(getData()[i][opts.dataPid] == pid){
					res.push(getData()[i]);
				}
			}
			return res;
		}
		
		//选择事件方法
		var change = function($dom){
			var value = $dom.val();
			var obj = getObj(value);
			var level = parseInt($dom.attr("level"));
			var isLast = level == undefined || level == opts.showConfig.length - 1;
			if(!isLast){
				for(var i = level + 1; i < opts.showConfig.length; i++){
					$thisDom.find("select")[i].innerHTML = buildOption(undefined, opts.showConfig[i].tipTitle);
				}
				if(obj){
					$thisDom.find("select")[level + 1].innerHTML = buildOption(getChildren(obj[opts.dataId]), opts.showConfig[level + 1].tipTitle);
				}
				
			}
			opts.commonChange($dom, level);
			if(isLast){
				opts.lastChange($thisDom.find("select:last"));
			}
			if(opts.isChosen){
				$thisDom.find("select").trigger("chosen:updated");
			}
			
		}
		
		var getLevel = function(obj, level){
			if(!level) level = 0;
			if(obj){
				if(obj[opts.dataPid] == opts.rootPid){
					return level;
				}else{
					return getLevel(getObj(obj[opts.dataPid]), ++level);
				}
			}
		}
		
		var setValue = function(obj, level){
			if(obj && level >= 0){
				$thisDom.find("select")[level].innerHTML = buildOption(getChildren(obj[opts.dataPid]), opts.showConfig[level].tipTitle, obj[opts.dataId]);
				setValue(getObj(obj[opts.dataPid]), --level);
			}else{
				return;
			}
		}
		 
		var setDefault = function(id){
			var obj = getObj(id);
			if(obj){
				var level = getLevel(obj);
				setValue(obj, level);
				if(level == opts.showConfig.length - 1){
					opts.lastChange($thisDom.find("select:last"));
				}else{
					$thisDom.find("select")[level + 1].innerHTML = buildOption(getChildren(obj[opts.dataId]), opts.showConfig[level + 1].tipTitle);
				}
			}else{
				$thisDom.find("select")[0].innerHTML = buildOption(getChildren(opts.rootPid), opts.showConfig[0].tipTitle);
			}
			
			if(opts.isChosen){
				$thisDom.find("select").trigger("chosen:updated");
			}
			
		}
		
		var setDefaultValue = function(id){
			var values = [];
			if(id != -1 && opts.isMultiple){
				values = id.toString().split(opts.separator);
				setDefault(values[0]);
				var $options = $thisDom.find("select:last option");
				for(var o in $options){
					for(var v in values){
						if($options[o].value == values[v]){
							$options[o].selected = true;
						}
					}
				}
				if(opts.isChosen){
					$thisDom.find("select").trigger("chosen:updated");
				}
			}else{
				setDefault(id);
			}
		}
		
		this.setDefaultValue = setDefaultValue;
		//初始化数据
		var initData = function(){
			var html = [];
			var values = [];
			for(var i = 0; i < opts.showConfig.length; i++){
				html.push('<select class="form-control" level="'+i+'" style="width:'+(opts.showConfig[i].width?opts.showConfig[i].width:width)+'" '+(opts.showConfig[i].name?'name="'+opts.showConfig[i].name+'"':'')+'><option value="">'+opts.showConfig[i].tipTitle+'</option></select>')
			}
			$thisDom.append(html.join(" "));
			if(opts.isDisabled){
				$thisDom.find("select").attr("disabled", true);
			}
			if(opts.isReadOnly){
				$thisDom.find("select").attr("readonly", true);
			}
			if(opts.defaultValue == -1 && $thisDom.attr(opts.valueAttr)){
				opts.defaultValue = $thisDom.attr(opts.valueAttr);
			}
			if(opts.isMultiple){
				$thisDom.find("select:last").attr("multiple", true);
			}
			
			setDefaultValue(opts.defaultValue);
			
			$thisDom.on("change", "select", function(event){
				change($(event.target));
			});
			if(opts.isChosen){
				//原来的
				/*$thisDom.find("select").chosen({
					search_contains : true,
					allow_single_deselect : false
				});*/
				
				//add by zhiwen start
				//将Select转化为chosen的时候并指定其的宽度
				$thisDom.find("select").each(function(i,item){
					$(this).chosen({
						search_contains : true,
						allow_single_deselect : false,
						width:opts.showConfig[i].width?opts.showConfig[i].width:width
					})
				});
				//add by zhiwen end
			}
			opts.initFinish();
		}
		
		//获取数据
		var getData = function(url){
			if(!url){
				url = opts.url;
			}
			if(ldData[url]){
				return ldData[url];
			}else{
				$.ajax({
					url : url,
					async : false,
					//headers :{"Referrer":window.location.href},
					dataType : "json",
					type:"post",
					data : {"src":window.location.href},
					success : function(data){
						ldData[url] = data;
					}
				});
				return ldData[url];
			}
		}
		
		//插件入口
		initData();
	}
	
	$.fn.createLd = function(param){
		return new ld($(this), param);
	}
	
	//省市区联动
	$.fn.areaLd = function(param){
		var defaults = {
				url : "common/area/getAll.jhtml",//获取数据的URL
				dataId : "areaId",//数据ID
				dataPid : "pid",//数据父ID
				rootPid : 0,//根节点PID
				dataTitle : "${title}",//显示出来的字段
				showConfig : [{name:"province",tipTitle:"--省--"},{name:"city",tipTitle:"--市--"},{name:"area",tipTitle:"--区--"}]
		};
		var opts = $.extend(defaults, param);
		return new ld($(this), opts);
	}
	
	//省市联动
	$.fn.cityLd = function(param){
		var defaults = {
				url : "common/area/getAll.jhtml",//获取数据的URL
				dataId : "areaId",//数据ID
				dataPid : "pid",//数据父ID
				rootPid : 0,//根节点PID
				dataTitle : "${title}",//显示出来的字段
				showConfig : [{name:"province",tipTitle:"--省--"},{name:"city",tipTitle:"--市--"}]
		};
		var opts = $.extend(defaults, param);
		return new ld($(this), opts);
	}
	
	//行业类别联动
	$.fn.tradeLd = function(param){
		var defaults = {
				url : "common/trade/getLdAll.jhtml",//获取数据的URL
				dataId : "tid",//数据ID
				dataPid : "pid",//数据父ID
				dataTitle : "${tradename}",
				rootPid : 0,//根节点PID
				showConfig : [{tipTitle:"请选择"},{tipTitle:"请选择"}]
		};
		var opts = $.extend(defaults, param);
		return new ld($(this), opts);
	}	
	
	//生鲜类别联动
	$.fn.freshLd = function(param){
		var defaults = {
				url : "fresh/manage/getLdAll.jhtml",//获取数据的URL
				dataId : "id",//数据ID
				dataPid : "fid",//数据父ID
				dataTitle : "${name}",
				rootPid : 0,//根节点PID
				showConfig : [{tipTitle:"请选择"},{tipTitle:"请选择"}]
		};
		var opts = $.extend(defaults, param);
		return new ld($(this), opts);
	}
})(jQuery);


