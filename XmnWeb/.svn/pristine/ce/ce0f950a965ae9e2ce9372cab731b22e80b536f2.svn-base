<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${cityInBusiness}">
			<div class="row">
				<div class="col-xs-3">
					<p><span style='color: rgb(51, 51, 51); font-family: "应用字体 Bold", "应用字体";'>已展示搜索标签</span><span style='color: rgb(0, 0, 0); font-family: "应用字体 Bold", "应用字体";'>：</span><span style='color: rgb(204, 204, 204); font-family: "应用字体 Bold", "应用字体";'>（最多6个）</span></p>
				</div>
			</div>
			<div class="row">
			<!-- 搜索标签 -->
				<div class="col-xs-2 board">
					<div class="board-list"/> 
				</div>
			
		
				<!-- 数据列表 -->
				<div class="col-xs-10">
							<ul class="nav nav-tabs">
						      <li class="active">
						        <a data-toggle="tab" href="#hotKey">城市热门关键词</a>
						      </li>
						     
						     
						      <li>
						        <a data-toggle="tab" href="#historySeting">历史设置</a>
						      </li>
						    </ul>
					    	<div class="tab-content">
						    	<div id="hotKey" class="tab-pane active">
						    		<div id="hotKeyList"></div>
						    	</div>
						    	<div id="historySeting" class="tab-pane">
				    				<div id="historySetList"></div>
				    			</div>
				    		</div>
				    		
				</div>
		</div>
		<c:if test="${!empty btnAu['user_terminal/searchTags/add'] }">
			<!-- 编辑层 -->
			<div class="modal fade" id="add_searchTag_modal" data-position="200px">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
							</button>
							<h4 class="modal-title">添加搜索标签</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="searchTagForm">
									<input type="hidden" name="areaId"> 
									<div class="col-xs-12"><input class="form-control" name="hotWord" id="hotWord" type="text" placeholder="搜索标签"></div>
									<br>
									<div class="col-xs-12"><input class="form-control" name="hotOrder" type="text" placeholder="排序"></div>
									<br>
									<div class="col-xs-12">
										<button class="btn btn-danger"  type="submit">保存</button>
										&nbsp;&nbsp;
										<button class="btn btn-warning"  data-dismiss="modal">取消</button>
									</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</c:if>
   <script type="text/javascript">
   
	   validate("searchTagForm", {
			rules:{
				hotWord:{
					required:true,
					maxlength : 100,
					remote:{
						url:baseURI+"/user_terminal/searchTags/add/checkHotWordsOfArea.jhtml",
						type: "post",
                        dataType: "json",
                        data: {
                        	hotWord: function(){ return $("#hotWord").val();},
                        	areaId :  function(){ return $("#city").val();}
                        },
                        dataFilter:function(data){
                        	return data;
                        }
					}
				},
				hotOrder : {
					required : true,
					digits:true,
					max:9999999,
				}
			},
			messages:{
				hotWord:{
					required:"搜索标签未填写",
					maxlength : "标签长度最大为100个字符",
					remote : "该搜索标签已存在"
				},
				hotOrder : {
					required : "排序不能为空,且必需为整数",
					digits: "排序必需为整数",
					max:"排序长度不能超过7位"
				}
			}
		}, formAjax);
	   
		$(function(){
			
			 
			refreshTag();
			
			var boardList = $(".board-list");
			 //删除标签
			 boardList.on("click","a",function(event){
				 deleteSearchTag($(this).attr("data-hid"));
				
			 });
			 //添加标签
			boardList.on("click","button",function(event){
				var $that = $(this);
					//效验标签数量 是否超过
					$.getJSON(baseURI+"/user_terminal/searchTags/add/checkHotWordsOfAreaCount.jhtml",
						  {
							areaId:  $("#city").val() 
						  }).done(function(data){
							  if(data===true){
								  $('#add_searchTag_modal').modal();
							  }else{
								showSmReslutWindow(data, "搜索标签数量已满,请先删除!");
								//达到最大标签数禁用并隐藏添加按钮
								$that.addClass("disabled");
								$that.parent().hide();
							  }
						  }).fail(function(){
							  	$('#prompt').hide();
								$('#triggerModal').modal('hide');
								showSmReslutWindow(false, "请求失败");
								
						  });
					return false;
			 });
			initList();
			
		});
		
		var hotKeyList,historySetList;
		function initList(){
			var param =  {"areaId": $("#city").val()};
			hotKeyList = $('#hotKeyList').page({
				url : baseURI+"/user_terminal/searchTags/init/hotKey/list.jhtml",
				success : hotKeySuccess,
				pageBtnNum : 10,
				param : param,
				error:function(){
					showSmReslutWindow(false, "请求失败");
				}
			});
			
			historySetList = $('#historySetList').page({
				url : baseURI+"/user_terminal/searchTags/init/historySet/list.jhtml",
				success : historySetSuccess,
				pageBtnNum : 10,
				param : param,
				error:function(){
					showSmReslutWindow(false, "请求失败");
				}
			});
		}
		
		function hotKeySuccess(data, obj){
			obj.find('div').eq(0).scrollTablel({
				data:data.content, 
				cols:[{
					title : "关键字",
					name : "hotWord",
					type : "string",
					width : 150
				},{
					title : "搜索次数",
					name : "hotNum",
					type : "string",
					width : 180
				},{
					title : "状态",
					name : "hotNum",
					type : "string",
					width : 180,
					customMethod : function(value, data) {
						return '展示中';
					}
				}]},{});
		}
		
		function historySetSuccess(datas, obj){
			
			obj.find('div').eq(0).scrollTablel({
				data:datas.content, 
			 	//操作列
				handleCols : {
					title : "操作",// 标题
					queryPermission : ["setDisplaySearchTag"],// 不需要选择checkbox处理的权限
					width : 150,// 宽度
					cols : [{
						title : "设置展示",// 标题
						linkInfoName : "method",
						linkInfo : {
							method :"show",
							param : ["hid"],
							permission : "setDisplaySearchTag"
						},
						customMethod : function(value, data) {
							return datas.isshow ? "设置展示": value;
						}
					}] 
				}, 
				cols:[{
					title : "设置时间",
					name : "updateTime",
					type : "string",
					width : 150
				},{
					title : "关键字",
					name : "hotWord",
					type : "string",
					width : 150
				},{
					title : "搜索次数",
					name : "hotNum",
					type : "string",
					width : 180
				}]},permissions);
		}
		
		function formAjax(){
			var form = $('#searchTagForm');
			var param = jsonFromt($('#searchTagForm').serializeArray());
			param.areaId = $("#city").val() ;
			
			$('#prompt').show();
			$.post(baseURI+"/user_terminal/searchTags/add.jhtml",param, 'json').done(function(data){ 
				if(data.success){
					$(':input',form)
					 .not(':button, :submit, :reset, :hidden')
					 .val('')
					 .removeAttr('checked')
					 .removeAttr('selected');
					//refreshTag();
					refresh();
				}
				$('#add_searchTag_modal').modal('hide');
				showSmReslutWindow(data.success, data.msg);
				$('#prompt').hide();
				
				
		 	}).fail(function(){
		 		$('#prompt').hide();
		 		$('#add_searchTag_modal').modal('hide');
				showSmReslutWindow(false, "请求失败");
		  });
			

		return false;
		}
		
		function deleteSearchTag(hid){
			showSmConfirmWindow(function(){
				$.post(baseURI+"/user_terminal/searchTags/delete.jhtml",{
					"hid"  : hid,
					"areaId" :  $("#city").val()
				}, 'json'
				).done(function(data){
					if(data.success){
						refresh();
					}
					showSmReslutWindow(data.success, data.msg);
			 	}).fail(function(){
				  	$('#prompt').hide();
					$('#triggerModal').modal('hide');
					showSmReslutWindow(false, "请求失败");
			  });
			});	
		}
		
		function refreshTag(){
			getView("/user_terminal/searchTags/init/getSearchTagsView.jhtml",{"aid": $("#city").val()},function(data){
				$(".board-list").html(data);
				var board = $('.board');
				//看板是否初始化
				if(board.data("zui.boards")){
					//移除初始化看板
					board.removeData("zui.boards");
				}
				//重新初始化
				board.boards();
			});
		}
		
		
		function refresh(){
			refreshTag();
			var param =  {"areaId":$("#city").val()};
			hotKeyList.reload(param);
			historySetList.reload(param);
		}
		
		function show(hid){
			showSmConfirmWindow(function(){
				$.get(baseURI+"/user_terminal/searchTags/setDisplaySearchTag.jhtml",{
					hid:hid,
					areaId : $("#city").val() 
				},'json'
				).done(function(data){
					if(data.success){
						refresh();
					}
					showSmReslutWindow(data.success, data.msg);
			 	}).fail(function(){
				  	$('#prompt').hide();
					$('#triggerModal').modal('hide');
					showSmReslutWindow(false, "请求失败");
			  });
			},"确定设为展示搜索标签?");	
		
		}
   </script>
   </c:if>
   <c:if test="${!cityInBusiness}">
   		<p class="lead text-center">该城市未开通商圈</p>
   </c:if>
  </body>
</html>
