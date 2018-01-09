package administrator;

public class Administrator extends StaffMember{
	int id;
	String login;
	String password;
	String name;
	String surname;

	public Administrator(String login, String name, String surname) {
		super();
		this.login = login;
		this.name = name;
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

}