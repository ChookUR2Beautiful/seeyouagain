/**
 * 
 * 类描述：js 分页插件
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2013-8-26 14：11
 * 
 */
(function() {

	// 初始化
	function initPage(param, obj) {

		// 默认属性
		defaults = {
			titleText : 'Page',
			url : '/system/user/queryUserList',
			removeUrl : '',
			dataType : 'json',
			imageLoading : '/widgets/page/image/load-b.gif',
			method : 'POST',//http提交方式
			contentType : 'application/x-www-form-urlencoded',
			pageBtnNum : 5,//默认翻页按钮数量
			page : 1,//默认页数
			total : 1,
			param : {},//默认参数
			paramForm : 'formId',
			pageSize : 10,//每也条数
			checked : false,
			ids:[],
			data : {},
			success : function(data, obj) {
				alert('获取数据成功-->' + data);
			},
			error : function() {
				// alert('获取数据失败');
			}
		};

		var opts = $.extend(defaults, param);

		//显示条数等相关信息
		var mergeData = function(data, obj) {
			var page,total;
			if(opts.dataType=="json"){
				try {
						data = eval("(" + data + ")");
					} catch (err) {
					} finally {
						page = data.page;
						total =data.total;
					}
			
			}else{
				var page = parseInt($(data).attr("data-page"))||0;
				var total =parseInt($(data).attr("data-total"))||0;
				
			}
			
			opts.page = page;
			opts.total = total;
			opts.data = data.content;
			opts.pages = Math.ceil(opts.total / opts.pageSize);
			var startIndex = (opts.page - 1) * opts.pageSize + 1;
			var endIndex = opts.page * opts.pageSize;

			var _page_text = obj.find('li.page_text');
			var _page_jump_text = obj.find('input.page_jump_text');
			if (_page_text.length == 1) {
				_page_text.html('<h6>&nbsp;&nbsp;&nbsp;&nbsp;显示 ' + startIndex + ' - ' + endIndex + '条，共 ' + opts.total + '条</h6>');
			}

			if (_page_jump_text.length == 1) {
				_page_jump_text.val(opts.page);
			}
			
		};

		//ajax请求
		var ajax = function() {
			opts.param = $.extend(opts.param, {
				"page" : opts.page + "",
				"limit" : opts.pageSize + ""
			});
			var param = opts.param;
			if(opts.contentType == 'application/json'){
				param = JSON.stringify(opts.param);
			}
			
			$.ajax({
				type : opts.method,
				url : opts.url + '?t=' + Math.random(),
				data : param,
				dataType : opts.dataType,
				contentType : opts.contentType,
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					success(data, obj);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					opts.error();
				}
			});
		};

		//Ajax成功以后回调
		var success = function(data, obj) {
			mergeData(data, obj);
			initPagenum();
			opts.success(data, obj);
			checkedAll();
			checkedVal();
		};

		//重新加载(参数为查询条件，页数不变)
		var reload = function(param) {
			var form = $('#' + opts.paramForm);
			if(form.length>0){
				opts.param = $.extend(opts.param,jsonFromt(form.serializeArray()));
			}
			opts.param = $.extend(opts.param, param);
			ajax();
		};

		//重新加载（参数为查询条件，加载到第一页）
		var load = function(param) {
			obj.find('input.page_jump_text').val(1);
			opts.page = 1;
			opts.param = $.extend(opts.param, param);
			obj.find('li.jump').each(function() {
				$(this).remove();
			});
			ajax();
		};

		//翻页
		var jump = function(page) {
			if(page != undefined){
				if(page < 1) page = 1;
				if(page > opts.pages) page = opts.pages;
				if(page == opts.page || page== 0){
					return;
				}
				opts.page = page;
				$('#' + opts.paramForm).find("input[name='page']").val(page);
				ajax();
			}
		};

		//初始化翻页按钮
		var initPagenum = function() {
			obj.find('li.jump').remove();
			var start = 1;
			var end = opts.pageBtnNum;
			var m = Math.floor(opts.pageBtnNum/2);
			if(opts.pages <= opts.pageBtnNum){
				end = opts.pages;
			}else{
				if(opts.page + m > opts.pages){
					end = opts.pages;
					start = end - opts.pageBtnNum;
				}else if(opts.page - m > 1){
					start = opts.page - m;
					end = opts.page + (opts.pageBtnNum - m) - 1;
				}
			}
			for (var i = end; i >= start; i--) {
					obj.find('li.previous').after('<li class="jump" page="' + i + '"><a href="javascript:">' + i + '</a></li>');
				
			}
			obj.find(".jump>a").click(function(event){
				var page = $(this).text();
				jump(parseInt(page));
			});
			obj.find("a[name=first]").off().click(function(event){
				jump(1);
			});
			obj.find("a[name=previous]").off().click(function(event){
				jump(opts.page-1);
			});
			obj.find("a[name=next]").off().click(function(event){
				jump(opts.page+1);
			});
			obj.find("a[name=last]").off().click(function(event){
				jump(opts.pages);
			});
			effect();
		};

		//全选
		var checkedAll = function() {
			var _checkbox = obj.find('table>thead>tr>th').eq(0).find('input[type=checkbox]');
			_checkbox.click(function() {
				cheched($(this).prop('checked'));
			});
		};
		
		//全选所有子按钮
		var cheched = function(isflag) {
			obj.find('table>tbody>tr').each(function() {
				var checkbox = $(this).find('th,td').eq(0).find('input[type=checkbox]');
				if (checkbox) {
					checkbox.prop('checked', isflag);
					if (opts.checked) {
						if (!isflag) {
							obj.find('div.ids').find('span[name=' + checkbox.attr('val') + ']').remove();
						} else {
							if(!obj.find('div.ids').find('span[name=' + checkbox.attr('val') + ']').html()){
								obj.find('div.ids').append('<span name="' + checkbox.attr('val') + '">' + checkbox.attr('val') + '</span>');
							}
						}
					}
				}
			});
		};

		//设置默认值
		var checkedVal = function() {
			obj.find('table>tbody>tr').each(function() {
				var _checkbox = $(this).find('th,td').eq(0).find('input[type=checkbox]');
				var _span = obj.find('div.ids').find('span[name=' + _checkbox.attr('val') + ']');
				if(_span.html()){
					_checkbox.prop('checked',true);
				}
				if (opts.checked) {
					_checkbox.click(function() {
						if (!$(this).prop('checked')) {
							obj.find('div.ids').find('span[name=' + _checkbox.attr('val') + ']').remove();
						} else {
							if(!obj.find('div.ids').find('span[name=' + _checkbox.attr('val') + ']').html()){
								obj.find('div.ids').append('<span name="' + $(this).attr('val') + '">' + $(this).attr('val') + '</span>');
							}
						}
					});
				}
			});
		};
		

		//获取选中的ID
		var getIds = function() {
//			debugger;
			var html = [];
			if(!opts.checked){
				obj.find('table>tbody>tr').each(function() {
					var checkbox = $(this).find('th,td').eq(0).find('input[type=checkbox]');
					if (checkbox && checkbox.prop('checked')) {
						html.push(checkbox.attr('val'));
					}
				});
			}else{
				obj.find('div.ids').find('span').each(function(){
					html.push($(this).html());
				});
			}
			return html.join(',');
		};

		
		/**
		 * 获取当前选中列name对应的值
		 */
		var getValue = function(name) {
			var res = [];
			var rows = getCheckRowNum();
			for(var i in rows){
				res.push(opts.data[rows[i]][name]);
			}
			return res;
		};
		
		/**
		 * 获取选中行数据
		 */
		var getCheckDatas=function(){
			var res = [];
			var rows = getCheckRowNum();
			for(var i in rows){
				res.push(opts.data[rows[i]]);
			}
			return res;
		};
		
		/**
		 * 获取选中行号
		 */
		var getCheckRowNum = function(){
			var res = [];
			obj.find('table>tbody>tr').each(function(i) {
				var checkbox = $(this).find('th,td').eq(0).find('input[type=checkbox]');
				if (checkbox && checkbox.prop('checked')) {
					res.push(i);
				}
			});
			return res;
		};
		
		var getRowValue = function(name){
			var res = {};
			var rows = getCheckRowNum();
			for(var i in rows){
				res[rows[i]] = opts.data[rows[i]][name];
			}
			return res;
		};
		
		/**
		 * 校验选中数据
		 */
		var validateChose = function(name, validateStr, showMsg){
			var flag = true;
			var values = getRowValue(name);
			var color = "rgb(243, 200, 200)";
			var oldColor = "#ffffff";
			for(var i in values){
				if(validateStr.indexOf(values[i]) < 0){
					flag = false;
					obj.find("table").removeClass("table-striped");
					
					obj.find('table>tbody>tr').eq(i).css("background-color", color);
					obj.find('table>tbody>tr').eq(i).find('input[type=checkbox]').off().click(function(){
						if (!$(this).prop('checked')) {
							$(this).parents("tr").css("background-color", oldColor);
						}else{
							$(this).parents("tr").css("background-color", color);
						}
					});
				}
			}
			if(!flag){
				if(showMsg){
					showWarningWindow("warning", "标记的" + showMsg);
				}else{
					showWarningWindow("warning", "标记的数据不符合条件，不能完成此操作！");
				}
			}
			return flag;
		};
		
		/**
		 * 校验选中数据(严格校验)
		 */
		var validateChoseStrict = function(name, validateStr, showMsg){
			var flag = true;
			var values = getRowValue(name);
			var color = "rgb(243, 200, 200)";
			var oldColor = "#ffffff";
			for(var i in values){
				if(!checkIn(validateStr,values[i])){
					flag = false;
					obj.find("table").removeClass("table-striped");
					
					obj.find('table>tbody>tr').eq(i).css("background-color", color);
					obj.find('table>tbody>tr').eq(i).find('input[type=checkbox]').off().click(function(){
						if (!$(this).prop('checked')) {
							$(this).parents("tr").css("background-color", oldColor);
						}else{
							$(this).parents("tr").css("background-color", color);
						}
					});
				}
			}
			if(!flag){
				if(showMsg){
					showWarningWindow("warning", "标记的" + showMsg);
				}else{
					showWarningWindow("warning", "标记的数据不符合条件，不能完成此操作！");
				}
			}
			return flag;
		};
		
		/**
		 * 检查str字符串(逗号分隔),是否包含item
		 */
		var checkIn = function(str,item){
			var flag=false;
			if(typeof str =="string"){
				var items=str.split(",");
				for(var i in items){
					if(items[i]==item){
						flag=true;
					}
				}
			}
			return flag;
		};

		//处理翻页按钮（设置禁用等）
		var effect = function() {
			obj.find('li').removeClass('active');
			obj.find('li').removeClass('disabled');
			obj.find('li[page=' + opts.page + ']').addClass('active');
			if (opts.page == 1) {
				var pre = obj.find('.previous');
				var home = pre.prev();
				pre.addClass('disabled');
				home.addClass('disabled');
				if (opts.page == opts.pages || opts.pages == 0) {
					var next = obj.find('.next');
					var last = next.next();
					next.addClass('disabled');
					last.addClass('disabled');
				}
			} else if (opts.page == opts.pages || opts.pages == 0) {
				var next = obj.find('.next');
				var last = next.next();
				next.addClass('disabled');
				last.addClass('disabled');
				if (opts.page == 1) {
					var pre = obj.find('.previous');
					var home = pre.prev();
					pre.addClass('disabled');
					home.addClass('disabled');
				}
			}
		};

		//构件翻页按钮组
		var build = function() {
			var html = [];
			var paramForm = $('#' + opts.paramForm);
			html.push('<div></div>');
			html.push('<div><ul class="pager pager-loose"" style="margin: -10px 0;">');
			html.push('<li><a name="first" href="javascript:">首页</a></li>');
			html.push('<li class="previous"><a name="previous" href="javascript:">上一页</a></li>');
			html.push('<li class="next" ><a name="next" href="javascript:">下一页</a></li>');
			html.push('<li class=""><a name="last" href="javascript:">尾页</a></li>');
			html.push('<li><span style="padding:0px;border:0px;">');
			html.push('<input class="page_jump_text"  type="text" class="form-control" style="width: 50px;float: left;height:30px;" >');
			html.push('<button  name="page_jump_btn" class="btn " type="button" style="height:30px;border-left:0px;margin-left:-4px;">Go!</button>');
			html.push('<li class="page_text" id="page_text"><h6>&nbsp;&nbsp;&nbsp;&nbsp;显示 0- 0条，共 0 条</h6></li>');
			html.push('</ul></div>');
			html.push('<div class="ids" style="display: none;"></div>');
			obj.html(html.join(''));
			if(!paramForm.find("input[name='page']").length){
				paramForm.append('<input type="hidden" name="page" value="1"/>');
			}else{
				opts.page = paramForm.find("input[name='page']").val();
			}
			obj.find('button[name=page_jump_btn]').click(function() {
				var reg = new RegExp("^(0|[1-9][0-9]*)$");
				var val = obj.find('input.page_jump_text').val();
				if ('' != val && reg.test(val)) {
					if (val > opts.pages) {
						val = opts.pages;
					}
					jump(val == 0 ? 1 : val);
				}
			});
			
			if (paramForm) {
				paramForm.find('input[data-bus=query]').click(function() {
					load(jsonFromt(paramForm.serializeArray()));
					paramForm.find("input[name='page']").val(1);
				});

				paramForm.find('input[data-bus=reset]').click(function() {
					paramForm[0].reset();
					paramForm.find(".chosen-single span").text("--请选择--");
					load(jsonFromt(paramForm.serializeArray()));
				});
			}
			
			if(opts.checked){
				for(var i=0;i<opts.ids.length;i++){
					obj.find('div.ids').append('<span name="' + opts.ids[i] + '">' + opts.ids[i]  + '</span>');
				}
			}
		};

		//处理参数
		var jsonFromt = function(data) {
			var json = {};
			for (var i = 0; i < data.length; i++) {
				json[data[i].name] = data[i].value;
			}
			return json;
		};

		//重置查询条件提交查询
		var reset = function() {
			var paramForm = $('#' + opts.paramForm);
			paramForm[0].reset();
			load(jsonFromt(paramForm.serializeArray()));
		};

		//公开方法
		method = {
			reload : reload,
			getIds : getIds,
			getValue : getValue,
			getCheckRowNum : getCheckRowNum,
			validateChose : validateChose,
			validateChoseStrict : validateChoseStrict,
			getCheckDatas:getCheckDatas,
			reset : reset,
			mergeData:mergeData,
			initPagenum:initPagenum
		};

		obj.extend(method);

		page = function() {
			opts.param = $.extend(opts.param,jsonFromt($('#' + opts.paramForm).serializeArray()));
			build();
			ajax();
		};
		
		page();
		return obj;
	}

	$.fn.page = function(param) {
		return initPage(param, $(this));
	};

})(jQuery);