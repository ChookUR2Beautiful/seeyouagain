(function($) {
	/**
	 * 模版
	 */
	var tableTemplate = '<table class="table table-hover table-bordered table-striped info" >{info}</table>', captionTemplate = '<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">{title}<font style="float:right;">共计【{total}】 个{totalTitle}&nbsp;</font></caption>', theadTemplat = '<thead><tr>{thead}</tr></thead>', tbodyTemplate = '<tbody>{tbody}</tbody>', defaultTemplate = '<tr><td colspan="50">暂无数据</td></tr>',thTemplate='<th style="width:{width}">{title}</th>';

	var defaultModel = {
		title : null,// 表格标题
		totalTitle : null,// 总数标题
		form : null,// 查询表单名称
		checkbox : false,// 是否创建复选框
		backButton : true,// 点击超链接跳转后在另一个页面是否有返回按钮
		addBtn : null,// 添加按钮是否有返回按钮 需要backButton 为true
		handleColumn : {
			title : null,// 标题
			name : null,// 参数名称
			handlePermissions : null,// 需要批量处理的权限 需要设置checkbox为true
			queryPermissions : null,// 单个处理的权限
			column : [{
				title : null,// 标题
				href : null, // 表示超链接调用的连接
				modal : null,// 表示该超链接调用的是模态框
				method : null,// 表示该超链接调用的是方法
				data_width:null,//模态框宽度
				param : [],// 参数
				permissions : null,// 列权限
				customIsShowMethod:null//自定义是否要显示方法，该操作列，使用情形：参见【商家补贴发放管理】-->【修改】操作列的显示是根据发行状态决定的
				}]
			// 当前列的中元素
		}// 操作列中属性
		,
		columns : [{
			title : null,// 标题
			name : null,
			width : 0,// 宽度
			isA : false,// 表示当前列是否是超链接 true:是 false：不是
			
			//<---add by zhiwen 2015-4-30 开始---
			isPercent:false,//表示数值显示是否以百分数形式显示 ,true:是，false:不是,只有用在值为数字时才会有用，其他的非数字字符串使用是无效的
			isZeroShow:false,//表示当数值为0时是否是显示0还是显示"-"，true:显示0,false:显示"-"，只有用在值为数字时才会有用，其他的非数字字符串使用是无效的
			//---add by zhiwen 2015-4-30 结束--->
			
			a : {
				must:false,//是否必须显示，如果为true则列没有权限则也会显示name所表示的数据
				href : null, // 表示超链接调用的连接
				modal : null,// 表示该超链接调用的是模态框
				data_position:null,//模态框显示位置
				data_width:null,//模态框宽度
				method : null,// 表示该超链接调用的是方法
				param : []
				// 参数
			}// 只有当isA为true时 a才有效
			,
			leng : 0,//表示显示几个字符 超过部分截取
			permissions : null,// 单列权限
			customMethod : null
				// 自定义渲染方法
			}]// 列属性
		,
		permissions : null
		// 所有权限

	};

	function gridView(model, data) {
		this.model = $.extend({}, defaultModel, model);
		this.data = data;
		this.html = new Array();
		return this;
	};
	gridView.prototype = {
		constructor : gridView,
		/**
		 * 清空数组并创建新数组
		 */
		newArray : function() {
			this.html = null;
			this.html = new Array();
		},
		/**
		 * 添加元素到数组中
		 */
		push : function(obj) {
			return (this.html.push(obj));
		},
		/**
		 * 将数组转换成字符串
		 */
		toString : function() {
			return (this.html.join(''));
		},
		/**
		 * 判断对象不为空
		 */
		notNull : function(obj) {
			return (null != obj && obj);
		},
		/**
		 * 根据param中的参数从data中取出指定值并序列化为一个URL地址查询字符串
		 */
		getParam : function(param, data) {
			var obj = {};
			if (param && param.length > 0) {
				$.each(param, function(i, k) {
							obj[k] = data[k];
						});
			}
			if (!$.isEmptyObject(obj)) {
				return $.param(obj);
			}
			return null;
		},

		/**
		 * 检查一个array中元素是否存在与obj。
		 */
		contains : function(obj, array) {
			if (array && array.length > 0) {
				var result = false;
				$.each(array, function(index, value) {
							if (obj[value] && $.trim(obj[value]).length > 0) {
								result = true;
								return;
							}
						});
				return result;
			}
			return null;
		},
		/**
		 * 判断对象是否为空
		 */
		isEmptyObject : function(obj) {
			var t;
			for (t in obj) {
				if (obj[t].length > 0)
					return !1;
			}
			return !0;
		},
		/**
		 * 渲染
		 */
		rendering : function() {
			var me = this;
			var model = me.model;
			var dataObj = me.data;
			var b = model.title&&model.totalTitle;
			var caption;
			if(b){
				caption = me.replaceHtml(captionTemplate, {
						"title" : model.title,
						"total" : dataObj.total,
						"totalTitle" : model.totalTitle
					});
			}

			var data = dataObj.content;
			var handleColumn = model.handleColumn;
			var permissions = model.permissions;
			var ishandlePermissions = null;
			if (handleColumn && !me.isEmptyObject(permissions)) {
				if (model.checkbox) {
					ishandlePermissions = me.contains(permissions,
							handleColumn.handlePermissions);
				}
			}
			var columns = model.columns;

			var head = me.renderingHead(handleColumn, columns, permissions,ishandlePermissions);

			var thead = me.replaceHtml(theadTemplat, {
						"thead" : head
					});
			var tbody;
			if (data && data.length > 0) {
				var callbackParam;
				if (model.backButton) {
					callbackParam = "&isBackButton=true&callbackParam="
							+ getFormParam($("#" + model.form).serialize());
					updateAddBtnHref("#" + model.addBtn, callbackParam);
				}
				tbody = me.renderingColumn(data, model, permissions,
						ishandlePermissions, callbackParam);
			} else {
				tbody = defaultTemplate;
			}
			var tb = me.replaceHtml(tbodyTemplate, {
						"tbody" : tbody
					});
			return (me.replaceHtml(tableTemplate, {
						"info" : me.assembled([caption, thead, tb])
					}));
		},
		/**
		 * 拼装table
		 */
		assembled : function(array) {
			var me = this;
			me.newArray();
			$.each(array, function(index, value) {
					if(value){
						me.push(value);
					}
						
					});
			return me.toString();
		},
		/**
		 * 用指定的元素替换html中的元标签
		 */
		replaceHtml : function(html, obj) {
			var key;
			var tempHtml = html;
			for (key in obj) {
				var replaceEment = "{" + key + "}";
				tempHtml = tempHtml.replace(replaceEment, obj[key]);
			}
			return tempHtml;

		},
		/**
		 * 渲染表格头部
		 */
		renderingHead : function(handleColumn, columns, permissions,
				ishandlePermissions) {
			var me = this;
			me.newArray();
			if (handleColumn) {
				if (me.notNull(ishandlePermissions)) {
					var obj = {"width":"2%","title":'<input type="checkbox" />'};
					me.push(me.replaceHtml(thTemplate,obj));
				}
				if (me.contains(permissions, handleColumn.queryPermissions)) {
					var obj = {"width":"8%","title":handleColumn.title};
					me.push(me.replaceHtml(thTemplate,obj));
				}
			}
			$.each(columns, function(index, column) {
						var th=null;
						var obj = {"width":column.width,"title":column.title};
						if (column.isA&&column.isA===true) {
							if (permissions[column.permissions] == "true") {
								th = me.replaceHtml(thTemplate,obj);
							}else if (column.a.must){
								th = me.replaceHtml(thTemplate,obj);
							}
						} else {
							th = me.replaceHtml(thTemplate,obj);
						}
						if(th){
							me.push(th);
						}
					});
			return me.toString();
		},
		/**
		 * 渲染表格行
		 */
		renderingColumn : function(datas, template, permissions,
				ishandlePermissions, callbackParam) {
			var me = this;
			me.newArray();
			var columnHtml,style;
			$.each(datas, function(index, data) {
				me.push('<tr>');
				me.renderingHandleColumn(data, template.handleColumn,
						permissions, ishandlePermissions, callbackParam);
				$.each(template.columns, function(index, column) {
					style= "";
					if (column.width) {
						style = "style='width:" + column.width + "'";
					}
					if (column.isA&&column.isA===true) {
						var value= column.name ? data[column.name] : column.title;
						columnHtml = me.createA(column.permissions, column.a,permissions, data, callbackParam, value,column.leng);
						if(!$($.parseHTML(columnHtml)).is("a")){
							if(column.leng && columnHtml.length>column.leng){
									var titleValue = columnHtml.substr(0,column.leng)+"....";
									style =style +' title='+columnHtml;
									columnHtml = titleValue;
							}
						}
					} else {
						
						//<---add by zhiwen 2015-4-30 开始---
						if(column.isZeroShow && column.isPercent){
							columnHtml = !isNaN(data[column.name])?(100*parseFloat(data[column.name])).toFixed(2):data[column.name] || "-";//如果是数字的话就会转化为百分数，不是的话就无效
						}else if(column.isZeroShow){
							columnHtml = data[column.name];
						}else if(column.isPercent){
							columnHtml = data[column.name]==0?"-":(100*parseFloat(data[column.name])).toFixed(2)+"%";
						}
						else if($.isNumeric(data[column.name])){
							columnHtml =  data[column.name];
						}
						else{
							columnHtml =  data[column.name]|| "-";
						}
						//---add by zhiwen 2015-4-30  结束---->
						
						//原来的代码如下：
						//columnHtml = data[column.name] || "-";
						if(column.leng && columnHtml.length>column.leng){
								var titleValue = columnHtml.substr(0,column.leng)+"....";
								style =style +' title='+columnHtml;
								columnHtml = titleValue;
						}
					}
					if ($.trim(columnHtml).length > 0) {
						// 调用自定义方法
						if (column.customMethod) {
							columnHtml = column.customMethod.call(me,columnHtml, data);
						}
						var td = '<td ' + style + '>' + columnHtml + '</td>';
						me.push(td);
					}
				});
				me.push('</tr>');
			});
			return me.toString();
		},
		/**
		 * 渲染处理行
		 */
		renderingHandleColumn : function(data, handleColumn, permissions,
				ishandlePermissions, callbackParam) {
			var me = this;
			if (handleColumn) {
				if ((me.notNull(ishandlePermissions))) {
					var th = '<th nowrap><input type="checkbox" val='
							+ data[handleColumn.name] + '></th>';
					me.push(th);
				}

				var columns = handleColumn.column;
				var columnHtml = "";
				$.each(columns, function(index, column) {
					if(column.customIsShowMethod){//配置了是否显示操作列
						if(column.customIsShowMethod.call(me,columnHtml, data)){//返回值为真的话就会显示
							columnHtml = columnHtml
							+ (me.createA(column.permissions, column,
									permissions, data, callbackParam,
									column.title) || "");
						}else{
							//columnHtml = '<a href="javascript:void(0)"></a>';//添加一个空的连接
						}
					}else{//没有配置就是默认显示的
						columnHtml = columnHtml
						+ (me.createA(column.permissions, column,
								permissions, data, callbackParam,
								column.title) || "");
					}
						});
				if ($.trim(columnHtml).length > 0) {
					var td = '<td>' + columnHtml + '</td>';
					me.push(td);
				}
			}
		},
		/**
		 * 创建超链接
		 */
		createA : function(columnPermissions, column, permissions, data,
				callbackParam, displayValue,leng) {
			if (permissions &&columnPermissions&& permissions[columnPermissions] == "true") {
				var a = "-";
				var titleValue=displayValue;
				if(leng && displayValue.length>leng){
						titleValue = displayValue.substr(0,leng)+"....";
				}
				if (column.href) {
					var param = this.getParam(column.param, data);
					if (param != null) {
						a = '<a href=' + column.href + '?' + param + "&"
								+ callbackParam + '" title='+displayValue+'>' + titleValue
								+ '</a>&nbsp;&nbsp;';
					} else {
						a = '<a href=' + column.href + '?' + callbackParam
								+ '" title='+displayValue+'>' + titleValue
								+ '</a>&nbsp;&nbsp;';
					}
				} else if (column.modal) {
					var param = this.getParam(column.param, data);
					var array = new Array();
					array.push("<a href='javascript:;' data-type='ajax'  data-toggle='modal' data-url=");
					array.push(column.modal);
					if(param){
						array.push("?");
						array.push(param);
					}
					array.push(" data-width=");
					array.push(column.data_width!=null ? column.data_width : "30%");
					if(column.data_position){
						array.push(" data-position=" );
						array.push(column.data_position);
					}
					array.push(" title=");
					array.push(displayValue);
					array.push(">");
					array.push(titleValue);
					array.push('</a>');
					array.push('&nbsp;&nbsp;');
					
					a = array.join("");
				} else if (column.method) {
					a = '<a href="javascript:' + column.method + '('
							+ data[column.param] + ')" title='+displayValue+'>' + titleValue
								+ '</a>&nbsp;&nbsp;';
				}
				return a;
			}else if(column.must){
				return displayValue;
			}
			return null;
		}
	}
	/**
	 * 挂载至jquery
	 */
	$.extend({
				renderGridView : function(model, data) {
					return new gridView(model, data).rendering();
				}
			});

})(jQuery)
