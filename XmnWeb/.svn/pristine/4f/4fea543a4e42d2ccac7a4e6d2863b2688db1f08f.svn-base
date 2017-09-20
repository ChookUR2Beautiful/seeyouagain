/**
 * 销毁验证提示框
 */
function destroyPopover(element){
	$(element).popover("destroy");
}

/**
 *	移除所有验证提示
 */
function popoverRemove(){
	$(".popover").remove();
}
/**
 * 创建效验不通过提示框
 * @param {} element
 * @param {} text
 */
function createPopover(element,text){
	 $(element).popover({
	    	content:["<h5><i class='icon-warning-sign'>&nbsp;&nbsp;",text,"&nbsp;&nbsp;</i></h5>"].join(""),
	    	placement:"top",
	    	trigger:"focus",
	    	html:true
	    });
	   
}

/**
 * 显示提示框
 * @param {} element
 */
function elementShowPopover(element){
$(element).popover("show");
}

/**
 * 显示元素
 * @param {} element
 */
function showElement(element){
	$(element).show();
}

/**
 * 效验元素的边框颜色
 * @param {} element  
 * @param {} isSuccess  true 成功 false  失败
 */
function createBorder(element,isSuccess){
	var color = isSuccess?"#ccc":"#FF3030";
	$(element).css("border","1px solid "+color);
}
/**
 * 校验消息提示 -----
 */
function dataError(element,text){
	destroyPopover(element);
	popoverRemove();
	createPopover(element,text);
	elementShowPopover(element);
	createBorder(element,false);
}

/**
 * 自定义表单验证消息提醒
 * @param {} element
 * @param {} text
 */
function submitDataError(element,text){
	destroyPopover(element);
	createPopover(element,text);
	elementShowPopover(element);
	createBorder(element,false);
}

/**
 * 效验成功
 * @param {} element
 */
function datasuccess(element){
	createBorder(element,true);
	destroyPopover(element);
	popoverRemove();
}

/**
 * 自定义表单验证消息提醒
 * @param {} element
 * @param {} text
 */
function submitDatasuccess(element){
	createBorder(element,true);
	destroyPopover(element);
}


