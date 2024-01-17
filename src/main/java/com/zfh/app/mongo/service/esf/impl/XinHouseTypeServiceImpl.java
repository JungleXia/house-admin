package com.zfh.app.mongo.service.esf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.XinHouseType;
import com.zfh.app.mongo.model.HouseTagModel;
import com.zfh.app.mongo.model.XinHouseTypeModel;
import com.zfh.app.mongo.service.esf.XinHouseTypeService;

@Service
public class XinHouseTypeServiceImpl extends BaseMongoServiceImpl<XinHouseType> implements XinHouseTypeService{

	@Override
	protected Class<XinHouseType> getEntityClass() {
		// TODO Auto-generated method stub
		return XinHouseType.class;
	}

	@Override
	public List<XinHouseTypeModel> findByProjectId(String projectId, int room) {
		List<XinHouseType> list = new ArrayList<XinHouseType>();
		List<XinHouseTypeModel> models = new ArrayList<XinHouseTypeModel>();
		if (room == 0) {
			list = findByProp("projectId", projectId);
		} else {
			list = findByProps(new String[]{"projectId", "room"}, new Object[]{projectId, room});
		}
		
		for (XinHouseType xinHouseType : list) {
			List<HouseTagModel> tagsList= xinHouseType.getTags();
			if (tagsList != null && tagsList.size() > 1) {
				xinHouseType.setTags(tagsList.subList(0, 1));
			}
			XinHouseTypeModel model = new XinHouseTypeModel();
			BeanUtils.copyProperties(xinHouseType, model);
			model.setHouseId(xinHouseType.getId().toString());
			models.add(model);
		}
		return models;
	}

}
