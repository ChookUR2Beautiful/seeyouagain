package com.xmniao.xmn.core.base;

import java.io.Serializable;

/**
 * 
 * 项目名称：TravelingWeb
 * 
 * 类名称：ResultFile
 * 
 * 类描述： 文件上传 结果
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年9月10日 下午12:42:22
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class ResultFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;// 状态 1--成功 0--失败
	private String message; // 消息
	private String relativePath; // 相对路径
	private String relativeBreviaryPath; // 缩略图相对路径
	private Long imgId;// ID
    
	public String getRelativeBreviaryPath() {
		return relativeBreviaryPath;
	}

	public void setRelativeBreviaryPath(String relativeBreviaryPath) {
		this.relativeBreviaryPath = relativeBreviaryPath;
	}

	public ResultFile(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

	@Override
	public String toString() {
		return "ResultFile [status=" + status + ", message=" + message + ", relativePath=" + relativePath + ", imgId=" + imgId + "]";
	}

}
