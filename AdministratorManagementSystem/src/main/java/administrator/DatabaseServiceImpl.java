package administrator;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Repository
public class DatabaseServiceImpl implements DatabaseService{
	final int MIN_PASSWORD_LENGTH = 30;

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

	public StaffMember getStaffMemberByName(String name, String surname) {
		String sql = "Select * from StaffMembers where name = ?";
        StaffMember staff = (StaffMember) jdbcTemplate.queryForObject(
                sql, new Object[] { name }, new BeanPropertyRowMapper(StaffMember.class));
		return staff;
	}

	public StaffMember getStaffMemberByPesel(long pesel) {
        String sql = "Select * from StaffMembers where pesel = ?";
        StaffMember staff = (StaffMember) jdbcTemplate.queryForObject(
                sql, new Object[] { pesel }, new BeanPropertyRowMapper(StaffMember.class));
        return staff;
	}

	public boolean addStaffMember(StaffMember staffMember) {
        String sql = "insert into StaffMembers (login, password, position, name, surname, department, address, contactNumber, pesel) " + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[] { staffMember.getLogin(), staffMember.getPassword(), staffMember.getPosition(), staffMember.getName(), staffMember.getSurname(), staffMember.getDepartment(), staffMember.getAddress(), staffMember.getContactNumber(), staffMember.getPesel() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER};
        if (jdbcTemplate.update(sql, params, types) == 1) {
            return true;
        } else { return false; }

    }

	public StaffMember getStaffMember(long pesel) {
		return getStaffMemberByPesel(pesel);

	}

	public boolean updateStaffMemberDepartment(long pesel, String department) {
        String updateQuery = "update StaffMembers set department = ? where pesel = ?";
        if(jdbcTemplate.update(updateQuery, department, pesel) == 1) {
            return true;
        } else { return false; }

	}

    public boolean updateStaffMemberPosition(long pesel, String position) {
        String updateQuery = "update StaffMembers set position = ? where pesel = ?";
        if(jdbcTemplate.update(updateQuery, position, pesel) == 1) {
         return true;
        } else { return false; }
    }

    public boolean deleteStaffMember(long pesel) {
        String deleteQuery = "delete from StaffMembers where pesel = ?";
        if(jdbcTemplate.update(deleteQuery, pesel) == 1) {
            return true;
        } else { return false; }
	}

	public long getPesel(StaffMember staffMember) {
        String sql = "Select pesel from StaffMembers where name = ?";
        long pesel = jdbcTemplate.queryForObject(
                sql, new Object[]{staffMember.getName()}, Long.class);
        return pesel;
    }

	public boolean authenticate(String login, String password) {
        String sql = "Select PasswordHash from User where LoginName = ?";
        String passwordFromDB = jdbcTemplate.queryForObject(
                sql, new Object[]{login}, String.class);
		// here passwordFromDB should be encoded - I dont know what is the coding system
        if (passwordFromDB.equalsIgnoreCase(password)) {
            return true;
        } else {
            return false;
        }
	}
	
	public boolean checkIfPasswordFullfillsRequirements(String password) {
		if (password.length() < MIN_PASSWORD_LENGTH) {
			return false;
		}
		return true;
	}
	
	public boolean signUp(String login, String password) {
        byte[] encodedPassword = Base64.getEncoder().encode(password.getBytes());
        String sql = "insert into User (LoginName, PasswordHash)" + " values (?, ?)";
        Object[] params = new Object[] { login, encodedPassword };
        int[] types = new int[] {Types.NVARCHAR, Types.BINARY};
        if (jdbcTemplate.update(sql, params, types) == 1) {
            return true;
        } else { return false; }
	}

	public boolean addEvent(Event event) {
        String sql = "insert into Events ( participantsList, startDate, endDate, place, description)" + "values(?, ?, ?, ?, ?)";
        Object[] params = new Object[] { (String) event.getParticipantsList().toString(), event.getStartDate(), event.getEndDate(), event.getPlace(), event.getDescription()};
        int[] types = new int[] { Types.VARCHAR, Types.TIME, Types.TIME, Types.VARCHAR, Types.VARCHAR };
        if (jdbcTemplate.update(sql, params, types) == 1) {
            return true;
        } else { return false; }
    }

    public boolean updateEventParticipants(Event event) {
        String updateQuery = "update Events set participantsList = ? where id = ?";
        if(jdbcTemplate.update(updateQuery, event.getParticipantsList().toString(), event.getId()) == 1) {
            return true;
        } else { return false; }
    }

    public boolean updateScheduleEvents(Schedule schedule) {
        String updateQuery = "update Schedule set eventsList = ? where id = ?";
        if(jdbcTemplate.update(updateQuery, schedule.getEventsList().toString(), schedule.getId()) == 1) {
            return true;
        } else { return false; }
    }

    public List<Event> getEventsByStartAndEndDate(Date startDate, Date endDate) {
        String sql = "Select * from Events where startDate = ? and endDate = ?";
        List<Event> eventsList = jdbcTemplate.queryForList(
                sql, new Object[] { startDate, endDate }, Event.class);
        return eventsList;
    }
}