$(function() {
	
	$.ajax({
		type : 'post',
		url : 'fresh/sale/orderCount.jhtml' + '?t=' + Math.random(),
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			var numArray=new Array();//总单列表
			var orderSum=0;	//总单数
			var wareNumArray=new Array();//总件数列表
			var wareNumSum=0;	//总件数
			var moneyArray=new Array();//总成交额列表
			var moneySum=0;	//总成交额
			$.each(data,function(i,item){
				orderSum+=item.num
				var numArr=new Array();
				numArr.push(new Date(item.date).getTime());
				numArr.push(item.num); 
				numArray.push(numArr);
				wareNumSum+=item.wareNum
				var wareNumArr=new Array();
				wareNumArr.push(new Date(item.date).getTime());
				wareNumArr.push(item.wareNum);
				wareNumArray.push(wareNumArr);
				moneySum+=item.money
				var moneyArr=new Array();
				moneyArr.push(new Date(item.date).getTime());
				moneyArr.push(item.money);
				moneyArray.push(moneyArr);
			});
			$("#order_sum").text(orderSum);
			$("#order_wareNum").text(wareNumSum);
			$("#order_money").text(moneySum);
			Highcharts.chart('container', {
				chart : {
					type : 'spline'
				},
				title : {
					text : ''
				},
				subtitle : {
					text : ''
				},
				xAxis : {
					type : 'datetime',
					dateTimeLabelFormats : { // don't display the dummy year
						month : "%B %Y",
						day : "%A, %b %e, %Y",
					},
					title : {
						text : ''
					},
					labels : {
						formatter : function() {
							var date = new Date(this.value);
							return (date.getMonth()+1)+ '/' + date.getDate();
						}
					}
				},
				yAxis : {
					title : {
						text : ''
					},
					min : 0
				},
				tooltip : {
					headerFormat : '',
					pointFormat : '{point.y}'
				},

				plotOptions : {
					spline : {
						marker : {
							enabled : true
						}
					}
				},

				series : [ {
					name : '单位(单)',
					// Define the data points. All series have a dummy year
					// of 1970/71 in order to be compared on the same x axis. Note
					// that in JavaScript, months start at 0 for January, 1 for February etc.
					data : numArray
				}, ]
			});
			Highcharts.chart('container1', {
				chart : {
					type : 'spline'
				},
				title : {
					text : ''
				},
				subtitle : {
					text : ''
				},
				xAxis : {
					type : 'datetime',
					dateTimeLabelFormats : { // don't display the dummy year
						month : "%B %Y",
						day : "%A, %b %e, %Y",
					},
					title : {
						text : ''
					},
					labels : {
						formatter : function() {
							var date = new Date(this.value);
							return (date.getMonth()+1)+ '/' + date.getDate();
						}
					}
				},
				yAxis : {
					title : {
						text : ''
					},
					min : 0
				},
				tooltip : {
					headerFormat : '',
					pointFormat : '{point.y}'
				},

				plotOptions : {
					spline : {
						marker : {
							enabled : true
						}
					}
				},

				series : [ {
					name : '单位(件)',
					// Define the data points. All series have a dummy year
					// of 1970/71 in order to be compared on the same x axis. Note
					// that in JavaScript, months start at 0 for January, 1 for February etc.
					data : wareNumArray
				}, ]
			});
			Highcharts.chart('container2', {
				chart : {
					type : 'spline'
				},
				title : {
					text : ''
				},
				subtitle : {
					text : ''
				},
				xAxis : {
					type : 'datetime',
					dateTimeLabelFormats : { // don't display the dummy year
						month : "%B %Y",
						day : "%A, %b %e, %Y",
					},
					title : {
						text : ''
					},
					labels : {
						formatter : function() {
							var date = new Date(this.value);
							return (date.getMonth()+1)+ '/' + date.getDate();
						}
					}
				},
				yAxis : {
					title : {
						text : ''
					},
					min : 0
				},
				tooltip : {
					headerFormat : '',
					pointFormat : '{point.y}'
				},
				
				plotOptions : {
					spline : {
						marker : {
							enabled : true
						}
					}
				},
				
				series : [ {
					name : '单位(元)',
					// Define the data points. All series have a dummy year
					// of 1970/71 in order to be compared on the same x axis. Note
					// that in JavaScript, months start at 0 for January, 1 for February etc.
					data : moneyArray
				}, ]
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
		}
	});
	
		
	});