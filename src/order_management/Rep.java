package order_management;

import java.util.Date;
import java.util.HashMap;

public class Rep {
	
	private static int index =0;
	
	private int identifier;
	
	private String uname;
	
	private String clientName;
	
	private String eventType;
	
	private Date fromDate;
	
	private Date toDate;
	
	private int no_of_attendees;
	
	private int exp_budget;
	
	private RepState state;
	
	private EventPreferences preference;
	
	private HashMap<String, String> comments = new HashMap<String, String>();
	
	public Rep(String uname, String clientName){		
		identifier = index;
		index = index +1;
		this.uname = uname;
		this.clientName = clientName;
		state = RepState.CREATED;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getNo_of_attendees() {
		return no_of_attendees;
	}

	public void setNo_of_attendees(int no_of_attendees) {
		this.no_of_attendees = no_of_attendees;
	}

	public int getExp_budget() {
		return exp_budget;
	}

	public void setExp_budget(int exp_budget) {
		this.exp_budget = exp_budget;
	}

	public EventPreferences getPreference() {
		return preference;
	}

	public void setPreference(EventPreferences preference) {
		this.preference = preference;
	}

	public int getIdentifier() {
		return identifier;
	}
	public RepState getState (){
		return state;
	}
	
	public void addComment(String role, String comment){
		comments.put(role, comment);
	}
	
	public void setState (RepState state){
		this.state = state;
	}
	
}
