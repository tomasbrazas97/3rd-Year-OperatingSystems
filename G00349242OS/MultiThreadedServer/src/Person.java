/**
 * 
 * @author Tomas Brazas
 * <p>G00349242</p>
 *
 */

public class Person {
	
	private String name;
	private String password;
	private String address;
	private String empNum;
	private String department;
	
	public Person() {
	
	}
	public Person(String name, String password, String address, String empNum, String department) {
		super();
		this.name = name;
		this.password = password;
		this.address = address;
		this.empNum = empNum;
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

}

