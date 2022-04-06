package videoco;

public class User {
	public String name;
	public String username;
	public String email;
	public String password; 
	public String point;
	public String orderStatus;
	public String orderID;
	
	
	
	public User(String name, String username, String email, String password, String point, String orderStatus, String orderID) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.point = point;
		this.orderStatus = orderStatus;
		this.orderID = orderID;
	}
	
	public User(){
		super();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
	public String getOrderstatus() {
		return orderStatus;
	}

	public void setOrderstatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	@Override
	public String toString() {
		return "User [name: " + name + ", username: " + username + ", email: " + email + ", password: " + password + 
				", point: " + point +", orderStatus: " + orderStatus +", orderID: " + orderID +"]";
	}
	
}
