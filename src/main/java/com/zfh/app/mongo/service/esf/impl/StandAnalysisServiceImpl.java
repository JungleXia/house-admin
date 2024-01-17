package com.zfh.app.mongo.service.esf.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mysiteforme.admin.util.DateUtil;
import com.mysiteforme.admin.util.VarianceUtil;
import com.zfh.app.core.BathUpdateOptions;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.House;
import com.zfh.app.mongo.entity.esf.StandAnalysis;
import com.zfh.app.mongo.entity.esf.StandAnalysisRecord;
import com.zfh.app.mongo.entity.esf.StandCommunity;
import com.zfh.app.mongo.entity.esf.StandUnitType;
import com.zfh.app.mongo.entity.esf.StandUnitTypeRecord;
import com.zfh.app.mongo.model.SameRoom;
import com.zfh.app.mongo.model.UnitTypeModel;
import com.zfh.app.mongo.service.esf.ErshoufangService;
import com.zfh.app.mongo.service.esf.HouseCodeMongoService;
import com.zfh.app.mongo.service.esf.HouseService;
import com.zfh.app.mongo.service.esf.StandAnalysisRecordService;
import com.zfh.app.mongo.service.esf.StandAnalysisService;
import com.zfh.app.mongo.service.esf.StandCommunityService;
import com.zfh.app.mongo.service.esf.StandUnitTypeRecordService;
import com.zfh.app.mongo.service.esf.StandUnitTypeService;

@Service
public class StandAnalysisServiceImpl extends BaseMongoServiceImpl<StandAnalysis> implements StandAnalysisService {
	private static final Log logger = LogFactory.getLog(StandAnalysisServiceImpl.class.getName());

	@Autowired
	StandCommunityService standCommunityService;

	@Autowired
	ErshoufangService ershoufangService;

	@Autowired
	HouseService houseService;
	
	@Autowired
	HouseCodeMongoService houseCodeService;

	@Autowired
	StandAnalysisRecordService standAnalysisRecordService;
	
	@Autowired
	StandUnitTypeService standUnitTypeService;
	
	@Autowired
	StandUnitTypeRecordService standUnitTypeRecordService;

