package videoco;

public class Movie {
	String title;
	String category;
	String stock;
	String actor;
	String director;
	String rdate;
	String des;
	
	public Movie(String title, String category, String stock,String actor,String director,String rdate,String des) {
		super();
		this.title = title;
		this.category = category;
		this.stock = stock;
		this.actor = actor;
		this.director = director;
		this.rdate = rdate;
		this.des = des;
		
	}
	
	public Movie(){
		super();
	}
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Override
	public String toString() {
		return "Movie [Title: " + title + ", Category: " + category + ", Stock: " + stock
				+ ", Actors: " + actor+ ", Directors: " + director+ ", Release-Date: " + rdate + ", Description: " + des + "]";
	}

}
