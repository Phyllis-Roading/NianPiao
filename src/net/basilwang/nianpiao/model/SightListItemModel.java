package net.basilwang.nianpiao.model;

public class SightListItemModel {

	private Integer id;
	private String title;
	private String level;
	private String price;
	private String discount;
	private String distance;
	private String openTime;
	private String address;
	private String introduce;

	public SightListItemModel() {
		super();
	}

	public SightListItemModel(Integer id, String title, String level,
			String price, String discount, String distance,String opentime) {
		super();
		this.id = id;
		this.title = title;
		this.level = level;
		this.price = price;
		this.discount = discount;
		this.distance = distance;
		this.openTime=opentime;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

}
