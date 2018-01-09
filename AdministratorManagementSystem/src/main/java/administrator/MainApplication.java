package administrator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;

public class MainApplication {

    // just for testing purposes
    public static void main(String[] args) throws SQLException {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        DatabaseService service = context.getBean(DatabaseService.class);

        StaffMember testStaff = service.getStaffMemberByName("Anna", "Nowak");
        System.out.println(testStaff);

       StaffMember newStaff = new StaffMember("myLogin", "Jan", "Nowak", "doctor","surgery", 1234567, "Piotrkowska Street", 123456, Base64.getEncoder().encode("myPassword".getBytes()).toString());
        System.out.println(service.addStaffMember(newStaff));

        testStaff = service.getStaffMember(123456);
        System.out.println(testStaff);

        System.out.println(service.updateStaffMemberDepartment(123456, "internal medicine"));

        System.out.println(service.updateStaffMemberPosition(123456, "doctor"));

        System.out.println(service.deleteStaffMember(1234567));

        StaffMember administrator = new Administrator("administratorLogin", "Administrator", "Nowak");
        Event event = new Event(new Date(), new Date(), "A10", "weekly meeting");
        System.out.println(service.addEvent(event));













    }
}
