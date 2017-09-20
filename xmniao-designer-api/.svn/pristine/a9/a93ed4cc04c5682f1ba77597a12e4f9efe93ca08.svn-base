function pieChat(options)
{
	var pieOption = {
		ele : options.ele,
		width : options.width,
		height : options.height,
		data : options.data,
		type : options.type ? options.type : 1
	}

	var clientWidth = document.body.clientWidth || document.documentElement.clientWidth;
	if(clientWidth > 420) clientWidth = 420;

	var pie=d3.layout.pie();//饼图布局 https://github.com/mbostock/d3/wiki/Pie-Layout
	//画圆
	if(pieOption.type == 1){
		var outerRadius=(clientWidth)/4;//外半径
		var innerRadius=50*(clientWidth/400);//内半径
	}else if(pieOption.type == 2){
		var outerRadius=(clientWidth)/3.5;//外半径
		var innerRadius=50*(clientWidth/350);//内半径
	}
	
	var marginTop = pieOption.data.dataset.length * 40;

	
	var arc=d3.svg.arc()
		.innerRadius(innerRadius)
		.outerRadius(outerRadius);

	var svg=d3.select(pieOption.ele)
		.append("svg")
		.attr("width",pieOption.width)
		.attr("height",pieOption.height);
		
	if(pieOption.type == 1){
		var arcs=svg.selectAll("g.arc")
			.data(pie(pieOption.data.dataset))
			.enter()
			.append("g")
			.attr("class","arc")
			.attr("transform","translate("+(outerRadius+25)+","+(outerRadius+20)+")");
			
	}else if(pieOption.type == 2){
		var arcs=svg.selectAll("g.arc")
			.data(pie(pieOption.data.dataset))
			.enter()
			.append("g")
			.attr("class","arc")
			.attr("transform","translate("+(clientWidth/2)+","+(outerRadius+marginTop+20)+")");

	}	


	var asacs=svg
		.append("text");

	arcs.append("path")
		.attr("fill",function(d,i){
			return "#fff";
		})
		.transition()
		.duration(1000)
		.attr("fill",function(d,i){
			return pieOption.data.color[i];
		})

		.attr("d",arc);

	arcs.append("text")
		.attr("transform",function(d){
			return "translate("+arc.centroid(d)+")";//定位文字到图形的中心
		})
		.attr("text-anchor","middle").attr("fill","#fff")//文字居中
		.text(function(d){
			return d.value+"%";
		})
	if(pieOption.type == 1){
		asacs.attr("transform",function(){
				return "translate("+(outerRadius+5)+","+(outerRadius+25)+")";//定位文字到图形的中心
			})
			.attr('fill','#999')
			.attr('style','font-size:20px;')
			.text('占比');
	}else if(pieOption.type == 2){
		asacs.attr("transform",function(){
				return "translate("+(clientWidth/2-15)+","+(outerRadius+marginTop+25)+")";//定位文字到图形的中心
			})
			.attr('fill','#999')
			.attr('style','font-size:20px;')
			.text('占比');
	}

	//画展示内容250 => 350
	//x => clientWidth
	if(pieOption.type == 1){
		var reX = (250 / 350) * clientWidth;
		var reY = -30;
		var reat = svg.selectAll("g.rat")
		.data(pieOption.data.title)
		.enter()
		.append("g")

		.attr("class","rat")
		.attr("transform",function(d,i){
			if(i%2 == 0){
				reX = (250 / 350) * clientWidth - 20;
				reY += 60;
			}else{
				reX = reX + 62;
			}
			return "translate("+reX+","+reY+")";
		});

	}else if(pieOption.type == 2){
		var reX = 100;
		var reY = -30;
		var reat = svg.selectAll("g.rat")
		.data(pieOption.data.title)
		.enter()
		.append("g")
		.attr("class","rat")
		.attr("transform",function(d,i){
			if(i%2 == 0){
				reX = clientWidth / 2 - 80;
				reY += 60;
			}else{
				reX = reX + 120;
			}
			return "translate("+reX+","+reY+")";
		});		
	}
	/*添加块*/
	reat
	.append("rect")
	.attr('width','25')
	.attr('height','25')
	.attr("fill","#fff")
	.transition()
	.duration(1000)
	.attr("fill",function(d,i){
		return pieOption.data.color[i];
	})
	;

	/*设置字体*/
	reat
	.append("text")
	.attr("transform",function(d,i){
		return "translate(12,42)";
	})
	.attr("style","font-size:14px;")
	.attr("fill","#fff")
	.transition()
	.duration(1000)
	.attr("text-anchor","middle")
	.attr("fill","#999")
	.text(function(d){
		return d;
	})
	/*设置百分比*/
	reat
	.append("text")
	.attr("transform",function(d,i){
		return "translate(12,18)";
	})
	.attr("style","font-size:9px;")
	.attr("fill","#fff")
	.transition()
	.duration(1000)
	.attr("text-anchor","middle")
	.attr("fill","#fff")
	.text(function(d,i){
		return pieOption.data.dataset[i]+"%";
	})
	
	


}

