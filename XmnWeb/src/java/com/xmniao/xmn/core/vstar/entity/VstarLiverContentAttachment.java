package com.xmniao.xmn.core.vstar.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.StringUtils;

public class VstarLiverContentAttachment extends BaseEntity {
	/**
	 * id
	 */
	private Long id;

	/**
	 * 图文教学ID(t_vstar_content)
	 */
	private Long contentId;

	/**
	 * 文件类型
	 */
	private String fileType;

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 文件路径
	 */
	private String fileUrl;

	/**
	 * 排序值
	 */
	private Integer sortVal;

	/**
	 * 有效状态，1有效，2无效
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private Long fileSize;

	private String fileSizeStr;

	public String getFileSizeStr() {
		if (fileSize == null) {
			return null;
		}
		return StringUtils.getPrintSize(fileSize);
	}

	public void setFileSizeStr(String fileSizeStr) {
		this.fileSizeStr = fileSizeStr;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * id
	 * 
	 * @return id id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * id
	 * 
	 * @param id
	 *            id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 图文教学ID(t_vstar_content)
	 * 
	 * @return content_id 图文教学ID(t_vstar_content)
	 */
	public Long getContentId() {
		return contentId;
	}

	/**
	 * 图文教学ID(t_vstar_content)
	 * 
	 * @param contentId
	 *            图文教学ID(t_vstar_content)
	 */
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	/**
	 * 文件类型
	 * 
	 * @return file_type 文件类型
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * 文件类型
	 * 
	 * @param fileType
	 *            文件类型
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType == null ? null : fileType.trim();
	}

	/**
	 * 文件名称
	 * 
	 * @return file_name 文件名称
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 文件名称
	 * 
	 * @param fileName
	 *            文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	/**
	 * 文件路径
	 * 
	 * @return file_url 文件路径
	 */
	public String getFileUrl() {
		return fileUrl;
	}

	/**
	 * 文件路径
	 * 
	 * @param fileUrl
	 *            文件路径
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl == null ? null : fileUrl.trim();
	}

	/**
	 * 排序值
	 * 
	 * @return sort_val 排序值
	 */
	public Integer getSortVal() {
		return sortVal;
	}

	/**
	 * 排序值
	 * 
	 * @param sortVal
	 *            排序值
	 */
	public void setSortVal(Integer sortVal) {
		this.sortVal = sortVal;
	}

	/**
	 * 有效状态，1有效，2无效
	 * 
	 * @return status 有效状态，1有效，2无效
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 有效状态，1有效，2无效
	 * 
	 * @param status
	 *            有效状态，1有效，2无效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 创建时间
	 * 
	 * @return create_time 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * 
	 * @return update_time 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}