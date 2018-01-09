package administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdministratorRestController {

	@Autowired
	private AdministratorService adminService;

	@RequestMapping(value = "/staffMember", method = RequestMethod.GET)
	public ResponseEntity<?> getStaffMember(@RequestParam String name, @RequestParam String surname) {
		
		if (adminService.getStaffMember(name, surname) != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Unprocessable entity", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addStaffMember(@RequestBody StaffMember staffMember) {
		if (adminService.addStaffMember(staffMember)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> updateStaffMemberPosition(@RequestBody StaffMember staffMember) {

		if (adminService.updateStaffMemberPosition(staffMember.getPesel(), staffMember.getPosition())) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> updateStaffMemberDepartment(@RequestBody StaffMember staffMember) {

		if (adminService.updateStaffMemberDepartment(staffMember.getPesel(), staffMember.getDepartment())) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStaffMemberByPesel(@RequestParam long pesel) {

			if (adminService.deleteStaffMemberByPesel(pesel)) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}
		
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStaffMember(@RequestBody StaffMember staffMember) {

			if (adminService.deleteStaffMember(staffMember)) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}
		
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public ResponseEntity<?> authenticate(@RequestParam String login, @RequestParam String password) {
		if (adminService.authenticate(login, password)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> signUp(@RequestParam String login,  @RequestParam String encodedPassword) {
		if (adminService.signUp(login, encodedPassword)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}