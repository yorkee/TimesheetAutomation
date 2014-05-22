package TimeSheetAutomation;

public class User {
	private String username;
	private String password;
	private String workOrder;
	
	public User(String username, String password, String workOrder) {
		this.username = username;
		this.password = password;
		this.workOrder = workOrder;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}

}
