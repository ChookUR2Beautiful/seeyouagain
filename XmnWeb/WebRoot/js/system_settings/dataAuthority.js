$(function(){
			 var form = $("form");
			 
			 $.each(form,function(i,f){
				var formElement = $(f);
				
				init(formElement);
				
				formElement.on('click', '.add', function()
				{
					createLdInfo(formElement);
				});

				formElement.on('click', '.remove', function()
			    {
			        $(this).parent().parent().parent().remove();
			    }); 
				validate(formElement.attr("id"),{
						rules:{
							province:{
								required:true
							},
							city:{
								required:true
							},
							area:{
								required:true
							}
						},
						messages:{
							province:{
								required:"请选择省！"
							},
							city:{
								required:"请选择市！"
							},
							area:{
								required:"请选择区域！"
							}
						}},formAjax);
			 });	
				   
			});
		
		function init(form){
			var divs = form.find("div.ldDiv");
			if(divs && divs.length>0){
				$.each(divs,function(i,v){
					initAreaLd(v);
				});
			}else{
				createLdInfo(form);
			}
			
		}
		
		function createLdInfo(formElement){
			var tr = $("<tr>").addClass("text-center").append($("<td>").css("width","25%"));
			var div = $("<div>").addClass("input-group").css("width","100%");
			tr.append($("<td>").append(div));
			
			var a = $("<a>").addClass("btn btn-mini remove").attr("href","javascript:;").append($("<li>").addClass("icon-remove"));
			
			var td = $("<td>").addClass("text-left text-middle").css("width","25%").append($("<h4>").append(a));
			tr.append(td);
			formElement.find("table").append(tr);
			initAreaLd(div);
			
			
		}
		
		function initAreaLd(div){
			$(div).areaLd({
				showConfig : [{name:"province",tipTitle:"--省--",width:'25%'},{name:"city",tipTitle:"--市--",width:'25%'},{name:"area",tipTitle:"--区--",width:'25%'}]
				//isChosen : true
			});
		}
		
		
		
		function formAjax(form){
			form = $(form);
			var subbut = form.find("[type=submit]");
			subbut.attr("disabled","disabled");
			var data =form.serializeArray();
			var areaList = serializeList(data);
			$.ajax({
				dataType:"json",
				contentType : 'application/json',
				type : form.attr("method"),
				url : form.attr("action"),
				data :JSON.stringify(areaList)
			}).done(function(data){
				showSmReslutWindow(data.success, data.msg);
				if(data.success){
					$("#formDiv").empty();
				}
			}).fail(function(data){
				window.messager.warning("操作失败!");
			});
			subbut.attr("disabled","");
			return false;
		}
		 
		function serializeList(info) {
			var jsonObj = {};
			var array = new Array();
			$.each(info, function(index, obj) {
				if(index>1){
					if (jsonObj[obj.name]) {
						jsonObj[info[0].name]=info[0].value|| '';
						jsonObj[info[1].name]=info[1].value|| '';
						array.push(jsonObj);
						jsonObj = {};
					}
					jsonObj[this.name] = obj.value || '';
				}
			});
			if (!$.isEmptyObject(jsonObj))
				jsonObj[info[0].name]=info[0].value|| '';
				jsonObj[info[1].name]=info[1].value|| '';
				array.push(jsonObj);
			return array;
		}
		