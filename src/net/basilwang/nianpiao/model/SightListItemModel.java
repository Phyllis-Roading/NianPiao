package net.basilwang.nianpiao.model;

public class SightListItemModel {
	
	private Integer id;
	private String title;
	private String level;
	private String price;
	private String discount;
	private String distance;

	public SightListItemModel(){
		super();
	}
	
	public SightListItemModel(Integer id,String title,String level,String price,String discount,String distance)
	{
		super();
		this.id=id;
		this.title=title;
		this.level=level;
		this.price=price;
		this.discount=discount;
		this.distance=distance;
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
	
	
}
