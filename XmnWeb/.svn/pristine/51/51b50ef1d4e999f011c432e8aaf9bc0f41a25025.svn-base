(function($){
	/**
	 * 对外接口
	 */
	$.fn.xmnPage=function(config){
		//初始化
		return xmmPage(config,this);
	
	}
	
	/**
	 * 页面处理
	 */
	function xmmPage(config,obj){
		
			/**
		 * 默认对象
		 */
		var options ={
	        containerID: "",
	        first: true,
	        previous: "上一页",
	        next: "下一页",
	        last: true,
	        pageSize : 10,//一页显示数量
			total : 100,//数据总数
			currentPage : 1,//当前页面
			maxPageButton : 5,//显示最大按钮数
	        //callback: undefined // function( pages, items ) { }
	        isAjax : false,//开启ajax
			async : true,//是否异步
			cache : false,//是否缓存
			timeout : 6000,//请求超时时间
			ajax : {
					url : "", //url
					pageCountId : '',//后台返回包含数据总数对应值 
					type : "get",//请求方式
					dataType : 'html',//返回方式
					param : !1,//参数
					callback : function() {
						return !1
					},//回调函数
					error : function() {
						return !1
					} //错误回调函数
				}
		};
		
		
		//使用用户自定义参数替换默认配置参数  没有则使用默认参数
		$(!0,options,config);
		
		var $this=$(obj);
		
		//总页数
		var _numPages = Math.ceil(options.total / options.pageSize);
		//当前页
    	var _currentPageNum = options.currentPage;
    	
    	//上一页
    	var _previous = options.previous;
    	
    	//下一页
    	var _next = options.next;
    	
    	//显示页面按钮
    	var _maxBtn = options.maxPageButton;
    	
    	
    	//开启ajax 
    	if(options.isAjax){
    		onRequest();
    	}else{  //未开启
    		createPage();
    	}
    	
    	/**
    	 * 回调处理
    	 */
    	function responseHandle(data) {
			var pageCountId = options.ajax.pageCountId, resultPageCount = 1;
			switch (options.ajax.dataType) {
				case "json" :
					try {
						data = eval("(" + data + ")")
					} catch (err) {
					} finally {
						resultPageCount = eval("data." + pageCountId)
					}
					break;
				default :
					resultPageCount = $(data).attr(pageCountId);
			}
			options.total = parseInt(resultPageCount);
			options.ajax.callback(data);
		}
    	
    	/**
    	 * 获取参数
    	 */
    	function getParam() {
			//页面参数
			var a = 'page=' + c.currentPage;
			//参数对象处理
			if (typeof options.ajax.param == "object") {
				var b = "&";
				for (var key in options.ajax.param) {
					b += key + "=" + options.ajax.param[key] + "&";
				}
				b = b.length == 1 ? "" : b.substring(0, b.length - 1), a += b
			} else //参数字符串处理
				typeof options.ajax.param == "string" && (a += "&" + options.ajax.param);
				
			return a //返回

		}
    	
    	/**
    	 * ajax请求
    	 */
    	function onRequest() {
			if (!options.ajax.url) {
				$.error('请设置分页插件Ajax属性为中的url');
				return;
			}
			if (!options.ajax.callback) {
				$.error('请自定义分页插件Ajax中的callback回调函数');
				return;
			}
			//获取参数
			var param = getParam();
			//ajax请求
			$.ajax({
						url : options.ajax.url,
						type : options.ajax.type,
						data : param,
						async : options.async,
						cache : options.cache,
						timeout : options.timeout,
						error : function() {
							if (!options.ajax.error) {
								alert("请求错误");
							} else {
								options.ajax.error();
							}
						},
						success : function(data) {
							//回调处理
							responseHandle(data);
							//建立页面
							createPage();
							//绑定页面点击事件
							bindClick()
						}

					});
		}

    	
		/**
		 * 创建分页html
		 */
		function createPage(){
			//删除之前分页页面
			$this.children().remove();
			
			if(_numPages<1) _numPages=1;
			
			var startIsDisabled,endIsDisabled,htmlarray= new Array();
			htmlarray.push("<ul class='pager'>");
			
			//第一页  禁用  首页与上一页
			startIsDisabled = _currentPageNum == 1?"disabled":"";
			//首页是否开启
			if(options.first){
				htmlarray.push(writeEnds("first "+startIsDisabled,'首页'));
			}
			htmlarray.push(writeEnds("previous "+startIsDisabled,_previous));
			
			//计算分页显示页数
			var pageCount = _numPages < _maxBtn ? _numPages : _maxBtn;
			
			//计算分页显示
			htmlarray.push(pageCompute(_currentPageNum, pageCount));
			
			//最后一页  禁用 尾页与下一页
			endIsDisabled =  _numPages > 1 && _currentPageNum != _numPages ? '': 'disabled';
			//尾页是否开启
			htmlarray.push(writeEnds("next "+endIsDisabled,_next));
			if(options.last){
				htmlarray.push(writeEnds("last "+endIsDisabled,'尾页'));
			}	
			
			htmlarray.push("</ul>");
			$this.append(htmlarray.join(""));
		}
		
		
		/**
		 * 分页按钮生成计算
		 */
		function pageCompute(currentPage,pageCount){
			var html = new Array(), pageNum = 0;
			if (currentPage > (pageCount / 2 + 1)) {
				if (c.maxPages - pageCount > currentPage) {
					pageNum = currentPage - Math.ceil(pageCount / 2);
					pageCount += pageNum;
				} else {
					pageNum = c.maxPages - pageCount - 1;
					pageCount = c.maxPages;
				}
				pageNum = pageNum >= 0 ? pageNum : 0;
			}
			for (var index = pageNum + 1; index <= pageCount; index++) {
				if (index == currentPage) {
					html .push(writeCentre(index, 'active'));
					continue;
				}
				html .push(writeCentre(index));
			}
			return html.join("");
		}
		
		/**
		 * 生成中间html
		 */
		function writeCentre() {
			var css = arguments.length>1?"class='" + arguments[1]+"'":"";
			return "<li "+css+" page='" + arguments[0] + "'><a  href='javascript:;'>"+arguments[0]+"</a></li>"
		}
		
		
		/**
		 * 生成首页 尾页 下一页 上一页 两端html
		 */
		function writeEnds(which,title) {
			return "<li class='" + which + "'><a>"+title+"</a></li>"
		}
		
		
		
		/**
		 * 绑定事件
		 */
		function bindClick() {
			$this.find("a").each(function() {
						if ($(this).parent().hasClass('disabled')) {
							$(this).blur();
							return;
						}
						$(this).on('click', function(e) {
									//响应函数
									onPageBtnClick(this);
								});
					});
		}
		
	

		/**
		 * 处理翻页点击事件参数数据
		 */
		function onPageBtnClick(pageBtn) {
			var $parent = $(pageBtn).parent();
			var pageNum = $parent.attr("page");
			if (!pageNum) {
				pageNum = $parent.attr("class");
			}
			var currenPage = $this.find('li.active').attr('page');
			if (pageNum == currenPage)
				return false;
			currenPage = parseInt(currenPage);
			var page;
			//跳页处理
			if (pageNum == "jumpPage") {
				page = $this.find('input').val();
				page = parseInt(page);
				var page = 1 > page ? 1 : page;
				page = page > _numPages ? _numPages : page;
				c.currentPage = page;
				onRequest();
				return;
			} else { //上一页  下一页  首页  尾页 处理
				switch (pageNum) {
					case "first" :
						page = 1;
						break;
					case "previous" :
						page = currenPage > 1 ? currenPage - 1 : null;
						break;
					case "next" :
						page = currenPage < _numPages ? currenPage + 1 : null;
						break;
					case "last" :
						page = _numPages;
						break;

				}
			}
			if (!page) {
				//相应页面按钮处理
				page = parseInt(pageNum);
			}
			
			onClickPage(page);

		}
		
		
			
		/**
		 * 判断翻页ajax请求是否开启
		 */
		function onClickPage(page) {
			options.currentPage = page;
			if (!c.isAjax) {
				//$.error('请设置分页插件的isAjax属性为true');
				return;
			}
			//发送请求
			onRequest();
		}	
	}	
	
})(jQuery)