function piechatFullline(options){

	var pieOption = {
		ele : options.ele,
		width : options.width,
		height : options.height,
		data : options.data
	}

	var pieData = pieOption.data.dataset;
	var otherData = 100 - parseInt(pieData);
	pieData.push(otherData);

	var clientWidth = document.body.clientWidth || document.documentElement.clientWidth;
	if(clientWidth > 420) clientWidth = 420;

	var pie=d3.layout.pie();//饼图布局 https://github.com/mbostock/d3/wiki/Pie-Layout
	//画圆
	if(pieData[0] < 50){


		pie.startAngle(Math.PI);
		pie.endAngle(-Math.PI);
	}else{
		pie.startAngle(-Math.PI);
		pie.endAngle(Math.PI);
	}
	var outerRadius=(375)/4;//外半径
	var innerRadius=0;//内半径
	
	var center = clientWidth / 2;
	
	var marginTop = pieData.length * 40;

	
	var arc=d3.svg.arc()
		.innerRadius(innerRadius)
		.outerRadius(outerRadius);

	var svg=d3.select(pieOption.ele)
		.append("svg")
		.attr("width",pieOption.width)
		.attr("height",pieOption.height);
		
	
	var arcs=svg.selectAll("g.arc")
		.data(pie(pieData))
		.enter()
		.append("g")
		.attr("class","arc")
		.attr("transform","translate("+(center)+","+(outerRadius+20)+")");
	


	var asacs=svg
		.append("text");

	var asacs2=svg
		.append("text");

	arcs.append("path")
		.attr("fill",function(d,i){
			return pieOption.data.color[i];
		})

		.attr("d",arc);

	// arcs.append("text")
	// 	.attr("transform",function(d){
			
	// 		var distance = arc.centroid(d);
	// 		for(var i in distance){
	// 			if(i == 0){
	// 					distance[i] = distance[i] - 10;
	// 			}else{
	// 					distance[i] = distance[i] + 30;
	// 			}	
	// 		}
	// 		return "translate("+distance+")";//定位文字到图形的中心
	// 	})
	// 	.attr("text-anchor","middle").attr("fill","#fff")//文字居中
	// 	.text(function(d){
	// 		return d.value+"%";
	// 	})

	arcs.append("line").
	attr("x1",function(d){
		
		return -30;
	}).
	attr("y1",function(d){
		
		return -40;
	}).
	attr("x2",function(d){
		return -0;
	}).
	attr("y2",function(d){
		return 140;
	}).
	attr("style",function(d){
		// console.log(pieOption.data.dataset[0]);
		if(d.data != pieData[0]){
			return '';
		}
		return 'stroke:rgb(245,162,0);stroke-width:1';
	});

	arcs.append("line").
	attr("x1",function(){
		return 0;
	}).
	attr("y1",function(){
		return 140;
	}).
	attr("x2",function(){
		return -140;
	}).
	attr("y2",function(){
		return 140;
	}).attr("style",function(d){
		if(d.data != pieData[0]){
			return '';
		}
		return 'stroke:rgb(245,162,0);stroke-width:1';
	});

	arcs.append("text")
	.attr("transform",function(d){
		return "translate(-140,128)";//定位文字到图形的中心
	})
	.attr("text-anchor","left").attr("fill","#f5a200")//文字居中
	.text('店外消费  占'+pieData[0]+'%');

		asacs.attr("transform",function(){
			return "translate("+(center)+","+(outerRadius+12)+")";//定位文字到图形的中心
		})
		.attr('fill','#fff')
		.attr('style','font-size:20px;')
		.attr("text-anchor","middle")
		.text(pieOption.data.title[0]);

	asacs2.attr("transform",function(){
			return "translate("+(center)+","+(outerRadius+40)+")";//定位文字到图形的中心
		})
		.attr('fill','#fff')
		.attr("text-anchor","middle")
		.attr('style','font-size:20px;')
		.text(pieOption.data.title[1]);
	

	//画展示内容250 => 350
	//x => clientWidth

		// var reX = (250 / 350) * clientWidth;
		// var reY = -30;
		// var reat = svg.selectAll("g.rat")
		// .data(pieOption.data.title)
		// .enter()
		// .append("g")

		// .attr("class","rat")
		// .attr("transform",function(d,i){
		// 	if(i%2 == 0){
		// 		reX = (250 / 350) * clientWidth;
		// 		reY += 60;
		// 	}else{
		// 		reX = reX + 50;
		// 	}
		// 	return "translate("+reX+","+reY+")";
		// });

	

	// reat
	// .append("rect")
	// .attr('width','20')
	// .attr('height','20')
	// .attr("fill","#fff")
	// .transition()
	// .duration(1000)
	// .attr("fill",function(d,i){
	// 	return pieOption.data.color[i];
	// })
	// ;

	
	// reat
	// .append("text")
	// .attr("transform",function(d,i){
	// 	return "translate(8,38)";
	// })
	// .attr("style","font-size:14px;")
	// .attr("fill","#fff")
	// .transition()
	// .duration(1000)
	// .attr("text-anchor","middle")
	// .attr("fill","#999")
	// .text(function(d){
	// 	return d;
	// })


}


	function brokenLineChat(options){	

		var opts = {
			ele : options.ele,
			color : options.color,
			listname : options.listname,
			xBar : options.xBar,
			data : options.data,
			isUpdate:options.isUpdate
		}

		if(opts.isUpdate){
			d3.select('svg').remove();
		}

		var dataset=[];
		var lines=[]; //保存折线图对象
		var xMarks=[];
		var lineNames=[]; //保存系列名称
		var lineColor=opts.color;
		var clientWidth = document.body.clientWidth || document.documentElement.clientWidth;
		var w=clientWidth;
		var h=400;
		var padding=40;
		var currentLineNum=0;
		//用一个变量存储标题和副标题的高度，如果没有标题什么的，就为0
		var head_height=padding;

		//用一个变量计算底部的高度，如果不是多系列，就为0
		var foot_height=padding;
		//模拟数据
		initData();

		//判断是否多维数组，如果不是，则转为多维数组，这些处理是为了处理外部传递的参数设置的，现在数据标准，没什么用
		if(!(dataset[0] instanceof Array))
		{
		var tempArr=[];
		tempArr.push(dataset);
		dataset=tempArr;
		}
		//保存数组长度，也就是系列的个数
		currentLineNum=dataset.length;
		//图例的预留位置
		foot_height+=25;
		//定义画布
		var svg=d3.select(opts.ele)
		.append("svg")
		.attr("width",w)
		.attr("height",h);
		//添加背景
		svg.append("g")
		.append("rect")
		.attr("x",0)
		.attr("y",0)
		.attr("width",w)
		.attr("height",h)
		.style("fill","#FFF");

		maxdata=getMaxdata(dataset);

		//横坐标轴比例尺
		var xScale = d3.scale.linear()
		.domain([0,dataset[0].length - 1])
		.range([padding,w-padding]);

		//纵坐标轴比例尺
		var yScale = d3.scale.linear()
		.domain([0,maxdata])
		.range([h-foot_height,head_height]);
		//定义横轴网格线
		var xInner = d3.svg.axis()
		.scale(xScale)
		.tickSize(-(h-head_height-foot_height),0,0)
		.tickFormat("")
		.orient("bottom")
		.ticks(dataset[0].length);
		//添加横轴网格线
		var xInnerBar=svg.append("g")
		.attr("class","inner_line axis_ah")
		.attr("transform", "translate(15," + (h - padding - 25) + ")")
		.call(xInner);
		//定义纵轴网格线
		var yInner = d3.svg.axis()
		.scale(yScale)
		.tickSize(-(w-padding*2),0,0)
		.tickFormat("")
		.orient("left")
		.ticks(10);
		//添加纵轴网格线
		var yInnerBar=svg.append("g")
		.attr("class", "inner_line")
		.attr("transform", "translate("+(padding + 15)+",0)")
		.call(yInner);
		//定义横轴
		var xAxis = d3.svg.axis()
		.scale(xScale)
		.orient("bottom")
		.ticks(dataset[0].length);
		//添加横坐标轴
		var xBar=svg.append("g")
		.attr("class","axis")
		.attr("transform", "translate(15," + (h - foot_height) + ")")
		.call(xAxis);
		//通过编号获取对应的横轴标签
		xBar.selectAll("text")
		.text(function(d){return xMarks[d];});

		
		//定义纵轴
		var yAxis = d3.svg.axis()
		.scale(yScale)
		.orient("left")
		.tickFormat(function(d){
			if(d >= 10000){
				return (d / 10000).toFixed(1)+"万";
			}else if(d >= 1000){
				return (d / 1000).toFixed(1) + "千";
			}else{
				return d;
			}
			
		})
		.ticks(10);
		//添加纵轴
		var yBar=svg.append("g")
		.attr("class", "axis")
		.attr("transform", "translate("+(padding)+",0)")
		.call(yAxis);
		//添加图例
		var legend=svg.append("g");
		addLegend();
		//添加折线
		lines=[];
		for(i=0;i<currentLineNum;i++){
			var newLine=new CrystalLineObject();
			newLine.init(i);
			lines.push(newLine);
		}

		setTimeout(function(){
			drawChart();
		},500);

		//重新作图
		function drawChart()
		{
			var _duration=1000;
			getData();
			addLegend();
			//设置线条动画起始位置
			var lineObject=new CrystalLineObject();
			for(i=0;i<dataset.length;i++){
				if(i<currentLineNum){
					//对已有的线条做动画
					lineObject=lines[i];
					lineObject.movieBegin(i);
				}else{
					//如果现有线条不够，就加上一些
					var newLine=new CrystalLineObject();
					newLine.init(i);
					lines.push(newLine);
				}
			}
			//删除多余的线条，如果有的话
			if(dataset.length<currentLineNum)
			{
				for(i=dataset.length;i<currentLineNum;i++)
				{
					lineObject=lines[i];
					lineObject.remove();
				}
				lines.splice(dataset.length,currentLineNum-dataset.length);
			}
			// console.log(dataset);
			maxdata=getMaxdata(dataset);

			newLength=dataset[0].length;
			//横轴数据动画
			xScale.domain([0,newLength-1]);

			


			xAxis.scale(xScale).ticks(newLength);
			xBar.transition().duration(_duration).call(xAxis);
			xBar.selectAll("text").text(function(d){return xMarks[d];});
			xBar.selectAll("text")
			.attr('style',function(d,i){
				var length = opts.xBar.length;
				var a = length / 2;

				if(opts.xBar.length > 3){
					if(i!= 0 && i!=Math.floor(a) && i!= (opts.xBar.length - 1)){
						return "display:none;";
					}
				}else{
					return '';
				}
				
			}).attr("text-anchor","middle");
			xInner.scale(xScale).ticks(newLength);
			xInnerBar.transition().duration(_duration).call(xInner);
			//纵轴数据动画
			console.log(maxdata);
			yScale.domain([0,maxdata]);
			yBar.transition().duration(_duration).call(yAxis);
			yInnerBar.transition().duration(_duration).call(yInner);
			//开始线条动画
			for(i=0;i<lines.length;i++)
			{
			lineObject=lines[i];
			lineObject.reDraw(i,_duration);
			}
			currentLineNum=dataset.length;
			dataLength=newLength;
		}
		//定义折线类
		function CrystalLineObject()
		{
			this.group=null;
			this.path=null;
			this.oldData=[];
			this.init=function(id){
				var arr=dataset[id];
				this.group=svg.append("g");
				var line = d3.svg.line()
				.x(function(d,i){return xScale(i);})
				.y(function(d){return yScale(d);});
				//添加折线
				this.path=this.group.append("path")
				.attr("d",line(arr))
				.style("fill","none")
				.style("stroke-width",1)
				.style("stroke",lineColor[id])
				.style("stroke-opacity",0.9)
				.attr("transform","translate(15,0)");
				//添加系列的小圆点
				this.group.selectAll("circle")
				.data(arr)
				.enter()
				.append("circle")
				.attr("cx", function(d,i) {
				return xScale(i);
				})
				.attr("cy", function(d) {
				return yScale(d);
				})
				.attr("r",3)
				.attr("fill",lineColor[id])
				.attr("transform","translate(15,0)");
				this.oldData=arr;
			};
			//动画初始化方法
			this.movieBegin=function(id){
				var arr=dataset[i];
				//补足/删除路径
				var olddata=this.oldData;
				var line= d3.svg.line()
				.x(function(d,i){if(i>=olddata.length) return w-padding; else return xScale(i);})
				.y(function(d,i){if(i>=olddata.length) return h-foot_height; else return yScale(olddata[i]);});
				//路径初始化
				this.path.attr("d",line(arr));
				//截断旧数据
				var tempData=olddata.slice(0,arr.length);
				var circle=this.group.selectAll("circle").data(tempData);
				//删除多余的圆点
				circle.exit().remove();
				//圆点初始化，添加圆点,多出来的到右侧底部
				this.group.selectAll("circle")
				.data(arr)
				.enter()
				.append("circle")
				.attr("cx", function(d,i){
				if(i>=olddata.length) return w-padding; else return xScale(i);
				})
				.attr("cy",function(d,i){
				if(i>=olddata.length) return h-foot_height; else return yScale(d);
				})
				.attr("r",3)
				.attr("fill",lineColor[id]).attr('transform','translate(15,0)');
		
				this.oldData=arr;
			};
			//重绘加动画效果
			this.reDraw=function(id,_duration){
				var arr=dataset[i];
				var line = d3.svg.line()
				.x(function(d,i){return xScale(i);})
				.y(function(d){return yScale(d);});
				//路径动画
				this.path.transition().duration(_duration).attr("d",line(arr));
				//圆点动画
					this.group.selectAll("circle")
					.transition()
					.duration(_duration)
					.attr("cx", function(d,i) {
					return xScale(i);
					})
					.attr("cy", function(d) {
						return yScale(d);
					})
			};
			//从画布删除折线
			this.remove=function(){
				this.group.remove();
			};
		}
		//添加图例
		function addLegend()
		{
			var textGroup=legend.selectAll("text")
			.data(lineNames);
			textGroup.exit().remove();
			legend.selectAll("text")
			.data(lineNames)
			.enter()
			.append("text")
			.text(function(d){return d;})
			.attr("class","legend")
			.attr("x", function(d,i) {return i*100;})
			.attr("y",0)
			.attr("fill",function(d,i){ return lineColor[i];});
			var rectGroup=legend.selectAll("rect")
			.data(lineNames);
			rectGroup.exit().remove();
			legend.selectAll("rect")
			.data(lineNames)
			.enter()
			.append("circle")
			.attr("cx", function(d,i) {return i*100-20;})
			.attr("cy",-5)
			.attr("r",5)
			.attr("width",12)
			.attr("height",12)
			.attr("fill",function(d,i){ return lineColor[i];})
			.attr("text-anchor","middle");
			legend.attr("transform","translate("+((w-lineNames.length*100)/2 - 50)+","+(23)+")");
		}
		//产生随机数据

		function initData()
		{
			var lineNum=Math.round(Math.random()*10)%3+1;
			var dataNum=13;
			oldData=dataset;
			dataset=[];
			xMarks=[];
			lineNames=[];
			for(i=0;i<dataNum;i++)
			{

				xMarks.push((i * 2));
			}

			for(i=0;i<2;i++)
			{
				var tempArr=[];
				for(j=0;j<dataNum;j++)
				{
					tempArr.push(0);
				}
				dataset.push(tempArr);
			}
		}


		function getData()
		{
			var lineNum=Math.round(Math.random()*10)%3+1;
			var dataNum=13;
			oldData=dataset;
			dataset=[];
			xMarks=[];
			lineNames=[];
			xMarks = opts.xBar;

			for(var key in opts.data){
				lineNames.push(key);
				dataset.push(opts.data[key]);
			}

			// for(i=0;i<2;i++)
			// {
			// 	var tempArr=[];
			// 	for(j=0;j<dataNum;j++)
			// 	{
			// 		tempArr.push(Math.round(Math.random()*10000));
			// 	}
			// 	dataset.push(tempArr);
				
			// }
		}
		//取得多维数组最大值
		function getMaxdata(arr)
		{

			maxdata=0;
			console.log(arr);
			for(i=0;i<arr.length;i++)
			{
				maxdata=d3.max([maxdata,d3.max(arr[i])]);
			}
			return maxdata;
		}

	}