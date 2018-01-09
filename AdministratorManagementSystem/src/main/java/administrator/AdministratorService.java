package administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AdministratorService {
	private final int MIN_PASSWORD_LENGTH = 6;
	private final int MIN_LOGIN_LENGTH = 6;
	
	@Autowired
	DatabaseServiceImpl databaseServiceImpl;

	public StaffMember getStaffMember(String name, String surname) {
		return databaseServiceImpl.getStaffMemberByName(name, surname);
	}

	public StaffMember getStaffMember(long pesel) {
		return databaseServiceImpl.getStaffMemberByPesel(pesel);
	}

	public boolean addStaffMember(StaffMember staffMember) {
		long pesel = databaseServiceImpl.getPesel(staffMember);

		if (databaseServiceImpl.getStaffMemberByPesel(pesel) != null)
			return false;
		else if (databaseServiceImpl.addStaffMember(staffMember)) {
			return true;
		}
		return false;
	}

	public boolean updateStaffMemberDepartment(long pesel, String department) {
		if (databaseServiceImpl.updateStaffMemberDepartment(pesel, department)) {
			return true;
		} return false;
	}

	public boolean updateStaffMemberPosition(long pesel, String position) {
		if (databaseServiceImpl.updateStaffMemberPosition(pesel, position)) {
			return true;
		} return false;
	}

	public boolean deleteStaffMemberByPesel(long pesel) {
		if (databaseServiceImpl.getStaffMemberByPesel(pesel) == null) {
			return false;
		} else if (databaseServiceImpl.deleteStaffMember(pesel)) {
			return true;
		}
		return false;
	}

	public boolean deleteStaffMember(StaffMember staffMember) {
		long pesel = databaseServiceImpl.getPesel(staffMember);

		if (databaseServiceImpl.getStaffMemberByPesel(pesel) == null) {
			return false;
		} else if (databaseServiceImpl.deleteStaffMember(pesel)) {
			return true;
		}
		return false;
	}
	
	public boolean signUp(String login, String encodedPassword) {
		if (databaseServiceImpl.checkIfPasswordFullfillsRequirements(Base64.getDecoder().decode(encodedPassword).toString())) {
			if (databaseServiceImpl.signUp(login, encodedPassword)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean authenticate(String login, String encodedPassword) {
		if (login == null || encodedPassword == null || (Base64.getDecoder().decode(encodedPassword).toString()).length() < MIN_PASSWORD_LENGTH	|| login.length() < MIN_LOGIN_LENGTH) {
			return false;
		}
		return databaseServiceImpl.authenticate(login, encodedPassword);
	}

}