package administrator;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Schedule {

	private List<Event> eventsList;
	private Administrator administrator;
	int id;

	@Autowired
	DatabaseServiceImpl service;

	public Schedule(Administrator administrator) {
		super();
		this.administrator = administrator;
	}

	public void setEventsList(List<Event> eventsList) {
		this.eventsList = eventsList;
	}

	public List<Event> getEventsList() {
		return eventsList;
	}
	
	public Administrator getAdministrator() {
		return administrator;
	}

	public int getId() {
		return this.id;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public boolean addEventToSchedule(Event event) {
		if (eventsList.add(event)) {
			if (service.updateScheduleEvents(this)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean removeEventFromSchedule(Event event) {
		if (eventsList.remove(event)) {
			if (service.updateScheduleEvents(this)) {
				return true;
			}
		}
		return false;
	}
}