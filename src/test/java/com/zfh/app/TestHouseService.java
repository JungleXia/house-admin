package com.zfh.app;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zfh.app.mongo.entity.esf.Ershoufang;
import com.zfh.app.mongo.entity.esf.House;
import com.zfh.app.mongo.entity.esf.StandAnalysis;
import com.zfh.app.mongo.entity.esf.StandCommunity;
import com.zfh.app.mongo.entity.esf.StandUnitType;
import com.zfh.app.mongo.service.esf.ErshoufangService;
import com.zfh.app.mongo.service.esf.HouseService;
import com.zfh.app.mongo.service.esf.StandUnitTypeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestHouseService {

	@Autowired
	private ErshoufangService ershoufangService;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private StandUnitTypeService standUnitTypeService ;
	
	//@Test
	public void testCopy() {
		/**
		 * id: 5cac47415ffe62178ca1b99d, md5Url: 8143c3b13a618a292e5da97397310427
			id: 5cac47425ffe62178ca1b99f, md5Url: f727d862fda2c8ad9b42dd1a0342e184
			id: 5cac47435ffe622c44c45e4b, md5Url: e42a95181d9198699a1a9165e117c077
			id: 5cac47445ffe62178ca1b9a1, md5Url: f157b663375594d257d5306699d30959
			id: 5cac47445ffe62178ca1b9a3, md5Url: be060e79ae11d5275d7170a61ed52a22
			id: 5cac47455ffe62178ca1b9a5, md5Url: 60a418693ba6a03784fa34fcd5607c02
			id: 5cac47475ffe622c44c45e4f, md5Url: e3014eb11094606367414b8e42c4389a
			id: 5cac47485ffe62178ca1b9a9, md5Url: fdcf9dbcdddcb3955874687f6edd2009
			id: 5cac47495ffe622c44c45e51, md5Url: b2cd092de88cb1b59db93709da692d82
			id: 5cac47495ffe622c44c45e53, md5Url: c36c862ebb65ca37f7d735dd8a1391b6
		 */
		Page<Ershoufang> page = ershoufangService.findReal(0, 10);
		for (Ershoufang ershoufang : page.getContent()) {
			System.out.println("id: " + ershoufang.getId() + ", md5Url: " + ershoufang.getMd5Url());
		}
		houseService.doCopy(page.getContent());
		
		Page<House> housePage = houseService.findPage(new PageRequest(0, 10));
		for (House house : housePage) {
			System.out.println(JSON.toJSON(house));
		}
	}
	
//	@Test
	public void findCommunityEmpty() {
		Page<House> housePage = houseService.findCommunityEnpty(0, 10);
		System.err.println("totalPage" + housePage.getTotalPages());
		List<House> list  = housePage.getContent();
		String[] ids = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ids[i] = list.get(i).getId();
		}
		List<Ershoufang> esfList = ershoufangService.findFieldsByIds(ids, new String[]{"community", "dataFrom"});
		for (Ershoufang ershoufang : esfList) {
			System.out.println(JSON.toJSONString(ershoufang));
		}
	}
	
	@Test
	public void getAveragePrice() {
		Page<StandUnitType> page = standUnitTypeService.findPage(new PageRequest(0, 100));
		for (int i = 0; i < page.getContent().size(); i++) {
			StandUnitType st = page.getContent().get(i);
			System.out.println("=====" + i);
			System.err.println(JSON.toJSON(st));
			houseService.getAveragePrice(st.getStandId(), st.getRoom(), 0);
		}
	}
}