	private final static Map<Integer, String> ROOM_TYPE = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;
		{
			put(1, "一室");
			put(2, "二室");
			put(3, "三室");
			put(4, "四室");
			put(5, "五室");
			put(6, "六室");
			put(7, "七室");
			put(8, "八室");
			put(9, "九室");
		}
	};

	private final static BigDecimal ONE_HUNDRED_TWENTY = BigDecimal.valueOf(120d);
	
	@Override
	protected Class<StandAnalysis> getEntityClass() {
		// TODO Auto-generated method stub
		return StandAnalysis.class;
	}

	@Override
	public void mulitDetection() {
		/**
		 *  setp 1. 使用多线程分析房源单价异常值
		 */
		// 线程池维护线程的最少数量
		int corePoolSize = 5;
		// 线程池维护线程的最大数量
		int maximumPoolSize = 10;
		// 线程池维护线程所允许的空闲时间
		long keepAliveTime = 10;
		// 线程池维护线程所允许的空闲时间的单位
		TimeUnit unit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(1000);
		// AbortPolicy 策略
		// 处理程序遭到拒绝将丢弃当前任务
		DiscardPolicy handler = new ThreadPoolExecutor.DiscardPolicy();
		// 初始化线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				taskQueue, handler);

		final int pageSize = 100;
		
		/**
		 * 遍历标准小区，每个标准小区单独分析
		 */
		Page<StandCommunity> page = standCommunityService.findPage(new PageRequest(0, pageSize));
		int totalPage = page.getTotalPages();
		final int tota = totalPage;
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			final int pageIndex = totalPage;
			threadPool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					List<StandCommunity> list = standCommunityService.findPage(new PageRequest(pageIndex, pageSize))
							.getContent();
					// 执行异常值检测
					logger.info("单价异常值检测，当前页倒数：" + pageIndex + " / " + tota);
					
					doDetection(list);
					
					logger.info("单价异常值检测，当前页倒数：" + pageIndex + " / " + tota);
				}

			}));
		}
		threadPool.shutdown();
	}
	
	@Override
	public void mulitAnalysis() {
		
		/**
		 *  setp 2. 使用多线程分析标准小区挂牌信息
		 */
		// 线程池维护线程的最少数量
		int corePoolSize = 5;
		// 线程池维护线程的最大数量
		int maximumPoolSize = 10;
		// 线程池维护线程所允许的空闲时间
		long keepAliveTime = 10;
		// 线程池维护线程所允许的空闲时间的单位
		TimeUnit unit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(1000);
		// AbortPolicy 策略
		// 处理程序遭到拒绝将丢弃当前任务
		DiscardPolicy handler = new ThreadPoolExecutor.DiscardPolicy();
		// 初始化线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				taskQueue, handler);

		final int pageSize = 100;
		
		/**
		 * 遍历标准小区，每个标准小区单独分析
		 */
		Page<StandCommunity> page = standCommunityService.findPage(new PageRequest(0, pageSize));
		int totalPage = page.getTotalPages();
		final int tota = totalPage;
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			final int pageIndex = totalPage;
			threadPool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					List<StandCommunity> list = standCommunityService.findPage(new PageRequest(pageIndex, pageSize))
							.getContent();
					// 执行分析
					logger.info("标准小区挂牌分析，当前页倒数：" + pageIndex + " / " + tota);
					
					doAnalysis(list);
					
					logger.info("end标准小区挂牌分析，当前页倒数：" + pageIndex + " / " + tota);
				}

			}));
		}
		threadPool.shutdown();

		// wait for thread all shutdown, after callback
		// try {
		// while (!threadPool.awaitTermination(30, TimeUnit.SECONDS)) {
		// // threalPool has not shutdown all
		// }
		// // after threadPool task
		// // ...continue
		// } catch (InterruptedException e) {
		// logger.error(e);
		// }

	}

	
	private void doDetection(List<StandCommunity> list) {
		String[] propsName = new String[]{"standId", "online"};
		String[] includeFields = new String[]{"unitPrice"};
		double[] data = null;
		Boolean[] result = null;
		List<BathUpdateOptions> options = null;
		for (StandCommunity stdcom : list) {
			options = new ArrayList<>();
			List<House> houseList = houseService.findByProps(propsName, new Object[]{stdcom.getStandId(), true}, includeFields, null);
			int size = houseList.size();
			data = new double[size];
			for (int i = 0; i < size; i++) {
				data[i] = houseList.get(i).getUnitPrice().doubleValue();
			}
			result = VarianceUtil.outlierDetection(data, 3);
			for (int j = 0; j < size; j++) {
				if (result[j] == true) {
					// 大于 3 个标准差
					logger.info("> 3σ: houseId" + houseList.get(j).getId());
					options.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(houseList.get(j).getId()))),
					Update.update("status", 30)
					.set("online", false),
					false,
					false));
				} else {
					// 正常
					options.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(houseList.get(j).getId()))),
					Update.update("status", 0)
					.set("online", true),
					false,
					false));
				}
			}
			if (options != null && options.size() > 0) {
				houseService.bathUpdate(options);
				ershoufangService.bathUpdate(options);
			}
		}
	}
	
	@Override
	public void doAnalysis(List<StandCommunity> list) {
		int total = 0, real = 0, fake = 0;
		for (StandCommunity stdcom : list) {
			// 对同标准小区下的二手房房源进行分析
			String standId = stdcom.getStandId();
			StandAnalysis analysis = getByStandId(standId);
			if (analysis == null) {
				analysis = new StandAnalysis();
				analysis.setStandId(standId);
				analysis.setStandName(stdcom.getBaseName());
				analysis.setDate(DateUtil.format(DateUtil.now()));
				analysis.setTime(DateUtil.currentDateTime());
			} else {
				// 备份上一次记录
				StandAnalysisRecord entity = new StandAnalysisRecord();
				BeanUtils.copyProperties(analysis, entity);
				entity.setTime(DateUtil.currentDateTime());
				standAnalysisRecordService.save(entity);
			}

			// 总数量 
			// 数量为0，不继续分析
			total = ershoufangService.countByCondition(new String[] { "standId", "online" }, new Object[] { standId, true });
			if (total < 1) {
				continue; 
			}
			logger.info("分析中，standId="+ standId + ", total="+total);
			
			// 真房源数量
			real = ershoufangService.countByCondition(new String[] { "standId", "verifyStatus", "online" },
					new Object[] { standId, true, true });
			// 假房源数量
			fake = total - real;
			analysis.setTotal(total);
			analysis.setReal(real);
			analysis.setFake(fake);
			try {
				// 户型分析
				countUnitTypeModel(standId, analysis);
			} catch (Exception e) {
				logger.error("标准小区户型分析错误，standId: " + standId);
				logger.error(e.getMessage(), e);
			}
			super.save(analysis);
		}
	}

	public StandAnalysis getByStandId(String standId) {
		if (StringUtils.isEmpty(standId)) {
			return null;
		}
		return uniqueByProp("standId", standId);
	}

	/**
	 * 户型分析
	 * @param standId
	 * @param analysis
	 * @return
	 * 2019年8月21日下午7:29:06
	 */
	public StandAnalysis countUnitTypeModel(String standId, StandAnalysis analysis) {
		// 同标准小区+同户型+真房源分析
		// 遍历户型
		// 计算挂牌数量、总价最低、均价最低、平均单价、笋盘数量
		List<UnitTypeModel> unitTypeList = new ArrayList<UnitTypeModel>();
		List<Object> coll = houseService.getDistinctRoom(standId);
		if (coll == null || (coll != null && coll.size() < 1)) {
			return null;
		}
		int count = 0;
		int discount = 0;
		House minTotalHouse = null;
		House minUnitHouse = null;

		BigDecimal minTotal = BigDecimal.valueOf(0.0);
		BigDecimal minUnit = BigDecimal.valueOf(0.0);
		BigDecimal averageUnit = BigDecimal.valueOf(0.0);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.ASC, "totalPrice"));
		PageRequest pageReq = new PageRequest(0, 1, new Sort(orders));
		List<Order> orders2 = new ArrayList<Order>();
		orders2.add(new Order(Direction.ASC, "unitPrice"));
		PageRequest pageReq2 = new PageRequest(0, 1, new Sort(orders2));

		// 遍历户型 room
		for (Object collItem : coll) {
			List<House> allHouseList = new ArrayList<>();
			List<House> othersHouseList = new ArrayList<>();
			
			// 同一标准小区，同一种户型， 真实房源做分析
			int room = Integer.valueOf(collItem.toString());
			int roomDiscount = 0;
			if (room == 0 && room > 9) {
				continue;
			}
			// 挂牌数
			count = houseService.countByCondition(
					new String[] { "standId", "room", "online" },
					new Object[] { standId, room, true });
			if (count <= 0) {
				continue;
			}
			
			// 计算平均价，把异常值剔除了
			averageUnit = houseService.getAveragePrice(standId, room, 0);
			if (averageUnit.compareTo(BigDecimal.ZERO) == 0) {
				logger.error("error zero standId="+ standId + ", room=" + room);
				continue;
			}
			
			// 最低总价
			minTotalHouse = houseService.findPage(pageReq, 
					new String[] { "standId", "room", "online", "special" },
					new Object[] { standId, room, true, false }, 
					"totalPrice asc").getContent().get(0);
			
			minTotal = minTotalHouse.getTotalPrice();
			minTotalHouse.setLowestTotal(true);
			minTotalHouse.setReco(true);
			houseService.save(minTotalHouse);// 保存总价最低
			
			// 最低单价
			minUnitHouse = houseService.findPage(pageReq2,
					new String[] { "standId", "room", "online", "special" },
					new Object[] { standId, room, true, false },
					"unitPrice asc").getContent().get(0);
			minUnit = minUnitHouse.getUnitPrice();
			minUnitHouse.setLowestUnit(true);
			minUnitHouse.setReco(true);
			houseService.save(minUnitHouse); // 保存均价最低
			
			SameRoom sameList = new SameRoom();
			sameList.setTitle(analysis.getStandName() + "·" + ROOM_TYPE.get(room));
			sameList.setMinTotal(minTotalHouse);
			sameList.setMinUnit(minUnitHouse);

			
			allHouseList = houseService.findByProps(
					new String[] { "standId", "room", "online" },
					new Object[] { standId, room, true},
					"totalPrice asc");
			
			int others = 0;
			for (House all : allHouseList) {
				if (all.getUnitPrice() == null) {
					// 价格有空的，需要特殊处理
					continue;
				}
				if (all.getUnitPrice().compareTo(averageUnit) < 0) {
					discount++;// 笋盘数量
					roomDiscount++;
				}
				all.setDiscountRate(all.getUnitPrice().divide(averageUnit, 2, BigDecimal.ROUND_HALF_DOWN));
				all.setAveragePrice(averageUnit);
				if (!all.getId().equals(minTotalHouse.getId()) && !all.getId().equals(minUnitHouse.getId())) {
					// 其他房源数据只给20个
					if (others < 30) {
						othersHouseList.add(all);
					}
					others++;
					all.setLowestTotal(false);
					all.setLowestUnit(false);
					all.setReco(false);
					if (!all.isSpecial() && all.getTotalPrice().compareTo(ONE_HUNDRED_TWENTY) <= 0) {
						all.setReco(true);
					}
					houseService.save(all); 
				}
			}
			sameList.setOthers(othersHouseList);
			UnitTypeModel unitType = new UnitTypeModel();
			
			StandUnitType sut = standUnitTypeService.findByStandIdAndRoom(standId, room);
			if (sut == null) {
				sut = new StandUnitType();
			} else {
				// record
				StandUnitTypeRecord record = new StandUnitTypeRecord();
				BeanUtils.copyProperties(sut, record);
				record.setId(null);
				record.setRefId(sut.getId());
				standUnitTypeRecordService.save(record);
			}
			sut.setStandId(standId);
			sut.setAvgUnit(averageUnit);
			sut.setGuapai(count);
			sut.setMinTotal(minTotal);
			sut.setMinUnit(minUnit);
			sut.setName(ROOM_TYPE.get(room));
			sut.setRoom(room);
			sut.setSameList(sameList);
			sut.setCreateTime(DateUtil.format(DateUtil.now(), DateUtil.DEFAULT_LONG_FORMATE));
			sut.setDataTime(DateUtil.now());
			sut.setDiscount(roomDiscount);
			standUnitTypeService.save(sut);
			BeanUtils.copyProperties(sut, unitType);

			unitTypeList.add(unitType);
		}
		
		analysis.setDiscount(discount);
		analysis.setUnitType(unitTypeList);
		
		return analysis;
	}

	@Override
	public int getHouseCount(String standId) {
		StandAnalysis analysis = uniqueByProp("standId", standId);
		if (analysis != null)
			return analysis.getReal(); // 真房源数量
		return 0;
	}

}
