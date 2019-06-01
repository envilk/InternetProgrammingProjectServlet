package es.unex.giiis.pi.model;

public class Chollo {

	private long id;
	private String title;
	private String description;
	private String link;
	private float price;
	
	private long idu;
	private long ids;
	
	private int likes;
	private int soldout;
	
	
	public int getSoldout() {
		return soldout;
	}
	public void setSoldout(int soldout) {
		this.soldout = soldout;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public long getIds() {
		return ids;
	}
	public void setIds(long ids) {
		this.ids = ids;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public long getIdu() {
		return idu;
	}
	public void setIdu(long idu) {
		this.idu = idu;
	}
	
	
	
}
