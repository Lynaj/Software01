package administrator;

// this class should be abstract and all employees and hospital staff should inherit from it
public class StaffMember {
	int id;
	String login;
	String password;
	String name;
	String surname;
	String position;
	String department;
	String address;
	long contactNumber;
	long pesel;

	public StaffMember() {}

	public StaffMember(String login, String name, String surname, String position, String department, long pesel, String address, long contactNumber, String password) {
		super();
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.department = department;
		this.pesel = pesel;
		this.address = address;
		this.contactNumber = contactNumber;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() { return this.password;}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public long getPesel() {
		return pesel;
	}

	public void setPesel(long pesel) {
		this.pesel = pesel;
	}

	@Override
	public String toString() {
		return this.getName() + " " + this.getSurname();
	}
}