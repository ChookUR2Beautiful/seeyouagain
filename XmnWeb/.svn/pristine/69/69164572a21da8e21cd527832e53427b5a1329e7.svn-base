var sellerModel = {
	title : "全部商家信息",
	totalTitle : "商家",
	form : "sellerFromId",
	checkbox : true,
	backButton : true,
	addBtn : "addSellerBto",
	handleColumn : {
		title : "操作",
		name : "sellerid",
		handlePermissions : ["sx","xx","setFlatAgio","isFirst"],//需要用到checkbox
		queryPermissions : ["xg", "ck"],//不需要用到checkbox
		column : [{
					title : "修改",
					href : "businessman/seller/update/init.jhtml",
					param : ["sellerid"],
					permissions : "xg"
				}, {
					title : "查看",
					href : "businessman/seller/getInit.jhtml",
					param : ["sellerid"],
					permissions : "ck"
				}]
	},
	columns : [{
				title : "商家编号",
				name : "sellerid",
				width : "120px"
			}, {
				title : "加入时间",
				name : "signdate",
				width : "50px",
				leng:8
			}, {
				title : "商家名称",
				name : "sellername",
				width : "50px",
				permissions : "order",
				isA : true,
				a : {
					must:true,
					href : "businessman/seller/initOrder.jhtml",
					param : ["sellerid"]
				},
				leng:8
			}, {
				title : "商家等级",
				name : "sellerGradeStr",
				width : "50px"
			}, {
				title : "商家手机号",
				name : "phoneid",
				width : "50px",
				leng:8
			}, {
				title : "所属行业",
				name : "typename",
				width : "50px"
			}, {
				title : "连锁店",
				name : "lssellername",
				width : "50px"
			}, {
				title : "账号",
				width : "50px",
				isA : true,
				a : {
					href : "businessman/sellerAccount/init.jhtml",
					param : ["sellerid"]
				},
				permissions : "zh",
				customMethod : function(value, data) {
					return (data.status == 3 ? value : "-");
				}
			}, {
				title : "折扣",
				isA : true,
				a : {
					href : "businessman/sellerAgio/init.jhtml",
					param : ["sellerid"]
				},
				width : "50px",
				permissions : "zk",
				customMethod : function(value, data) {
					return (data.status == 3 ? value : "-");
				}
			},{
				title : "平台补贴折(%)",
				name : "flatAgio",
				width : "50px",
				isZeroShow:true,
				isPercent:true
			}, {
				title : "营销信息",
				width : "50px",
				isA : true,
				a : {
					href : "businessman/sellerMarketing/init.jhtml",
					param : ["sellerid","sellerGrade","isonline"]
				},
				permissions : "yx",
				customMethod : function(value, data) {
					return (data.status == 3 ? value : "-");
				}
			}, {
				title : "钱包",
				width : "50px",
				isA : true,
				a : {
					modal : "businessman/seller/viewWallet.jhtml",
					param : ["sellerid"],
					data_position:"200px"
			
				},
				permissions : "wallet",
				customMethod : function(value, data) {
					return (data.status == 3 ? value : "-");
				}
			}, {
				title : "地址",
				name : "address",
				width : "50px",
				leng:8
			}, {
				title : "区域",
				name : "title",
				width : "50px",
				leng:8
			}, {
				title : "商圈",
				name : "btitle",
				width : "50px",
				leng:8	
			}, {
				title : "归属合作商",
				name : "corporate",
				width : "50px",
				leng:8
			}, {
				title : "归属业务员",
				name : "salesman",
				width : "50px"
			}, {
				title : "审核状态",
				name : "statusText",
				width : "50px",
					isA : true,
					a : {
						modal : "businessman/seller/init/examineInfo.jhtml",
						param : ["sellerid"],
						data_position:"200px"
					},
					customMethod : function(value, data) {
						value=data.statusText+value.substring(0,(value.indexOf(">")+1))+"</br>[原因]</a>";
						if(undefined==data.statusText){return "-";}
						return (data.status==2 ? value:data.statusText);
					},
					permissions : "zh"

			}, {
				title : "上线状态",
				name : "isonlineText",
				width : "50px",
				isA : true,
				a : {
					modal : "businessman/seller/init/examineInfo.jhtml",
					param : ["sellerid","isonline"],
					data_position:"200px"
				},
				customMethod : function(value, data) {
					value=data.isonlineText+value.substring(0,(value.indexOf(">")+1))+"</br>[原因]</a>";
					if(undefined==data.isonlineText){return "-";}
					return (data.isonline==3 ? value:data.isonlineText);
				},
				permissions : "zh"
			},{
				title : "上下线时间",
				name : "dateOperate",
				width : "50px"
			},{
				title : "是否开启首次",
				name : "isFirstText",
				width : "50px",
				leng:8
			},{
				title : "是否开通点菜",
				name : "isOpenBookingText",
				customMethod : function(value,data){
					if(data.category == 1){
						console.log(data.isOpenBookingText);
						return data.isOpenBookingText!=undefined ? data.isOpenBookingText : "-";
					}else{
						return "-";
					}
				}
			},{
				title : "分类",
				name : "foodClassNum",
				isA : true,
				a : {
					href : "businessman/seller/food/class/add/init.jhtml",
					param : ["sellerid"]
				},
				customMethod : function(value, data) {
					var result;
					if(data.category == 1 && data.isOpenBooking==1){
						if(data.foodClassNum != undefined && data.foodClassNum === 0){
							result='<a href="javascript:void();" data-position="" data-type="ajax" data-url="businessman/seller/food/class/add/init.jhtml?sellerid='+data.sellerid+'" data-toggle="modal">添加分类</a>';
						}else{
							result = value.replace(/businessman\/seller\/food\/class\/add\/init.jhtml/,"businessman/seller/food/class/init.jhtml");
						}
					}else{
						result = "-";
					}
					
					return result;
				},
				permissions : "foodClass"
			},{
				title : "菜品数量",
				name : "foodNum",
				isA : true,
				a : {
					href : "businessman/seller/food/add/init.jhtml",
					param : ["sellerid"],
				},
				customMethod : function(value, data) {
					var result;
					if(data.category == 1 &&  data.isOpenBooking==1){
						if(data.foodNum != undefined &&data.foodNum === 0){
							if(data.foodClassNum!= undefined && data.foodClassNum > 0){//商家菜品分类存在
								result='<a href="javascript:void();" data-position="" data-type="ajax" data-url="businessman/seller/food/add/init.jhtml?sellerid='+data.sellerid+'" data-toggle="modal">添加菜品</a>';
							}else{////商家菜品分类不存在
								result = "<a href='javascript:showWarningWindow("+'"warning","请先添加分类！");'+"'>添加菜品</a>";
							}
						}else{
							result = value.replace(/businessman\/seller\/food\/add\/init.jhtml/,"businessman/seller/food/init.jhtml");
						}
					}else{
						result = "-";
					}
					
					return result;
				},
				permissions : "food"
			},{
				title : "意见",
				name : "examineinfo",
				width : "50px",
				leng:8
			}],
	permissions : permissions
}