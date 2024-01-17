package com.zfh.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.zfh.app.mongo.entity.esf.HousePhoto;
import com.zfh.app.mongo.service.esf.HousePhotoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHousePhotoService {

	private static final Logger logger = LoggerFactory.getLogger(TestHousePhotoService.class);

	@Autowired
	private HousePhotoService housePhotoService;

	@Test
	public void test() {
		Page<HousePhoto> page = housePhotoService.findUndownload(0, 10);
		housePhotoService.upload(page.getContent(), 1);
	}

}
