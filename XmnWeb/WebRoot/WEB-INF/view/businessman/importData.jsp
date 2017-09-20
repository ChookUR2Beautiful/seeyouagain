<%@ page pageEncoding="utf-8" import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel">
	<div class="panel-heading">
    <strong><i class="icon-building"></i> 导入数据</strong>
  </div>
  <div class="panel-body">
  
      <table class="borderBottom table table-form">
        <tbody><tr>
          <td style="width: 120px;text-align: right;">模版:</td>
          <td >
            <a  href="businessman/sellerSubsidy/importData/downloadTemplate.jhtml" >模版下载</a>
          </td>
        </tr>
        <tr>
          <td style="width: 120px;text-align: right;">导入数据:</td>
          <td >
          			<div id="importData"></div>
          
          
          </td>
        </tr>
         <tr>
          <td style="width: 120px;text-align: right;">提示:</td><td >
          	
          	<section class="abstract">
            <p><strong></strong><label style="color: red; font-size: 12px">请将需要导入的数据保存到模版中并上传,如果格式不正确将导致数据不能正确添加!</label></p>
         	 </section>
          
          
          </td>
        </tr>
        <tr>
          <th ></th><td > <input type="button" data-dismiss="modal"  class="btn btn-default" value="关闭"> </td>
        </tr>
      </tbody></table>
  </div>
</div>



<script type="text/javascript">
$(function(){
	$("#importData").uploadFile({
		server : '${pageContext.request.contextPath}'+"/businessman/sellerSubsidy/importData.jhtml",
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
				pageDiv.reload();
			}
			
		}
	});

});


</script>


