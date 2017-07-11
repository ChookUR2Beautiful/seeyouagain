package com.xmn.saas.controller.api.v1.shop.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.shop.SellerPic;

/**
 * 
*      
* 类名称：ImagesPutRequest   
* 类描述：   商家图片修改
* 创建人：xiaoxiong   
* 创建时间：2016年9月28日 下午2:18:34   
* 修改人：xiaoxiong   
* 修改时间：2016年9月28日 下午2:18:34   
* 修改备注：   
* @version    
*
 */
public class ImagesPutRequest extends Request{
	
	/**
	 * 店铺logo图
	 */
	private String logo;
	
	/**
	 * 店铺封面图
	 */
	private String cover;
	
	/**
	 * 环境图JSON数组
	 */
	private String pics;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}
	
	public List<SellerPic> toSellerPic(){
		try {
			List<SellerPic> list=new ArrayList<>();
			//logo图片
			if(this.logo!=null&&this.logo.length()>0){
				SellerPic logPic=new SellerPic();
				logPic.setUrl(logo);
				logPic.setType(0);
				list.add(logPic);
			}
			//封面图
			if(this.cover!=null&&this.cover.length()>0){
				SellerPic cover=new SellerPic();
				cover.setUrl(this.cover);
				cover.setType(2);
				list.add(cover);
			}
			//添加商家环境图
			try {
				if(this.pics!=null&&this.pics.length()>0){
					String[] envirpic=pics.split(",");
					for(String pic:envirpic){
						SellerPic envir=new SellerPic();
						envir.setUrl(pic);
						envir.setType(1);
						list.add(envir);
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				return null;
			}
			
			return list;
		} catch (Exception e) {
			return null;
		}
		
	}
	

}
