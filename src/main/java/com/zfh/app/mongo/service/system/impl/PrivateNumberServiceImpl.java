package com.zfh.app.mongo.service.system.impl;

import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mysiteforme.admin.util.DateUtil;
import com.mysiteforme.admin.util.RandomUtil;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.PrivateNumber;
import com.zfh.app.mongo.model.AXBResponse;
import com.zfh.app.mongo.service.system.PrivateNumberService;

@Service("privateNumberService")
public class PrivateNumberServiceImpl extends BaseMongoServiceImpl<PrivateNumber> implements PrivateNumberService {

	private static final Log logger = LogFactory.getLog(PrivateNumberService.class.getName());

	@Override
	protected Class<PrivateNumber> getEntityClass() {
		// TODO Auto-generated method stub
		return PrivateNumber.class;
	}


	@Override
	public String bindAxB(String callerNum, String calleeNum, String houseId) {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(calleeNum) || StringUtils.isEmpty(calleeNum)) {
			logger.error("号码不能为空");
			return null;
		}
		if (StringUtils.equals(callerNum, calleeNum)) {
			logger.error("号码不能相同");
			return null;
		}
		if (!callerNum.startsWith("+86")) {
			callerNum = "+86" + callerNum;
		}

		if (!calleeNum.startsWith("+86")) {
			calleeNum = "+86" + calleeNum;
		}
		logger.info("User Phone: " + callerNum + " , Agent Phone: " + calleeNum);
		// 如果找到绑定此隐私号，如果未绑定则 修改第一个的隐私号绑定
		String privateNum = "";

		// 1、寻找A和B 绑定过的隐私号，进行更新
		PrivateNumber privateNumber = uniqueByProps(new String[] { "callerNum", "calleeNum", "expired" },
				new Object[] { callerNum, calleeNum, false });
		if (privateNumber != null) {
			long interval = DateUtil.secondsBetween(
					DateUtil.format(privateNumber.getExpiredTime(), DateUtil.DEFAULT_LONG_FORMATE), DateUtil.now());
			// 判断过期时间
			if (interval > -600) {
				updateBindAXB(privateNumber, callerNum, calleeNum, houseId);
			}
			return privateNumber.getRelationNum();
		}


		//2、 寻找满足 callerNum 和 calleeNum 都未绑定的一个隐私号，新增隐私号绑定
		logger.info("step:1");
		String[] numbers = AXBInterfaceDemoImpl.getInstance().relationNums;
		// 随机打乱
		RandomUtil.shuffle(numbers);
		if (numbers == null || (numbers != null && numbers.length == 0)) {
			return null;
		}
		for (int i = 0; i < numbers.length; i++) {
			String relationNum = numbers[i];
			privateNumber = uniqueByProps(new String[] { "callerNum", "relationNum", "expired" },
					new Object[] { callerNum, relationNum, false });
			PrivateNumber privateNumber2 = uniqueByProps(new String[] { "relationNum", "calleeNum", "expired" },
					new Object[] { relationNum, calleeNum, false });
			if (privateNumber == null && privateNumber2 == null) {
				logger.info("step:2");
				privateNum = bindAXBNew(relationNum, callerNum, calleeNum, houseId);
				if (StringUtils.isNotEmpty(privateNum)) {
					return privateNum;
				}
			}

		}
		// 3、如果不满足2， 修改B
		for (int i = 0; i < numbers.length; i++) {
			String relationNum = numbers[i];
			privateNumber = uniqueByProps(new String[] { "callerNum", "relationNum", "expired" },
					new Object[] { callerNum, relationNum, false });
			PrivateNumber privateNumber2 = uniqueByProps(new String[] { "relationNum", "calleeNum", "expired" },
					new Object[] { relationNum, calleeNum, false });
			if (privateNumber != null && privateNumber2 == null) {
				logger.info("step:3");
				privateNum = updateBindAXB(privateNumber, null, calleeNum, houseId);
				if (StringUtils.isNotEmpty(privateNum)) {
					return privateNum;
				}
			}
		}

		// 4、不让不满足3，B 都被绑定过，修改 A
		for (int i = 0; i < numbers.length; i++) {
			String relationNum = numbers[i];
			privateNumber = uniqueByProps(new String[] { "callerNum", "relationNum", "expired" },
					new Object[] { callerNum, relationNum, false });
			PrivateNumber privateNumber2 = uniqueByProps(new String[] { "relationNum", "calleeNum", "expired" },
					new Object[] { relationNum, calleeNum, false });
			if (privateNumber == null && privateNumber2 != null) {
				logger.info("step:4");
				privateNum = updateBindAXB(privateNumber2, callerNum, null, houseId);
				if (StringUtils.isNotEmpty(privateNum)) {
					return privateNum;
				}
			}
		}

