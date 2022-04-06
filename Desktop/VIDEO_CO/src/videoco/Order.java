package videoco;

public class Order {
	String userName;
	String movie;

	public Order(String userName, String movie) {
		super();
		this.userName = userName;
		this.movie = movie;
	}
	
	public Order() {
		super();
	}

	public String getName() {
		return userName;
	}

	public void setName(String userName) {
		this.userName = userName;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}
	
	@Override
	public String toString() {
		return "Movie [Name: " + userName + ", Movie: " + movie + "]";
	}
}
