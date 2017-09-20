$.fn.searchChosen = function(param){
	var $this = $(this);
	var $div,$iframe,$choices,wdocument;
	var opts = $.extend({
		url : "member/memberList/init.jhtml",
		divId : "",
		separator : ",",
		initUrl : "",
		initId :"",
		initTitle :""
	},param||{});
	
	var build = function(){
		var html = [];
		html.push('<div class="chosen-container chosen-container-multi chosen-with-drop chosen-container-active" style="float:left;" '+(opts.divId ? 'id="'+opts.divId+'"' : '')+'>');
		html.push('<ul class="chosen-choices"></ul>');
		html.push('</div>');	
		html.push('<button type="button" class="btn btn-primary" id="searchChosen" style="float:left;margin-left: 10px;">请选择</button>');
		$this.after(html.join(" "));
		$div = $this.next();
		$choices = $(".chosen-choices");
		$div.css("width", ($this[0].offsetWidth - 100) + "px");
		$choices.css("min-height", $this[0].offsetHeight + "px");
		$this.hide();
		
		//生成选择页面
		$("#searchChosen").click(function(){
			var choicesHtml = $choices.html();
			//初始化选择页面模态框
			$("#choseModal").remove();//处理刷新问题
			if(!$("#choseModal").length){
				$("body").append('<div class="modal fade" id="choseModal" aria-hidden="true" data-type="'+opts.type+'" data-position="10px" style="display: none;z-index: 9990;"><div class="modal-dialog modal-lg" style="margin-top: 151.33333333333334px;width:1400px;"><div class="modal-content"><div class="modal-header"><button type="button" class="close"  data-dismiss="modal"><span class="sr-only">关闭</span></button><h4 class="modal-title">请选择</h4></div><div class="modal-body"><iframe id="iframetriggerModal" src="'+opts.url+'" frameborder="no"allowtransparency="true" scrolling="auto"style="width: 100%; height: 100%; left: 0px;"></iframe></div></div></div></div>');
				$("#choseModal").find("iframe").load(function(){
					 wdocument = $(this.contentWindow.document);
					$(wdocument).find("#choseDatas").on("click", "a.search-choice-close", function(){
						var l=$(this).parent();
						$(this).parent().remove();
						var id=$(l).attr("id");
						$(wdocument).find('[name="'+id+'"]').attr("checked",false);
						$this.val($this.val().replace($(this).attr("data")+ opts.separator, ""));
						$choices.find("a[data='"+$(this).attr("data")+"']").parent().remove();
						var $subBox = $(wdocument).find("input[items='oneCheck']");
				        $(wdocument).find("input[id='allCheck']").attr("checked",$subBox.length == $(wdocument).find("input[items='oneCheck']:checked").length ? true : false);//全选框
					}).find(".chosen-choices").html(choicesHtml);//初始化值
					$(wdocument).find(".closeChosen").click(function(){
						$("#choseModal").modal('hide');
					});
				});
				$("#choseModal").modal();
			}else{
				var iframe=$("#choseModal").find("iframe")[0];
				$(iframe.contentWindow.document).find(".chosen-choices").html(choicesHtml);
				choseloadtwo($(iframe.contentWindow.document));
				$("#choseModal").modal();
			}
			
		});
		
		//删除事件
		$div.find("ul.chosen-choices").on("click", "a.search-choice-close", function(){
			$(this).parent().remove();
			//$this.val($this.val().replace($(this).attr("data")+ opts.separator, ""));
			//添加的时候调用了encodeURI，删除的时候也要调用,
			$this.val($this.val().replace(encodeURI($(this).attr("data"))+opts.separator,""));
			if(document.getElementById('iframetriggerModal')){
				$(document.getElementById('iframetriggerModal').contentWindow.document).find("a[data='"+$(this).attr("data")+"']").parent().remove();
			}
		});
	}
	
	//显示已选择项
	var showChose = function(value, show){
		//console.info(value);
		var li = $(createLi(value,show));
		//$(createLi(value,show)).appendTo($choices);
		$choices.append(li);
		//$choices.append('<li id="'+value+'" class="search-choice"><span>'+show+'</span><a class="search-choice-close" data	="'+value+'"></a></li>');
		$(document.getElementById('iframetriggerModal').contentWindow.document).find("#choseDatas").find(".chosen-choices").append($(createLi(value,show)));
	}
	
	var createLi = function(value,show){
		var li = $("<li>").attr({"id":value,"class":"search-choice"});
		createSpan(show,li);
		createA(value,li);
		return li;
	}
	
	var createSpan =function(show,li){
		$("<span>").html(show).appendTo(li);
	}
	
	var createA =function(value,li){
		$("<a>").attr({"data":value,"class":"search-choice-close"}).appendTo(li);
	}
	
	
	var choseload=function(){//翻页checkbox选中初始化
		$(wdocument).find(':checkbox').attr("checked",false);
		$(wdocument).find("li").each(function(){
			var id=$(this).attr("id");
			$(wdocument).find('input[name="'+id+'"]').attr("checked",true);
		});
		var $subBox = $(wdocument).find("input[items='oneCheck']");
        $(wdocument).find("input[id='allCheck']").attr("checked",$subBox.length == $(wdocument).find("input[items='oneCheck']:checked").length ? true : false);
	
	}
	
	var choseloadtwo=function(twdocument){//进入选择页面checkbox选中初始化
		$(twdocument).find(':checkbox').attr("checked",false);
		$choices.find("li").each(function(){
			var id=$(this).attr("id");
			var check = $(twdocument).find('[name="'+id+'"]');
			if(!($(check).is(':checked'))){
				$(check).prop("checked", true);
			}
		});
	}
	//选择数据
	var chose = function(value, show,obj){
		if(obj.checked){
			 if($this.val().indexOf(value+opts.separator)<0){
				$this.val($this.val() + value + opts.separator);
				showChose(value, show);
			 }
		}else{
			var currentObj=$('#'+value);
			currentObj.remove();
			$this.val($this.val().replace(value+ opts.separator, ""));
			if(document.getElementById('iframetriggerModal')){
				$(document.getElementById('iframetriggerModal').contentWindow.document).find("a[data='"+value+"']").parent().remove();
			}	
		}
		var $subBox = $(wdocument).find("input[items='oneCheck']");
        $(wdocument).find("input[id='allCheck']").attr("checked",$subBox.length == $(wdocument).find("input[items='oneCheck']:checked").length ? true : false);
	}
	//会员选择数据
	var memberChose = function(value, show,obj){
		if(obj.checked){
			 if($this.val().indexOf(value+opts.separator)<0){
				var datas = $(obj).attr("datas");
				$this.val($this.val() + encodeURI(datas) + opts.separator);
				memberShowChose(value, show,datas);
			 }
		}else{
			var currentObj=$('#'+value);
			currentObj.remove();
			//添加的时候用了encodeURI进行编码，删除时也要进行编码
			var datas = $(obj).attr("datas");
			$this.val($this.val().replace(encodeURI(datas)+opts.separator,""));
			if(document.getElementById('iframetriggerModal')){
				$(document.getElementById('iframetriggerModal').contentWindow.document).find("a[idData='"+value+"']").parent().remove();
			}
		}
	}
	//会员显示已选择项
	var memberShowChose = function(value,show,datas){
		$choices.append('<li id="'+value+'" class="search-choice"><span>'+show+'</span><a class="search-choice-close" idData="'+value+'" data="'+datas+'"></a></li>');
		$(document.getElementById('iframetriggerModal').contentWindow.document).find("#choseDatas").find(".chosen-choices").append('<li class="search-choice"><span>'+show+'</span><a class="search-choice-close" idData="'+value+'" data="'+datas+'"></a></li>');
	}
	/*//显示已选择项
	var memberShowChose = function(value, show){
		$choices.append('<li class="search-choice"><span>'+show+'</span><a class="search-choice-close" data="'+value+'"></a></li>');
		$(document.getElementById('iframetriggerModal').contentWindow.document).find("#choseDatas").find(".chosen-choices").append('<li class="search-choice"><span>'+show+'</span><a class="search-choice-close" data="'+value+'"></a></li>');
	}
	
	//选择数据
	var memberChose = function(value, show){
		if($this.val().indexOf(value) < 0){
			$this.val($this.val() + value + opts.separator);
			memberShowChose(value, show);
		}else{
			console.info("重复选择");
		}
		
	}*/
	//获取标题
	var getTitles = function(){
		var titles = "";
		$choices.find("span").each(function(i){
			titles += $(this).html() + opts.separator;
		});
		return titles;
	}
	var destory = function(){
		$($this).siblings().each(function(index, element){
			$(element).remove();
		});
		$this.show();
	};
	//初始化数据
	var init = function(){
		var initValue = "";
		$.ajax({
			url : opts.initUrl,
			async : false,
			dataType : "json",
			success : function(data){
				for(var i = 0; i < data.length; i++){
					initValue += data[i][opts.initId] + opts.separator;
					$choices.append('<li class="search-choice" id="'+data[i][opts.initId]+'"><span>'+data[i][opts.initTitle]+'</span><a class="search-choice-close" idData="'+data[i][opts.initId]+'" data="'+data[i][opts.initId]+'"></a></li>');
				}
			}
		});
		$this.val(initValue);
		
	}
	
	setTimeout(function(){
		build();
		init();
	}, 100);
	
    var allCancel = function(){//清除已选择内容的方法   dongjt
			$(wdocument).find(':checkbox').attr("checked",false);//去掉所有复选框
			$choices.find(".search-choice").remove();//取消基础页面选择内容
			$this.val("");//清除值
			if(document.getElementById('iframetriggerModal')){
			  $(document.getElementById('iframetriggerModal').contentWindow.document).find(".search-choice").remove();//取消弹出页面选择内容
			}	
    }
	$.extend($this,{
		memberChose:memberChose,
		choseload:choseload,
		chose : chose,
		getTitles : getTitles,
		allCancel:allCancel,
		destory:destory
	});
	return $this;
}