package com.zfh.app.mongo.service.esf;

import java.util.List;

import org.springframework.data.domain.Page;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.HousePhoto;

public interface HousePhotoService extends BaseMongoService<HousePhoto>{

	
	/**
	 * 批量下载图片
	 * 
	 * 2019年4月24日下午2:04:40
	 */
	public void download();
	
	public void upload(List<HousePhoto> photoList, int pageIndex);
	
	public Page<HousePhoto> findUndownload(int page, int size);
	
	public String findOSSUrl(String url, String dataFrom);
}
