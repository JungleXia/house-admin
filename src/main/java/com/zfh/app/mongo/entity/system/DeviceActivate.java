package com.zfh.app.mongo.entity.system;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 每日激活统计
 * @author CB
 * 
 * @dateTime 2019年11月12日上午10:12:13
 */
@Document(collection = "user_device_activate")
public class DeviceActivate implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String client;
	
	private String clientKey;
	
	private String os;
	
	private String userCode;
	
	private String token;
	
	private String idfa;
	
	private String uuid;
	
	private String imei;
	
	private String md5imei;
	
	private String md5mac;
	
	private String android_id;
	
	private String createTime;
	
	private String createDate;
	
	private String adTime;		// 广告监测时间
	
	private String csite;   	//	 广告投放位置	 1:今日头条	 10001:西瓜视频	 30001:火山小视频	 40001:抖音

	private boolean allowLocate; // 是否允许定位
	private boolean allowDevice;// 是否允许获取设备信息
	private int nextfp;			// 操作换一批次数
	private int nextpg;			// 已注册用户加载更多房源（下一页）
	private int viewDetail;		// 查看详情页页数
	private boolean register;	// 是否注册
	private boolean modifysf;	// 是否修改首付 及比例
	private boolean modifyother;// 是否修改其他条件
	private String hujixgSel;	// 户籍+限购
	private int huji;			// 0 深户 ；1 非深户； 2未填写
	private boolean buylimit;	// 是否限购
	private boolean officeSel;	// 是否设置工作地点
	private int schoolSel;		// 学位选择
	private int xinoresf; 		// 新房二手房选择
	private int huxingSel; 		// 户型选择
	private int useTypeSel;		// 用途选择
	private int useAxb;			// 隐私电话点击次数
	private int downPayment;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getAndroid_id() {
		return android_id;
	}

	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMd5imei() {
		return md5imei;
	}

	public void setMd5imei(String md5imei) {
		this.md5imei = md5imei;
	}

	public String getMd5mac() {
		return md5mac;
	}

	public void setMd5mac(String md5mac) {
		this.md5mac = md5mac;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCsite() {
		return csite;
	}

	public void setCsite(String csite) {
		this.csite = csite;
	}

	public String getAdTime() {
		return adTime;
	}

	public void setAdTime(String adTime) {
		this.adTime = adTime;
	}

	public boolean isAllowLocate() {
		return allowLocate;
	}

	public void setAllowLocate(boolean allowLocate) {
		this.allowLocate = allowLocate;
	}

	public boolean isAllowDevice() {
		return allowDevice;
	}

	public void setAllowDevice(boolean allowDevice) {
		this.allowDevice = allowDevice;
	}

	public int getNextfp() {
		return nextfp;
	}

	public void setNextfp(int nextfp) {
		this.nextfp = nextfp;
	}

	public int getViewDetail() {
		return viewDetail;
	}

	public void setViewDetail(int viewDetail) {
		this.viewDetail = viewDetail;
	}

	public boolean isRegister() {
		return register;
	}

	public void setRegister(boolean register) {
		this.register = register;
	}

	public boolean isModifysf() {
		return modifysf;
	}

	public void setModifysf(boolean modifysf) {
		this.modifysf = modifysf;
	}

	public boolean isModifyother() {
		return modifyother;
	}

	public void setModifyother(boolean modifyother) {
		this.modifyother = modifyother;
	}

	public String getHujixgSel() {
		return hujixgSel;
	}

	public void setHujixgSel(String hujixgSel) {
		this.hujixgSel = hujixgSel;
	}

	public boolean isOfficeSel() {
		return officeSel;
	}

	public void setOfficeSel(boolean officeSel) {
		this.officeSel = officeSel;
	}

	public int getSchoolSel() {
		return schoolSel;
	}

	public void setSchoolSel(int schoolSel) {
		this.schoolSel = schoolSel;
	}

	public int getHuxingSel() {
		return huxingSel;
	}

	public void setHuxingSel(int huxingSel) {
		this.huxingSel = huxingSel;
	}

	public int getUseTypeSel() {
		return useTypeSel;
	}

	public void setUseTypeSel(int useTypeSel) {
		this.useTypeSel = useTypeSel;
	}

	public int getUseAxb() {
		return useAxb;
	}

	public void setUseAxb(int useAxb) {
		this.useAxb = useAxb;
	}

	public int getHuji() {
		return huji;
	}

	public void setHuji(int huji) {
		this.huji = huji;
	}

	public boolean isBuylimit() {
		return buylimit;
	}

	public void setBuylimit(boolean buylimit) {
		this.buylimit = buylimit;
	}

	public int getXinoresf() {
		return xinoresf;
	}

	public void setXinoresf(int xinoresf) {
		this.xinoresf = xinoresf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getNextpg() {
		return nextpg;
	}

	public void setNextpg(int nextpg) {
		this.nextpg = nextpg;
	}

	public int getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(int downPayment) {
		this.downPayment = downPayment;
	}
	
}
