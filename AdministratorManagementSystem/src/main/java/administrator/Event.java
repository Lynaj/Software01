package administrator;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

	List<StaffMember> participantsList;
	Date startDate;
	Date endDate;
	String place;
	String description;
	int id;

	@Autowired
	DatabaseServiceImpl service;

	public Event( Date startDate, Date endDate, String place, String description) {
		super();
		this.participantsList = new ArrayList<>();
		this.startDate = startDate;
		this.endDate = endDate;
		this.place = place;
		this.description = description;
	}

	public List<StaffMember> getParticipantsList() {
		return participantsList;
	}

	public void setParticipantsList(List<StaffMember> participantsList) {
		this.participantsList = participantsList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return this.id;
	}
	
	public boolean addParticipant(StaffMember participant) {
		if (this.participantsList.add(participant)) {
			if (service.updateEventParticipants(this)) {
				return true;
			}
		} return false;
	}
	
	public boolean removeParticipant(StaffMember participant) {
		if (this.participantsList.remove(participant)) {
			if (service.updateEventParticipants(this)) {
				return true;
			}
		} return false;
	}

	public boolean checkIfParticipantCanBeAdded(StaffMember participant) {
		List<Event> eventsList = service.getEventsByStartAndEndDate(this.startDate, this.endDate);
		for (Event e : eventsList) {
			if (e.getParticipantsList().contains(participant)) {
				return false;
			}
		}
		return true;
	}
}