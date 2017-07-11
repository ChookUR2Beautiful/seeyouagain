var loadinit = 0;

 //简单实现页面路由
function router(){
    var hash = window.location.hash;
    var bs = hash.split('/');
    var id;
    if(bs.length == 2){
        hash = bs[0];
        id = bs[1];
    }else{
        hash = bs[0];
    }
    // console.log(id);
    if(hash == '' || hash == '#home'){

        if(loadinit){
            $('#homepanel').addClass('animation-reverse').addClass('animation-active').addClass('slide-out');
            $('#pconsumpanel').addClass('animation-reverse').addClass('animation-active').addClass('slide-in');
            setTimeout(function(){
                $('#homepanel').addClass('active').removeClass('animation-reverse').removeClass('animation-active').removeClass('slide-out');
                $('#pconsumpanel').removeClass('active').removeClass('animation-reverse').removeClass('animation-active').removeClass('slide-in');
                
            },550);  
        }else{
            loadinit = 1;
        }
           
    }else if(hash == '#personalconsum'){
        if(loadinit){
            $('#homepanel').addClass('animation-active').addClass('slide-out');
            $('#pconsumpanel').addClass('animation-active').addClass('slide-in');
            setTimeout(function(){
                $('#homepanel').removeClass('active').removeClass('animation-active').removeClass('slide-out');
                $('#pconsumpanel').addClass('active').removeClass('animation-active').removeClass('slide-in');;
            },300);
        }else{
             $('#homepanel').removeClass('active');
             $('#pconsumpanel').addClass('active');
            loadinit = 1;
        }
        getPersondata();
        
    }
}

function showtips(c){
    this.i = 1;
}

showtips.prototype.show = function(content){
    $('body').append('<div class="tipsmodle"><p>'+content+'</p></div>');
    var twidth = $('.tipsmodle').css('width');
    $('.tipsmodle').css('marginLeft',- parseInt(twidth) / 2 + "px").show();
    this.showAction();
}

showtips.prototype.showAction = function(){ 
    var _this = this;
    $('.tipsmodle').animate({opacity : "0.9" , bottom : "25%"},100,"linear",function(){
        var tips_index = this;
        setTimeout(function(){
            _this.destoryTips(tips_index);
        },2000);
    });
}

showtips.prototype.destoryTips = function(_this){   

    $(_this).animate({opacity : "0" , bottom : "30%"},100,"linear",function(){
        $(_this).remove();
    });
    
}   
var tips = new showtips();

var loading = {
    show : function(){
        var loadingHtml = '<div class="index-showok-model"></div><div class="index-showok"><i class="loadingimg"></i><p class="loadingp">请稍候</p></div>';
        if($('.index-showok-model').length == 0 ){
            $('body').append(loadingHtml);
            $('.index-showok').animate({opacity : "0.6" },500,"linear");
        }
    },
    hide : function(){
        $('.index-showok').animate({opacity : "0" },500,"linear",function(){
            $('.index-showok-model').remove();
            $('.index-showok').remove();
        });
    }
}


