var topicModel = {
		title : "成长记列表",
		totalTitle:"成长记",
		form : "searchForm",
		checkbox : true,
		backButton : true,
		addBtn : "addTopicBtn",
		handleColumn :{
					title : "操作",
					name : "id",
					handlePermissions : ["delTopic"],
					queryPermissions :["check","update"],
					column : [{
								title : "修改",
								href : "user_terminal/topic/update/init.jhtml",
								param : ["id"],
								permissions : "update"
							},

							{
								title : "查看",
								href : "user_terminal/topic/check/init.jhtml",
								param : ["id"],
								permissions : "check"
							}, {
								title : "删除",
								method : "remove",
								param : ["id"],
								permissions : "delTopic"
							}]
		},
		columns : [{
					title : "话题内容",
					name : "content",
					width : "10%",
					leng:8
				}, {
					title : "话题创建时间",
					name : "time",
					width : "10%"
				}, {
					title : "评论数量",
					name : "commentNum",
					width : "5%"
				}, {
					title : "点赞数",
					name : "praiseNum",
					width : "5%"
				}, {
					title : "话题是否显示",
					name : "status",
					width : "5%",
					customMethod : function(value,data) {
						var status = "不显示";
						if (value == 0) {
							status = "不显示";
						} else if (value == 1) {
							status = "显示";
						}
						return status;
					}
				}],
		permissions : permissions
	}