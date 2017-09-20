function getArea()
		{	
			$("#province,#city,#area").empty()
			$("#province").append("<option selected='true'>请选择区域</option>");
			$("#city").append("<option selected='true'>请选择区域</option>");
			$("#area").append("<option selected='true'>请选择区域</option>");
			$.ajax({
				url : "common/area/provinceList.jhtml",
			dataType:"json",
			method : "Get",
			success:function(data)
			{ 
			for(var i=0;i<data.length;i++){			
				$("#province").append("<option value="+data[i].areaId+">"+data[i].title+"</option>");
			}
			}
		});
		
		$("#province").on("change",function(){
			
			$.ajax({
					url:"common/area/cityList.jhtml?areaId="+($(this).val()),
					dataType:"json",
					method : "Get", 
					success:function(data){
						$("#city").empty();
						$("#city").append('<option value="0">请选择地区</option>');
						$("#area").empty();
						$("#area").append('<option value="0">请选择地区</option>');
						for(var i=0;i<data.length;i++)
						{
							$("#city").append("<option value="+data[i].areaId+">"+data[i].title+"</option>");				
						}
						 
					}
					
				   })
		});
		
		$("#city").on("change",function()
		{
			
			$.ajax(
			{ 
				url : "common/area/selectAreaList.jhtml?areaId="+($("#city").val()),
				dataType:"json",
				method : "Get", 
				success:function(data)
				{
					$("#area").empty();
					$("#area").append('<option value="0">请选择地区</option>');
					for(var i=0;i<data.length;i++)
					{
						
						$("#area").append("<option value="+data[i].areaId+">"+data[i].title+"</option>");
					} 
				} 
			});
		});
	}

	 
	$(function(){
		getArea();
		$("#province,#city").on("change",function()
		{
			$("#business").empty();
			$("#business").append('<option value="0">请先选择区域再选择商圈</option>');
		})
		$("#area").on("change",function()
		{
			$.ajax(
			{ 
				url : "common/business/BusinessList.jhtml?areaId="+($("#area").val()),
				dataType:"json",
				method : "Get", 
				success:function(data)
				{
					$("#business").empty();
					$("#business").append('<option value="0">请选择商圈</option>');
					for(var i=0;i<data.length;i++)
					{
						
						$("#business").append("<option value="+data[i].bid+">"+data[i].title+"</option>");
					} 
				} 
			});
		});
	})
	