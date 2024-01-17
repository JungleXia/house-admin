package com.zfh.app.mongo.service.esf.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.XinAnalysis;
import com.zfh.app.mongo.entity.esf.XinHouseType;
import com.zfh.app.mongo.model.CostCompareModel;
import com.zfh.app.mongo.model.CostModel;
import com.zfh.app.mongo.service.esf.XinAnalysisService;
import com.zfh.app.mongo.service.esf.XinHouseTypeService;

@Service
public class XinAnalysisServiceImpl extends BaseMongoServiceImpl<XinAnalysis> implements XinAnalysisService{

	@Autowired
	private XinHouseTypeService xinHouseTypeService;
	
	@Override
	protected Class<XinAnalysis> getEntityClass() {
		// TODO Auto-generated method stub
		return XinAnalysis.class;
	}

	@Override
	public XinAnalysis findByHouseId(String houseId) {
		// TODO Auto-generated method stub
		XinAnalysis xinAnalysis = uniqueByProp("houseId", houseId);
		if (xinAnalysis == null) {
			xinAnalysis = createAnalysis(houseId);
		}
		return xinAnalysis;
	}

	public XinAnalysis createAnalysis(String houseId) {
		XinHouseType xinHouseType = xinHouseTypeService.getById(houseId);
		BigDecimal totalPrice = xinHouseType.getTotalPrice();
		BigDecimal deedtax = BigDecimal.ZERO;
		if (xinHouseType.getArea().compareTo(BigDecimal.valueOf(90)) < 0) {
			deedtax = totalPrice.multiply(new BigDecimal(0.01)).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		} else {
			deedtax = totalPrice.multiply(new BigDecimal(0.015)).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		}
		CostModel xinf = new CostModel();
		BigDecimal downpay = totalPrice.multiply(new BigDecimal(0.3)).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		xinf.setDownpay(downpay.toString());
		xinf.setDeedt(deedtax.toString());
		xinf.setVat("0");
		xinf.setTax("0");
		xinf.setRedt("0");
		xinf.setAgcfee("0");
		xinf.setTotal(downpay.add(deedtax).toString());
		CostModel esf = new CostModel();
		BigDecimal vat = totalPrice.multiply(new BigDecimal(0.0565)).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal tax = totalPrice.multiply(new BigDecimal(0.01)).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal redt = totalPrice.multiply(new BigDecimal(0.7 * 0.0008 * 20)).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal agc = totalPrice.multiply(new BigDecimal(0.03)).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		esf.setDownpay(downpay.toString());
		esf.setDeedt(deedtax.toString());
		esf.setVat(vat.toString());
		esf.setTax(tax.toString());
		esf.setRedt(redt.toString());
		esf.setAgcfee(agc.toString());
		esf.setTotal(downpay.add(vat).add(tax).add(redt).add(agc).toString());
		CostCompareModel compare = new CostCompareModel();
		compare.setXinf(xinf);
		compare.setEsf(esf);
		XinAnalysis analysis = new XinAnalysis();
		analysis.setHouseId(xinHouseType.getId());
		analysis.setReason("本房源为近期热卖新房，与您预算匹配");
		analysis.setCost(compare);
		analysis.setWorth("暂无");
		return super.save(analysis);
	}
}
