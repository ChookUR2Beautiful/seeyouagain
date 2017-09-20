+function($, window) {
	
	var name = 'zui.datatable';
	/**
	 * 构造器
	 */
	var ScrollTablel = function(element, options, permissions) {
		this.$ = $(element);
		this.initScrollTablel(options, permissions);
	};

	/**
	 * 默认配置项
	 */
	ScrollTablel.DEFAULTS = {
		checkable : false,//checkable
		identifier : null,// 编号  显示checkbox时需要
		checkdisenable : null,	//2016-4-21 By ChenBo check复选框依据checkdisenable对应属性的值来设置为是否禁止可选。 属性值为true，禁止可选，属性为null或属性值为false时，允许可选
		tableClass : "table-bordered table-striped info", // 表格样式
		callbackParam : null, //返回上一级参数
		// 数据
		data : null,
		// 数据行
		cols : [ {
			title : null,// 标题
			name : null,//字段名称
			sort : null,// 是否排序  up 升序 down 降序
			width : null,// 宽度,
			leng : 0,// 显示长度
			type : null,// 数据类型
			isLink : false,// 表示当前列是否是超链接 true:是 false：不是
			
			// 连接信息 只有当isLink为true时 才有效
			link : {
				required : false,// 是否必须显示，如果为true则列没有权限则也会显示name所表示的数据
				linkInfoName : null,// href , modal ,method
				linkInfo : {
					href : null, // 表示超链接调用的连接
					// 表示该超链接调用的是模态框
					modal : {
						url : null,// url
						position : null,// 模态框显示位置
						width : null,	// 模态框宽度
						title : null //模态框标题
				
					},
					method : null // 表示该超链接调用的是方法(这里填的是方法名称，如：在js中定义了一个方法update,则在此处填"update")
				
				},
				param : [],// 参数
				customParam:[],//自定义参数
				permission : null // 单列权限
			},
			customMethod : null // 自定义渲染方法
		
		} ],
		// 操作列
		handleCols : {
			title : null,// 标题
			handlePermission : [],// 需要选择checkbox处理的权限
			queryPermission : [],// 不需要选择checkbox处理的权限
			width : null,// 宽度
			// 当前列的中元素
			cols : [ {
				width : null,// 宽度
				title : null,// 标题
				linkInfoName : null, // href , modal ,method
				linkInfo : {
					href : null, // 表示超链接调用的连接
					// 表示该超链接调用的是模态框
					modal : {
						url : null,// url
						position : null,// 模态框显示位置
						width : null, // 模态框宽度
						title : null //模态框标题
					
					},
					method : null ,// 表示该超链接调用的是方法
					param : [],// 参数
					customParam:[],//自定义参数
					permission : null// 列权限
				
				},
				customMethod : null // 自定义渲染方法
			} ]
		}
	};

	/**
	 * 初始化
	 * 
	 * @param options
	 *            配置参数
	 * @param permissions
	 *            权限
	 */
	ScrollTablel.prototype.initScrollTablel = function(options, permissions) {
		//debugger;
		this.permissions = permissions;
		this.data = {
			cols : [],
			rows : []
		};
		this.options = $.extend({}, ScrollTablel.DEFAULTS, options);
	};

	/**
	 * 装载数据
	 */
	ScrollTablel.prototype.load = function() {
		//debugger;
		var that = this;
		var options = that.options, data = that.data, cols = that.data.cols, rows = that.data.rows;
		this.loadCols(options, cols);
		this.loadRows(options, rows);
		return data;
	};

	/**
	 * 装载列
	 * 
	 * @param options
	 *            配置参数
	 * @param cols
	 *            列数组
	 */
	ScrollTablel.prototype.loadCols = function(options, cols) {
		var that = this;
		var setCols = function(cols,col){
			cols.push({
				width : col.width,
				colClass : "text-ellipsis",
				text : col.title,
				type : col.type,
				flex : true,
				sort : col.sort || false
			});
		};
		that.loadHandleCols(options.handleCols, cols);
		that.loop(options.cols, function(i,col,cols){
			// 超链接
			if (col.isLink === true) {
				// 权限认证 或者该字段是必须的 则显示 否则不显示
				if ((that.checkPermission(col.link.permission) === true)
						|| (col.link.required === true)) {
					setCols(cols,col);
				}
			} else {// 非超链接
				setCols(cols,col);
			}
		}, cols);
	};

	/**
	 * 装载处理列
	 * 
	 * @param handleCols
	 *            处理列
	 * @param cols
	 *            列数组
	 */
	ScrollTablel.prototype.loadHandleCols = function(handleCols, cols) {
		var that = this;
		var options = that.options,
		handleable = false, queryable = false,
				handlePermission = handleCols.handlePermission,
				queryPermission = handleCols.queryPermission;
		// 权限认证回调
		var fnc = function(i, permission, arg) {
			if (that.checkPermission(permission) === true) {
				arg.able = true;
				return false;
			}
		}
		// 需要checkbox权限的列
		if (handlePermission) {
			var able = {"able":handleable};
			that.loop(handlePermission, fnc, able);
			handleable = able.able;
		}
		// 不需要checkbox权限的列
		if (queryPermission) {
			var able = {"able":queryable};
			that.loop(queryPermission, fnc, able);
			queryable = able.able;
		}
		// 需要checkbox权限的列有权限
		if (options.checkable || (handleable === true)) {
			// 标记checkable
			options.checkable = true;
			cols.push({
				text : '<input type="checkbox">',
				type : 'string',
				flex : true,
				css : 'width:55px',
				sort : false
			});
		}
		// 处理列权限
		if (handleable === true || queryable === true) {
			// 标记处理列
			options.handleable = true;
			cols.push({
				width : handleCols.width,
				text : handleCols.title,
				type : 'string',
				flex : true,
				sort : false
			});
		}

	};

	/**
	 * 装载行
	 * 
	 * @param options
	 *            配置参数
	 * @param rows
	 *            行数组
	 */
	ScrollTablel.prototype.loadRows = function(options, rows) {
		//debugger;
		var that = this;
		var value,row,
		data = options.data,
		//dataLength = options.data.length, 
		identifier = options.identifier,
		handleCols = options.handleCols.cols,
		checkdisenable=options.checkdisenable,
		//handleColslength = options.handleCols.cols.length, 
		cols = options.cols, 
		//colsLength = options.cols.length, 
		checkable = options.checkable, 
		handleable = options.handleable;
		
		var getValue= function(value,escape){
			//debugger;
			if(escape){
				value=escapeHtml(value);
			}
			return value || $.isNumeric(value) ? value :  "-";
		};
		
		/**
		 * HTML内容进行转义和过滤
		 */
		var escapeHtml = function(value) {
			if (typeof value === 'string'){
				value = value.replace(/&/g, '&amp;');
				value = value.replace(/</g, '&lt;');
				value = value.replace(/>/g, '&gt;');
				value = value.replace(/"/g, '&quto;');
				value = value.replace(/'/g, '&#39;');
			}
			return value;
		};

		// 返回数据遍历
		that.loop(data, function(i,d){
			row = {data : []};
			// 所有列遍历
			that.loop(cols, function(c,col,d){
				//debugger;
				var name = col.name;
				var escape=col.escape==undefined ? true:col.escape;
				console.log("escape="+escape);
				var colsIndex = 0;
				// 如果是第一列并且 处理列有权限
				if (c === 0) {
					if (checkable) {
						colsIndex = 1;
						if(checkdisenable!=null && d[checkdisenable]==true){
							row.data.push({css : 'width:55px',text :'-'});
						}else{
							row.data.push({css : 'width:55px',text :'<input type="checkbox" val='+ d[identifier] + '>'});
						}
					}
					
					if(handleable){
						var theadCol = that.data.cols[colsIndex];
						var link = "";
						// 操作列遍历
						that.loop(handleCols, function(handleIndex,handleCol,d){
							// 认证权限
							if (that.checkPermission(handleCol.linkInfo.permission)) {
								var l = that.createLink(handleCol, d, false);
								//自定义方法调用
								if(handleCol.customMethod){
									l = handleCol.customMethod.call(handleCol,l,d);
								}
								link =  l +"&nbsp;&nbsp;"+ link;
								//设置权限列宽度 默认最小宽度80px
								if(i==0 && handleCol.width){
									handleCol.width = parseInt(handleCol.width) ;
									handleCol.width = handleCol.width>80?handleCol.width:80;
									theadCol.width = theadCol.width != undefined ? theadCol.width + handleCol.width : handleCol.width;
								}
								
							}
						}, d);
						if(link.length>0){
							row.data.push(link);
						}
					}
				}
				
				// 超链接
				if (col.isLink) {
					// 权限认证 或者该字段是必须的 则显示 否则不显示
					if (that.checkPermission(col.link.permission)) {
						value = that.createLink(col, d, true);
						//row.data.push(link);
					} else if (col.link.required) { // 必须显示
						value = that.createLink(col, d, true);
						//row.data.push(d[name]);
					}
				} else {// 非超链接
					value = getValue(d[name],escape);
					//row.data.push(d[name]);
				}
				
				var is = value || ($.isNumeric(value));
				//自定义方法调用
				if(is && col.customMethod){
					value = col.customMethod.call(col,value,d);
				}
				if(is){
					row.data.push(value);
					
				}
				value = null;
				
			}, d);
			rows.push(row);
		});
		
	};

	/**
	 * 创建超链接列
	 * 
	 * @param col
	 *            当前列
	 * @param data
	 *            当前列数据
	 * @param handleCol
	 *            是否是处理列
	 */
	ScrollTablel.prototype.createLink = function(col, data, handleCol) {
		var that =this,$a = $("<a/>");
		var url,value,link, linkInfoName,param,customParam;
		if (!handleCol) {
			link = col.linkInfo;
			linkInfoName = col.linkInfoName;
			param = link.param;
			customParam = link.customParam;
			value = col.title;
		} else {
			link = col.link.linkInfo;
			linkInfoName = col.link.linkInfoName;
			param = col.link.param;
			customParam = col.link.customParam;
			value = data[col.name];
		}
		var urlParam = that.getParam(param, data);
		var callbackParam = that.options.callbackParam;
		var linkInfo = link[linkInfoName];
		var getUrl = function(linkUrl,urlParam){
			url = linkUrl;
			if (urlParam != null) {
				url = url + ("?" + urlParam);
			}
		}
		if (linkInfoName === "href") {
			getUrl(linkInfo,urlParam);
			if (callbackParam != null) {
				var prefix ="?";
				if(urlParam != null){
					prefix = "&";
				}
				url += (prefix + callbackParam);
			}
			if(customParam && customParam.length>0){
				var prefix ="?";
				if(urlParam != null || callbackParam != null){
					prefix = "&";
				}
				url += (prefix + customParam[0]);
			}
			$a.attr("href", url);
		} else if (linkInfoName === "modal") {
			getUrl(linkInfo.url,urlParam);
			if(customParam && customParam.length>0){
				var prefix ="?";
				if(urlParam != null){
					prefix = "&";
				}
				url += (prefix+customParam[0]);
			}
			$a.attr({
				"href" : "javascript:;",
				"data-type" : "ajax",
				"data-toggle" : "modal",
				"data-url" : url,
				"data-width" : linkInfo.width || "30%",
				"data-position" : linkInfo.position || 'fit',
			});
			if(linkInfo.title){
				$a.attr("data-title" , linkInfo.title);
			}

		} else if (linkInfoName === "method") {
			
			var m = 'javascript:' + linkInfo + '(';
			var isParam = param&&param.length>0;
			if(isParam){
				that.loop(param,function(i,p,d){
					if(i!=0){
						m += ",";
					}
					var value = d[p];
					var is = value || ($.isNumeric(value));
					value = is?value:"";
					m += "'"+value+"'";
				},data);
			}
			if(customParam&&customParam.length>0){
				that.loop(customParam,function(i,p){
					if(isParam&&i==0){
						m += ",";
					}
					if(i!=0){
						m += ",";
					}
					var value = p;
					var is = p || ($.isNumeric(p));
					value = is?value:"";
					m += "'"+value+"'";
				});
			}
			m +=')';
			$a.attr("href",m);
		}
		var leng = col.leng;

		var displayValue;
		if(value==null){
			displayValue = "-";
		}
		else if (leng && value.length > leng) {
			$a.attr("title", value);
			displayValue = value.substr(0, leng) + "....";
		} else if(!leng || value.length <= leng){
			displayValue = value;
		}else if($.isNumeric(value) ){
			displayValue = value;
		}
		else{
			displayValue = "-";
		}
		$a.html(displayValue);
		return $a[0].outerHTML;
	};

	/**
	 * 权限认证
	 * 
	 * @param permission
	 *            认证的权限
	 * @returns {Boolean}
	 */
	ScrollTablel.prototype.checkPermission = function(permission) {
		return (this.permissions[permission] == "true");
	};

	ScrollTablel.prototype.getParam = function(param, data) {
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
	};

	/**
	 * 遍历
	 * @param obj  对象或者数组
	 * @param fnc 回调方法
	 * @param arg 添加至回调方法的参数  额外加入回调方法的参数
	 */
	ScrollTablel.prototype.loop = function(obj, fnc, arg) {
		var value, i = 0, length = obj.length, isArray = $.isArray(obj);
		if ( arg) {
			if (isArray) {
				for (; i < length; i++) {
					value = fnc.call(obj[i], i, obj[i], arg);
					if (value === false) {
						break;
					}
				}
				;
			} else {
				for (i in obj) {
					value = fnc.call(obj[i], i, obj[i], args);
					if (value === false) {
						break;
					}
				}
			}

		} else {
			if (isArray) {
				for (; i < length; i++) {
					value = fnc.call(obj[i], i, obj[i]);
					if (value === false) {
						break;
					}
				}
				;
			} else {
				for (i in obj) {
					value = fnc.call(obj[i], i, obj[i]);
					if (value === false) {
						break;
					}
				}
			}
		}

	};

	/**
	 * 挂载至jquery
	 */
	$.fn.scrollTablel = function(option, permissions) {
		var $this = $(this);
		var datatable = $this.data(name);
		var tablel = new ScrollTablel(this, option, permissions);
		var data = tablel.load();
		var options = {
			tableClass : option.tableClass || ScrollTablel.DEFAULTS.tableClass,
			sortable : true,
			storage : false,
			caption : option.caption,
			checkedClass : "",
			data : data
		};
		if(!datatable){
			$this.datatable(options);
		}else{
			datatable["getOptions"](options);
			datatable["load"](data);
		}
		
	};
	/**
	 * 挂载构造函数
	 */
	$.fn.scrollTablel.Constructor = ScrollTablel;
}(jQuery, window);
