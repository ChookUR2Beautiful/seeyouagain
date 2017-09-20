var postageTemplateListSize = 0;//运费模板个数
var clickN = 0;
//权限
var showPostage = $("#showPostage");
var updatePostage = $("#updatePostage").val() == 'true' ? '修改' : "&nbsp;";
var deletePostage = ''; //$("#deletePostage").val() == 'true' ? '删除' : "&nbsp;";
var copyPostage =''; //$("#copyPostage").val() == 'true' ? '复制模板' : "&nbsp;";
$(function() {
		inserTitle(' > 积分商城 > <a href="fresh/postagetemplate/init.jhtml" target="right">运费模板管理</a>','allbillSpan',true);
		  /**
		   * 页面加载完成时调用分页查询
		   */
		  getPage();
	 });
/**
 * 复制模板
 */
$('body').on('click','.copbtn',function(){
		var par = $(this).parent().parent().parent().parent();
		var delBtn = '<a href="javascript:;" style="font-size:13px;margin-left: 1%;" class="delbtn">删除</a>';
		//改变模板文本名称
		var titleText = par.find('.titleText').html();
		//取到被复制的运费模板tid，数据库新增数据后返回tid
		var copbtnPar = $(this).parentsUntil('.tbDiv').parent();
		var tid = copbtnPar.find('table').attr("attid");
		$.post('fresh/postagetemplate/add/copy.jhtml', 'tid='+tid+'&copyTitle='+ titleText + '的副本('+(++clickN)+')', function(result) {
			var a = JSON.parse(result);
//			console.log(result);
	        if (a.flag == true) {
	        	var tmpHtml = '<div class="tbDiv"><hr></hr><table attid="'+a.tid+'" style="width:100%;" class="templateList['+(++postageTemplateListSize)+']" cellpadding="5px" cellspacing="2">'+par.html()+"</table></div>";
	    		var tmpHtml2 = tmpHtml.replace(/<span><\/span>/g,delBtn).replace('<font class="titleText" style="font-family: inherit;font-weight: bold;line-height: 1.1;color: inherit;font-size: 15px;">'+titleText+'</font>','<font class="titleText" style="font-family: inherit;font-weight: bold;line-height: 1.1;color: inherit;font-size: 15px;">' + titleText + '的副本('+(clickN)+')</font>');
	    		par.after(tmpHtml2);
	        } else {
	        	window.messager.warning("模板名称过长！");
	        	setTimeout(function() {
					 location.href='fresh/postagetemplate/init.jhtml.jhtml';
		         }, 3000);
	        }
	    }, "json");
});
/**
 * 删除模板
 */
$('body').on('click','.delbtn',function(){
	var par = $(this).parentsUntil('.tbDiv').parent();
	//获取模板表格的attid属性值
	var tid = par.find('table').attr("attid");
		//根据tid删除运费模板
		showSmConfirmWindow(function() {
			$.ajax({
				type : 'post',
				url : 'fresh/postagetemplate/delete.jhtml',
				data : 'tid=' + tid,
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					if (data.success) {
						showSmReslutWindow(data.success, data.msg);
						 setTimeout(function() {
							 location.href='fresh/postagetemplate/init.jhtml.jhtml';
				         }, 2000);
					}
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
				}
			});
		});
});

