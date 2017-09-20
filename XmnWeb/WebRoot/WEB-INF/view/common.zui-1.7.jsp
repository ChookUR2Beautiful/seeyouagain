<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- 操作结果提示层 -->
<div class="modal fade" id="sm_reslut_window" data-position="100px">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body"></div>
		</div>
	</div>
</div>

<!-- 操作询问层 -->
<div class="modal fade" id="sm_comfir_window" style="z-index: 9999;" data-position="100px">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
	        <h4 class="modal-title">操作提示</h4>
	      </div>
	      <div class="modal-body">
	        <p>确定需要执行删除操作？</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取  消</button>&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary yes" >确  定</button>
	      </div>
		</div>
	</div>
</div>

<!-- 操作提示层，顶层提示 -->
<div class="modal fade" id="sm_result_tip_window" style="z-index: 9999;" data-position="100px">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
	        <h4 class="modal-title">操作提示</h4>
	      </div>
	      <div class="modal-body">
	        <p>请选择对应用户</p>
	      </div>
		</div>
	</div>
</div>

<!-- 操作询问层 仅供退款调用-->
<div class="modal fade" id="tk_comfir_window" style="z-index: 9999;" data-position="100px">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
	        <h4 class="modal-title">操作提示</h4>
	      </div>
	      <div class="modal-body">
	        <p>你确定要退款吗？</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取  消</button>&nbsp;&nbsp;
	        <a class="btn btn-primary yes" href="javascript:;" onclick="openWind();">确  定</a>	        
	      </div>
		</div>
	</div>
</div> 

<!-- 编辑层 -->
<div class="modal fade" id="sm_edit_window" data-position="200px">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body"></div>
		</div>
	</div>
</div>

<!-- 编辑层 -->
<div class="modal fade" id="lg_edit_window" data-position="50px">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body"></div>
		</div>
	</div>
</div>


<!-- 编辑层 -->
<div  class="modal fade" id="sm_edit_iframe" data-position="100px">
	<div class="modal-content">
		<div class="modal-header">
			<button class="close" data-dismiss="modal">×</button>
			<h4 class="modal-title">
				<i class="modal-icon icon-null"></i> <span class="modal-title-name">iframe模态框</span>
			</h4>
		</div>
		<div class="modal-body" style="padding: 0px; height: 88px;">
			<iframe id="iframe-triggerModal"  frameborder="no"
				allowtransparency="true" scrolling="auto"
				style="width: 100%; height: 100%; left: 0px;"></iframe>
		</div>
	</div>
</div>
<input type="hidden" id="fastfdsHttp"  value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
<script src="<%=path%>/resources/zui/lib/jquery/jquery.js"></script>
<script src="<%=path%>/resources/zui/js/zui-1.7.js"></script>
<script src="<%=path%>/resources/page/page.js"></script>
<script src="<%=path%>/ux/js/jquery.validate.js"></script>
<script src="<%=path%>/ux/js/jquery.validate.additional-methods.js"></script>
<script src="<%=path%>/ux/js/jquery.validate.messages_zh.js"></script>
<script src="<%=path%>/resources/web/js/popoverUtil.js"></script>
<script src="<%=path%>/resources/web/js/util.js"></script>
<script src="<%=path%>/resources/web/js/show.js"></script>