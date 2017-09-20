<%@ page pageEncoding="utf-8" import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
		<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
		<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
		<div class="panel">
			<div class="panel-heading">导入说明</div>
			<div class="panel-body">
					<div class="row">
						<label class="col-md-4 text-right">产品信息模版：</label>
						<div class="col-md-8">
							<a  href="fresh/manage/importProduct/downloadTemplate.jhtml" >模版下载</a>
						</div>
					</div>
					<div class="row">
						<label class="col-md-4 text-right">导入产品数据：</label>
						<div class="col-md-8">
							<div id="importData"></div>
						</div>
					</div>
					<div class="row">
						<label class="col-md-4 text-right">提示：</label>
						<div class="col-md-8">
							<section class="abstract">
					           <p><strong></strong><label style="color: red; font-size: 12px">请将需要导入的数据保存到模版中并上传,如果格式不正确将导致数据不能正确添加!该模版最多导入1000条数据。</label></p>
					        </section>
						</div>
					</div>
					<div class="row text-center" style="margin-top:20px;">
						<button class="btn btn-default" data-dismiss="modal" type="button">
							<i class="icon-close"></i>&nbsp;关闭
						</button>
					</div>
			</div>
		</div>
	<script src="<%=path%>//resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script type="text/javascript">
	$("#importData").uploadFile({
		server : '${pageContext.request.contextPath}'+"/fresh/manage/importProduct/importData.jhtml",
		fileVal : "importData",
		isImport : true,
		filterMsg : "请选择下载好的模版文件！",
		accept: {
	        title: 'file',
	        extensions: 'xls',
	        mimeTypes: 'application/vnd.ms-excel'
		    
	    },
		callback:function(data){
			$('#triggerModal').modal('hide');
			$('#prompt').hide();
			showSmReslutWindow(data.status, data.message);
			if(data.status){
				freshList.reload();
			}
			
		}
	});
	</script>
</body>
</html>