$(function(){
	var clientWidth = document.body.clientWidth || document.documentElement.clientWidth || window.innerWidth;
	var clientHeight = document.body.clientHeight || document.documentElement.clientHeight || window.innerHeight;
	
	if(!clientHeight){
		clientHeight = $('body')[0].scrollHeight + 500;
	}
	
	
	if(!clientHeight){
		clientHeight = 600;
	}
	
	var initCss = {
		"width" : clientWidth + "px",
		"height" : clientHeight + "px"
	};
	
	$('.view').css(initCss);
	
	$('#homepanel').scrollTop(0);
    // console.log('asg');
    window.onhashchange = router;
    // location.hash = 'personalconsum';
    router();

    $('.allcous-label-box li a').bind('click',function(){
        if($(this).parent().hasClass('allcous-label-selected')){
            $(this).parent().removeClass('allcous-label-selected');
        }else{
            $(this).parent().addClass('allcous-label-selected');
        }

        getAllcustomer();
    });

    $('.coustomerinfo-content-nav ul li a').bind('click',function(){
        $('.coustomerinfo-content-nav ul li').removeClass('nav-active');
        $(this).parent().addClass('nav-active');

        var _index = $(this).parent().index();
        
        $('.coustomerinfo-cc-localconsum').hide().eq(_index).show();

        if($('.coustomerinfo-content-nav ul li:eq(1)').hasClass('nav-active')){
            getLocalshopConsum();
        }
    });

    $('#homepanel')[0].onscroll = onScrollEvent;
    // $('#homepanel').onscroll = onScrollEvent;
    $('#homepanel')[0].addEventListener('touchmove', onScrollEvent , false);

    $('#pconsumpanel')[0].onscroll = pconScrollEvent;

    $('#pconsumpanel')[0].addEventListener('touchmove', pconScrollEvent , false);

    



    initAllcouScroll();

});
var basePath = $("#basePath").val();
var customerListUrl = $("#customerListUrl").val();
var customerInfoUrl = $("#customerInfoUrl").val();
var localShopConsumUrl = $("#localShopConsumUrl").val();
var sellerid = $("#sellerid").val();
var apiversion = $("#apiversion").val();
var sessiontoken = $("#sessiontoken").val();
var grouptype = $("#grouptype").val();
var isCustomerEnd = 0;
    /*取客户数据*/
    function getAllcustomer()
    {
        var allCus = $('.allcous-label-box li');
        var selLabel = [];
        allCus.each(function(){
            if($(this).hasClass('allcous-label-selected')){
                selLabel.push($(this).attr('attid'));
            }
        });
        if (grouptype != null && grouptype != "" && grouptype != undefined) {
        	selLabel.push(grouptype);
		}
        loading.show();
        $.ajax({
            type: "post",
            url: customerListUrl,
            data: {
                'grouptype' : selLabel.join(","),"page" : allConsumpage,"sellerid" : sellerid,"apiversion" : apiversion,"sessiontoken" : sessiontoken
            },
            dataType: "json",
            success: function(data){
            	if (data != null && data != "" && data != undefined) {
            		if (data.state == 100) {
            			/*模拟真实请求*/
            				loading.hide();
            				
            				var _cHtml = '';
            				
            				var customers = $(data.response.customers);
            				if (customers != null && customers !="" && customers != undefined ) {
            					 $.each(customers,function(n,value) {
            						 var _sHtml = '<li class="allcous-list">';
            						 var headpic = value.headpic + "";
            						 if (headpic != "") {
            							 _sHtml += '<div class="allcous-content-leftimg"><span style="background-image: url(' + headpic +');"></span></div>';
									}else {
										_sHtml += '<div class="allcous-content-leftimg"><span style="background-image: url(' + basePath +'imgs/default_avatar.png);"></span></div>';
									}
             						_sHtml += '<div class="allcous-content-rightinfo"><p class="allcous-content-name">' + value.customername + '</p>';
             						_sHtml += '<p class="allcous-content-phone">' + value.phone + '</p>';
             						_sHtml += '<ul class="allcous-label-box">';
             						
             						if (value.rolea == 1) {
                						_sHtml += '<li class="allcous-label-red"><a href="javascript:;">大款</a></li>';
									}
             						if (value.roleb == 1) {
             							_sHtml += '<li class="allcous-label-red"><a href="javascript:;">工薪一族</a></li>';
             						}
             						if (value.rolec == 1) {
             							_sHtml += '<li class="allcous-label-red"><a href="javascript:;">吃货</a></li>';
             						}
             						if (value.roled == 1) {
             							_sHtml += '<li class="allcous-label-red"><a href="javascript:;">工作餐</a></li>';
             						}
             						if (value.rolee == 1) {
             							_sHtml += '<li class="allcous-label-red"><a href="javascript:;">其他</a></li>';
             						}
            						
            						_sHtml += '</ul></div><a href="#personalconsum/' + value.customerid + '" class="all-list-link"></a>';
            						_sHtml += '</li>';
            						
            						_cHtml += _sHtml;
            						
            					 });
            					 if(customers.length < 20 && customers == "[]"){
                 					isCustomerEnd = 1;
                 				}
            					 $('.allcous-content').html(_cHtml);
							}
					}else {
						alert(data.info);
					}
				}else {
					window.history.go(-1);
				}
            }
        });

    }

    /*客户数据分页*/
    var getAllstatus = 0;
    var allConsumpage = 1;
    function onScrollEvent(){
        var scrollTop = $('#homepanel').scrollTop();
        var scrollHeight = $('#homepanel')[0].scrollHeight;
        var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
        
        var scid = window.location.hash;
        
        if(parseInt(scrollTop) > parseInt(scrollHeight) - parseInt(clientHeight) - 20){
            if(scid == '' && isCustomerEnd) getAllstatus == 0 && getAlldcumBypage();
        }
    }

    function initAllcouScroll(){
        var scrollHeight = $('#homepanel')[0].scrollHeight;
        var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
//        parseInt(clientHeight) < parseInt(scrollHeight - 20) && $('.allcous-content').append('<div class="loading"><img src="' + basePath + 'imgsimgs/loading.gif" ></div>');
    }

    function getAlldcumBypage()
    {
        
        getAllstatus = 1;
        var allCus = $('.allcous-label-box li');
        var selLabel = [];
        allCus.each(function(){
            if($(this).hasClass('allcous-label-selected')){
                selLabel.push($(this).attr('attid'));
            }
        });
        if (grouptype != null && grouptype != "" && grouptype != undefined) {
        	selLabel.push(grouptype);
		}
         $.ajax({
            type: "post",
            url: customerListUrl,
            data: {
                'page' : allConsumpage,'grouptype' : selLabel.join(","),"sellerid" : sellerid,"apiversion" : apiversion,"sessiontoken" : sessiontoken
            },
            dataType: "json",
            success: function(data){
            	if (data != null && data != "" && data != undefined) {
            		if (data.state == 100) {
            			/*模拟真实请求*/
            				var _cHtml = '';
            				var customers = $(data.response.customers);
            				if (customers != null && customers !="" && customers != undefined ) {
            					 $.each(customers,function(n,value) {
            						 var _sHtml = '<li class="allcous-list">';
            						 var headpic = value.headpic + "";
            						 if (headpic != "") {
            							 _sHtml += '<div class="allcous-content-leftimg"><span style="background-image: url(' + headpic +');"></span></div>';
									}else {
										_sHtml += '<div class="allcous-content-leftimg"><span style="background-image: url(' + basePath +'imgs/default_avatar.png);"></span></div>';
									}
             						_sHtml += '<div class="allcous-content-rightinfo"><p class="allcous-content-name">' + value.customername + '</p>';
             						_sHtml += '<p class="allcous-content-phone">' + value.phone + '</p>';
             						_sHtml += '<ul class="allcous-label-box">';
             						
             						if (value.rolea == 1) {
                						_sHtml += '<li class="allcous-label-red"><a href="javascript:;">大款</a></li>';
									}
             						if (value.roleb == 1) {
             							_sHtml += '<li class="allcous-label-red"><a href="javascript:;">工薪一族</a></li>';
             						}
             						if (value.rolec == 1) {
             							_sHtml += '<li class="allcous-label-red"><a href="javascript:;">吃货</a></li>';
             						}
             						if (value.roled == 1) {
             							_sHtml += '<li class="allcous-label-red"><a href="javascript:;">工作餐</a></li>';
             						}
             						if (value.rolee == 1) {
             							_sHtml += '<li class="allcous-label-red"><a href="javascript:;">其他</a></li>';
             						}
            						
            						_sHtml += '</ul></div><a href="#personalconsum/' + value.customerid + '" class="all-list-link"></a>';
            						_sHtml += '</li>';
            						
            						_cHtml += _sHtml;
            						
            					 });  
            					 
            					 $('.allcous-content').find('.loading').remove();
            	                    /*返回结束字段*/
            	                    if(data){
            	                        _cHtml += '<div class="loading"><img src="' + basePath + 'imgs/loading.gif" ></div>';
            	                    }else{
            	                        _cHtml += '<div class="loading">已到底部</div>';

            	                    }

            	                    $('.allcous-content').append(_cHtml);
            	                    //复位
            	                    getAllstatus = 0;

            	                    //页数+1
            	                    allConsumpage++;
							}
					}else {
						tips.show(data.info);
					}
				}else {
					window.history.go(-1);
				}
            }
        });

    }
    
    var isPersonEnd = 0;

    function getPersondata(){
    	$('.coustomerinfo-content-nav ul li:eq(1)').removeAttr('isget');
        var sid = window.location.hash.split('/')[1];
        loading.show();
        if(sid){
        	//清空上一个页面残留的信息
        	$('.coustomerinfo-cc-list').empty();
        	$('.ccoustomerinfo-right-name').empty();
        	$('.ccoustomerinfo-right-phone').empty();
        	$('#pconlabellist').empty();
        	$('.coustomerinfo-cc-totalcou-order span').empty();
        	$('.coustomerinfo-cc-totalcou-money').empty();
        	$('#othershopordernum span').empty();
        	$('#othershopmoney').empty();
        	$('#localshopordernum span').empty();
        	$('#localshopmoney').empty();
        	$('#localshop').empty();
        	$('#piechat').empty();
        	$('.coustomerinfo-content-nav ul li').removeClass('nav-active');
        	$('.coustomerinfo-content-nav ul li:eq(0)').addClass('nav-active');
        	$('.coustomerinfo-cc-localconsum:eq(0)').show();
        	$('.coustomerinfo-cc-localconsum:eq(1)').hide();
        	getordStatus = 0;
        	isPersonEnd = 0;
            $.ajax({
                type: "post",
                url: customerInfoUrl,
                data: {
                    'customerid' : sid,'page' : 1,"sellerid" : sellerid,"apiversion" : apiversion,"sessiontoken" : sessiontoken
                },
                dataType: "json",
                success: function(data){
                	if (data != null && data != "" && data != undefined) {
                		if (data.state == 100) {
                			orderListPage = 2;
                				//修改标题
                				$("title").html("客户分析");
                				/*头像*/
                				var headimgUrl = "";
                				var headpic = data.response.headpic + "";
                				if (headpic != "") {
                					headimgUrl = headpic;
                				}else {
                					headimgUrl = basePath + 'imgs/default_avatar.png';
                				}
                				/*名字*/
                				var perName = data.response.customername;
                				/*phone*/
                				var perPhone = data.response.perPhone;
                				var phone = data.response.phone;
                				
                				$('.coustomerinfo-left-img span').css('backgroundImage','url('+headimgUrl+')');
                				$('.ccoustomerinfo-right-name').html(perName);
                				$('.ccoustomerinfo-right-phone').html(perPhone);
                				$('.callphone').attr("href","tel:"+phone);
                				
                				/*修改标签*/
                				var labelList = '';
                				if (data.response.rolea == 1) {
                					labelList += '<li class="allcous-label-yellow"><a href="javascript:;">大款</a></li>';
								}
                				if (data.response.roleb == 1) {
                					labelList += '<li class="allcous-label-yellow"><a href="javascript:;">工薪一族</a></li>';
                				}
                				if (data.response.rolec == 1) {
                					labelList += '<li class="allcous-label-yellow"><a href="javascript:;">吃货</a></li>';
                				}
                				if (data.response.roled == 1) {
                					labelList += '<li class="allcous-label-yellow"><a href="javascript:;">工作餐</a></li>';
                				}
                				if (data.response.rolee == 1) {
                					labelList += '<li class="allcous-label-yellow"><a href="javascript:;">其他</a></li>';
                				}
                				
                				$('#pconlabellist').html(labelList);
                				
                				/*修改订单*/
                				var totalOrder = data.response.counts;
                				$('.coustomerinfo-cc-totalcou-order span').html(totalOrder);
                				
                				/*修改订单总金额*/
                				var totalMoney = data.response.fee;
                				$('.coustomerinfo-cc-totalcou-money').html('￥'+totalMoney);
                				
                				/*修改订单列表*/
                				var consumelogs = $(data.response.consumelogs);
                				var orderList = '';
                			
                				if (consumelogs != null && consumelogs != "" && consumelogs != undefined) {
                					$.each(consumelogs,function(n,value){
                						var _olist = '<li>';
                						_olist += '<div class="coustomerinfo-list-leftcc">';
                						_olist += '<p class="coustomerinfo-list-coustime">' + value.sdate + '</p>';
                						_olist += '<p class="coustomerinfo-list-coumoney">消费金额</p></div>';
                						_olist += '<p class="coustomerinfo-list-rightcc">￥' + value.money + '</p>';
                						_olist += '</li>';
                						orderList += _olist; 
                					});
								}else {
									isPersonEnd = 1;
								}
                				
                				if(consumelogs.length < 20){
                					isPersonEnd = 1;
                				}
                				
                				$('.coustomerinfo-cc-list').html(orderList);
                				initPconScroll();
                				loading.hide();
						}else {
							tips.show(data.info);
						}
					}else {
						window.history.go(-1);
					}
                }

            });
        }
    }

    var getordStatus = 0;

    function pconScrollEvent(){
        var scrollTop = $('#pconsumpanel').scrollTop();
        var scrollHeight = $('#pconsumpanel')[0].scrollHeight;
        var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
        
        var scid = window.location.hash;
        // console.log(scrollTop);
        if(parseInt(scrollTop) > parseInt(scrollHeight) - parseInt(clientHeight) - 20){
            var navIsactive = $('.coustomerinfo-content-nav ul li:eq(0)').hasClass('nav-active');
            if(scid != '' && navIsactive && !isPersonEnd) getordStatus == 0 && getOrderList();
        }
    }

    function initPconScroll(){
        var scrollHeight = $('#pconsumpanel')[0].scrollHeight;
        var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
//        parseInt(clientHeight) < parseInt(scrollHeight - 20) && $('.coustomerinfo-cc-list').append('<div class="loading"><img src="' + basePath + 'imgs/loading.gif" ></div>');
    }
    var orderListPage = 1;
    /*取订单数据*/
    function getOrderList(){
        var sid = window.location.hash.split('/')[1];
        if(sid){
            getordStatus = 1;
            $.ajax({
                type: "post",
                url: customerInfoUrl,
                data: {
                    'customerid' : sid,'page' : orderListPage,"sellerid" : sellerid,"apiversion" : apiversion,"sessiontoken" : sessiontoken
                },
                dataType: "json",
                success:function(data){
                	if (data != null && data != "" && data != undefined) {
                		if (data.state == 100) {
                			 /*修改订单列表*/
            				 var consumelogs = $(data.response.consumelogs);
                			 var orderList = '';
                			 if (consumelogs != null && consumelogs != "" && consumelogs != undefined) {
             					$.each(consumelogs,function(n,value){
             						var _olist = '<li>';
             						_olist += '<div class="coustomerinfo-list-leftcc">';
             						_olist += '<p class="coustomerinfo-list-coustime">' + value.sdate + '</p>';
             						_olist += '<p class="coustomerinfo-list-coumoney">消费金额</p></div>';
             						_olist += '<p class="coustomerinfo-list-rightcc">￥' + value.money + '</p>';
             						_olist += '</li>';
             						orderList += _olist; 
             					});
							}
                             /*是否结束*/
                             if(consumelogs.length == 20){
                            	 orderListPage++;
                                 orderList += '<div class="loading"><img src="' + basePath + 'imgs/loading.gif" ></div>';
                                 getordStatus = 0;
                             }else{
                                 orderList += '<div class="loading">已到底部</div>';
                                
                             }

                             $('.coustomerinfo-cc-list').find('.loading').remove();

                             $('.coustomerinfo-cc-list').append(orderList);
                             

                             
                		}else {
                			tips.show(data.info);
						}
					}else {
						window.history.go(-1);
					}
                }   
            });
        }
    }

    /*店外消费*/
    function getLocalshopConsum(){
        if(!$('.coustomerinfo-content-nav ul li:eq(1)').attr('isget')){
            loading.show();
            var sid = window.location.hash.split('/')[1];
            if(sid){
                $.ajax({
                    type: "post",
                    url: localShopConsumUrl,
                    data: {
                    	'customerid' : sid,"sellerid" : sellerid,"apiversion" : apiversion,"sessiontoken" : sessiontoken
                    },
                    dataType: "json",
                    success:function(data){
                    	if (data != null && data != "" && data != undefined) {
                    		if (data.state == 100) {
	                            /*店外消费订单数*/
	                            var othercounts = data.response.othercounts;
	                            /*店外消费总额*/
	                            var otherfees = data.response.otherfees;
	                            $('#othershopordernum span').html(othercounts);
	                            $('#othershopmoney').html('￥'+otherfees);
	
	                            $('.coustomerinfo-content-nav ul li:eq(1)').attr('isget',1);
	
	                            
	                            /*商圈名称*/
	                            var businessname = data.response.businessname;
	                            /*商圈内消费订单数*/
	                            var businesscounts = data.response.businesscounts;
	                            /*商圈内消费总额*/
	                            var businessfee = data.response.businessfee;
	                            $('#localshop').html(businessname + '商圈内');
	                            $('#localshopordernum span').html(businesscounts);
	                            $('#localshopmoney').html('￥'+businessfee);
	
	                            /*
	                            * 饼图
	                            * @param 参数说明 
	                            * @type 类型，类型1为类型横放示例图,类型2为类型竖放示例图
	                            * @data 数据
	                            */
	                            var dataset = [];
	                            var title = [];
	                            var ratios = $(data.response.ratios);
	                            if (ratios != null && ratios != "" && ratios != undefined) {
	                            	$.each(ratios,function(n,value){
	                            		dataset.push(value.num);
	                            		title.push(value.typeName);
	                            	})
									
								}
	                            
	                            var singleDistance = dataset.length - 4;
	                            var distance = 400 + 60;
	                            if(singleDistance > 0){
	                            	singleDistance = (singleDistance / 2) * 60;
	                            	distance = parseInt(distance) + singleDistance;
	                            }
	                            
	                            
	                            pieChat({
	                                ele : '#piechat',
	                                width : '100%',
	                                height : distance,
	                                type : 2,
	                                data : {
	                                    /*颜色值*/
	                                    'color' : ['#ff9474','#fae25b','#fac85b','#a9e25e','#69d5a5','#8ecdf6','#f2a23e','#709cff','#928ff7','#e46df2','#47e14f','#ed497b'],
	                                    /*数值*/
	                                    'dataset' : dataset,
	                                    /*对应的标题名*/
	                                    'title' : title
	                                    }
	                            });
	                            loading.hide();
                    		}else {
                    			tips.show(data.info);
    						}
    					}else {
    						window.history.go(-1);
    					}
                    }
                });

            }
        }
    }

    