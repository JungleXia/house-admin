package com.mysiteforme.admin.util;

/**
 * 高德IP定位
 * ClassName: AmapIpLocation
 * @author Jungle
 * @dateTime 2021年3月31日上午10:52:19
 * @version v1.0
 */
public class AmapIpLocation {

	private Integer status;
	private String info;
	private String infocode;
	private String province;
	private String city;
	private String adcode;
	private String rectangle;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getInfocode() {
		return infocode;
	}
	public void setInfocode(String infocode) {
		this.infocode = infocode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAdcode() {
		return adcode;
	}
	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}
	public String getRectangle() {
		return rectangle;
	}
	public void setRectangle(String rectangle) {
		this.rectangle = rectangle;
	}
	@Override
	public String toString() {
		return "AmapIpLocation {status=" + status + ", info=" + info + ", infocode=" + infocode + ", province="
				+ province + ", city=" + city + ", adcode=" + adcode + ", rectangle=" + rectangle + "}";
	}
	
}