		// 如果A、B都被绑定过，修改A、B 绑定第一个隐私号
		privateNumber = uniqueByProps(new String[] { "callerNum", "relationNum", "expired" },
				new Object[] { callerNum, numbers[0], false });
		PrivateNumber privateNumber2 = uniqueByProps(new String[] { "relationNum", "calleeNum", "expired" },
				new Object[] { numbers[0], calleeNum, false });
		if (privateNumber != null && privateNumber2 != null) {
			logger.info("step:5");
			privateNumber.setExpired(true);
			super.save(privateNumber);
			AXBInterfaceDemoImpl.getInstance().axbUnbindNumber(privateNumber.getSubscriptionId(), null);

			privateNumber2.setExpired(true);
			super.save(privateNumber2);
			AXBInterfaceDemoImpl.getInstance().axbUnbindNumber(privateNumber2.getSubscriptionId(), null);

			privateNum = bindAXBNew(numbers[0], callerNum, calleeNum, houseId);
			return privateNum;
		}

		return null;
	}

	private String bindAXBNew(String relationNum, String callerNum, String calleeNum, String houseId) {
		// 调用接口
		logger.info("new AXB:");
		String result = AXBInterfaceDemoImpl.getInstance().axbBindNumber(relationNum, callerNum, calleeNum);
		AXBResponse reponse = JSON.parseObject(result, AXBResponse.class);
		if (reponse != null && StringUtils.equals("0", reponse.getResultcode())) {

			PrivateNumber newPrivateNum = new PrivateNumber();
			newPrivateNum.setCalleeNum(calleeNum);
			newPrivateNum.setCallerNum(callerNum);
			newPrivateNum.setCreateTime(DateUtil.currentDateTime());
			newPrivateNum.setDuration(Integer.valueOf(reponse.getDuration()));
			newPrivateNum.setExpiredTime(DateUtil.format(
					DateUtils.addSeconds(DateUtil.now(), newPrivateNum.getDuration()), DateUtil.DEFAULT_LONG_FORMATE));
			newPrivateNum.setExpired(false);
			newPrivateNum.setRelationNum(relationNum);
			newPrivateNum.setResultcode(reponse.getResultcode());
			newPrivateNum.setResultdesc(reponse.getResultdesc());
			newPrivateNum.setSubscriptionId(reponse.getSubscriptionId());
			newPrivateNum.setHouseId(houseId);
			super.save(newPrivateNum);
			return newPrivateNum.getRelationNum();
		}
		return null;
	}

	/**
	 * 
	 * @param privateNum
	 * @param callerNum
	 *            主叫号码，不修改则不填
	 * @param calleeNum
	 *            被叫号码，不修改则不填
	 * @return 2019年8月12日下午4:25:40
	 */
	private String updateBindAXB(PrivateNumber privateNum, String callerNum, String calleeNum, String houseId) {
		// 旧的使之失效
		privateNum.setExpired(true);
		super.save(privateNum);
		logger.info("update AXB:");
		String result = AXBInterfaceDemoImpl.getInstance().axbModifyNumber(privateNum.getSubscriptionId(), callerNum,
				calleeNum);
		AXBResponse reponse = JSON.parseObject(result, AXBResponse.class);
		if (reponse != null && StringUtils.equals("0", reponse.getResultcode())) {
			if (StringUtils.isEmpty(callerNum)) {
				callerNum = privateNum.getCallerNum();
			}
			if (StringUtils.isEmpty(calleeNum)) {
				calleeNum = privateNum.getCalleeNum();
			}
			PrivateNumber newPrivateNum = new PrivateNumber();
			newPrivateNum.setCalleeNum(calleeNum);
			newPrivateNum.setCallerNum(callerNum);
			newPrivateNum.setCreateTime(DateUtil.currentDateTime());
			newPrivateNum.setDuration(7776000); //单位秒 7776000 表示90天，0 表示用不过期
			newPrivateNum.setExpiredTime(DateUtil.format(
					DateUtils.addSeconds(DateUtil.now(), newPrivateNum.getDuration()), DateUtil.DEFAULT_LONG_FORMATE));
			newPrivateNum.setExpired(false);
			newPrivateNum.setRelationNum(privateNum.getRelationNum());
			newPrivateNum.setResultcode(reponse.getResultcode());
			newPrivateNum.setResultdesc(reponse.getResultdesc());
			newPrivateNum.setSubscriptionId(privateNum.getSubscriptionId());
			newPrivateNum.setHouseId(houseId);
			super.save(newPrivateNum);
			return newPrivateNum.getRelationNum();
		}
		return null;
	}


	@Override
	public PrivateNumber findLastByPhoneNumber(String callerNum, String calleeNum, String relationNum) {
		List<PrivateNumber> list = findByProps(new String[]{"callerNum", "calleeNum", "relationNum", "expired"}, new Object[]{callerNum, calleeNum, relationNum, false}, "_id desc");
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
