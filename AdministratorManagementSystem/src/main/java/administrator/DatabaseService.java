package administrator;

import java.util.Date;
import java.util.List;

public interface DatabaseService {
    StaffMember getStaffMemberByName(String name, String surname);
    boolean addStaffMember(StaffMember staffMember);
    StaffMember getStaffMemberByPesel(long pesel);
    StaffMember getStaffMember(long pesel);
    boolean updateStaffMemberDepartment(long pesel, String department);
    boolean updateStaffMemberPosition(long pesel, String position);
    boolean deleteStaffMember(long pesel);
    long getPesel(StaffMember staffMember);
    boolean authenticate(String login, String password);
    boolean checkIfPasswordFullfillsRequirements(String password);
    boolean signUp(String login, String password);
    boolean addEvent(Event event);
    boolean updateEventParticipants(Event event);
    boolean updateScheduleEvents(Schedule schedule);
    List<Event> getEventsByStartAndEndDate(Date startDate, Date endDate);




}