function getPage(){
	/**
	 * 分页插件
	 */
	$("#page").myPagination({
		   cssStyle:'msdn',
		   currPage: 1,
		   pageNumber:5,
           pageCount:0,
           ajax : {
            on : true,
            type:"post",
            pageCountId: "pageCount",
               url : "fresh/postagetemplate/list.jhtml",
               dataType : "json",
               param:'',
               callback: function(data) {
                   ZENG.msgbox.hide(); //隐藏加载提示
                   var result = data.result;
//                   $.fn.debug(result);
                   //循环html代码
                   var tb = "";
                   var tr = "";
                   var zdtr = "";
                   var tbdy = "";
                   var curData = [];
                   /**
                    * 把默认模板放在前面
                    */
                   var data = data.result;
                   postageTemplateListSize = data.length;
                   for(var i in data){
                	   if(data[i].defaultTemplate == 1){
                		   curData.push(data[i]);
                	   }
                   }
                   
                   for(var i in data){
                	   if(data[i].defaultTemplate == 0){
                		   curData.push(data[i]);
                	   }
                   }
                   console.log(curData);
                   $("#dataTemp").html("");
                   for(var i in curData){
                	   tb = createTB(curData[i],curData[i].tid);
                	   //插入html代码
                       $("#dataTemp").append(tb);
                	   var list = curData[i].postageRuleList;
                	   for(var k in list){
                		   tr =  createTR(list[k]);
                		   $('#dataTemp table[attid="'+curData[i].tid+'"]').append(tr);
                	   }
                	   if(curData[i].freeRule != null && curData[i].freeRule !=""){//默认模板则不指定
                		   zdtr = crecteZDTR(curData[i]);
                    	   $('#dataTemp table[attid="'+curData[i].tid+'"]').append(zdtr);
                	   }
                	   tbdy = createTBody(curData[i]);
                	   $('#dataTemp table[attid="'+curData[i].tid+'"]').append(tbdy);
                   }
                   $("#data").html($("#dataTemp").html());
               }
     },
		  panel:{
		   tipInfo_on:true,
//		   tipInfo: '<span class="skip-box">{input}/{sumPage}页</span>',
		   tipInfo: '&nbsp;&nbsp;第{input}页/共{sumPage}页',
		   tipInfo_css: {
		    width: '30px',
		    height: "23px",
		    border: "1px solid #CCCCCC",
		    padding: "0 2px 0 2px",
		    margin: "0px 5px 0px 5px",
		    color: "#0D57CB",
		    textAlign:"center"
		   }
		  }
       });
   }
/**
 * 组装TB
 */

function createTB(obj,i){
	var delBtn = '<a href="javascript:;" style="font-size:13px;margin-left: 1%;" class="delbtn">'+deletePostage+'</a>';
	var sp = '<span></span>';
	var spanEle = obj.defaultTemplate == 1 ? sp : delBtn;
	var caption = "<div class='tbDiv'><hr></hr><table attid="+i+" style='width:100%;' cellpadding='5px' cellspacing='2';>";
	caption += '<tbody style="border: 1px solid;text-align:center;"> ' +
	'<tr style="border: 1px solid;">'+
		'<td colspan="6" style="border: 1px solid;">' +
			'<font class="titleText" style="font-family: inherit;font-weight: bold;line-height: 1.1;color: inherit;font-size: 15px;">'+obj.title+'</font>' +
	'</tr>' + 
	'<tr style="border: 1px solid;">'+
	'<td colspan="6" style="border: 1px solid;">' +
		'<a href="javascript:;" style="font-size:13px;margin-left: 80%;"'+
		'class="copbtn">'+copyPostage+'</a><a href="javascript:;"'+
		'style="font-size:13px;margin-left: 1%;" class="updbtn">'+updatePostage+'</a>'+ spanEle +'</td>'+
	'</tr>' + 
	'<tr>' +
		'<td style="width:20%;border: 1px solid;"><h5>运送到</h5></td>'+
		'<td style="width:20%;border: 1px solid;"><h5>首重</h5></td>'+
		'<td style="width:20%;border: 1px solid;"><h5>运费</h5></td>'+
		'<td style="width:20%;border: 1px solid;"><h5>续重</h5></td>'+
		'<td style="width:20%;border: 1px solid;"><h5>续运费</h5></td>'+
	'</tr>';
 return caption;
}
function createTBody(obj){
	var tbd = "";
	tbd +='</tbody>'+
	'</table></div>';
 return tbd;
}
function createTR(obj){
	var tr = "";
	tr +='<tr>'+
	    '<td style="width:20%;border: 1px solid;">'+obj.area_r+'</td>'+
		'<td style="width:20%;border: 1px solid;">'+obj.baseWeight+'kg</td>'+
		'<td style="width:20%;border: 1px solid;">'+obj.baseFee+'元</td>'+
		'<td style="width:20%;border: 1px solid;">'+obj.extraWeight+'kg</td>'+
		'<td style="width:20%;border: 1px solid;">'+obj.extraFee+'元</td>'+
	'</tr>';
 return tr;
}
function crecteZDTR(obj){
	var zdtr = "";
	zdtr += '<tr>'+
		'<td style="width:20%;border: 1px solid;">指定包邮条件</td>'+ 
		'<td colspan="4" style="width:20%;border: 1px solid;">'+obj.freeRule+'</td>'+
	   '</tr>';
	return zdtr;
}
/**
 * 修改模板事件
 */
$("body").on('click','.updbtn',function(){
	var par = $(this).parent().parent().parent().parent();
	var tid = par.attr('attid');
	location.href='fresh/postagetemplate/update/init.jhtml?tid='+tid+'';
});
