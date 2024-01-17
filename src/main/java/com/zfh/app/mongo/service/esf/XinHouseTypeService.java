package com.zfh.app.mongo.service.esf;

import java.util.List;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.XinHouseType;
import com.zfh.app.mongo.model.XinHouseTypeModel;

public interface XinHouseTypeService extends BaseMongoService<XinHouseType> {

	/**
	 * 此方法返回的标签只有一个
	 * @param projectId
	 * @param room
	 * @return
	 * 2019年9月16日下午2:20:02
	 */
	public List<XinHouseTypeModel> findByProjectId(String projectId, int room);
}
