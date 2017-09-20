$(function() {
			inserTitle(' <span class="text-mute"> > 系统管理 > </span><span class="text-mute"><a href="system_settings/role/init.jhtml" target="right">角色管理</a> </span> > 角色权限分配','sellerList',true);
			isChecked();
			bindForm();
		})
		
		function bindForm(){
			$("form").on("submit",function(evet){
				var info = setValu(this);
				fAjax(info);
				return false;
			});
		}
		
		//组装对象
		function serializeJSON(from) {
			var jsonObj = {};
			var ary = new Array();
			var parent={};
		    var info = $(from).serializeArray();
		    var id;
		    $.each(info, function(index,obj) {
		    	if(index>0){
		    		 if (jsonObj[obj.name]) {
		    			 jsonObj[info[0].name] = info[0].value;
		    			 ary.push(jsonObj);
		    			 jsonObj={};
				        } 
				        jsonObj[obj.name] = obj.value || '';
				        getParent(ary,parent,obj.name,obj.value,info[0]);
				       
		    	} else{
		    		   jsonObj[obj.name] = obj.value || '';
		    		   ary.push(jsonObj);
		    		   jsonObj={};
		    	}
		    });
		    if (!$.isEmptyObject(jsonObj)){
		    	jsonObj[info[0].name] = info[0].value;
		    	ary.push(jsonObj);
		    	jsonObj={};
		    }
		    return JSON.stringify(ary);
		   }
		   
		  //遍历父id
		  function getParent(ary,parent,name,id,roleInfo){
			  var pid= $("#"+id).attr("pid");
			   if(pid && !parent[pid]){
				   parent[pid]=pid;
				   var authInfo={};
				   authInfo[roleInfo.name]=roleInfo.value;
				   authInfo[name]=pid;
				   ary.push(authInfo);
				   getParent(ary,parent,name,pid,roleInfo);
			   }  
		   }
		   
		function setValu(form){
			var url= [$(form).attr("action"),".jhtml"].join("");
			var method= $(form).attr("method");
			var data = serializeJSON(form);
			var info={
				dataType:"json",
				url:url,
				method:method,
				data:data
			}
			return info;
		}
		
		
		function fAjax(info){
			$.ajax({
					contentType : 'application/json',
					type :info.method,
					url: info.url,
					data:info.data
				}).done(function(data){
					if(data.success){
						var url = baseURI+"system_settings/role/init.jhtml";
						location.href =url;
					}else{
						showEditWindow("系统提示",data.msg);
					}
			}).fail(function(data){
				window.messager.warning("操作失败!");
			});
		}
		
		
		function isChecked() {
			$("body").on("click", "input[type=checkbox]", function(evet) {
				var ids = evet.target.value;
				var $div = $("#" + ids);
				if ($div.length > 0) {
					var checked = evet.target.checked;
					checkedDependent($div);
					removeDendent(checked,$div.attr("pid"),ids);
					$div.find("input:checkbox").each(function() {
						this.checked = checked;
					});
				}
			});
		}
		
		
		function checkedDependent(el){
					//获取该复选框依赖的复选框id
					var dependent = $(el).attr("dependent");
					var pid = $(el).attr("pid");
					//是否存在被依赖复选框
					if(dependent&&$.trim(dependent).length>0){
						var d = $("#"+dependent);
						//依赖复选框是否选中
						if(dependentIsChecked(pid,dependent)){
							//被依赖复选框是否选中 选择则不选
							if(!($(d).is(':checked'))){
								$(d).prop("checked", true);
								checkedDependent(d);
							}
						}else{
							//所有依赖复选框没有选中  则被依赖复选框也不选中
							$(d).prop("checked", false);
							checkedDependent(d);
						}
					}		
		}
		
		/**
		 * 效验依赖权限是否被选中
		 * @param {} pid 容器id
		 * @param {} dependent 依赖权限id
		 * @return {}
		 */
		function dependentIsChecked(pid,dependent){
			var statu=false;
			$("#"+pid).find("input:checkbox[dependent="+dependent+"]").each(function() {
						if($(this).is(':checked')){
							statu= true;
							return;
						}
			});
			return statu;
		}
		
		function removeDendent(checked,pid,id){
			if(!checked&&isdependent(pid,id)){
				removeDendentChecked(pid,id);
			}
		}
		
		function isdependent(pid,dependent){
		 return $("#"+pid).find("input:checkbox[dependent="+dependent+"]").length>0
		}
		
		function removeDendentChecked(pid,dependent){
			$("#"+pid).find("input:checkbox[dependent="+dependent+"]").each(function() {
						if($(this).is(':checked')){
							$(this).prop("checked", false);
							var id = $(this).attr("id");
							var pid = $(this).attr("pid");
							removeDendentChecked(pid,id);
						}
			});
		}