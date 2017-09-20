$(function() {
	var zone = resetData();
	
	/*显示隐藏城市*/
    $('body').on('click','.allowdown',function(){
        var par = $(this).parentsUntil('.ecity').parent();
        if(par.hasClass('showCityPop')){
            par.removeClass('showCityPop');
        }else{
            $('.ecity').removeClass('showCityPop');
            par.addClass('showCityPop');
        }
    });

	/*城市关闭按钮*/
    $('body').on('click','.close_button',function(){
        var par = $(this).parentsUntil('.ecity').parent();
        if(par.hasClass('showCityPop')) par.removeClass('showCityPop');
        else par.addClass('showCityPop');
    });

    /*省按钮*/
    $('body').on('change','.J_Province',function(){
        var ecityEle = $(this).parentsUntil('.ecity').parent();
        if($(this).prop('checked')){
            //选上
            ecityEle.find('.citys .J_City').prop('checked','checked');
            calnum(ecityEle.find('.citys .J_City'),ecityEle);
        }else{
            ecityEle.find('.citys .J_City').removeProp('checked');
            calnum(ecityEle.find('.citys .J_City'),ecityEle);
        }

        checkCity(this,ecityEle);
        proCheck(this);
    });

    //城市
    $('body').on('change','.J_City',function(){
        var ecityEle = $(this).parentsUntil('.ecity').parent();
        checkCity(this,ecityEle);
        proCheck(this);
    });

    function calnum(e,p){
        var cNum = 0;
        e.each(function(){
            if($(this).prop('checked')){
                cNum ++;
            }
        });
        if(cNum == 0){
            p.find('.check_num').html('');
        }else{
            p.find('.check_num').html('('+cNum+')');
        }
    }

    function checkCity(_this,p){
        var cNum = 0;
        var plist = p.find('.J_City');
        plist.each(function(){
            if($(this).prop('checked')){
                cNum++;
            }
        });

        // console.log(cNum);
        if(cNum == plist.length){
            p.find('.J_Province').prop('checked','checked');
        }else{
            p.find('.J_Province').removeProp('checked');
        }

        calnum(p.find('.citys .J_City'),p);
    }


    /*区域按钮*/
    $('body').on('change','.J_Group',function(){
        var par = $(this).parentsUntil('.dcity').parent();
        if($(this).prop('checked')){
            par.find('.province-list .J_Province').prop('checked','checked');
            par.find('.province-list .J_City').prop('checked','checked');
        }else{
            par.find('.province-list .J_Province').removeProp('checked');
            par.find('.province-list .J_City').removeProp('checked');
        }

        par.find('.province-list .ecity').each(function(){
            var cNum = 0;
            $(this).find('.J_City').each(function(){
                if($(this).prop('checked')){
                    cNum++;
                }
            });
            if(cNum == 0){
                $(this).find('.check_num').html('');
            }else{
                $(this).find('.check_num').html('('+cNum+')');
            }
        });

        proCheck(this);
    });


    /*省检查*/
    function proCheck(_this){
        var proNum = 0;
        var proList = $(_this).parentsUntil('.dcity').parent();
        proList.find('.J_Province').each(function(){
            if($(this).prop('checked')){
                proNum++;
            }
        });
       
        if(proNum == proList.find('.J_Province').length){
            proList.find('.J_Group').prop('checked','checked');
        }else{
            proList.find('.J_Group').removeProp('checked');
        }

    }
    
    function getZoneData(ele,callback,myinitData){
    	
    	$.ajax({
            type: "GET",
            url: "/XmnWeb/fresh/postagetemplate/initArea.jhtml",
            data: {},
            dataType: "json",
            success:function(data){
//              console.log(data);
            	zone = resetData();
                initialData(JSON.parse(data),ele,callback,myinitData);
            }
       })
    }
    
    function resetData(){
    	var iniZone = [
    	                {
    	                    '华东' : [{'上海':[]},{'江苏':[]},{'浙江':[]},{'安徽':[]},{'江西':[]}]
    	                },
    	                {
    	                    '华北' : [{'北京':[]},{'天津':[]},{'山西':[]},{'山东':[]},{'河北':[]},{'内蒙古':[]}]
    	                },
    	                {
    	                    '华中' :[{'湖南':[]},{'湖北':[]},{'河南':[]}]
    	                },
    	                {
    	                    '华南' :[{'广东':[]},{'广西':[]},{'福建':[]},{'海南':[]}]
    	                },
    	                {
    	                    '东北' :[{'辽宁':[]},{'吉林':[]},{'黑龙江':[]}]
    	                },
    	                {
    	                    '西北' :[{'陕西':[]},{'新疆':[]},{'甘肃':[]},{'宁夏':[]},{'青海':[]}]
    	                },
    	                {
    	                    '西南' :[{'重庆市':[]},{'云南':[]},{'贵州':[]},{'西藏':[]},{'四川':[]}]
    	                }
    	            ];
    	
    	return iniZone;
    }
    
    function initialData(data,ele,callback,myinitData){
        for(var i in zone){
            for(var k in zone[i]){
                for(var m in zone[i][k]){
                    for(var p in zone[i][k][m]){
                        for(var s in data){
                            if(p == data[s].title){
                                zone[i][k][m][p].push(data[s]);
                            }
                        }
                    }
                }
                
            }
        }
        
        loadData(zone,ele,callback,myinitData);
//      console.log(zone);
    }
    
    function loadData(zone,ele,callback,myinitData){
    	var allzoneTxt = '';
        var cid = 'city_id_00';
        var pid = 'pro_id_00';
        var ccount = 0;
        var pcount = 0;
        // console.log(zone);
        for(var i in zone){
            for(var k in zone[i]){
                var libody = getmodel('prolist');
                var zoneName = k;
                var curTxt = libody.replace(/\{zonename\}/g,zoneName);
                var proList = zone[i][k];
                var allProTxt = '';
                for(var m in proList){
                    for(var l in proList[m]){
                        var proBody = getmodel('probody');
                        var proName = l;
                        var areaId = '';
                        
                        for(var g in proList[m][l]){
                            areaId = proList[m][l][g].areaId;
             
                        }
                        var proTxt = proBody
                        .replace(/\{proname\}/g,proName)
                        .replace(/\{proid\}/g,pid+ccount+pcount)
                        .replace(/\{proval\}/g,areaId)

                        ;
                        pcount ++;

                        var cityList = proList[m][l];
                        var allcityTxt = '';
                        var cityCount = 0;
                        var allcityCount = 0;
                        for(var s in cityList){

                            for(var g in cityList[s].cityList){
                                var cityBody = getmodel('citybody');
                                var cityName = cityList[s].cityList[g].ctitle;
                                var cityCode = cityList[s].cityList[g].careaId;

                                var attTxt = '';
                                var disEle;

                                var disabledCity = myinitData.selected;
                                

                                for(var ci in disabledCity){
                                    if(cityName == disabledCity[ci]){
                                        attTxt = 'checked="checked"';
                                        cityCount++;

                                    }
                                }

                                // var disTxt = 'disabled="disabled" checked="checked"';
                                var cityTxt = cityBody
                                .replace(/\{cityname\}/g,cityName)
                                .replace(/\{citycode\}/g,cityCode)
                                .replace(/\{cityid\}/g,cid+ccount)
                                .replace(/\{attr\}/g,attTxt)
                                ;
                                allcityTxt += cityTxt;
                                ccount++;
                                allcityCount++;

                            }
                        }

                        
                        var cityCountNum = cityCount == 0 ? '' : '('+cityCount+')';
                        var proattrtxt =  cityCount == allcityCount ? 'checked="checked"' : '';
                        allProTxt += proTxt.replace(/\{cityList\}/g,allcityTxt).replace(/\{cknum\}/g,cityCountNum).replace(/\{proattr\}/g,proattrtxt);
                    }
                }
                allzoneTxt += curTxt.replace(/\{proList\}/g,allProTxt);
            }
        }
//      console.log(allzoneTxt);
        if($('.address_madal').length == 0){
            var contain = '<div class="address_modal_bg"></div><div class="address_madal"><div class="address_title"><span>选择区域</span><a href="javascript:;" class="address_closebtn">关闭</a></div><ul id="J_CityList">'+allzoneTxt+'</ul><div class="confirm_btn"><a href="javascript:;" class="confirmbtn">确定</a></div></div>'
            ele.append(contain);
            $('.confirmbtn').bind('click',function(){
            	$('.address_modal_bg').remove();
            	if(typeof(callback) == 'function') callback();
            })
            
        }
    }

    function getlibody(){
        /*
            <li>
                <div class=" dcity clearfix">
                    <div class="ecity gcity">
                        <span class="group-label"><input type="checkbox" value="" class="J_Group" id="J_Group_0">
                            <label for="J_Group_0">{zonename}</label></span>
                    </div>
                    <div class="province-list">
                        {proList}
                    </div>
                </div>
            </li>
        */
    }

    function probody(){
        /*
            <div class="ecity">
                <span class="gareas"><input type="checkbox" value="{proval}" id="{proid}" {proattr} class="J_Province">
                    <label for="{proid}">{proname}</label><span class="check_num">{cknum}</span><i class="allowdown"></i></span>
                <div class="citys">
                    {cityList}
                    <p style="text-align:right;"><input type="button" value="关闭" class="close_button"></p>
                </div>
            </div>
        */
    }

    function citybody(){
        /*
            <span class="areas"><input {attr} type="checkbox" value="{citycode}" id="{cityid}" class="J_City">
            <label for="{cityid}">{cityname}</label></span>
        */
    }

    

    function getVal(){
    	 var res = [];
         var pValarr= [];
         $('#J_CityList li').each(function(){
             var g = $(this).find('.J_Group');
             var _this = this;
             if(!g.prop('checked')){
                 $(this).find('.J_Province').each(function(){
                     
                     if(!$(this).prop('checked')){

                         $(this).parentsUntil('.ecity').parent().find('.J_City').each(function(){
                                 if($(this).prop('checked')){
                                     var p = $(this).parent().find('label').html();
                                     res.push(p);

                                     var pval = $(this).parent().find('input').val();
                                     pValarr.push(pval);
                                     // console.log(p);
                                 }
                         });
                     }else{
                    	 var mypar = $(this).parentsUntil('.ecity').parent();
                    	 var cityArr = [];
                    	 mypar.find('.citys span').each(function(){
                    		 var cityVal = $(this).find('input').val();
                    		 cityArr.push(cityVal);
                    	 });
                    	 
                         var l = $(this).parent().find('label').html();
//                         
                         res.push(l);
//
//                       var lval = $(this).parent().find('input').val();
                         pValarr.push(cityArr);
                     }
                 });
             }else{
            	 var zonePar = g.parentsUntil('.dcity').parent();
            	 var zoneCityArr = [];
            	 zonePar.find('.province-list .ecity').each(function(){
            		 var provinces = $(this).find('.citys span');
            		 provinces.each(function(){
            			 var getcitys = $(this).find('input').val();
            			 console.log(getcitys);
            			 zoneCityArr.push(getcitys);
            		 })
            	 });
            	 
//            	 console.log(zoneCityArr);
            	 
                 var v = g.parent().find('label').html();
                 res.push(v);
//               var vval = g.parent().find('input').val();
                 pValarr.push(zoneCityArr);
             }
         });

         var resArr = {
             'strArr' : res,
             'valArr' : pValarr
         }

         return resArr;
        // console.log();
    }

    

    var model = {
        prolist : getlibody,
        probody : probody,
        citybody : citybody
    };

    /**
     * 积分商品编辑：
     * 配送城市下拉框
     */
    $('#deliveryCity').bind('focus',function(){
//        var _glist = getmodel('term');
//        if($('.address_madal').length == 0){
//        $('.deliveryCity').append(_glist);
//  }
    	var obj = $("#deliveryCity").val();
    	var arrObj = obj.split(",");
    	var myinitData = {
            "selected" : arrObj
        };
    	getZoneData($('.deliveryCity'),function(){
    		var res = getVal();
    		$("#deliveryCity").val(res.strArr);
    		$("#deliveryCityId").val(res.valArr);
     	    $('.address_madal').remove();
    	},myinitData);
    });
    
    /**
     * 积分商品编辑：
     * 销售城市下拉框
     */
    $('#saleCity').bind('focus',function(){
//      var _glist = getmodel('term');
//      if($('.address_madal').length == 0){
//          $('.deliveryCity').append(_glist);
//   }
    	var obj = $("#saleCity").val();
    	var arrObj = obj.split(",");
    	var myinitData = {
            "selected" : arrObj
        };
    	getZoneData($('.saleCity'),function(){
    		var res = getVal();
    		$('#saleCity').val(res.strArr)
    		$('#saleCityId').val(res.valArr);
     	   	$('.address_madal').remove();
    	},myinitData);
    });
    
    
    /**
     * 运费模板的城市选择
     */
    $("table").on('click','.addbtn',function(){
    	var _this   = this;
    	var obj = $(_this).parentsUntil('.cityPostageGroup').parent().find(".postlstAreaR").val() + "";
    	console.log(obj);
    	var arrObj = obj.split(",");
    	var myinitData = {
            "selected" : arrObj
        };
    	getZoneData($(this).parentsUntil('.cityPostageGroup').parent(),function(){
    		var res = getVal();
    		$(_this).parent().find('span').html(res.strArr.join(","));
    		$(_this).parentsUntil('.cityPostageGroup').parent().find('.postaddress').val(res.valArr);
    		$('.address_madal').remove();
    	},myinitData);
	});
    
    $("table").on('click','.chanceseladdress',function(){
    	var _this = this;
    	var obj = $(this).parentsUntil('.postageFreeRuleGroup').parent().parent().find(".postfreelstAreaF").val() + "";
    	var arrObj = obj.split(",");
    	var myinitData = {
            "selected" : arrObj
        };
    	getZoneData($(this).parentsUntil('.postageFreeRuleGroup').parent(),function(){
    		var res = getVal();
    		$(_this).parentsUntil('.postageFreeRuleGroup').parent().find('.chanceseladdress').parent().find('span').html(res.strArr.join(","));
    		$(_this).parentsUntil('.postageFreeRuleGroup').parent().find('.postaddress').val(res.valArr);
    		$('.address_madal').remove();
    	},myinitData);
	});
    

    $('body').on('click','.address_closebtn',function(){
    	$('.address_madal').remove();
    	$('.address_modal_bg').remove();
    });


    function getmodel(m){
        var r=/\/\*([\S\s]*?)\*\//m,
        m=r.exec(model[m].toString());
        return m&&m[1]||m;
    }
	
});
