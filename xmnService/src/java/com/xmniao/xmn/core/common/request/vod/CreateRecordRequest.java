package com.xmniao.xmn.core.common.request.vod;

import java.util.Date;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class CreateRecordRequest extends BaseRequest{
	
		
		@NotNull(message="直播记录ID不能为空")
		private String recordId;	//直播记录ID
		
//		@NotNull(message="开始时间不能为空")
//		private String startTime;
//		@NotNull(message="结束时间不能为空")
//		private String endTime;
		
		@NotNull(message="文件ID不能为空")
		private String fileId;
		public String getRecordId() {
			return recordId;
		}

		public void setRecordId(String recordId) {
			this.recordId = recordId;
		}

		public String getFileId() {
			return fileId;
		}

		public void setFileId(String fileId) {
			this.fileId = fileId;
		}


		
	
	
}
